package com.ftd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public static String parseHexString(String hexStr) {
		if (hexStr != null) {
			if (hexStr.charAt(0) == '0' && hexStr.charAt(1) == 'x') {
				hexStr = hexStr.substring(2);
			}
			return new String(HexBin.decode(hexStr));
		}
		return null;
	}

	public static String datetime(long date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String dateHm(long date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	public static long parseDatetime(String dateTime, long defaultValue) {
		if (StrUtil.isEmpty(dateTime))
			return defaultValue;
		long value = defaultValue;
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime);
		} catch (ParseException e) {
			// do nothing
		}
		if (d != null)
			value = d.getTime();
		return value;
	}

}
