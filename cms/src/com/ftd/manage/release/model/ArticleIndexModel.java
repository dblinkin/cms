package com.ftd.manage.release.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;

public class ArticleIndexModel implements ModelProvider {

	@Override
	public boolean isCached() {
		return true;
	}

	@Override
	public void setModel(int articleId, int... channels) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getModel(int articleId, int... channels) {
		Map<String, Object> model = new HashMap<String, Object>();

		for (int i = channels.length; i > 0; i--) {
			if (channels[i - 1] <= 0)
				continue;

			Channel c = ChannelMgr.getInstance().getChannel(channels[i - 1]);
			if (c == null)
				continue;

			if (c.getParentChannelId() == 0) {
				Channel parentChannel = ChannelMgr.getInstance().getChannel(
						c.getChannelId());
				if (parentChannel != null) {
					List<ArticleModelAdapter> am = new ArrayList<ArticleIndexModel.ArticleModelAdapter>();
					for (Channel cc : parentChannel.getChildren()) {
						ArticleModelAdapter ama = new ArticleModelAdapter();
						ama.setChannel(cc);
						ama.setArticleIndex(ArticleMgr.getInstance()
								.getArticles(cc.getChannelId(), 5));
						am.add(ama);
					}
					model.put("channel_articleIndex", am);
				}

			} else {
				model.put(
						"articleIndex",
						ArticleMgr.getInstance().getArticles(c.getChannelId(),
								-1));
			}
		}
		return model;
	}

	public static class ArticleModelAdapter {
		private Channel channel;
		private List<Article> articleIndex = new ArrayList<Article>();

		public Channel getChannel() {
			return channel;
		}

		public void setChannel(Channel channel) {
			this.channel = channel;
		}

		public List<Article> getArticleIndex() {
			return articleIndex;
		}

		public void setArticleIndex(List<Article> articleIndex) {
			this.articleIndex = articleIndex;
		}
	}

}
