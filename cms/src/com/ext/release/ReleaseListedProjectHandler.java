package com.ext.release;

import com.ext.project.listed.ListedProject;
import com.ext.project.listed.ListedProjectDao;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ReleaseListedProjectHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int projectId = StrUtil.parseInt(
				(String) ctx.paramMap.get("projectId"), 0);
		if (projectId != 0) {
			ListedProject p = ListedProjectDao.select(projectId);
			if (p != null) {
				ReleaseMgr.getInstance().release(0, p);
			}
		}

	}

}
