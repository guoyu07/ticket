package com.ticket.util;

import java.io.File;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @Description：
 * @author：涂有
 * @date 2015年11月12日 上午12:02:51
 */
public class EmailUtil {
	
	public static final String hostname = "smtp.qq.com";
	public static final String username = "2327199539@qq.com";
	public static final String password = "aabbcc";
	
	/**
	 * 发送邮件
	 * @param add 邮箱地址
	 * @param title 邮箱标题
	 * @param content 邮箱内容（可以是html代码）
	 * @param files 附件
	 * @return 发送是否成功
	 */
	public static boolean send(String add, String title, String content, File...files){
		
		try {  
			// 不要使用SimpleEmail,会出现乱码问题  
			HtmlEmail email = new HtmlEmail();  
			// 这里是发送服务器的名字：，163的如下：  
			email.setHostName(hostname);  
			// 编码集的设置  
			email.setCharset("utf-8");  
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码  
			email.setAuthentication(username, password);  
			
			// 发送人的邮箱  
			email.setFrom(username);
			// 收件人的邮箱  
			email.addTo(add);
			// 邮件标题
			email.setSubject(title);  
			// 要发送的信息  
			email.setHtmlMsg(content);  
			
			//添加附件
			for(File file : files){
				
				email.attach(file);
			}
			
			// 发送  
			email.send();  
		} catch (EmailException e) {  
			e.printStackTrace();  
			return false;
		} 
		return true;
	}
	
	public static void main(String[] args) {
		
		EmailUtil.send("497122758@qq.com", "123", "<h1>涂有</h1>");
//		new EmailUtil2().sendEmail("497122758@qq.com", "", "");
	}
}
