package com.ticket.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.constants.ContextConstants;
import com.ticket.pojo.Member;
import com.ticket.service.IMemberService;
import com.ticket.util.SpringUtil;
import com.ticket.util.UrlUtil;
import com.ticket.util.WebUtil;

/**
 * WAP端会员中心拦截器
 * @author lenovo
 */
@SuppressWarnings("unused")
public class WapMemberInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	private static IMemberService memberService = (IMemberService) SpringUtil.getObjectFromSpring("memberService");
	
	@SuppressWarnings("unchecked")
	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		Map application = invocation.getInvocationContext().getApplication();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);
		
		if(WebUtil.validateRequestParameter(request, "fromApp")) {
			session.put("fromApp", "app");
			request.setAttribute("fromApp", "app");
		}
		
		if(WebUtil.validateRequestParameter(request, "mid") && session.get(ContextConstants.SCOPE_MEMBER) == null) {
			Member member = memberService.queryById(Member.class.getSimpleName(), request.getParameter("mid")) ;
			if(member != null) {
				session.put(ContextConstants.SCOPE_MEMBER, member) ;
				request.getSession().setAttribute(ContextConstants.SCOPE_MEMBER, member) ;
			}
		}
		if(WebUtil.validateRequestParameter(request, "murl") && session.get(ContextConstants.SCOPE_MEMBER) == null) {
			Member member = memberService.queryByUrl(request.getParameter("murl"));
			if(member != null) {
				session.put(ContextConstants.SCOPE_MEMBER, member) ;
				request.getSession().setAttribute(ContextConstants.SCOPE_MEMBER, member) ;
			}
		}
		//如果会员没有登录则跳转到登录页面
		if(session.get(ContextConstants.SCOPE_MEMBER) == null) {
			String methodName = invocation.getProxy().getMethod();
			if("focusFlight".equals(methodName)) {
				session.put("prevUrlSession", 
						"/airportm_focusFlight.action?flightNumber="+
						request.getParameter("flightNumber")+"&flightDate="+
						request.getParameter("flightDate")+"&memberRole="+
						request.getParameter("memberRole")+"&flightState="+
						request.getParameter("flightState"));
			}
			//保存登录前的路径
			request.setAttribute("prevUrl", request.getRequestURL() + "?" + UrlUtil.mapToString(UrlUtil.getMap(request)));
			return "login";
		}
		return invocation.invoke();
	}
}