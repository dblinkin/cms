package com.ext.project.recommend;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class RcmdProjectHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY: {
			ctx.putResult("rows", RcmdProjectDao.selectAll());

		}
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			RcmdProject rp = (RcmdProject) JSONObject.toBean(obj,
					RcmdProject.class);
			RcmdProjectDao.insert(rp);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = StrUtil.parseInt(idStr, 0);
					if (id != 0)
						RcmdProjectDao.delete(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			RcmdProject rp = (RcmdProject) JSONObject.toBean(obj,
					RcmdProject.class);
			RcmdProjectDao.update(rp);
		}
			break;
		default:
			break;
		}

	}

}
