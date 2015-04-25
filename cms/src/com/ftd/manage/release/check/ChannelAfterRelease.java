package com.ftd.manage.release.check;

import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelDao;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;

public class ChannelAfterRelease implements AfterRelease {

	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
		ChannelDao.updateRelease(rm);

		Channel c = ChannelMgr.getInstance().getChannel(rm.getChannelId());
		if (c != null) {
			c.setChannelUrl(rm.getReleaseFilename());
		}
	}

}
