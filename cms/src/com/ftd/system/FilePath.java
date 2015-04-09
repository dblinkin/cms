package com.ftd.system;

import javax.servlet.ServletContext;

public class FilePath {
	public static String FILE_SYSTEM = "/WEB-INF/systemConfig.xml";

	public static String LANG_RESOURCE_PATH = "/WEB-INF/lang";

	public static String TEMPLATE_PATH = "/WEB-INF/template";

	public static void initRealPath(ServletContext ctx) {
		FILE_SYSTEM = ctx.getRealPath(FILE_SYSTEM);
		LANG_RESOURCE_PATH = ctx.getRealPath(LANG_RESOURCE_PATH);
		TEMPLATE_PATH = ctx.getRealPath(TEMPLATE_PATH);
	}

}
