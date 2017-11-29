package com.ticket.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.ticket.exception.ServiceException;

/**
 * @Description：Sms短信发送工具类
 * @author：涂有
 * @date 2015年12月17日 下午4:45:56
 */
public class SmsUtil {
	
	private static final String account = "kmjingh_yncw";
	private static final String pswd = "YNcw121212";
	private static final String needstatus = "true";
	private static final String product = ""; 
	
	//短信签名
	public static final String SMS_SIGN = "【长水机场】";
	
	/**
	 * @Description：发送短信
	 * @date 2015年12月17日 下午4:47:36
	 * @param mobile 接收的手机号
	 * @param content 短信内容
	 */
	public static String sendSms(String mobile, String content) throws ServiceException {
		
		HttpClient client = new HttpClient(); 		
		PostMethod post = new PostMethod("http://120.24.167.205/msg/HttpSendSM");  		
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码 		
		NameValuePair[] data ={
				new NameValuePair("account", account),
				new NameValuePair("pswd", pswd),
				new NameValuePair("mobile", mobile),
				new NameValuePair("msg",SMS_SIGN + content),
				new NameValuePair("needstatus", needstatus),
				new NameValuePair("product", product)}; 		
		post.setRequestBody(data); 		
		
		try {
			client.executeMethod(post);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 	
//		Header[] headers = post.getResponseHeaders(); 		
//		int statusCode = post.getStatusCode(); 		
//		System.out.println("statusCode:"+statusCode); 		
//		for(Header h : headers){ 			
//			System.out.println(h.toString()); 		
//			} 		
		String result = null;
		try {
			result = new String(post.getResponseBodyAsString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}  		
//		System.out.println(result); //打印返回消息状态 		
		post.releaseConnection();
		return resultMsg(result);
	}
	
	public static String resultMsg(String result) throws ServiceException{
		
		if(GeneralUtil.isNull(result)){
			
			return "返回信息为空";
		}else{
			
			String s = result.substring(result.indexOf(',')+1);
			if("0".equals(s)){
				
				return "提交成功";
			}else if("101".equals(s)){
				
				throw new ServiceException("无此用户");
			}else if("102".equals(s)){
				
				throw new ServiceException("密码错");
			}else if("103".equals(s)){
				
				throw new ServiceException("提交过快（提交速度超过流速限制）");
			}else if("104".equals(s)){
				
				throw new ServiceException("系统忙（因平台侧原因，暂时无法处理提交的短信）");
			}else if("105".equals(s)){
				
				throw new ServiceException("敏感短信（短信内容包含敏感词）");
			}else if("106".equals(s)){
				
				throw new ServiceException("消息长度错（>536或<=0）");
			}else if("107".equals(s)){
				
				throw new ServiceException("包含错误的手机号码");
			}else if("108".equals(s)){
				
				throw new ServiceException("手机号码个数错（群发>50000或<=0;单发>200或<=0）");
			}else if("109".equals(s)){
				
				throw new ServiceException("无发送额度（该用户可用短信数已使用完）");
			}else if("110".equals(s)){
				
				throw new ServiceException("不在发送时间内");
			}else if("111".equals(s)){
				
				throw new ServiceException("超出该账户当月发送额度限制");
			}else if("112".equals(s)){
				
				throw new ServiceException("无此产品，用户没有订购该产品");
			}else if("113".equals(s)){
				
				throw new ServiceException("extno格式错（非数字或者长度不对）");
			}else if("115".equals(s)){
				
				throw new ServiceException("自动审核驳回");
			}else if("116".equals(s)){
				
				throw new ServiceException("签名不合法，未带签名（用户必须带签名的前提下）");
			}else if("117".equals(s)){
				
				throw new ServiceException("IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
			}else if("118".equals(s)){
				
				throw new ServiceException("用户没有相应的发送权限");
			}else if("119".equals(s)){
				
				throw new ServiceException("用户已过期");
			}else{
				
				return "默认返回";
			}
		} 
	}
}
