package com.ftd.manage.news;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class NewsHandlerQuery extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		String newsIdStr = (String) ctx.paramMap.get("newsId");

		// 不传Id查全部，传Id查单个
		if (StrUtil.isEmpty(newsIdStr)) {
			List<News> newsList = NewsDao.selectAll();

			JSONArray ja = new JSONArray();
			for (News news : newsList) {
				JSONObject obj = JSONObject.fromObject(news);
				ja.add(obj);
			}

			ctx.putResult("rows", ja);
		} else {
			int newsId = Integer.parseInt(newsIdStr);
			News news = NewsDao.select(newsId);
			ctx.putResult("news", news);
		}

	}

}
