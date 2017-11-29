<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	StringBuffer url = new StringBuffer();
	Enumeration enums = request.getParameterNames();
	while(enums.hasMoreElements()){
		
		String name = (String)enums.nextElement();
		if(!"pn".equals(name)){
			
			url.append("&").append(URLEncoder.encode(name, "UTF-8")).append("=").append(URLEncoder.encode(request.getParameter(name), "UTF-8"));
		}
	}
	request.setAttribute("url", url);
%>
<s:if test="pageModule != null && pageModule.totalCount > 0">
	<div class="paging">
		<div class="black">
			<a>总数：${pageModule.totalCount }条</a>
			<pg:pager items="${pageModule.totalCount}"
				url="/${actionName}_${currentMethod}.action" maxIndexPages="10"
				maxPageItems="${pageSize}"
				export="currentPageNumber=pageNumber">
				<pg:param name="method" />
				<pg:first>
					<a href="${pageUrl}${url }">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}${url }">上一页</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber }">
							<span class="current">${pageNumber}</span>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}${url }">${pageNumber}</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}${url }">下一页</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl}${url }">末页</a>
				</pg:last>
			</pg:pager>
		</div>
	</div>
</s:if>