package com.ticket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.ticket.constants.ContextConstants;
import com.ticket.pojo.SystemUser;

/**
 * 后台管理员日志工具
 * @author xw
 *
 */
public class SystemOparetionLogUtil {
	/**
	 * 获取后台管理员的名称
	 * @return
	 */
	public static String getLogName(){
		SystemUser user = (SystemUser)ActionContext.getContext().getSession().get(ContextConstants.SCOPE_SYSTEM_USER);
		String logName = user.getName();
		return logName;
	}
	/**
	 * 获取管理员操作的时间
	 * @return
	 */
	public static String getLogTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String now = sdf.format(date);
		return now;
	}
	/**
	 * 获取管理员进行操作的IP
	 * @return
	 */
	public static String getLogIp(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
	}
}
