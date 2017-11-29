package com.ticket.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * @Description：处理url的工具类
 * @author：涂有
 * @date 2015年11月20日 上午1:27:23
 */
public class UrlUtil {

	/**
	 * @Description：从map转换为url参数字符串
	 * @param params
	 * @return
	 */
	public static String mapToString(Map<String, String> params){
		
		if(ValidateUtil.isNull(params)){
			
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		Set<String> keys = params.keySet();
		for(String key : keys){
			
			sb.append(key);
			sb.append("=");
			sb.append(params.get(key));
			sb.append("&");
		}
		return sb.substring(0, sb.length()-1);
	}
	
	/**
	 * @Description：将url参数字符串转换为map
	 * @param params
	 * @return
	 */
	public static Map<String, String> stringToMap(String params){
		
		Map<String, String> map = new TreeMap<String, String>();
		String[] param = params.split("&");
		for(String p : param){
			
			String key = p.split("=")[0];
			String value = p.split("=")[1];
			map.put(key, value);
		}
		return map;
	}
	
	/**
	 * @Description：得到本服务器的url路径
	 * @return
	 */
	public static String getUrlPath(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		return path;
	}
	
	/**
	 * @Description：获取本服务器的域名（不带路径）
	 * @return
	 */
	public static String getDomainName(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = null;
		if(80 == request.getServerPort()){
			
			basePath = request.getScheme()+"://"+request.getServerName() + "/";
		}else{
			
			basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
		}
		return basePath;
	}
	
	/**
	 * 得到远程访问客户端的IP
	 * @param request
	 * @return
	 */
	 public static String getIpAddr(HttpServletRequest request)  {
		 
		 String ip = request.getHeader("x-forwarded-for");
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))  {
			 
			 ip = request.getHeader("Proxy-Client-IP");
		 } 
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))  {
			 
			 ip = request.getHeader("WL-Proxy-Client-IP");
		 } 
		 if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))  {
			 
		 	ip = request.getRemoteAddr();
		 } 
		 return ip;
	 }
	 
	 public static Map<String, String> getMap(HttpServletRequest request){
		 
		 Map<String,String> params = new TreeMap<String,String>();
		 Map requestParams = request.getParameterMap();
		 for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			 String name = (String) iter.next();	
			 String[] values = (String[]) requestParams.get(name);
			 String valueStr = "";
			 for (int i = 0; i < values.length; i++) {
				 valueStr = (i == values.length - 1) ? valueStr + values[i]
						 : valueStr + values[i] + ",";
			 }
			 //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			 try {
//				 valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			 } catch (UnsupportedEncodingException e) {
//				 e.printStackTrace();
//			 }
			 params.put(name, valueStr);
		 }
		 return params;
	 }
	 
	 public static String getRequestContent(HttpServletRequest request){
		 
		 try {
			 BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
			 StringBuffer sb = new StringBuffer();
			 String line = null;
			 while((line = reader.readLine()) != null){
				 
				 sb.append(line);
			 }
			 return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	 }
}
