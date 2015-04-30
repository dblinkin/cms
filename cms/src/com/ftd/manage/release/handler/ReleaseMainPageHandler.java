package com.ftd.manage.release.handler;

import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.SysMgr;

public class ReleaseMainPageHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		ReleaseMgr.getInstance().release(Release.Src.MAIN_PAGE,
				SysMgr.getInstance().getMainPageChannel(), 0);

	}

}
