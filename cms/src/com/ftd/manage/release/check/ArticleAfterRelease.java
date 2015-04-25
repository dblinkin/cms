package com.ftd.manage.release.check;

import com.ftd.manage.article.ArticleDao;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;

public class ArticleAfterRelease implements AfterRelease {
	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
		ArticleDao.updateRelease(rm);
	}
}
