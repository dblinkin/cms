package com.ftd.manage.release.preview;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;

public class PreviewHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int channel1Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel1Id"), 0);
		int channel2Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel2Id"), 0);
		int articleId = StrUtil.parseInt(
				(String) ctx.paramMap.get("articleId"), 0);

		if (channel2Id != 0) {
			if (articleId == 0) {
				ReleaseMgr.getInstance().preview(Release.Src.SECOND_CHANNEL,
						channel2Id, 0, ctx);
			} else {
				Article a = ArticleMgr.getInstance().getArticle(articleId);
				if (a != null) {
					ReleaseMgr.getInstance().preview(Release.Src.ARTICLE,
							a.getChannelId(), articleId, ctx);
				}
			}
			return;
		} else if (channel1Id != 0) {
			ReleaseMgr.getInstance().preview(Release.Src.FIRST_CHANNEL,
					channel1Id, 0, ctx);
			return;
		}

		ReleaseMgr.getInstance().preview(Release.Src.MAIN_PAGE,
				SysMgr.getInstance().getMainPageChannel(), 0, ctx);
	}
}
