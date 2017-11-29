package com.ticket.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.action.BaseAction;
import com.ticket.util.MD5Util;
import com.ticket.util.WebUtil;

/**
 * APP接口拦截器
 * @ClassName: AppInterceptor
 * @Description: 
 * @date 2015-10-02 下午12:04:18
 */
@SuppressWarnings("unused")
public class AppInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		Map application = invocation.getInvocationContext().getApplication();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
		BaseAction action = (BaseAction)invocation.getAction();
		if(WebUtil.validateRequestParameter(request, "token")) {
			String token = request.getParameter("token"); //获取访问的令牌
			String decodeToken = MD5Util.decryptString(token);
			if("ynhx_app_java".equals(decodeToken) || "kmcsjc_app_java".equals(decodeToken)) {
				return invocation.invoke();
			} else {
				action.setData("Token invalid ...");
				return "TEXT";
			}
		} else {
			action.setData("Token invalid ...");
			return "TEXT";
		}
	}
}