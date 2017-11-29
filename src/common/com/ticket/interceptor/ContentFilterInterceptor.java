package com.ticket.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 内容过滤拦截器
 * @ClassName: ContentFilterInterceptor   
 * @Description:对于前台,后台提交的内容进行第一道拦截和过滤
 * @author HiSay  
 * @date Jul 10, 2013 7:14:54 AM   
 *
 */
public class ContentFilterInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ContentFilterInterceptor.class);

	/**
	 * 进行拦截操作
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (Exception e) {
			log.equals(e.fillInStackTrace());
		}
		return invocation.invoke();
	}
}
