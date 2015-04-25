package com.ftd.manage.release.preview;

import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class PreviewHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int channel1Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel1Id"), 0);
		int channel2Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel2Id"), 0);
		if (channel2Id != 0) {
			channel1Id = channel2Id & ChannelMgr.CHANNEL_1_MASK;
		}

		int articleId = StrUtil.parseInt(
				(String) ctx.paramMap.get("articleId"), 0);

		String releaseSrc = StrUtil.parseStr((String) ctx.paramMap.get("src"),
				"");

		if (articleId != 0)
			releaseSrc = Release.Src.ARTICLE.toString();
		else if (channel2Id != 0) {
			releaseSrc = Release.Src.SECOND_CHANNEL.toString();
		} else if (channel1Id != 0) {
			releaseSrc = Release.Src.FIRST_CHANNEL.toString();
		} else {
			releaseSrc = Release.Src.MAIN_PAGE.toString();
		}

		ReleaseMgr.getInstance().preview(articleId, releaseSrc, ctx, null,
				channel1Id, channel2Id);
	}

}
