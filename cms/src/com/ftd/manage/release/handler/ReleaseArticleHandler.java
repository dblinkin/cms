package com.ftd.manage.release.handler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ReleaseArticleHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		int articleId = StrUtil.parseInt(
				(String) ctx.paramMap.get("articleId"), 0);

		String ids = StrUtil.parseStr((String) ctx.paramMap.get("ids"), "");
		if (StrUtil.isEmpty(ids)) {
			Article a = ArticleMgr.getInstance().getArticle(articleId);
			if (a != null) {
				ReleaseMgr.getInstance().release(Release.Src.ARTICLE,
						a.getChannelId(), articleId);
			}
		} else {
			JSONArray ja = JSONArray.fromObject(ids);
			for (int i = 0, size = ja.size(); i < size; i++) {
				JSONObject obj = ja.getJSONObject(i);
				articleId = obj.getInt("articleId");
				Article a = ArticleMgr.getInstance().getArticle(articleId);
				if (a != null) {
					ReleaseMgr.getInstance().release(Release.Src.ARTICLE,
							a.getChannelId(), articleId);
				}
			}
		}

	}

}
