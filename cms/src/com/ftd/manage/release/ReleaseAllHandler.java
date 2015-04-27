package com.ftd.manage.release;

import java.util.List;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.SysMgr;

public class ReleaseAllHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		if (!ChannelMgr.getInstance().initChannelUrl()) {
			throw new FtdException(null, "set.channel.url.error");
		}

		List<Article> articles = ArticleMgr.getInstance().getArticleAll();
		for (Article a : articles) {
			ReleaseMgr.getInstance().release(a.getArticleId(),
					Release.Src.ARTICLE.toString(),
					a.getChannelId() & ChannelMgr.CHANNEL_1_MASK,
					a.getChannelId());
		}

		for (Channel c : ChannelMgr.getInstance().getCopyChannels()) {
			if (c.getChannelId() == SysMgr.getInstance().getMainPageChannel()) {
				ReleaseMgr.getInstance().release(0,
						Release.Src.MAIN_PAGE.toString(), c.getChannelId());
				continue;
			}

			if (c.getIsNav() == 0) {
				continue;
			}

			ReleaseMgr.getInstance().release(0,
					Release.Src.FIRST_CHANNEL.toString(), c.getChannelId());
			for (Channel cc : c.getChildren()) {
				ReleaseMgr.getInstance().release(0,
						Release.Src.SECOND_CHANNEL.toString(),
						c.getChannelId(), cc.getChannelId());
			}

		}

	}

}
