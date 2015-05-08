package com.ftd.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.servlet.Handler;
import com.ftd.util.StrUtil;
import com.ftd.util.XmlUtil;
import com.ftd.util.dbclient.DBClient;

public class SysMgr {

	// 默认首页栏目
	private int mainPageChannel;
	// 挂牌项目栏目
	private int listedProjectChannel;
	// 受让项目栏目
	private int purchaseProjectChannel;
	//
	private int articleIndexNum;
	// 默认语言
	private String defaultLang;

	private DBClient dbClient;

	private Map<String, Handler> handlerMap = new HashMap<String, Handler>();

	private static Logger logger = LoggerFactory.getLogger(SysMgr.class);

	private SysMgr() {
	}

	private static SysMgr instance = new SysMgr();

	public static SysMgr getInstance() {
		return instance;
	}

	public DBClient getDbClient() {
		return dbClient;
	}

	public Handler getHandler(String cmd) {
		return handlerMap.get(cmd);
	}

	public String getDefaultLang() {
		return defaultLang;
	}

	public int getMainPageChannel() {
		return mainPageChannel;
	}

	public int getArticleIndexNum() {
		return articleIndexNum;
	}

	public int getListedProjectChannel() {
		return listedProjectChannel;
	}

	public int getPurchaseProjectChannel() {
		return purchaseProjectChannel;
	}

	public boolean init(String filePath) {
		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(filePath);
		} catch (DocumentException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		if (document == null) {
			return false;
		}

		Element root = document.getRootElement();
		Element dbconfigElement = root.element("Database");
		HashMap<String, String> dbkv = XmlUtil.getAttribute(dbconfigElement);

		String jdbcUrl = dbkv.get("JdbcUrl");
		String user = dbkv.get("User");
		String password = dbkv.get("Password");

		try {
			this.dbClient = new DBClient(jdbcUrl, user, password);
		} catch (ClassNotFoundException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		} catch (SQLException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}

		List<Element> handlerElements = root.element("Handlers").elements();
		for (Element handlerElement : handlerElements) {
			HashMap<String, String> hkv = XmlUtil.getAttribute(handlerElement);

			Handler h = null;
			try {
				h = (Handler) Class.forName(hkv.get("Class")).newInstance();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			if (h != null)
				this.handlerMap.put(hkv.get("Req"), h);
		}

		Element mainPageElement = root.element("MainPageChannel");
		this.mainPageChannel = StrUtil.parseInt(mainPageElement.getTextTrim(),
				256);

		Element lpElement = root.element("ListedProjectChannel");
		this.listedProjectChannel = StrUtil
				.parseInt(lpElement.getTextTrim(), 0);

		Element ppElement = root.element("PurchaseProjectChannel");
		this.purchaseProjectChannel = StrUtil.parseInt(ppElement.getTextTrim(),
				0);

		Element langElement = root.element("Lang");
		this.defaultLang = StrUtil.parseStr(langElement.getTextTrim(), "zh_CN");

		Element articleIndexNumElement = root.element("ArticleIndexNum");
		this.articleIndexNum = StrUtil.parseInt(
				articleIndexNumElement.getTextTrim(), 6);

		return true;
	}

}
