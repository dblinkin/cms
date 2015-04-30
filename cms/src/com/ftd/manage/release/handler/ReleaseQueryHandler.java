package com.ftd.manage.release.handler;

import com.ftd.i18n.I18nMgr;
import com.ftd.manage.release.Release;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.servlet.Handler;

public class ReleaseQueryHandler extends Handler {

	@Override
	public void handle(Context ctx) throws FtdException {

		Release re = ReleaseMgr.getInstance().getRelease(Release.Src.MAIN_PAGE);
		ctx.putResult(re.getId(),
				I18nMgr.getInstance().getMsg(ctx.lang, re.getId()));

		re = ReleaseMgr.getInstance().getRelease(Release.Src.FIRST_CHANNEL);
		ctx.putResult(re.getId(),
				I18nMgr.getInstance().getMsg(ctx.lang, re.getId()));

		re = ReleaseMgr.getInstance().getRelease(Release.Src.SECOND_CHANNEL);
		ctx.putResult(re.getId(),
				I18nMgr.getInstance().getMsg(ctx.lang, re.getId()));

		re = ReleaseMgr.getInstance().getRelease(Release.Src.ARTICLE);
		ctx.putResult(re.getId(),
				I18nMgr.getInstance().getMsg(ctx.lang, re.getId()));

		for (Release r : ReleaseMgr.getInstance().getReleases()) {
			if (!ctx.getResult().containsKey(r.getId())) {
				ctx.putResult(r.getId(),
						I18nMgr.getInstance().getMsg(ctx.lang, r.getId()));
			}
		}

	}

}
