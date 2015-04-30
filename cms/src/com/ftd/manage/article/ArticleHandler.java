package com.ftd.manage.article;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ArticleHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY: {
			String articleIdStr = (String) ctx.paramMap.get("articleId");

			// 不传Id查全部，传Id查单个
			if (StrUtil.isEmpty(articleIdStr)) {
				int channel1Id = StrUtil.parseInt(
						(String) ctx.paramMap.get("channel1Id"), 0);
				int channel2Id = StrUtil.parseInt(
						(String) ctx.paramMap.get("channel2Id"), 0);

				String startDate = StrUtil.parseStr(
						(String) ctx.paramMap.get("startDate"), "");
				String endDate = StrUtil.parseStr(
						(String) ctx.paramMap.get("endDate"), "");

				int pageNum = StrUtil.parseInt(
						(String) ctx.paramMap.get("pageNum"), 0);
				int pageSize = StrUtil.parseInt(
						(String) ctx.paramMap.get("pageSize"), 0);
				List<Article> articles = ArticleDao.selectAll(channel1Id,
						channel2Id, startDate, endDate, pageSize, pageNum);

				JSONArray ja = new JSONArray();
				for (Article a : articles) {
					JSONObject obj = JSONObject.fromObject(a);
					ja.add(obj);
				}

				ctx.putResult("rows", ja);
				ctx.putResult("results", ArticleDao.selectNum(channel1Id,
						channel2Id, startDate, endDate));
			} else {
				int articleId = StrUtil.parseInt(articleIdStr, 0);
				if (articleId != 0) {
					Article article = ArticleDao.select(articleId);
					ctx.putResult("article", article);
				}
			}
		}
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			Article a = (Article) JSONObject.toBean(obj, Article.class);
			ArticleMgr.getInstance().addArticle(a);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = StrUtil.parseInt(idStr, 0);
					if (id != 0)
						ArticleMgr.getInstance().removeArticle(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			Article a = (Article) JSONObject.toBean(obj, Article.class);
			ArticleMgr.getInstance().updateArticle(a);
		}
			break;
		default:
			break;
		}

	}

}
