package com.ftd.manage;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.ContentMgr;

public class PreviewHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		String templateName = (String) ctx.paramMap.get("ftl");
		if (templateName == null) {
			throw new FtdException(null, "file.not.found", templateName);
		}

		ContentMgr.getInstance().getTemplateMgr()
				.writeResponse(ctx.response, ctx.paramMap, templateName);
	}

}
