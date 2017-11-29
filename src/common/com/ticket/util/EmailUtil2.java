/**
 * 文件名：EmailUtil.java
 *
 * 版本信息：
 * 日期：2010-4-8
 * Copyright 冠科 2010 
 * 版权所有
 *
 */
package com.ticket.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 项目名称 ：yhs
 * 类  名  称  ：WebUtil
 * 类  描  述  ：邮件的发送与接收
 * 创  建   人 ：冠科 {QQ:25069430}
 * 创建时间 ：2010-4-7 下午11:42:16
 * 修  改   人 ：冠科 {QQ:25069430}
 * 修改时间 ：2010-4-7 下午11:42:16
 * 修改备注 ：
 * @version 
 * 
 */
public class EmailUtil2 {
	/**
	 * 登录邮件服务器验证状态激活：{@value}
	 * */
	public static final boolean AUTH_STATE_ENABLE = true;

	/**
	 * 登录邮件服务器验证状态禁用：{@value}
	 * */
	public static final boolean AUTH_STATE_DISABLE = false;

	/**
	 * 发送邮件调式状态激活：{@value}
	 * */
	public static final boolean DEBUG_STATE_ENABLE = false;

	/**
	 * 发送邮件调试状态禁用：{@value}
	 * */
	public static final boolean DEBUG_STATE_DISABLE = false;

	/** Log4对象 */
	private static final Logger log = LoggerFactory.getLogger(EmailUtil2.class);

	/**
	 * 美国Yahoo SMTP 邮件服务器地址：{@value}
	 * */
	public static final String SMTP_SERVER_YAHOO_US = "plus.smtp.mail.yahoo.com";

	/**
	 * 中国Yahoo SMTP 邮件服务器地址：{@value}
	 * */
	public static final String SMTP_SERVER_YAHOO_CN = "smtp.mail.yahoo.com.cn";

	/**
	 * 中国网易126 SMTP 邮件服务器地址：{@value}
	 * */
	public static final String SMTP_SERVER_126 = "smtp.126.com";

	/**
	 * 中国网易126 SMTP 邮件服务器地址：{@value}
	 * */
	public static final String POP_SERVER_126 = "pop.126.com";

	/**
	 * 中国网易163 SMTP 邮件服务器地址：{@value}
	 * */
	public static final String SMTP_SERVER_163 = "smtp.163.com ";

	/**
	 * Tom SMTP 邮件服务器地址：{@value}
	 * */
	public static final String SMTP_SERVER_TOM = "mail.wodeshangcheng.com";

	/**
	 * 发送使用的箱件服务器
	 * */
	private String senddMailServerAddress = "mail.wodeshangcheng.com";

	/**
	 * 接收使用的箱邮服务器
	 * */
	private String getMailServerAddress = "mail.wodeshangcheng.com";

	/**
	 * 发件人邮箱地址
	 * */
	private String sendMailAddress = "support@wodeshangcheng.com";

	/**
	 * 登录邮件服务器的账号
	 * */
	private String userName = "support@wodeshangcheng.com";

	/**
	 * 登录邮件服务器对应账号的密码
	 * */
	private String password = "g5d4t8q8";

	/**
	 * 在控制台打印调试信息，默认值：{@value}
	 * */
	private boolean debug = false;

	/**
	 * 是否需要服务器验证，默认值：{@value}
	 * */
	private boolean auth = true;

	/**
	 * 将一封邮件发送给一个收件人
	 * 
	 * @param toMail
	 *            收件人邮件地址
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @return 发送邮箱结果状态
	 * */
	public boolean sendEmail(String toMail, String title, String content) {
		return sendEMail(new String[] { toMail }, title, content, false);
	}

	/**
	 * 将一封邮件以HTML格式发送给一个收件人
	 * 
	 * @param toMail
	 *            收件人邮件地址
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @return 发送邮箱结果状态
	 * */
	public boolean sendEmailHTML(String toMail, String title, String content) {
		return sendEMail(new String[] { toMail }, title, content, true);
	}

	/**
	 * 将一封邮件发送给多个收件人
	 * 
	 * @param toMail
	 *            收件人邮件地址列表
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @return 发送邮箱结果状态
	 * */
	public boolean sendEmail(String[] toMail, String title, String content) {
		return sendEMail(toMail, title, content, false);
	}

	/**
	 * 将一封邮件以HTML格式发送给多个收件人
	 * 
	 * @param toMail
	 *            收件人邮件地址列表
	 * @param title
	 *            邮件标题
	 * @param content
	 *            邮件内容
	 * @return 发送邮箱结果状态
	 * */
	public boolean sendEmailHTML(String[] toMail, String title, String content) {
		return sendEMail(toMail, title, content, true);
	}

	/**
	 * 给一个列表收件人发送邮件
	 * 
	 * @param toMail
	 *            收件人的地址
	 * @param toTitle
	 *            邮件标题
	 * @param toContent
	 *            邮件内容
	 * @param useHTML
	 *            是否使用HTML格式
	 * @return boolean
	 * */
	private boolean sendEMail(String[] toMail, String toTitle,
			String toContent, boolean useHTML) {
		SendMail sendMail = new SendMail();
		sendMail.sendToMail(toMail, toTitle, toContent, useHTML);
		return true;
	}

	/**
	 * 私有内部类用于多线程发送邮件
	 * */
	private class SendMail implements Runnable {

		private String[] toMail;
		private String toTitle;
		private String toContent;
		private boolean useHTML;

		/**
		 * 设置邮件发送内容并且启动线程发送邮件
		 * 
		 * @param toMail
		 *            收件人邮件地址列表
		 * @param toTitle
		 *            邮件标题
		 * @param toContent
		 *            邮件内容信息
		 * @param useHTML
		 *            是否使用HTMl格式发送
		 * */
		public void sendToMail(String[] toMail, String toTitle,
				String toContent, boolean useHTML) {
			// TODO 设置邮件内容并启动线程发送
			this.toMail = toMail;
			this.toTitle = toTitle;
			this.toContent = toContent;
			this.useHTML = useHTML;
			// 启动线程
			Thread thread = new Thread(this);
			thread.start();
		}

		public void run() {
			// TODO 发送邮件线程
			if (toMail == null || toTitle == null || toTitle == null)
				return;
			Session mailSession = Session.getInstance(getEMailProperties(true));
			mailSession.setDebug(debug);
			MimeMessage mimeMessage = new MimeMessage(mailSession);
			try {
				// 创建发件人地址
				InternetAddress internetAddress = new InternetAddress(
						sendMailAddress);
				// 设置发件人
				mimeMessage.setFrom(internetAddress);
				if (log.isDebugEnabled()) {
					log.debug("调试模式状态:" + debug);
					log.debug("邮件发送服务器主机:" + senddMailServerAddress);
					log.debug("登录邮件服务器账号:" + userName);
					log.debug("登录邮件服务器密码:" + password);
					log.debug("是否需要服务器验证:" + auth);
					log.debug("发送邮件地址:" + sendMailAddress);
				}
				// 循环设置收件人
				for (String to : toMail) {
					if (log.isDebugEnabled()) {
						log.debug("收件人地址:" + to);
					}
					mimeMessage.addRecipient(Message.RecipientType.TO,
							new InternetAddress(to));
				}
				// 邮件主题
				mimeMessage.setSubject(toTitle, "utf-8");
				// 设置邮件内容
				if (useHTML) {
					Multipart multipart = new MimeMultipart();
					MimeBodyPart mbp = new MimeBodyPart();
					mbp.setContent(toContent, "text/html;charset=gb2312");
					multipart.addBodyPart(mbp);
					mimeMessage.setContent(multipart);
				} else
					mimeMessage.setText(toContent);
				// 发送日期（服务器系统时间）
				mimeMessage.setSentDate(new Date());
				// 发送邮件
				mimeMessage.saveChanges();
				Transport transport = mailSession.getTransport("smtp");
				transport.connect(senddMailServerAddress, userName, password);
				transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
				transport.close();
			} catch (MessagingException me) {
				if (log.isErrorEnabled()) {
					log.error("邮件发送失败:", me);
				} else {
				}
			}
		}
	}

	/**
	 * 取得邮件邮件服务器连接信息
	 * 
	 * @return properties 邮件服务器连接信息
	 * */
	private Properties getEMailProperties(boolean isSend) {
		Properties properties = new Properties();
		if (isSend)
			properties.put("mail.smtp.host", senddMailServerAddress);
		else
			properties.put("mail.smtp.host", getMailServerAddress);
		properties.put("mail.smtp.auth", "" + auth);
		return properties;
	}

	/**
	 * 接收邮箱内的邮件
	 * 
	 * @return message 邮件列表,如果找不支返回空.
	 * */
	@SuppressWarnings("unused")
	private Message[] getMail() {
		Session session = Session.getDefaultInstance(getEMailProperties(false));
		// 设置调试状态
		session.setDebug(debug);
		try {
			// 使用POP3会话连接邮件服务器
			Store store = session.getStore("pop3");
			store.connect(getMailServerAddress, userName, password);
			// 获取默认文件夹
			Folder folder = store.getDefaultFolder();
			if (folder == null)
				return null;
			/*
			 * getFoloder(Value) POP三协议只支持Inbox INBOX:收件箱 DRAFT:草稿箱 NEW:新邮件
			 */
			folder = folder.getFolder("INBOX");
			if (folder == null)
				return null;
			// 以只读方式打开邮件
			folder.open(Folder.READ_ONLY);
			// 取得文件夹中的邮件列表
			Message[] message = folder.getMessages();
			store = null;
			folder = null;
			return message;
		} catch (NoSuchProviderException e) {
		} catch (MessagingException e) {
		}
		return null;
	}

	/**
	 * 设置发送邮件服务器地址、接收邮件服务器地址、登录账号、登录密码、 发送邮件人的邮箱地址
	 * 
	 * @param snedMailServerAddress
	 *            发送邮件服务器地址
	 * @param getMailServerAddress
	 *            接收邮件服务器地址
	 * @param userName
	 *            登录账号
	 * @param password
	 *            登录密码
	 * @param sendMailAddress
	 *            发送邮件人的邮箱地址
	 * */
	public void setMailServer(String snedMailServerAddress,
			String getMailServerAddress, String userName, String password,
			String sendMailAddress) {
		this.senddMailServerAddress = snedMailServerAddress;
		this.getMailServerAddress = getMailServerAddress;
		this.userName = userName;
		this.password = password;
		this.sendMailAddress = sendMailAddress;
	}

	/**
	 * 取得发送邮件服务器地址
	 * 
	 * @return snedMailServerAddress
	 * */
	public String getsendMailServerAddress() {
		return senddMailServerAddress;
	}

	/**
	 * 发送邮件服务器地址
	 * 
	 * @param mailServerAddress
	 * */
	public void setSendMailServerAddress(String mailServerAddress) {
		this.senddMailServerAddress = mailServerAddress;
	}

	/**
	 * 取得接收邮件服务器地址
	 * 
	 * @return getMailServerAddress
	 * */
	public String getGetMailServerAddress() {
		return getMailServerAddress;
	}

	/**
	 * 设置接收邮件服务器地址
	 * 
	 * @param getMailServerAddress
	 *            接收邮件服务器的地址
	 * */
	public void setGetMailServerAddress(String getMailServerAddress) {
		this.getMailServerAddress = getMailServerAddress;
	}

	/**
	 * 取得登录密码
	 * 
	 * @return password
	 * */
	public String getPassword() {
		return password;
	}

	/**
	 * 设定登录邮件服务器的密码
	 * 
	 * @param password
	 *            登录服务器的密码
	 * */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 取得发件人邮箱地址
	 * 
	 * @return sendMailAddress
	 * */
	public String getSendMailAddress() {
		return sendMailAddress;
	}

	/**
	 * 设置邮件服务器的发送地址
	 * 
	 * @param sendMailAddress
	 *            发件人邮箱地址
	 * */
	public void setSendMailAddress(String sendMailAddress) {
		this.sendMailAddress = sendMailAddress;
	}

	/**
	 * 取得登录服务器的账号
	 * 
	 * @return userName
	 * */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置登录服务器的账号
	 * 
	 * @param userName
	 *            登录账号
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 取得服务器验证状态
	 * 
	 * @return auth {@link #AUTH_STATE_ENABLE} {@link #AUTH_STATE_DISABLE}
	 * */
	public boolean getAuth() {
		return auth;
	}

	/**
	 * 设置登录邮件服务器是否需要验证状态
	 * 
	 * @param auth
	 *            服务器验证状态 {@link #AUTH_STATE_ENABLE} {@link #AUTH_STATE_DISABLE}
	 * */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	/**
	 * 取得调试状态 {@link #DEBUG_STATE_ENABLE} {@link #DEBUG_STATE_DISABLE}
	 * */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * 设置调试状态是否启用
	 * @param debug
	 * 调试状态 {@link #DEBUG_STATE_ENABLE} {@link #DEBUG_STATE_DISABLE}
	 * */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
