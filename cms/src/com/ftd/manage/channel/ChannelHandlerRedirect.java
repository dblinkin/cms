package com.ftd.manage.channel;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ChannelHandlerRedirect extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		int channel1Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel1Id"), 0);
		int channel2Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel2Id"), 0);
		String releaseUrl = StrUtil.parseStr(
				(String) ctx.paramMap.get("releaseUrl"), null);
		if (StrUtil.isEmpty(releaseUrl))
			return;

		if (channel2Id != 0) {
			ChannelMgr.getInstance().updateChannelUrl(channel2Id, releaseUrl);
		} else if (channel1Id != 0) {
			ChannelMgr.getInstance().updateChannelUrl(channel1Id, releaseUrl);
		}
	}

}
