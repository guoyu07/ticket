package com.ticket.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * 微信支付信任管理器
 * @ClassName: MyX509TrustManager
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author HiSay 
 * @date Mar 31, 2015 10:02:54 PM
 */
public class MyX509TrustManager implements X509TrustManager {

	// 检查客户端证书
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 检查服务器端证书
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	// 返回受信任的X509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
