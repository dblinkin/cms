package com.ftd.manage.release;

import java.util.ArrayList;
import java.util.List;

import com.ftd.manage.release.check.AfterRelease;
import com.ftd.manage.release.model.ModelProvider;

public class Release {
	// 发布源
	private Src src;
	// 发布所需数据集
	private List<ModelProvider> models = new ArrayList<ModelProvider>();
	// 发布后所需要的一些操作
	private List<AfterRelease> afterReleases = new ArrayList<AfterRelease>();
	// 发布使用的模板
	private String templateName;
	// 发布后的文件名格式化
	private String filenameFormat;

	// -----------------setter----------getter
	public Src getSrc() {
		return src;
	}

	public void setSrc(Src src) {
		this.src = src;
	}

	public List<ModelProvider> getModels() {
		return models;
	}

	public void setModels(List<ModelProvider> models) {
		this.models = models;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getFilenameFormat() {
		return filenameFormat;
	}

	public void setFilenameFormat(String filenameFormat) {
		this.filenameFormat = filenameFormat;
	}

	public List<AfterRelease> getAfterReleases() {
		return afterReleases;
	}

	public void setAfterReleases(List<AfterRelease> afterReleases) {
		this.afterReleases = afterReleases;
	}

	public enum Src {
		// 占位
		NULL,
		// 首页
		MAIN_PAGE,
		// 一级栏目
		FIRST_CHANNEL,
		// 二级栏目
		SECOND_CHANNEL,
		// 文章
		ARTICLE,
		// 即时预览
		PREVIEW,
		// 用户自定义
		USER_DEFINE;
	}
}
