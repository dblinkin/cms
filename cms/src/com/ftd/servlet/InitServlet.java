package com.ftd.servlet;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		logger.info("cms starup success.");

	}

}
