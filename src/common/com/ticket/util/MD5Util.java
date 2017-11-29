package com.ticket.util;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * MD5的加密工具类
 * 
 * @ClassName: MD5Util
 * @Description: 使用了java提供的AZDG算法实现MD5加密的功能
 * @author HiSay
 * @date Jul 8, 2013 11:30:57 AM
 * 
 */
public class MD5Util {
	private MD5Util() {
	}
	
	/**
	 * 自定义加密钥匙
	 */
	//private static final String DEFAUL_KEY = "wwwaykjnet_md5_java_1234567890!@#$%^&*()QAZWSXEDCRFVTGBYHNUJMIK<OL>P:?";
	private static final String DEFAUL_KEY = "wwwkmcsjccom_ticket_airport_md5_java_1234567890!@#$%^&*()QAZWSXEDCRFVTGBYHNUJMIK<OL>P:?";

	/**
	 * 对字符串进行加密
	 * @Title: MD5Util
	 * @Description: 对传入的sourceString进行MD5加密操作   
	 * @param @param sourceString 要加密的字符串
	 * @param @return  加密后的字符串
	 * @return  字符串
	 * @throws
	 */
	public static String encryptString(String sourceString) {
		return new Azdg().encrypt(sourceString, DEFAUL_KEY);
	}

	/**
	 * 对加密后的字符串进行解密操作
	 * @Title: MD5Util
	 * @Description: 对传入的sourceString进行MD5解密操作   
	 * @param @param sourceString 需要解密的字符串
	 * @param @return  解密后的字符串
	 * @return  字符串
	 * @throws
	 */
	public static String decryptString(String sourceString) {
		return new Azdg().decrypt(sourceString, DEFAUL_KEY);
	}
	
	public static class Azdg {
		public String encrypt(String txt, String key) {
			String encrypt_key = "0f9cfb7a9acced8a4167ea8006ccd098";
			int ctr = 0;
			String tmp = "";
			int i;
			for (i = 0; i < txt.length(); i++) {
				ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
				tmp = tmp + encrypt_key.charAt(ctr)
						+ (char) (txt.charAt(i) ^ encrypt_key.charAt(ctr));
				ctr++;
			}
			return base64_encode(key(tmp, key));
		}
		
		public String decrypt(String cipherText, String key) {
			cipherText = base64_decode(cipherText);
			cipherText = key(cipherText, key);
			String tmp = "";
			for (int i = 0; i < cipherText.length(); i++) {
				int c = cipherText.charAt(i) ^ cipherText.charAt(i + 1);
				String x = "" + (char) c;
				tmp += x;
				i++;
			}
			return tmp;
		}
		
		public String key(String txt, String encrypt_key) {
			encrypt_key = strMD5(encrypt_key);
			int ctr = 0;
			String tmp = "";
			for (int i = 0; i < txt.length(); i++) {
				ctr = (ctr == encrypt_key.length()) ? 0 : ctr;
				int c = txt.charAt(i) ^ encrypt_key.charAt(ctr);
				String x = "" + (char) c;
				tmp = tmp + x;
				ctr++;
			}
			return tmp;
		}
		
		public String base64_encode(String str) {
			return new sun.misc.BASE64Encoder().encode(str.getBytes());
		}
		
		public String base64_decode(String str) {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			if (str == null)
				return null;
			try {
				return new String(decoder.decodeBuffer(str));
			} catch (IOException e) {
				return null;
			}
		}
		
		public static final String strMD5(String s) {
			char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			try {
				byte[] strTemp = s.getBytes();
				MessageDigest mdTemp = MessageDigest.getInstance("MD5");
				mdTemp.update(strTemp);
				byte[] md = mdTemp.digest();
				int j = md.length;
				char str[] = new char[j * 2];
				int k = 0;
				for (int i = 0; i < j; i++) {
					byte byte0 = md[i];
					str[k++] = hexDigits[byte0 >>> 4 & 0xf];
					str[k++] = hexDigits[byte0 & 0xf];
				}
				return new String(str);
			} catch (Exception e) {
			}
			return null;
		}
		
	}
}