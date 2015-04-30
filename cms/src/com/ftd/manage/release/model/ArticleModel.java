package com.ftd.manage.release.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.article.ArticleDao;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.servlet.FtdException;

public class ArticleModel implements ModelProvider {
	private static final Logger logger = LoggerFactory
			.getLogger(ArticleModel.class);

	private int articleId;

	@Override
	public void setModelId(int modelId) {
		this.articleId = modelId;
	}

	@Override
	public int getModelId() {
		return this.articleId;
	}

	@Override
	public Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException {
		if (articleId != 0)
			this.articleId = articleId;

		Map<String, Object> model = new HashMap<String, Object>();

		if (this.articleId != 0) {
			model.put("article", ArticleDao.select(this.articleId));
		}
		return model;
	}

	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
		ArticleMgr.getInstance().releaseArticle(rm);
	}

}
