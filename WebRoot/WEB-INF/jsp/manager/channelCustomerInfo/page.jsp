<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:url var="url" value="" includeParams="get" />
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
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }">上一页</i>
					</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber }">
							<span class="current">${pageNumber}</span>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }">${pageNumber}</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }">下一页</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }">末页</a>
				</pg:last>
			</pg:pager>
		</div>
	</div>
</s:if>