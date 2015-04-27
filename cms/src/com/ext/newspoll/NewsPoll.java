package com.ext.newspoll;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;

public class NewsPoll {
	// 新闻ID
	private int newsId;
	// 新闻标题
	private String newsTitle;
	// 轮播图片的URL
	private String pollImgUrl;
	// 对应的文章
	private int articleId;
	// 是否激活
	private int active;
	// 对应文章的URL
	private String articleUrl;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getPollImgUrl() {
		return pollImgUrl;
	}

	public void setPollImgUrl(String pollImgUrl) {
		this.pollImgUrl = pollImgUrl;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleUrl() {
		Article a = ArticleMgr.getInstance().getArticle(articleId);
		if (a != null)
			return a.getArticleUrl();
		return "";
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
