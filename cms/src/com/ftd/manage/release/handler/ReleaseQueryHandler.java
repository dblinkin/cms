package com.ftd.manage.release.handler;

import com.ftd.i18n.I18nMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;

public class ReleaseQueryHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		ctx.putResult(I18nMgr.getInstance().getGroup(ctx.lang, "channelTpl"));

	}

}
