package com.ftd.manage.release.model;

import java.util.Map;

public interface ModelProvider {
	/**
	 * 是否使用缓存，如果不使用先调用setModel 再调用getModel
	 * 
	 * @return
	 */
	boolean isCached();

	/**
	 * 设置数据模型
	 * 
	 * @param articleId
	 *            文章Id
	 * @param channels
	 *            各级别的栏目ID， chanel[0]-- 一级栏目ID， channel[2]-- 二级栏目ID
	 */
	void setModel(int articleId, int... channels);

	/**
	 * 取得数据模型
	 * 
	 * @param articleId
	 *            文章Id
	 * @param channels
	 *            各级别的栏目ID， chanel[0]-- 一级栏目ID， channel[2]-- 二级栏目ID
	 * @return
	 */
	Map<String, Object> getModel(int articleId, int... channels);

}
