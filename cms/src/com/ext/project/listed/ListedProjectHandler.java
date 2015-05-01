package com.ext.project.listed;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.JSONUtil;
import com.ftd.util.StrUtil;

public class ListedProjectHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY: {
			JSONArray ja = JSONArray.fromObject(ListedProjectDao.selectAll(),
					JSONUtil.Releasable_Config);
			ctx.putResult("rows", ja.toString());

		}
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			ListedProject p = (ListedProject) JSONObject.toBean(obj,
					ListedProject.class);
			ListedProjectDao.insert(p);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = StrUtil.parseInt(idStr, 0);
					if (id != 0)
						ListedProjectDao.delete(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			ListedProject p = (ListedProject) JSONObject.toBean(obj,
					ListedProject.class);
			ListedProjectDao.update(p);
		}
			break;
		default:
			break;
		}

	}

}
