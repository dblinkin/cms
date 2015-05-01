package com.ext.project.purchase;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.JSONUtil;
import com.ftd.util.StrUtil;

public class PurchaseProjectHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY: {
			JSONArray ja = JSONArray.fromObject(PurchaseProjectDao.selectAll(),
					JSONUtil.Releasable_Config);
			ctx.putResult("rows", ja.toString());

		}
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			PurchaseProject p = (PurchaseProject) JSONObject.toBean(obj,
					PurchaseProject.class);
			PurchaseProjectDao.insert(p);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = StrUtil.parseInt(idStr, 0);
					if (id != 0)
						PurchaseProjectDao.delete(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			PurchaseProject p = (PurchaseProject) JSONObject.toBean(obj,
					PurchaseProject.class);
			PurchaseProjectDao.update(p);
		}
			break;
		default:
			break;
		}

	}

}
