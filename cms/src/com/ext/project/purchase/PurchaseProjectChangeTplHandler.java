package com.ext.project.purchase;

import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class PurchaseProjectChangeTplHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int projectId = StrUtil.parseInt(
				(String) ctx.paramMap.get("projectId"), 0);
		if (projectId == 0) {
			throw new FtdException(null, "article.not.found", projectId);
		}

		String releaseId = StrUtil.parseStr(
				(String) ctx.paramMap.get("releaseId"), "");
		Release r = ReleaseMgr.getInstance().getRelease(releaseId);
		if (r == null) {
			throw new FtdException(null, "release.not.found");
		}

		PurchaseProjectDao.updateReleaseId(projectId, releaseId);
	}
}
