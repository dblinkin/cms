package com.ftd.manage.article;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ArticleChangeTplHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int articleId = StrUtil.parseInt(
				(String) ctx.paramMap.get("articleId"), 0);
		if (articleId != 0) {
			String releaseId = StrUtil.parseStr(
					(String) ctx.paramMap.get("releaseId"), "");
			ArticleMgr.getInstance().updateArticleTpl(articleId, releaseId);
		}
	}
}
