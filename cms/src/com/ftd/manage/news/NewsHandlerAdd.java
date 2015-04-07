package com.ftd.manage.news;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrZipUtil;

public class NewsHandlerAdd extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		// TODO Auto-generated method stub
		JSONObject obj = JSONObject.fromObject(ctx.paramMap);

		News news = (News) JSONObject.toBean(obj, News.class);

		String content = (String) ctx.paramMap.get("newsContent");

		NewsDao.insert(news, StrZipUtil.compress(content));
	}

}
