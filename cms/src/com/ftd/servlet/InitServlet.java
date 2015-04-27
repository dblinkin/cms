package com.ftd.servlet;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.ReleaseMgr;
import com.ftd.system.FilePath;
import com.ftd.system.SysMgr;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(InitServlet.class);

	public void init() {
		logger.info("init cms.");

		FilePath.initRealPath(this.getServletContext());

		if (!SysMgr.getInstance().init(FilePath.FILE_SYSTEM)) {
			logger.info("cms startup failed. load system config error.");
			System.exit(0);
		}

		if (!ReleaseMgr.getInstance().init()) {
			logger.info("cms startup failed. load release manager error.");
			System.exit(0);
		}

		if (!ChannelMgr.getInstance().init()) {
			logger.info("cms startup failed. load channel error.");
			System.exit(0);
		}

		if (!ArticleMgr.getInstance().init()) {
			logger.info("cms startup failed. load article error.");
			System.exit(0);
		}

		logger.info("cms starup success.");

	}

}
