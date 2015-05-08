package com.ftd.manage.release.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;

public class ArticleIndexModel implements ModelProvider {

	protected String channel1Key = "channel_articleIndex";
	protected String channel2Key = "articleIndex";

	private int channelId;

	@Override
	public void setModelId(int modelId) {
		this.channelId = modelId;
	}

	@Override
	public int getModelId() {
		return this.channelId;
	}

	@Override
	public Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException {
		if (this.channelId != 0)
			channelId = this.channelId;

		Map<String, Object> model = new HashMap<String, Object>();
		Channel c = ChannelMgr.getInstance().getChannel(channelId);
		if (c == null)
			return model;

		if (c.getParentChannelId() == 0) {
			Channel parentChannel = ChannelMgr.getInstance().getChannel(
					c.getChannelId());
			if (parentChannel != null) {
				List<ArticleModelAdapter> am = new ArrayList<ArticleIndexModel.ArticleModelAdapter>();
				for (Channel cc : parentChannel.getChildren()) {
					ArticleModelAdapter ama = new ArticleModelAdapter();
					ama.setChannel(cc);
					ama.setArticleIndex(ArticleMgr.getInstance().getArticles(
							cc.getChannelId(),
							SysMgr.getInstance().getArticleIndexNum()));
					am.add(ama);
				}
				model.put(channel1Key, am);
			}

		} else {
			model.put(channel2Key,
					ArticleMgr.getInstance().getArticles(c.getChannelId(), -1));
		}

		return model;
	}

	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
		// TODO Auto-generated method stub

	}

	public String getChannel1Key() {
		return channel1Key;
	}

	public void setChannel1Key(String channel1Key) {
		this.channel1Key = channel1Key;
	}

	public String getChannel2Key() {
		return channel2Key;
	}

	public void setChannel2Key(String channel2Key) {
		this.channel2Key = channel2Key;
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
