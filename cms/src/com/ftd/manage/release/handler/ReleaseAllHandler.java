package com.ftd.manage.release.handler;

import java.util.List;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
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
			ReleaseMgr.getInstance().release(Release.Src.ARTICLE,
					a.getChannelId(), a.getArticleId());
		}

		for (Channel c : ChannelMgr.getInstance().getCopyChannels()) {
			if (c.getChannelId() == SysMgr.getInstance().getMainPageChannel()) {
				ReleaseMgr.getInstance().release(Release.Src.MAIN_PAGE,
						c.getChannelId(), 0);
				continue;
			}

			if (c.getIsNav() == 0) {
				continue;
			}

			ReleaseMgr.getInstance().release(Release.Src.FIRST_CHANNEL,
					c.getChannelId(), 0);
			for (Channel cc : c.getChildren()) {
				ReleaseMgr.getInstance().release(Release.Src.SECOND_CHANNEL,
						cc.getChannelId(), 0);
			}

		}

	}

}
