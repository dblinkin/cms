package com.ext.release;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		String ids = StrUtil.parseStr((String) ctx.paramMap.get("ids"), "");
		if (StrUtil.isEmpty(ids)) {
			if (projectId != 0) {
				ListedProject p = ListedProjectDao.select(projectId);
				if (p != null) {
					ReleaseMgr.getInstance().release(p.getProjectType(), p);
				}
			}
		} else {
			JSONArray ja = JSONArray.fromObject(ids);
			for (int i = 0, size = ja.size(); i < size; i++) {
				JSONObject obj = ja.getJSONObject(i);
				projectId = obj.getInt("projectId");
				ListedProject p = ListedProjectDao.select(projectId);
				if (p != null) {
					ReleaseMgr.getInstance().release(p.getProjectType(), p);
				}
			}
		}

	}

}
