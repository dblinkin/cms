package com.ftd.util;

public class StrUtil {
	public static boolean isEmpty(String str) {
		if (str == null || str.isEmpty() || str.trim() == ""
				|| str.equals("null") || str.equals("undefine")) {
			return true;
		}
		return false;
	}

	public static int parseInt(String str, int defaultValue) {
		int value = defaultValue;

		try {
			value = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// do nothing
		}

		return value;
	}

	public static int parseHexInt(String str, int defaultValue) {
		int value = defaultValue;

		try {
			value = Integer.parseInt(str, 16);
		} catch (NumberFormatException e) {
			// do nothing
		}

		return value;
	}

}
