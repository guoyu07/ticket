package com.ticket.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.constants.ContextConstants;
import com.ticket.util.UrlUtil;

/**
 * pc端会员中心拦截器
 * @author dfq
 * @descript 
 * @date 2015-12-31 15:08
 */
public class PcMemberInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		//如果会员没有登录则跳转到登录页面
		if(session.get(ContextConstants.SCOPE_MEMBER) == null) {
			//保存登录前的路径
			request.setAttribute("prevUrl", request.getRequestURL()  + "?" + UrlUtil.mapToString(UrlUtil.getMap(request)));
			return "toLoginPage";
		}
		return invocation.invoke();
	}
}
