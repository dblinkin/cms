package com.ftd.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

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

	public static String parseStr(String str, String defaultStr) {
		if (str == null || str.trim().length() == 0)
			return defaultStr;
		return str;
	}

	// 将二进制流转为Hexstring
	public static String toHexString(byte[] bin) {
		if (bin.length > 0) {
			return "0x" + HexBin.encode(bin);
		}

		return null;
	}

}
