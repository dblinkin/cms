package com.ftd.system;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;

public class FilePath {
	public static final String ARTICLE_PATH = "/article";

	public static final String ARTICLE_NEWS_PATH = "news";

	public static final String ARTICLE_NOTICE_PATH = "notice";

	public static final String ARTICLE_TRADE_PATH = "trade";

	public static final String EXT_HTML = ".html";

	public static final String IMAGE_UPLOAD_PATH = ARTICLE_PATH + "/image";

	public static String ROOT_PATH;

	public static String FILE_SYSTEM = "/WEB-INF/systemConfig.xml";

	public static String RELEASE_CONFIG_PATH = "/WEB-INF/release.xml";

	public static String LANG_RESOURCE_PATH = "/WEB-INF/lang";

	public static String TEMPLATE_PATH = "/WEB-INF/template";

	public static String ARTICLE_WRITE_PATH = ARTICLE_PATH;

	public static String IMAGE_UPLOAD_REALPATH;

	public static void initRealPath(ServletContext ctx) {
		ROOT_PATH = ctx.getRealPath("/");
		FILE_SYSTEM = ctx.getRealPath(FILE_SYSTEM);
		RELEASE_CONFIG_PATH = ctx.getRealPath(RELEASE_CONFIG_PATH);
		LANG_RESOURCE_PATH = ctx.getRealPath(LANG_RESOURCE_PATH);
		TEMPLATE_PATH = ctx.getRealPath(TEMPLATE_PATH);
		ARTICLE_WRITE_PATH = ctx.getRealPath(ARTICLE_PATH);
		IMAGE_UPLOAD_REALPATH = ctx.getRealPath(IMAGE_UPLOAD_PATH);
	}

	public static String getFolderPath(String articleType) {

		String tiemStr = new SimpleDateFormat("yyyyMMdd").format(new Date());

		String articlePath = ARTICLE_PATH + "/" + articleType + "/" + tiemStr;

		return articlePath;
	}

	public static String getWritePath(String articleType) {
		String tiemStr = new SimpleDateFormat("yyyyMMdd").format(new Date());

		String articlePath = ARTICLE_WRITE_PATH + "/" + articleType + "/"
				+ tiemStr;

		File dir = new File(articlePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		return articlePath;
	}

	public static String getFilename(String fileExt) {
		Random random = new Random();
		return "" + random.nextInt(10000) + System.currentTimeMillis()
				+ fileExt;
	}

}
