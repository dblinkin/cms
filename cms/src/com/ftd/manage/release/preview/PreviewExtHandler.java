package com.ftd.manage.release.preview;

import com.ext.project.listed.ListedProject;
import com.ext.project.listed.ListedProjectDao;
import com.ext.project.purchase.PurchaseProject;
import com.ext.project.purchase.PurchaseProjectDao;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class PreviewExtHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int projectId = StrUtil.parseInt(
				(String) ctx.paramMap.get("projectId"), 0);
		int channelId = StrUtil.parseInt(
				(String) ctx.paramMap.get("channelId"), 0);
		if (projectId != 0) {
			String type = StrUtil.parseStr((String) ctx.paramMap.get("type"),
					null);
			if ("listed".equals(type)) {
				ListedProject p = ListedProjectDao.select(projectId);
				if (p != null) {
					ReleaseMgr.getInstance().preview(channelId, p, ctx);
				}
			} else if ("purchase".equals(type)) {
				PurchaseProject p = PurchaseProjectDao.select(projectId);
				if (p != null) {
					ReleaseMgr.getInstance().preview(channelId, p, ctx);
				}

			}

		}

	}
}
