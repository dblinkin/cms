package com.ext.project.recommend;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;

public class RcmdProject {
	// 新闻ID
	private int projectId;
	// 新闻标题
	private String projectTitle;
	// 轮播图片的URL
	private String projectImgUrl;
	// 对应的文章
	private int articleId;
	// 是否激活
	private int active;
	// 对应文章的URL
	private String articleUrl;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectImgUrl() {
		return projectImgUrl;
	}

	public void setProjectImgUrl(String projectImgUrl) {
		this.projectImgUrl = projectImgUrl;
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
