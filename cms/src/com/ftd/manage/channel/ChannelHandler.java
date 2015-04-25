package com.ftd.manage.channel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;

public class ChannelHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		switch (ctx.getOpr()) {
		case QUERY:
			JSONArray ja = new JSONArray();
			for (Channel c : ChannelMgr.getInstance().getCopyChannels()) {
				JSONObject obj = JSONObject.fromObject(c);
				// obj.element("expanded", true);
				ja.add(obj);
			}
			ctx.putResult("rows", ja);
			break;
		case INSERT: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			Channel c = (Channel) JSONObject.toBean(obj, Channel.class);
			ChannelMgr.getInstance().addChannel(c);
		}
			break;
		case DELETE: {
			String[] ids = ctx.request.getParameterValues("ids[]");
			if (ids != null) {
				for (String idStr : ids) {
					int id = Integer.parseInt(idStr);
					ChannelMgr.getInstance().removeChannel(id);
				}
			}
		}
			break;
		case UPDATE: {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			Channel c = (Channel) JSONObject.toBean(obj, Channel.class);
			ChannelMgr.getInstance().updateChannel(c);
		}
			break;
		default:
			break;
		}

	}

}
