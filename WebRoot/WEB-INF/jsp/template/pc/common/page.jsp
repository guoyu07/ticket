<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<s:url var="url" value="" includeParams="get" />

<!-- 传送action的名称，默认是当前action名称 -->
<s:if test="#parameters.actionName != null">
	<s:set var="actionName" value="#parameters.actionName" />
</s:if>
<!-- 传送method的名称，默认是当前method名称 -->
<s:if test="#parameters.currentMethod != null">
	<s:set var="currentMethod" value="#parameters.currentMethod" />
</s:if>

<s:if test="pageModule != null && pageModule.totalCount > 0">
	<div class="page_list">
		<ul class="black">
			<a><li class="bhh">总数：${pageModule.totalCount }条</li></a>
			<pg:pager items="${pageModule.totalCount}"
				url="/${actionName}_${currentMethod}.action" maxIndexPages="10"
				maxPageItems="${pageSize}"
				export="currentPageNumber=pageNumber">
				<pg:param name="method" />
				<pg:first>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }"><li class="bhh">首页</li></a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }"><li class="bhh">上一页</li></i>
					</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber }">
							<span class="current"><li class="bhh">${pageNumber}</li></span>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }"><li class="bhh">${pageNumber}</li></a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }"><li class="bhh">下一页</li></a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl}&${fn:substring(url,fn:indexOf(url,pn)+2,fn:length(url)) }"><li class="bhh">末页</li></a>
				</pg:last>
			</pg:pager>
		</ul>
	</div>
</s:if>