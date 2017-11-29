package com.ticket.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码解码工具类
 * @ClassName: DecoderUtil   
 * @Description: 对客户端ajax方式提交的字符串进行编码和解码操作   
 * @author HiSay  
 * @date Jul 8, 2013 11:44:17 AM   
 *
 */
public class DecoderUtil {
	private DecoderUtil(){}
	
	/**
	 * UTF-8编码
	 * @param value 要编码的值
	 * @return
	 */
	public static String UtfDecoder(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8").trim();
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * UTF-8编码
	 * @param value 要编码的值
	 * @return
	 */
	public static String UtfEncoder(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8").trim();
		} catch(Exception e) {
			return value;
		}
	}
}
