package com.ftd.manage.release.model;

import java.util.Map;

import com.ftd.servlet.FtdException;

public class NewsModel extends ArticleIndexModel {

	@Override
	public Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException {
		super.setChannel1Key("news_channel");
		return super.getModel(channelId, articleId);
	}

}
