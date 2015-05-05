package com.ext.project.listed;

import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.SysMgr;

public class ListedProjectTypeQueryHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		Channel c = ChannelMgr.getInstance().getChannel(
				SysMgr.getInstance().getListedProjectChannel());
		if (c != null) {
			for (Channel cc : c.getChildren()) {
				ctx.putResult(Integer.toString(cc.getChannelId()),
						cc.getChannelName());
			}
		}

	}

}
