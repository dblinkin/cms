package com.ext.release;

import com.ext.project.purchase.PurchaseProject;
import com.ext.project.purchase.PurchaseProjectDao;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ReleasePchsProjectHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int projectId = StrUtil.parseInt(
				(String) ctx.paramMap.get("projectId"), 0);
		if (projectId != 0) {
			PurchaseProject p = PurchaseProjectDao.select(projectId);
			if (p != null) {
				ReleaseMgr.getInstance().release(0, p);
			}
		}

	}

}
