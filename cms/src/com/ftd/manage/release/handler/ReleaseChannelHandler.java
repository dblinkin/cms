package com.ftd.manage.release.handler;

import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ReleaseChannelHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		int channel1Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel1Id"), 0);
		int channel2Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel2Id"), 0);
		if (channel2Id != 0) {
			ReleaseMgr.getInstance().release(Release.Src.SECOND_CHANNEL,
					channel2Id, 0);
		} else if (channel1Id != 0) {
			ReleaseMgr.getInstance().release(Release.Src.FIRST_CHANNEL,
					channel1Id, 0);
		}

	}

}
