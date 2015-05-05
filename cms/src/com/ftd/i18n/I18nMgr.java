package com.ftd.i18n;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;
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
	public String getMsg(String resourceName, String groupId, String strId) {
		Resource resource = langResourceMap.get(resourceName);
		if (resource == null) {
			String filePath = FilePath.LANG_RESOURCE_PATH + "/" + resourceName
					+ "/resource.xml";
			resource = loadResource(filePath);
		}

		if (resource == null)
			return strId;
		return resource.getMsg(groupId, strId);
	}

	public JSONObject getGroup(String resourceName, String groupId) {
		Resource resource = langResourceMap.get(resourceName);
		if (resource == null) {
			String filePath = FilePath.LANG_RESOURCE_PATH + "/" + resourceName
					+ "/resource.xml";
			resource = loadResource(filePath);
		}
		if (resource == null)
			return new JSONObject();
		return resource.getJsonGroup(groupId);
	}

	private Resource loadResource(String filePath) {

		Document document = XmlUtil.getDocument(filePath);
		if (document == null)
			return null;

		Resource resource = new Resource();
		Element root = document.getRootElement();
		List<Element> groupElements = root.elements("Group");
		for (Element groupElement : groupElements) {
			Map<String, String> kv = XmlUtil.getAttribute(groupElement);
			String groupId = StrUtil.parseStr(kv.get("Id"), null);
			if (groupId == null)
				continue;

			OrderedMap map = new LinkedMap();
			for (Element strElement : groupElement.elements("String")) {
				Map<String, String> strkv = XmlUtil.getAttribute(strElement);

				String strId = StrUtil.parseStr(strkv.get("Id"), "");
				String msg = StrUtil.parseStr(strkv.get("Msg"), "");
				map.put(strId, msg);
			}
			resource.getResMap().put(groupId, map);
		}

		return resource;

	}

}
