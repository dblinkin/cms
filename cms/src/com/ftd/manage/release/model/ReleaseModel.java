package com.ftd.manage.release.model;

public class ReleaseModel {
	private int articleId;
	private int channelId;
	private long releaseTime;
	private String releaseFilename;

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

	public long getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(long releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseFilename() {
		return releaseFilename;
	}

	public void setReleaseFilename(String releaseFilename) {
		this.releaseFilename = releaseFilename;
	}

}
