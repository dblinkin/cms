package com.ftd.manage.release.model;

import java.util.Map;

import com.ftd.servlet.FtdException;

public interface ModelProvider {
	/**
	 * 设置数据ID(article ID, channel ID)
	 * 
	 * @param modelId
	 */
	void setModelId(int modelId);

	/**
	 * 取得数据ID
	 * 
	 * @return
	 */
	int getModelId();

	/**
	 * 取得数据模型
	 * 
	 * @return
	 */
	Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException;

	/**
	 * 发布完成后需要做的操作
	 * 
	 * @throws FtdException
	 */
	void afterRelease(ReleaseModel rm) throws FtdException;

}
