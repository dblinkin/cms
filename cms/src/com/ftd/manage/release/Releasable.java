package com.ftd.manage.release;

import java.util.Map;

import com.ftd.servlet.FtdException;

public interface Releasable {

	/**
	 * 取得发布使用的模板ID
	 * 
	 * @return
	 */
	String getReleaseId();

	/**
	 * 取得发布数据
	 * 
	 * @return
	 */
	Map<String, Object> getReleaseModel();

	/**
	 * 取得发布后的URL
	 * 
	 * @return
	 */
	String getReleaseUrl();

	/**
	 * 发布后需要做的操作
	 * 
	 * @throws FtdException
	 */
	void afterRelease(AfterRelease ar) throws FtdException;
}
