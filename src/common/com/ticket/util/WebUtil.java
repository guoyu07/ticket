package com.ticket.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Web操作的常用方法
 * @ClassName: WebUtil   
 * @Description:    
 * @author HiSay  
 * @date Jul 31, 2013 9:24:20 AM   
 *
 */
public class WebUtil {
	
	private WebUtil(){}
	
	/**
	 * 
	 * writerCookie
	 * 	     (根据指定的name和value写入cookie到客户端)
	 * 
	 * @param response 
	 * 	     {@link javax.servlet.http.HttpServletResponse}
	 * 
	 * @param name 
	 * 	     cookie的名称
	 * 
	 * @param value
	 * 		 cookie的值
	 * 
	 * @author 海水 
	 * 
	 */
	public static void writerCookie(HttpServletResponse response, String name, String value) {
		if(GeneralUtil.isNotNull(name) && GeneralUtil.isNotNull(value)) {
			Cookie cookie = new Cookie(name, value);
			response.addCookie(cookie);
		}
	}
	
	
	/**
	 * getCookieByName
	 * 		(根据Cookie的名称获取Cookie对象)
	 * 
	 * @param request 
	 * 		{@link javax.servlet.http.HttpServletRequest}对象
	 * 
	 * @param cookieName 
	 * 		要获得的cookie对象的名称
	 *  
	 * @return 
	 * 		Cookie对象
	 */
	public static Cookie readCookieByName(HttpServletRequest request, String cookieName) {
		/** 获得客户端所有的cookie */
		Cookie[] cookieList = request.getCookies();
		
		/** 进行循环判断, 有则返回匹配的cookie, 否则返回null */
		if(cookieList != null && cookieList.length > 0) {
			for(Cookie cookie : cookieList) {
				if(cookieName.equals(cookie.getName())) {
					return cookie;
				}
			}
		} 
		return null;
	}
	
	/**
	 * validateRequestParameter(验证request请求里面是否包含某个值)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 * @param name 请求参数的名称 
	 * @return 如果请求的参数存在则返回true, 如果不存在则返回false
	 */
	public static boolean validateRequestParameter(HttpServletRequest request, String name) {
		if(name == null || "".equals(name.trim())) return false;
		if(request.getParameter(name) != null && !"".equals(request.getParameter(name))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * validateRequestAttribute(验证request属性域里面是否包含某个值)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 * @param name 存在属性域的名称 
	 * @return 如果请求的值存在则返回true, 如果不存在则返回false
	 */
	public static boolean validateRequestAttribute(HttpServletRequest request, String name) {
		if(name == null || "".equals(name.trim())) return false;
		if(request.getAttribute(name) != null && !"".equals(request.getAttribute(name))) {
			return true;
		}
		return false;
	}
	
	/**
	 * validateSessionAttribute(验证session属性域里面是否包含某个值)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 * @param name 存在属性域的名称 
	 * @return 如果请求的值存在则返回true, 如果不存在则返回false
	 */
	public static boolean validateSessionAttribute(HttpServletRequest request, String name) {
		if(name == null || "".equals(name.trim())) return false;
		if(request.getSession().getAttribute(name) != null && !"".equals(request.getSession().getAttribute(name))) {
			return true;
		}
		return false;
	}
	
	/**
	 * validateApplicationAttribute(验证application属性域里面是否包含某个值)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 * @param name 存在属性域的名称 
	 * @return 如果请求的值存在则返回true, 如果不存在则返回false
	 */
	public static boolean validateApplicationAttribute(HttpServletRequest request, String name) {
		if(name == null || "".equals(name.trim())) return false;
		if(request.getSession().getServletContext().getAttribute(name) != null && !"".equals(request.getSession().getServletContext().getAttribute(name))) {
			return true;
		}
		return false;
	}
	
	/**
	 * validateApplicationAttribute(验证application属性域里面是否包含某个值)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 * @param name 存在属性域的名称 
	 * @return 如果请求的值存在则返回true, 如果不存在则返回false
	 */
	public static boolean validateApplicationAttribute(HttpSession session, String name) {
		if(name == null || "".equals(name.trim())) return false;
		if(session.getServletContext().getAttribute(name) != null && !"".equals(session.getServletContext().getAttribute(name))) {
			return true;
		}
		return false;
	}
	
	/**
	 * getServerPath(获得服务器的绝对路径)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 * @result java.lang.String 当前工程所在的绝对路径
	 */
	public String getServerPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	/**
	 * 
	 * getObject(根据pojo类与request参数生成pojo对象)
	 * @param c pojo所对应的完成类名(包名+类名)
	 * @param request {@link javax.servlet.http.HttpServletRequest}对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObject(Class c, HttpServletRequest request) throws Exception{    
        T o = (T) c.newInstance();    
        Set<String> set = request.getParameterMap().keySet();    
        Field[] cfs = c.getDeclaredFields();    
        for (String str : set) {    
            for (Field field : cfs) {    
                if(str.equals(field.getName())){    
                    setObject(c, request, o, str);    
                    break;    
                }    
            }    
   
        }    
        return o;    
    }    
	
	/**
	 * setObject(内部服务方法)
	 */
    @SuppressWarnings("unchecked")
	private static <T> void setObject(Class c, HttpServletRequest request, T o, String str) throws NoSuchFieldException, NoSuchMethodException,    
            ParseException, IllegalAccessException, InvocationTargetException {    
        Field f = c.getDeclaredField(str);    
        String cname = f.getType().getSimpleName();    
        String fname = str.substring(0,1).toUpperCase()+str.substring(1);    
        Method m = c.getDeclaredMethod("set"+fname, f.getType());    
        if(cname.equals("Date")){    
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd 00:00:00");    
            Date date=format.parse(request.getParameter(str));    
            m.invoke(o, date);    
        }else if(cname.equals("int")){    
            int i=Integer.parseInt(bool(request.getParameter(str)));    
            m.invoke(o, i);    
        }else if(cname.equals("Long")){    
            Long l=Long.parseLong(bool(request.getParameter(str)));    
            m.invoke(o, l);    
        }else {    
            m.invoke(o, request.getParameter(str));    
        }    
    }    
    /**
	 * setObject(内部服务方法)
	 */
    private static String bool(String str){    
        return str.equals("1")?"1":"0";    
    }  

    /**
     * 
     * forword(转向到其他视图)
     * @param request {@link javax.servlet.http.HttpServletRequest}对象
     * @param response {@link javax.servlet.http.HttpServletResponse}对象
     * @return 跳转到指定的URL路径地址
     * @Exception ServletException, IOException
     */
    public static void forword(HttpServletRequest request,HttpServletResponse response,String url) throws ServletException, IOException{    
        request.getRequestDispatcher(url).forward(request, response);    
    }
}
