package com.ftd.util.dbclient;

import org.apache.commons.lang.StringEscapeUtils;

public class SQLFilter
{

	private static final String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|; |or|-|+|,|\\";

	public static String sql_inj(String str)
	{
		if (str.indexOf("'") >= 0)
		{
			str = str.replaceAll("'", "");
		}

		String[] inj_stra = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++)
		{
			if (str.indexOf(" " + inj_stra[i] + " ") >= 0)
			{
				str = str.replaceFirst(inj_stra[i], "");
			}
		}
		return str;
	}
	public static String grepSpecialString(String str)
	{
		str = StringEscapeUtils.escapeSql(str); // 防止截尾字符串中有'的情况, 例如，John's 转换为
												// John' ' s
		str = str.replace("\\", ""); // 过滤转意字符
		return str;
	}
}
