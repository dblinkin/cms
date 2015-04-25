package com.ftd.manage.release.preview;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ftd.manage.article.Article;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class RealtimePreviewHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {
		int channel1Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel1Id"), 0);
		int channel2Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channelId"), 0);
		if (channel2Id != 0) {
			channel1Id = channel2Id & ChannelMgr.CHANNEL_1_MASK;
		}

		int articleId = StrUtil.parseInt(
				(String) ctx.paramMap.get("articleId"), 0);

		String releaseSrc = Release.Src.PREVIEW.toString();

		Map<String, Object> article = new HashMap<String, Object>();
		if (Release.Src.ARTICLE.toString().equals(releaseSrc)) {
			JSONObject obj = JSONObject.fromObject(ctx.paramMap);
			Article a = (Article) JSONObject.toBean(obj, Article.class);
			article.put("article", a);
		}

		ReleaseMgr.getInstance().preview(articleId, releaseSrc, ctx, article,
				channel1Id, channel2Id);
	}

}
