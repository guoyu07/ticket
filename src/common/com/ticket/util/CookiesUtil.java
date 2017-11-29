package com.ticket.util;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

public class CookiesUtil {
	public static Cookie getCookies(String cookieName) {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if(cookies == null){
			return null;
		}else{
			for(int i=0;i<cookies.length;i++){
				Cookie cookie = cookies[i];
				if(cookie.getName().equalsIgnoreCase(cookieName)){
					return cookie;
				}
			}
		}
		return null;
	}
}
