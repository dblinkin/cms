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
		if (channels.length == 1) {
			Channel parentChannel = ChannelMgr.getInstance().getChannel(
					channels[0]);
			if (parentChannel != null) {
				List<ArticleModelAdapter> am = new ArrayList<ArticleIndexModel.ArticleModelAdapter>();
				for (Channel c : parentChannel.getChildren()) {
					ArticleModelAdapter ama = new ArticleModelAdapter();
					ama.setChannel(c);
					ama.setArticleIndex(ArticleMgr.getInstance().getArticles(
							c.getChannelId(), 5));
					am.add(ama);
				}
				model.put("channel_articleIndex", am);
			}
		} else if (channels.length == 2) {
			Channel c = ChannelMgr.getInstance().getChannel(channels[1]);
			if (c != null) {
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
