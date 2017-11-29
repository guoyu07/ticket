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
					<a href="javascript:;" onclick="toPage('${pageUrl}','${keyword}');">首页</a>
				</pg:first>
				<pg:prev>
					<a href="javascript:;" onclick="toPage('${pageUrl}','${keyword}');">上一页</i>
					</a>
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber }">
							<span class="current">${pageNumber}</span>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" onclick="toPage('${pageUrl}','${keyword}');">${pageNumber}</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="javascript:;" onclick="toPage('${pageUrl}','${keyword}');">下一页</a>
				</pg:next>
				<pg:last>
					<a href="javascript:;" onclick="toPage('${pageUrl}','${keyword}');">末页</a>
				</pg:last>
			</pg:pager>
		</div>
	</div>
</s:if>
<script>
	function toPage(pageUrl,param2){
		window.location.href=pageUrl+"&keyword="+JM.encodeByValue(param2);
	}
</script>