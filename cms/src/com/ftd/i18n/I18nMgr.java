package com.ftd.i18n;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import com.ftd.system.FilePath;
import com.ftd.util.StrUtil;
import com.ftd.util.XmlUtil;

public class I18nMgr {
	// 资源名称(zh_cn, en...)——资源
	private Map<String, Resource> langResourceMap = new HashMap<>();

	private static I18nMgr instance = new I18nMgr();

	private I18nMgr() {
	}

	public static I18nMgr getInstance() {
		return instance;
	}

	/**
	 * 
	 * @param resourceName
	 *            the name of resource , eg : zh_cn, en...
	 * @return
	 */
	public String getMsg(String resourceName, String strId) {
		Resource resource = langResourceMap.get(resourceName);
		if (resource == null) {
			String filePath = FilePath.LANG_RESOURCE_PATH + "/" + resourceName
					+ "/resource.xml";
			resource = loadResource(filePath);
		}

		if (resource == null)
			return strId;
		return resource.getMsg(strId);
	}

	private Resource loadResource(String filePath) {

		Document document = XmlUtil.getDocument(filePath);
		if (document == null)
			return null;

		Resource resource = new Resource();
		Element root = document.getRootElement();
		List<Element> strElements = root.elements("String");

		for (Element element : strElements) {
			Map<String, String> kv = XmlUtil.getAttribute(element);

			String strId = StrUtil.parseStr(kv.get("Id"), "");
			String msg = StrUtil.parseStr(kv.get("Msg"), "");

			resource.add(strId, msg);
		}

		return resource;

	}

}
