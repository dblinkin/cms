package com.ftd.manage.release.model;

import java.util.Map;

public class NoticeModel implements ModelProvider {

	@Override
	public boolean isCached() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setModel(int articleId, int... channels) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getModel(int articleId, int... channels) {
		// TODO Auto-generated method stub
		ArticleIndexModel aim = new ArticleIndexModel();
		return aim.getModel(0, 768);
	}

}
