package com.ftd.i18n;

import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class SuggestHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		String groupId = StrUtil.parseStr((String) ctx.paramMap.get("type"),
				null);
		if (!StrUtil.isEmpty(groupId)) {
			JSONObject jsonRes = I18nMgr.getInstance().getGroup(ctx.lang,
					groupId);
			ctx.putResult(jsonRes);
		}

	}

}
