package com.ticket.util;

import java.net.URLDecoder;

import com.capinfo.crypt.RSA_MD5;

public class RsaUtil {
	
	//密匙
	public static final String KEY = "wwwjms100";//
	
	public RsaUtil() {}

	/**
	 * 生成MD5加密字符串
	 * @param text
	 * @param key
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String generateMD5Info(String text) {
		try {
			RsaMd5 md5 = new RsaMd5( "" );
			md5.hmac_Md5(text, KEY) ;
			byte b[]= md5.getDigest();
			return md5.stringify(b) ;
		} catch(Exception e) {
			return "";
		}
	}
	
	/**
	 * 验证网银服务器发过来的信息是否合法
	 * @param signValue
	 * @param source
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static boolean verirySign(String signValue, String source) {
		try {
			/** 获取当前的上下文路径 */
			String contextPath = IpUtil.class.getResource("").getPath();

			/** 用URLDecoder */
			contextPath = URLDecoder.decode(contextPath);

			/** 组合路径 */
			String path = new StringBuilder(contextPath).append("Public1024.key")
					.toString();

			/** 解码path路径 */
			path = URLDecoder.decode(path);

			
			RSA_MD5 rsaMD5 = new RSA_MD5();
			int k = rsaMD5.PublicVerifyMD5(path, signValue, source);
			if (k == 0) {
				return true;
			}
			return false;
		} catch(Exception e) {
			return false;
		}
	}
}