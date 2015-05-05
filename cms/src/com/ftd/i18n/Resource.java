package com.ftd.i18n;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

public class Resource {

	// groupId--Resource
	private Map<String, OrderedMap> resMap = new HashMap<String, OrderedMap>();

	public static final String ERROR_CODE_PREFIX = "error";

	@SuppressWarnings("unchecked")
	public void add(String groupId, String strId, String msg) {
		OrderedMap group = resMap.get(groupId);
		if (group == null) {
			group = new LinkedMap();
		}
		group.put(strId, msg);
	}

	public String getMsg(String groupId, String strId) {
		OrderedMap group = resMap.get(groupId);
		if (group == null)
			return null;
		return (String) group.get(strId);
	}

	public JSONObject getJsonGroup(String groupId) {
		JSONObject jsonRes = new JSONObject();
		OrderedMap group = resMap.get(groupId);
		if (group == null)
			return jsonRes;
		jsonRes.putAll(group);
		return jsonRes;
	}

	public String getError(String id) {
		return getMsg(ERROR_CODE_PREFIX, id);
	}

	public Map<String, OrderedMap> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, OrderedMap> resMap) {
		this.resMap = resMap;
	}

	public static class Str {
		public final String id;
		public final String msg;

		public Str(String id, String msg) {
			this.id = id;
			this.msg = msg;
		}

	}
}
