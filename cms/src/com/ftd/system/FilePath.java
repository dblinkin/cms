package com.ftd.system;

import javax.servlet.ServletContext;

public class FilePath {
	public static String FILE_SYSTEM = "/WEB-INF/systemConfig.xml";

	public static void initRealPath(ServletContext ctx) {
		FILE_SYSTEM = ctx.getRealPath(FILE_SYSTEM);

	}

}
