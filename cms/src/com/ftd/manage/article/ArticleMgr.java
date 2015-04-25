package com.ftd.manage.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;

public class ArticleMgr {
	private static final Logger logger = LoggerFactory
			.getLogger(ArticleMgr.class);

	private List<Article> articles = new CopyOnWriteArrayList<Article>();

	private Map<Integer, Article> articleId_article = new ConcurrentHashMap<Integer, Article>();

	private AtomicInteger articleId = new AtomicInteger();

	private static ArticleMgr instance = new ArticleMgr();

	private ArticleMgr() {
	}

	public static ArticleMgr getInstance() {
		return instance;
	}

	/**
	 * 取得一个二级栏目的文章索引
	 * 
	 * @param channelId
	 * @param count
	 *            -1表示取得全部文章，否则表示取得文章数
	 * @return
	 */
	public List<Article> getArticles(int channelId, int count) {
		List<Article> result = new ArrayList<Article>();
		Channel c = ChannelMgr.getInstance().getChannel(channelId);
		if (c == null)
			return result;

		if (c.getParentChannelId() != 0) {
			for (Article a : this.articles) {
				if (a.getChannelId() == channelId)
					result.add(a);
			}
		}

		Collections.sort(result, articleComparator);

		if (count != -1 && count < result.size()) {
			result = result.subList(0, count);
		}

		return result;
	}

	public void addArticle(Article a) throws FtdException {
		a.setArticleId(articleId.incrementAndGet());
		ArticleDao.insert(a);

		a.setArticleContent(null);
		articles.add(a);
		articleId_article.put(a.getArticleId(), a);
	}

	public void removeArticle(int articleId) throws FtdException {
		ArticleDao.delete(articleId);
		articleId_article.remove(articleId);
		Iterator<Article> itr = articles.iterator();
		while (itr.hasNext()) {
			Article a = itr.next();
			if (a.getArticleId() == articleId)
				itr.remove();
		}
	}

	public void updateArticle(Article a) throws FtdException {
		ArticleDao.update(a);

		Article old = articleId_article.get(a.getArticleId());
		if (old != null) {
			old.updateEdit(a);
		}
	}

	public void releaseArticle(ReleaseModel rm) throws FtdException {
		ArticleDao.updateRelease(rm);

		Article a = articleId_article.get(rm.getArticleId());
		if (a != null) {
			a.setReleased(true);
			a.setReleasedTime(rm.getReleaseTime());
			a.setArticleUrl(rm.getReleaseFilename());
		}
	}

	public Comparator<Article> articleComparator = new Comparator<Article>() {
		@Override
		public int compare(Article o1, Article o2) {
			return -o1.getCreateTime().compareTo(o2.getCreateTime());
		}
	};

	public boolean init() {
		List<Article> articles = null;
		try {
			articles = ArticleDao.selectAll(false, null);
		} catch (FtdException e) {
			logger.error(ExceptionUtils.getStackTrace(e.getCause()));
			return false;
		}

		this.articles.addAll(articles);
		for (Article a : articles) {
			if (a.getArticleId() > articleId.get())
				articleId.set(a.getArticleId());
			articleId_article.put(a.getArticleId(), a);
		}

		return true;
	}

	public List<Article> getCopyArticles() {
		List<Article> list = new ArrayList<Article>();
		for (Article a : articles) {
			list.add(a);
		}
		return list;
	}

}
