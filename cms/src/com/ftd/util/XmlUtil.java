package com.ftd.util;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtil {
	private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	public static HashMap<String, String> getAttribute(Element element) {
		HashMap<String, String> kv = new HashMap<String, String>();
		Iterator<Attribute> it = element.attributeIterator();
		while (it.hasNext()) {
			Attribute at = it.next();
			kv.put(at.getName(), at.getValue());
		}
		return kv;
	}

	public static Document getDocument(String filePath) {
		SAXReader reader = new SAXReader();
		Document document = null;

		try {
			document = reader.read(filePath);
		} catch (DocumentException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return document;
	}
}
