package com.ftd.manage.channel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ChannelHandlerQueryFirst extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		String idStr = (String) ctx.paramMap.get("id");

		if (StrUtil.isEmpty(idStr)) {
			for (Channel c : ChannelMgr.getInstance().getCopyChannels()) {
				String id = Integer.toString(c.getChannelId());
				ctx.putResult(id, c.getChannelName());
			}
		} else {
			int id = StrUtil.parseInt(idStr, 0);
			if (id == 0) {
				JSONArray ja = new JSONArray();
				for (Channel c : ChannelMgr.getInstance().getCopyChannels()) {
					JSONObject obj = new JSONObject();
					obj.element("id", c.getChannelId());
					obj.element("text", c.getChannelName());
					obj.element("leaf", false);

					JSONArray jaa = new JSONArray();
					for (Channel cc : c.getChildren()) {
						JSONObject objc = new JSONObject();
						objc.element("id", cc.getChannelId());
						objc.element("text", cc.getChannelName());
						objc.element("leaf", true);
						jaa.add(objc);
					}
					if (jaa.size() > 0)
						obj.element("children", jaa);

					ja.add(obj);
				}
				ctx.putResult("nodes", ja);
				ctx.putResult("hasError", false);
			} else {
				Channel pc = ChannelMgr.getInstance().getChannel(id);
				JSONArray ja = new JSONArray();
				for (Channel c : pc.getChildren()) {
					JSONObject obj = new JSONObject();
					obj.element("id", c.getChannelId());
					obj.element("text", c.getChannelName());
					obj.element("leaf", true);
					ja.add(obj);
				}
				ctx.putResult("nodes", ja);
				ctx.putResult("hasError", false);
			}
		}

	}

}
