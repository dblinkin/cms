package com.ftd.manage.article;

import com.ftd.util.StrUtil;

public class Article {
	// 文章ID
	private int articleId;
	// 文章栏目ID
	private int channelId;
	// 文章标题
	private String articleTitle;
	// 文章跳转URL
	private String articleUrl;
	// 文章创建时间
	private long createTime;
	// 是否发布
	private boolean released;
	// 发布时间
	private long releasedTime;
	// 文章来源
	private String articleSrc;
	// 文章内容
	private String articleContent;
	// 发布器ID
	private String releaseId;

	public void updateEdit(Article a) {
		this.channelId = a.channelId;
		this.articleTitle = a.articleTitle;
		this.articleSrc = a.articleSrc;
		this.releaseId = a.releaseId;
	}

	// -----------getter setter-------------
	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleUrl() {
		return articleUrl;
	}

	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public String getReleasedTime() {
		return StrUtil.datetime(releasedTime);
	}

	public void setReleasedTime(long releasedTime) {
		this.releasedTime = releasedTime;
	}

	public void setReleasedTime(String releasedTime) {
		this.releasedTime = StrUtil.parseDatetime(releasedTime, 0);
	}

	public String getCreateTime() {
		return StrUtil.datetime(createTime);
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = StrUtil.parseDatetime(createTime,
				System.currentTimeMillis());
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleSrc() {
		return articleSrc;
	}

	public void setArticleSrc(String articleSrc) {
		this.articleSrc = articleSrc;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

}
