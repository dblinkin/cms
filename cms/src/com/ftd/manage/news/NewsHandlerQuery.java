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
			int newsType = StrUtil.parseInt(
					(String) ctx.paramMap.get("newsType"), 0);

			String startDate = (String) ctx.paramMap.get("startDate");
			String endDate = (String) ctx.paramMap.get("endDate");

			int pageNum = StrUtil.parseInt(
					(String) ctx.paramMap.get("pageNum"), 0);
			int pageSize = StrUtil.parseInt(
					(String) ctx.paramMap.get("pageSize"), 0);
			List<News> newsList = NewsDao.selectAll(newsType, startDate,
					endDate, pageSize, pageNum);

			JSONArray ja = new JSONArray();
			for (News news : newsList) {
				JSONObject obj = JSONObject.fromObject(news);
				ja.add(obj);
			}

			ctx.putResult("rows", ja);
			ctx.putResult("results", NewsDao.selectNum(newsType, startDate,
					endDate, pageSize, pageNum));
		} else {
			int newsId = Integer.parseInt(newsIdStr);
			News news = NewsDao.select(newsId);
			ctx.putResult("news", news);
		}

	}

}
