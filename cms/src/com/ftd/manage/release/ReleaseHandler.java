package com.ftd.manage.release;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ftd.manage.channel.ChannelMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;

public class ReleaseHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		int channel1Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel1Id"), 0);
		int channel2Id = StrUtil.parseInt(
				(String) ctx.paramMap.get("channel2Id"), 0);
		if (channel2Id != 0) {
			channel1Id = channel2Id & ChannelMgr.CHANNEL_1_MASK;
		}

		int articleId = StrUtil.parseInt(
				(String) ctx.paramMap.get("articleId"), 0);

		String releaseSrc = StrUtil.parseStr((String) ctx.paramMap.get("src"),
				"");

		if (articleId != 0)
			releaseSrc = Release.Src.ARTICLE.toString();
		else if (channel2Id != 0) {
			releaseSrc = Release.Src.SECOND_CHANNEL.toString();
		} else if (channel1Id != 0) {
			releaseSrc = Release.Src.FIRST_CHANNEL.toString();
		} else {
			releaseSrc = Release.Src.MAIN_PAGE.toString();
		}

		String ids = StrUtil.parseStr((String) ctx.paramMap.get("ids"), "");
		if (StrUtil.isEmpty(ids)) {
			ReleaseMgr.getInstance().release(articleId, releaseSrc, channel1Id,
					channel2Id);
		} else {
			JSONArray ja = JSONArray.fromObject(ids);
			for (int i = 0, size = ja.size(); i < size; i++) {
				JSONObject obj = ja.getJSONObject(i);
				channel2Id = obj.getInt("channelId");
				channel1Id = channel2Id & ChannelMgr.CHANNEL_1_MASK;
				articleId = obj.getInt("articleId");
				releaseSrc = Release.Src.ARTICLE.toString();
				ReleaseMgr.getInstance().release(articleId, releaseSrc,
						channel1Id, channel2Id);
			}
		}

	}

}
