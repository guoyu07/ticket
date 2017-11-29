package com.ticket.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.constants.ContextConstants;
import com.ticket.util.UrlUtil;

/**
 * 后台系统用户操作拦截器
 * @ClassName: SystemUserInterceptor
 * @author HiSay 聚名项目部（云南天蝎网络科技有限公司）
 * @date 2014-11-9 下午07:09:28
 *
 */
@SuppressWarnings("unchecked")
public class SystemUserInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		
		// 如果session中不存在用户,则弹出到登陆页面
		if (session.get(ContextConstants.SCOPE_SYSTEM_USER) == null) {
			request.setAttribute(ContextConstants.LOGIN_REDIRECT_URL, 
					request.getRequestURL() + "?" + UrlUtil.mapToString(UrlUtil.getMap(request)));
			return "adminLogin";
		} else {
			return invocation.invoke();
		}
	}
}