package com.ext.project.listed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ftd.manage.article.Article;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.model.ArticleIndexModel;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;

public class ListedProjectIndexModel extends ArticleIndexModel {

	@Override
	public void setModelId(int modelId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getModelId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException {
		Map<String, Object> model = new HashMap<String, Object>();
		Channel c = ChannelMgr.getInstance().getChannel(channelId);
		if (c == null)
			return model;

		List<ListedProject> projects = ListedProjectDao.selectForIndex();
		if (c.getParentChannelId() == 0) {
			Channel parentChannel = ChannelMgr.getInstance().getChannel(
					c.getChannelId());
			if (parentChannel != null) {
				List<ArticleModelAdapter> am = new ArrayList<ArticleIndexModel.ArticleModelAdapter>();
				for (Channel cc : parentChannel.getChildren()) {
					ArticleModelAdapter ama = new ArticleModelAdapter();
					ama.setChannel(cc);
					ama.setArticleIndex(getArticleIndex(projects, cc
							.getChannelId(), SysMgr.getInstance()
							.getArticleIndexNum()));
					am.add(ama);
				}
				model.put(channel1Key, am);
			}

		} else {
			model.put(channel2Key,
					getArticleIndex(projects, c.getChannelId(), -1));
		}

		return model;
	}

	private List<Article> getArticleIndex(List<ListedProject> projects,
			int channelId, int size) {
		List<Article> articles = new ArrayList<Article>();
		for (ListedProject lp : projects) {
			if (lp.getProjectType() == channelId) {
				Article a = new Article();
				a.setArticleTitle(lp.getProjectTitle());
				a.setArticleUrl(lp.getReleaseUrl());
				a.setCreateTime(lp.getCreateTime());
				articles.add(a);
				if (size == articles.size())
					break;
			}
		}
		return articles;
	}

	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
		// TODO Auto-generated method stub

	}

}
