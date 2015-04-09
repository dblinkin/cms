package com.ftd.i18n;

import java.util.HashMap;
import java.util.Map;

public class Resource {
	private Map<String, String> resMap = new HashMap<String, String>();

	public static final String ERROR_CODE_PREFIX = "error.msg.";

	public void add(String id, String msg) {
		resMap.put(id, msg);
	}

	public String getMsg(String id) {
		String res = resMap.get(id);
		if (res == null)
			res = "";
		return res;
	}

	public String getError(String id) {
		return getMsg(ERROR_CODE_PREFIX + id);
	}
}
