package com.ftd.manage.release.model;

import java.util.HashMap;
import java.util.Map;

import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.servlet.FtdException;

public class ChannelModel implements ModelProvider {

	private int channelId;

	@Override
	public void setModelId(int modelId) {
		this.channelId = modelId;

	}

	@Override
	public int getModelId() {
		return this.channelId;
	}

	@Override
	public Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException {
		if (channelId != 0)
			this.channelId = channelId;

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("channels", ChannelMgr.getInstance().getCopyChannels());
		if (this.channelId != 0) {
			Channel c = ChannelMgr.getInstance().getChannel(this.channelId);
			if (c == null) {
				return model;
			}

			if (c.getParentChannelId() != 0) {
				Channel pc = ChannelMgr.getInstance().getChannel(
						c.getParentChannelId());
				if (pc != null) {
					model.put("currentChannel", pc);
					model.put("currentChannel2", c);
				}
			} else {
				model.put("currentChannel", c);
			}
		}

		return model;
	}

	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
	}

}
