package com.ftd.manage.channel;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	private String channelTpl;

	private int isNav;

	// 用于前台标示
	private boolean leaf;

	private List<Channel> children = new CopyOnWriteArrayList<Channel>();

	public void copy(Channel c) {
		channelName = c.channelName;
		channelDesc = c.channelDesc;
		channelUrl = c.channelUrl;
		channelTpl = c.channelTpl;
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
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getChannelTpl() {
		return channelTpl;
	}

	public void setChannelTpl(String channelTpl) {
		this.channelTpl = channelTpl;
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

}
