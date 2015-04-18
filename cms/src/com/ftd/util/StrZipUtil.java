package com.ftd.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Module: ZipUtil.java Description: 对字符串的压缩及解压 Company: Author: pantp Date: May
 * 6, 2012
 */
public class StrZipUtil {
	private static Logger logger = LoggerFactory.getLogger(StrZipUtil.class);

	/**
	 * 字符串的压缩
	 * 
	 * @param str
	 *            待压缩的字符串
	 * @return 返回压缩后的字符串
	 * @throws IOException
	 */
	public static String compress(String str) {
		if (null == str || str.length() <= 0) {
			return str;
		}
		// 创建一个新的 byte 数组输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 使用默认缓冲区大小创建新的输出流
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			// 将 b.length 个字节写入此输出流
			gzip.write(str.getBytes());
			gzip.close();
		} catch (IOException e) {
			// do nothing
		}

		String result = str;
		try {
			result = out.toString("ISO-8859-1");
		} catch (UnsupportedEncodingException ue) {
			// do nothing
			ue.printStackTrace();
		}
		// 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
		return result;
	}

	/**
	 * 字符串的解压
	 * 
	 * @param str
	 *            对字符串解压
	 * @return 返回解压缩后的字符串
	 * @throws IOException
	 */
	public static String unCompress(String str) {
		if (null == str || str.length() <= 0) {
			return str;
		}
		// 创建一个新的 byte 数组输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
		ByteArrayInputStream in = null;
		try {
			in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// do nothing
		}

		if (in == null)
			return str;

		// 使用默认缓冲区大小创建新的输入流
		GZIPInputStream gzip = null;
		try {
			gzip = new GZIPInputStream(in);
		} catch (IOException e) {
			// do nothing
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		if (gzip == null)
			return str;

		byte[] buffer = new byte[256];
		int n = 0;
		try {
			while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
				// 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			// do nothing
		}

		String result = str;

		try {
			result = out.toString("UTF-8");// 使用指定的
											// charsetName，通过解码字节将缓冲区内容转换为字符串
		} catch (UnsupportedEncodingException e) {
			// do nothing
		}
		return result;
	}

	public static void main(String[] args) throws IOException {

		String comStr = StrZipUtil.compress("afasfasdfasfasdfas");
		String src = StrUtil.toHexString(comStr.getBytes());
		System.out.println(comStr);
		System.out.println(src);

		String hexStr = StrUtil.parseHexString(src);
		String strs = StrZipUtil.unCompress(hexStr);
		System.out.println(hexStr);
		System.out.println(strs);
	}

}
