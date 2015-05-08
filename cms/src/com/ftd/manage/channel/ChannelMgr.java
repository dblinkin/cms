package com.ftd.manage.channel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;

public class ChannelMgr {
	private static final Logger logger = LoggerFactory
			.getLogger(ChannelMgr.class);

	public static final int OFFSET = 8;

	public static final int CHANNEL_1_MASK = 0xFFFFFF00;

	public static final int MASK = 0xFF;
	// 0x10000 0x10001, 第五位开始表示一级栏目ID, 低四位表示二级栏目
	private AtomicInteger maxFstId = new AtomicInteger();

	private Map<Integer, AtomicInteger> idMap = new ConcurrentHashMap<Integer, AtomicInteger>();

	private List<Channel> channels = new CopyOnWriteArrayList<Channel>();

	private Map<Integer, Channel> channelId_channel = new ConcurrentHashMap<Integer, Channel>();

	private static ChannelMgr instance = new ChannelMgr();

	private ChannelMgr() {
	}

	public static ChannelMgr getInstance() {
		return instance;
	}

	public boolean init() {
		List<Channel> channels = null;
		try {
			channels = ChannelDao.selectAll();
		} catch (FtdException e) {
			logger.error(ExceptionUtils.getStackTrace(e.getCause()));
			return false;
		}

		for (Channel c : channels) {
			channelId_channel.put(c.getChannelId(), c);

			int firstId = c.getChannelId() >> OFFSET;
			int secondId = c.getChannelId() & MASK;
			if (maxFstId.get() < firstId) {
				maxFstId.set(firstId);
			}
			AtomicInteger secId = idMap.get(firstId);
			if (secId == null) {
				idMap.put(firstId, new AtomicInteger(secondId));
			} else {
				if (secId.get() < secondId) {
					secId.set(secondId);
				}
			}

			if (c.getParentChannelId() == 0) {
				this.channels.add(c);
			} else {
				Channel parentChannel = channelId_channel.get(c
						.getParentChannelId());
				if (parentChannel != null) {
					parentChannel.addChild(c);
				}
			}
		}

		if (!initChannelUrl()) {
			return false;
		}

		return true;
	}

	public boolean initChannelUrl() {
		for (Channel c : channelId_channel.values()) {
			if (c.getChannelId() == SysMgr.getInstance().getMainPageChannel()) {
				String url = ReleaseMgr.getInstance().getReleaseFilename(
						Release.Src.MAIN_PAGE, c.getChannelId());
				c.setChannelUrl(url);
				continue;
			}

			if (c.getParentChannelId() == 0) {
				if (c.getIsNav() == 1 && StrUtil.isEmpty(c.getChannelUrl())) {
					String url = ReleaseMgr.getInstance().getReleaseFilename(
							Release.Src.FIRST_CHANNEL, c.getChannelId());
					c.setChannelUrl(url);
				}
			} else {
				if (c.getIsNav() == 1 && StrUtil.isEmpty(c.getChannelUrl())) {
					String url = ReleaseMgr.getInstance().getReleaseFilename(
							Release.Src.SECOND_CHANNEL, c.getChannelId());
					c.setChannelUrl(url);
				}
			}
		}
		return true;
	}

	public List<Channel> getCopyChannels() {
		List<Channel> list = new ArrayList<Channel>();
		for (Channel c : channels) {
			list.add(c);
		}
		return list;
	}

	public Channel getChannel(int channelId) {
		return channelId_channel.get(channelId);
	}

	public void addChannel(Channel channel) throws FtdException {
		// 增加一级目录
		if (channel.getParentChannelId() == 0) {
			channel.setChannelId(maxFstId.incrementAndGet() << OFFSET);
		} else {
			Channel parentChannel = channelId_channel.get(channel
					.getParentChannelId());
			if (parentChannel == null)
				throw new FtdException(null, "channel.id.not.found",
						channel.getParentChannelId());

			AtomicInteger aid = idMap
					.get(parentChannel.getChannelId() >> OFFSET);
			if (aid == null)
				throw new FtdException(null, "channel.id.not.found",
						channel.getParentChannelId());

			channel.setChannelId(parentChannel.getChannelId()
					| aid.incrementAndGet());
		}

		ChannelDao.insert(channel);

		channelId_channel.put(channel.getChannelId(), channel);
		if (channel.getParentChannelId() == 0) {
			if (channel.getChannelId() == SysMgr.getInstance()
					.getMainPageChannel()) {
				channel.setChannelUrl(ReleaseMgr.getInstance()
						.getReleaseFilename(Release.Src.MAIN_PAGE,
								channel.getChannelId()));
			} else {
				channel.setChannelUrl(ReleaseMgr.getInstance()
						.getReleaseFilename(Release.Src.FIRST_CHANNEL,
								channel.getChannelId()));
			}
			channels.add(channel);
			idMap.put(channel.getChannelId() >> OFFSET, new AtomicInteger());
		} else {
			Channel parentChannel = channelId_channel.get(channel
					.getParentChannelId());
			if (parentChannel != null) {
				channel.setChannelUrl(ReleaseMgr.getInstance()
						.getReleaseFilename(Release.Src.SECOND_CHANNEL,
								channel.getChannelId()));
				parentChannel.addChild(channel);
			}
		}
	}

	public void removeChannel(int channelId) throws FtdException {
		Channel c = channelId_channel.get(channelId);
		if (c == null)
			return;
		// 一级栏目则删除包含的二级栏目
		if (c.getParentChannelId() == 0) {
			for (Channel ch : c.getChildren()) {
				ChannelDao.delete(ch.getChannelId());
				channelId_channel.remove(ch.getChannelId());
			}
		}

		// 本身栏目删除
		ChannelDao.delete(channelId);
		channelId_channel.remove(channelId);

		if (c.getParentChannelId() == 0) {
			channels.remove(c);
		} else {
			Channel parentChannel = channelId_channel.get(c
					.getParentChannelId());
			if (parentChannel != null) {
				parentChannel.removeChild(c);
			}
		}
	}

	public void updateChannel(Channel c) throws FtdException {
		Channel _c = channelId_channel.get(c.getChannelId());
		if (_c == null)
			throw new FtdException(null, "channel.not.found", c.getChannelId());

		ChannelDao.update(c);
		_c.copy(c);

	}

	public void updateChannelTpl(int channelId, String releaseId)
			throws FtdException {
		Channel c = channelId_channel.get(channelId);
		if (c == null) {
			throw new FtdException(null, "channel.not.found", channelId);
		}

		Release r = ReleaseMgr.getInstance().getRelease(releaseId);
		if (r == null) {
			throw new FtdException(null, "release.not.found");
		}

		ChannelDao.updateReleaseId(channelId, releaseId);
		c.setReleaseId(releaseId);
	}

	public void updateChannelUrl(int channelId, String redirectUrl)
			throws FtdException {
		Channel c = channelId_channel.get(channelId);
		if (c == null) {
			throw new FtdException(null, "channel.not.found", channelId);
		}

		ChannelDao.updateRedirectUrl(channelId, redirectUrl);
		c.setRedirectUrl(redirectUrl);
	}

}
