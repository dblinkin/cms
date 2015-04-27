package com.ext.newspoll;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class NewsPollHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY: {
			ctx.putResult("rows", NewsPollDao.selectAll());

		}
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			NewsPoll np = (NewsPoll) JSONObject.toBean(obj, NewsPoll.class);
			NewsPollDao.insert(np);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = StrUtil.parseInt(idStr, 0);
					if (id != 0)
						NewsPollDao.delete(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			NewsPoll np = (NewsPoll) JSONObject.toBean(obj, NewsPoll.class);
			NewsPollDao.update(np);
		}
			break;
		default:
			break;
		}

	}

}
