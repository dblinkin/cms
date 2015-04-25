package com.ftd.manage.release.model;

import java.util.HashMap;
import java.util.Map;

import com.ftd.manage.channel.ChannelMgr;

public class ChannelModel implements ModelProvider {

	@Override
	public boolean isCached() {
		return true;
	}

	@Override
	public void setModel(int articleId, int... channels) {
		// do nothing
	}

	@Override
	public Map<String, Object> getModel(int articleId, int... channels) {

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("channels", ChannelMgr.getInstance().getCopyChannels());

		if (channels.length >= 1) {
			model.put("currentChannel",
					ChannelMgr.getInstance().getChannel(channels[0]));
		}

		if (channels.length >= 2) {
			model.put("currentChannel2",
					ChannelMgr.getInstance().getChannel(channels[1]));
		}

		return model;
	}

}
