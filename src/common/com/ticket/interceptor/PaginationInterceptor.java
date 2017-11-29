package com.ticket.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.ticket.constants.ContextConstants;
import com.ticket.util.PaginationContext;
import com.ticket.util.WebUtil;

/**
 * 分页参数拦截器
 * @ClassName: PaginationInterceptor   
 * @Description: 拦截到当前请求地址的分页参数.   
 * @author HiSay  
 * @date Jul 31, 2013 9:21:30 AM   
 *
 */
public class PaginationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(PaginationInterceptor.class);

	/**
	 * 进行拦截操作
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			HttpServletRequest httpRequest = getRequest(invocation);
			Integer currentPage = 0;
			if(WebUtil.validateRequestParameter(httpRequest, "pn")) {
				currentPage = Integer.parseInt(httpRequest.getParameter("pn"));
			}
			Integer pageSize = 30;
			if(WebUtil.validateRequestParameter(httpRequest, "ps")) {
				pageSize = Integer.parseInt(httpRequest.getParameter("ps"));
			}
			PaginationContext.setOffset(currentPage); //计算分页的偏移量
			PaginationContext.setPagesize(pageSize); //计算分页的偏移量
			if (WebUtil.validateRequestAttribute(httpRequest, "versionFlag")) {
				httpRequest.setAttribute("versionFlag", httpRequest.getParameter("versionFlag"));
			} else {
				httpRequest.setAttribute("versionFlag", ContextConstants.VERSION_FLAG_OF_CHINESE);
			}
		} catch (Exception e) {
			log.equals(e.fillInStackTrace());
		}
		return invocation.invoke();
	}

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @param invocation
	 *            ActionInvocation对象
	 * @return request对象
	 */
	private HttpServletRequest getRequest(ActionInvocation invocation) {
		ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		return request;
	}

	protected int getOffset(Integer currentPage, Integer pageSize) {
		/*int offset = 0;
		try {
			if(currentPage != null && pageSize != null) {
				offset = (currentPage - 1) * pageSize;
			}
			if (offset <= 0) {
				offset = 0;
			}
		} catch (NumberFormatException ignore) {
		}
		return offset;*/
		return currentPage;
	}
}
