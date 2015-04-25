package com.ftd.manage.release.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleDao;
import com.ftd.servlet.FtdException;

public class ArticleModel implements ModelProvider {
	private static final Logger logger = LoggerFactory
			.getLogger(ArticleModel.class);

	private Article article;

	@Override
	public boolean isCached() {
		return false;
	}

	@Override
	public void setModel(int articleId, int... channels) {
		try {
			article = ArticleDao.select(articleId);
		} catch (FtdException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	@Override
	public Map<String, Object> getModel(int articleId, int... channels) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("article", article);
		return model;
	}

}
