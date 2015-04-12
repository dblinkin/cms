package com.ftd.system;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

public class FilePath {
	public static String FILE_SYSTEM = "/WEB-INF/systemConfig.xml";

	public static String LANG_RESOURCE_PATH = "/WEB-INF/lang";

	public static String TEMPLATE_PATH = "/WEB-INF/template";

	public static String ARTICLE_PATH = "/article";

	public static void initRealPath(ServletContext ctx) {
		FILE_SYSTEM = ctx.getRealPath(FILE_SYSTEM);
		LANG_RESOURCE_PATH = ctx.getRealPath(LANG_RESOURCE_PATH);
		TEMPLATE_PATH = ctx.getRealPath(TEMPLATE_PATH);
		ARTICLE_PATH = ctx.getRealPath(ARTICLE_PATH);
	}

	public static String getArticlePath(String articleType) {

		String tiemStr = new SimpleDateFormat("yyMMdd").format(new Date());

		String articlePath = ARTICLE_PATH + "/" + articleType + "/" + tiemStr
				+ "/";

		File dir = new File(articlePath);
		if (!dir.exists()) {
			dir.mkdir();
		}

		return articlePath;

	}

}
