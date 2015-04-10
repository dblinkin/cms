package com.ftd.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.news.News;
import com.ftd.manage.news.NewsDao;
import com.ftd.servlet.FtdException;
import com.ftd.util.dbclient.PageQuery;

public class NewsMgr {

	// newsType --- newList
	private Map<Integer, CopyOnWriteArrayList<News>> newsMap = new ConcurrentHashMap<Integer, CopyOnWriteArrayList<News>>();

	private static final Logger logger = LoggerFactory.getLogger(NewsMgr.class);

	public boolean init() {
		return reload();
	}

	public boolean reload() {
		newsMap.clear();

		List<News> list;
		try {
			list = NewsDao.selectAll();
		} catch (FtdException e) {
			logger.error(ExceptionUtils.getStackTrace(e.getCause()));
			return false;
		}

		for (News news : list) {
			int newsType = news.getNewsType();
			CopyOnWriteArrayList<News> nl = newsMap.get(newsType);
			if (nl == null) {
				nl = new CopyOnWriteArrayList<News>();
			}
			nl.add(news);
		}
		return false;
	}

	public PageQuery.Result<News> getPageNews(int type, int page, int pageSize) {
		CopyOnWriteArrayList<News> list = newsMap.get(type);
		if (list != null) {
			return PageQuery.getPageResult(page, pageSize, list);
		}
		return new PageQuery.Result<News>(0, new ArrayList<News>());
	}

}
