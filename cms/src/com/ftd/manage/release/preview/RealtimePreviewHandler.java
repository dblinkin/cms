package com.ftd.manage.release.preview;

import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class RealtimePreviewHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		int channelId = StrUtil.parseInt(
				(String) ctx.paramMap.get("channelId"), 0);

		ReleaseMgr.getInstance().realTimePreview(Release.Src.ARTICLE,
				channelId, 0, ctx);
		;
	}

}
