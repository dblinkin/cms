package com.ftd.util;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.ftd.manage.release.Releasable;

public class JSONUtil {

	public static JsonConfig Releasable_Config;

	static {
		initReleasableConfig();
	}

	private static void initReleasableConfig() {
		Releasable_Config = new JsonConfig();
		Releasable_Config.setJsonPropertyFilter(new PropertyFilter() {

			@Override
			public boolean apply(Object source, String name, Object value) {
				// TODO Auto-generated method stub
				return source instanceof Releasable
						&& "releaseModel".equalsIgnoreCase(name);
			}
		});

	}

}
