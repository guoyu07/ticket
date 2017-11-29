package com.ticket.util;

import com.ticket.menu.HttpClientConnectionManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 微笑自定义菜单工具类
 */
public class WxMenuUtils {
	// http客户端
	public static DefaultHttpClient httpclient = null;
	public static final String SUCCESS = "ok";
	
	private WxMenuUtils() {}
	
	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
	}
	
	/**
	 * 创建菜单
	 */
	public static String createMenu(String params, String accessToken)
			throws Exception {
		HttpPost httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ accessToken);
		httpost.setEntity(new StringEntity(params, "UTF-8"));
		HttpResponse response = httpclient.execute(httpost);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject object = JSON.parseObject(jsonStr);
		return object.getString("errmsg");
	}

	/**
	 * 获取accessToken
	 */
	public static String getAccessToken(String appid, String secret) {
		try {
			HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appid + "&secret=" + secret);
			HttpResponse response = httpclient.execute(get);
			String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
			JSONObject object = JSON.parseObject(jsonStr);
			return object.getString("access_token");
		} catch(Exception e) {
			return "error";
		}
	}

	/**
	 * 查询菜单
	 */
	public static String getMenuInfo(String accessToken) throws Exception {
		HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		return jsonStr;
	}

	/**
	 * 删除自定义菜单
	 */
	public static String removeMenu(String accessToken) throws Exception {
		HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject object = JSON.parseObject(jsonStr);
		return object.getString("errmsg");
	}
}