package com.ext.friendlink;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class FriendLinkHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY: {
			ctx.putResult("rows", FriendLinkDao.selectAll());

		}
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			FriendLink fl = (FriendLink) JSONObject.toBean(obj,
					FriendLink.class);
			FriendLinkDao.insert(fl);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = StrUtil.parseInt(idStr, 0);
					if (id != 0)
						FriendLinkDao.delete(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			FriendLink fl = (FriendLink) JSONObject.toBean(obj,
					FriendLink.class);
			FriendLinkDao.update(fl);
		}
			break;
		default:
			break;
		}

	}

}
