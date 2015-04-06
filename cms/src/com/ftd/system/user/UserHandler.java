package com.ftd.system.user;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;
import com.ftd.util.dbclient.DBClient;

public class UserHandler extends Handler {
	@Override
	public void handle(Context ctx) throws FtdException {
		DBClient client = SysMgr.getInstance().getDbClient();
		UserDao dao = new UserDao(client);

		switch (ctx.getOpr()) {
		case UPDATE: {
			String passwordStr = (String) ctx.paramMap.get("password");
			String idStr = (String) ctx.paramMap.get("userId");
			if (!StrUtil.isEmpty(passwordStr) && !StrUtil.isEmpty(idStr)) {
				dao.updatePassword(passwordStr, StrUtil.parseInt(idStr, -1));
			}
			break;
		}
		case NULL:
			break;
		default:
			break;
		}
	}
}
