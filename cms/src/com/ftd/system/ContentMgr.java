package com.ftd.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentMgr {
	private NewsMgr newsMgr = new NewsMgr();
	private TemplateMgr templateMgr = new TemplateMgr();

	private static ContentMgr instance = new ContentMgr();

	private static final Logger logger = LoggerFactory
			.getLogger(ContentMgr.class);

	private ContentMgr() {
	}

	public static ContentMgr getInstance() {
		return instance;
	}

	public boolean init() {
		if (!newsMgr.init()) {

			logger.error("news content mgr init failed.");

			return false;
		}

		if (!templateMgr.init(FilePath.TEMPLATE_PATH)) {
			logger.error("template mgr init failed.");
			return false;
		}

		return true;

	}

	public NewsMgr getNewsMgr() {
		return newsMgr;
	}

	public void setNewsMgr(NewsMgr newsMgr) {
		this.newsMgr = newsMgr;
	}

	public TemplateMgr getTemplateMgr() {
		return templateMgr;
	}

	public void setTemplateMgr(TemplateMgr templateMgr) {
		this.templateMgr = templateMgr;
	}

}
