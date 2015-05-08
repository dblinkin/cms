package com.ftd.manage.channel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.ftd.util.StrUtil;

public class Channel {
	// 栏目ID
	private int channelId;
	// 父栏目ID
	private int parentChannelId;
	// 栏目中文名
	private String channelName;
	// 栏目在URL上的显示
	private String channelDesc;
	// 栏目跳转的URL
	private String channelUrl;
	// 栏目使用的模板
	private String releaseId;
	// 是否在导航栏上显示
	private int isNav;
	// 重定向URL
	private String redirectUrl;

	// 用于前台标示
	private boolean leaf;

	private List<Channel> children = new CopyOnWriteArrayList<Channel>();

	public void copy(Channel c) {
		channelName = c.channelName;
		channelDesc = c.channelDesc;
		channelUrl = c.channelUrl;
		releaseId = c.releaseId;
		isNav = c.isNav;
	}

	public void removeChild(Channel c) {
		children.remove(c);
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getParentChannelId() {
		return parentChannelId;
	}

	public void setParentChannelId(int parentChannelId) {
		this.parentChannelId = parentChannelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

	public String getChannelUrl() {
		if (StrUtil.isEmpty(redirectUrl))
			return channelUrl;
		return redirectUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public List<Channel> getChildren() {
		return children;
	}

	public void addChild(Channel childChannel) {
		childChannel.setLeaf(true);
		this.children.add(childChannel);
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public int getIsNav() {
		return isNav;
	}

	public void setIsNav(int isNav) {
		this.isNav = isNav;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
