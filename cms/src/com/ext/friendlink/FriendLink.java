package com.ext.friendlink;

public class FriendLink {
	private int linkId;
	private String linkTitle;
	private String linkUrl;
	private String linkImgUrl;
	private int active;

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkImgUrl() {
		return linkImgUrl;
	}

	public void setLinkImgUrl(String linkImgUrl) {
		this.linkImgUrl = linkImgUrl;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
