package com.ticket.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.constants.ContextConstants;

/**
 * 手机CRM拦截器
 * @author xw
 *
 */
public class AirportEmployeeInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		Map session = arg0.getInvocationContext().getSession();
		if(session.get(ContextConstants.SCOPE_SYSTEM_USER) == null){
			return "loginPage";
		}
		return arg0.invoke();
	}

}
