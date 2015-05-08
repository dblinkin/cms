package com.ftd.manage.article;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ext.project.listed.ListedProject;
import com.ext.project.listed.ListedProjectDao;
import com.ext.project.purchase.PurchaseProject;
import com.ext.project.purchase.PurchaseProjectDao;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;
import com.ftd.util.dbclient.PageQuery;
import com.ftd.util.dbclient.PageQuery.Result;

public class ArticlePageIndexHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int channelId = StrUtil.parseInt(
				(String) ctx.paramMap.get("channelId"), 0);
		int page = StrUtil.parseInt((String) ctx.paramMap.get("page"), 0);
		int pageSize = StrUtil.parseInt((String) ctx.paramMap.get("pageSize"),
				0);

		Channel c = ChannelMgr.getInstance().getChannel(channelId);
		if (c == null)
			return;
		if (c.getParentChannelId() == 0)
			return; // 一级栏目直接返回

		if (c.getChannelId() == SysMgr.getInstance()
				.getPurchaseProjectChannel()) {
			JSONArray ja = new JSONArray();
			List<PurchaseProject> projects = PurchaseProjectDao.selectForPage(
					page, pageSize);
			for (PurchaseProject p : projects) {
				JSONObject obj = new JSONObject();
				obj.element("title", p.getProjectTitle());
				obj.element("url", p.getReleaseUrl());
				obj.element("time", p.getCreateTime());
				ja.add(obj);
			}
			ctx.putResult("articles", ja);
			ctx.putResult("total",
					PurchaseProjectDao.selectCountForPage(page, pageSize));
		} else if (c.getParentChannelId() == SysMgr.getInstance()
				.getListedProjectChannel()) {
			JSONArray ja = new JSONArray();
			List<ListedProject> projects = ListedProjectDao.selectForPage(
					channelId, page, pageSize);
			for (ListedProject p : projects) {
				JSONObject obj = new JSONObject();
				obj.element("title", p.getProjectTitle());
				obj.element("url", p.getReleaseUrl());
				obj.element("time", p.getCreateTime());
				ja.add(obj);
			}
			ctx.putResult("articles", ja);
			ctx.putResult("total", ListedProjectDao.selectCountForPage(
					channelId, page, pageSize));

		} else if (channelId != 0) {
			JSONArray ja = new JSONArray();

			List<Article> articles = ArticleMgr.getInstance().getArticles(
					channelId, -1);
			Result<Article> res = PageQuery.getPageResult(page, pageSize,
					articles);
			for (Article a : res.list) {
				JSONObject obj = new JSONObject();
				obj.element("title", a.getArticleTitle());
				obj.element("url", a.getArticleUrl());
				obj.element("time", a.getCreateTime());
				ja.add(obj);
			}
			ctx.putResult("articles", ja);
			ctx.putResult("total", res.total);
		}

	}

}
