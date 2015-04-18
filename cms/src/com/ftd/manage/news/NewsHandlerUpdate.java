package com.ftd.manage.news;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.ContentMgr;
import com.ftd.system.FilePath;
import com.ftd.util.StrUtil;

public class NewsHandlerUpdate extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		JSONObject obj = JSONObject.fromObject(ctx.paramMap);
		News news = (News) JSONObject.toBean(obj, News.class);

		String deploy = (String) ctx.paramMap.get("deploy");
		if ("true".equalsIgnoreCase(deploy)) {
			String news_url = FilePath
					.getFolderPath(FilePath.ARTICLE_NEWS_PATH)
					+ "/"
					+ FilePath.getFilename(FilePath.EXT_HTML);
			news.setNewsUrl(news_url);
			news.setNewsTime(StrUtil.dateHm(System.currentTimeMillis()));

			String fileName = FilePath.getWritePath(FilePath.ARTICLE_NEWS_PATH)
					+ "/" + FilePath.getFilename(FilePath.EXT_HTML);

			String templateName = (String) ctx.paramMap.get("ftl");
			if (templateName == null) {
				throw new FtdException(null, "file.not.found", templateName);
			}
			ContentMgr.getInstance().getTemplateMgr()
					.writeFile(fileName, ctx.paramMap, templateName);
		}

		String content = (String) ctx.paramMap.get("newsContent");
		NewsDao.update(news, content);

	}

}
