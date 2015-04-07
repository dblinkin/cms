package com.ftd.manage.news;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;

public class NewsHandlerQuery extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		List<News> newsList = NewsDao.selectAll();

		JSONArray ja = new JSONArray();
		for (News news : newsList) {
			JSONObject obj = JSONObject.fromObject(news);
			ja.add(obj);
		}

		ctx.putResult("rows", ja);

	}

}
