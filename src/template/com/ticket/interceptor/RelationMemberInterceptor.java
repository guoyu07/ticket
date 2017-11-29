package com.ticket.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ticket.constants.ContextConstants;
import com.ticket.pojo.MemberQQ;
import com.ticket.pojo.MemberWeiBo;

public class RelationMemberInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		if(session.get(ContextConstants.SCOPE_MEMBER) instanceof MemberWeiBo){//如果是微博登录进来的，并且没有绑定会员
			MemberWeiBo bo = (MemberWeiBo)session.get(ContextConstants.SCOPE_MEMBER);
			if(bo.getMember() == null){
				return "";
			}
		}
		if(session.get(ContextConstants.SCOPE_MEMBER) instanceof MemberQQ){//如果是qq登录进来的，并且没有绑定会员
			MemberQQ qq = (MemberQQ)session.get(ContextConstants.SCOPE_MEMBER);
			if(qq.getMember() == null){
				return "";
			}
		}
		return invocation.invoke();
	}

}
