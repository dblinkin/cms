package com.ftd.manage.channel;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ChannelChangeTplHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int channelId = StrUtil.parseInt(
				(String) ctx.paramMap.get("channelId"), 0);
		if (channelId != 0) {
			String releaseId = StrUtil.parseStr(
					(String) ctx.paramMap.get("releaseId"), "");
			ChannelMgr.getInstance().updateChannelTpl(channelId, releaseId);
		}
	}
}
