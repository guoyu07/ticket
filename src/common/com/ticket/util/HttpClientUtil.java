package com.ticket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 网络请求的公用工具类
 * @author 涂有
 */
public class HttpClientUtil {
	
	/**
	 * 发起post请求
	 * @param url
	 * @param params
	 * @param encode
	 * @return
	 */
	public static String post(String url, String content, String encode){
		
		try {
			URL u = new URL(url);
			URLConnection con = u.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "text/xml; charset=" + encode);
			
			if(ValidateUtil.isNotNull(content)){
				
				OutputStream output = con.getOutputStream();
				output.write(content.getBytes(encode));
				output.close();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), encode));
			StringBuffer result = new StringBuffer();
			String line;
			while((line = reader.readLine()) != null){
				
				result.append(line);
			}
			reader.close();
			return result.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String post(String url, String content){
		
		return post(url, content, "utf-8");
	}

	/**
	 * 发起post请求
	 * @param url
	 * @param params
	 * @param encode
	 * @return
	 */
	public static String post(String url, Map<String, String> params, String encode){
		
		String p = UrlUtil.mapToString(params);
		return post(url, p, encode);
	}
	
	/**
	 * 发起utf-8编码post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, Map<String, String> params){
		
		return post(url, params, "utf-8");
	}
	
	/**
	 * 发起get请求
	 * @param url
	 * @param encode
	 * @param params
	 * @return
	 */
	public static String get(String url, Map<String, String> params, String encode){
		
		try {
			URL u = new URL(url + "?" + UrlUtil.mapToString(params));
			HttpURLConnection con = (HttpURLConnection)u.openConnection();
			con.setRequestProperty("Content-Type", "text/html; charset=" + encode);
			con.setRequestMethod("GET");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), encode));
			StringBuffer result = new StringBuffer();
			String line;
			while((line = reader.readLine()) != null){
				
				result.append(line);
			}
			reader.close();
			return result.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发起utf-8编码get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, Map<String, String> params){
		
		return get(url, params, "utf-8");
	}
	
	/**
	 * 请求获取JSON
	 * @param url
	 * @param encode
	 * @param params
	 * @return
	 */
	public static JSONObject getJSON(String url, Map<String, String> params, String encode){
		
		String result = post(url, params, encode);
		if(result == null){
			
			return null;
		}
		return JSON.parseObject(result);
	}
	
	/**
	 * 请求获取JSON
	 * @param url
	 * @param encode
	 * @param params
	 * @return
	 */
	public static JSONObject getJSON(String url, Map<String, String> params){
		
		return getJSON(url, params, "utf-8");
	}
	
	/**
	 * 请求获取JSON
	 * @param url
	 * @param encode
	 * @param params
	 * @return
	 */
	public static Map<String, String> getXML(String url, Map<String, String> params, String encode){
		
		String result = post(url, params, encode);
		if(result == null){
			
			return null;
		}
		try {
			return XMLUtil.parseTomap(result);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return new HashMap<String, String>();
	}
	
	/**
	 * 请求获取JSON
	 * @param url
	 * @param encode
	 * @param params
	 * @return
	 */
	public static Map<String, String> getXML(String url, Map<String, String> params){
		
		return getXML(url, params, "utf-8");
	}
	
	public static void main(String[] args) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "abc");
		params.put("age", "123");
//		System.out.println(get("http://localhost:8888/servlet/rest", params));
		
		System.out.println(post("http://localhost:8888/servlet/rest", params));
	}
}