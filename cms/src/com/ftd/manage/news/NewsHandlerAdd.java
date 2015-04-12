package com.ftd.manage.news;

import java.util.UUID;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.ContentMgr;
import com.ftd.system.FilePath;

public class NewsHandlerAdd extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		// TODO Auto-generated method stub
		JSONObject obj = JSONObject.fromObject(ctx.paramMap);

		News news = (News) JSONObject.toBean(obj, News.class);

		String content = (String) ctx.paramMap.get("newsContent");

		NewsDao.insert(news, content);

		String deploy = (String) ctx.paramMap.get("deploy");
		if ("true".equalsIgnoreCase(deploy)) {
			UUID uuid = UUID.randomUUID();
			String fileName = FilePath.getArticlePath("news") + uuid.toString()
					+ ".html";

			String templateName = (String) ctx.paramMap.get("ftl");
			if (templateName == null) {
				throw new FtdException(null, "file.not.found", templateName);
			}
			ContentMgr.getInstance().getTemplateMgr()
					.writeFile(fileName, ctx.paramMap, templateName);
		}
	}

}
