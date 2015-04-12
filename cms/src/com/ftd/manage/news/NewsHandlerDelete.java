package com.ftd.manage.news;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;

public class NewsHandlerDelete extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		String ids = (String) ctx.paramMap.get("ids");

		System.out.println(ids);
	}

}
