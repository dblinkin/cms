package com.ftd.manage.release.check;

import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;

public interface AfterRelease {

	/**
	 * 发布后做一些数据更新
	 * 
	 * @param ReleaseModel
	 * @throws FtdException
	 */
	void afterRelease(ReleaseModel rm) throws FtdException;
}
