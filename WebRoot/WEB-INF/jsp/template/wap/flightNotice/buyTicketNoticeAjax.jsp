<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="newsCommonObj" value="${id }"/>
<s:if test="#request.newsCommonObj != null">
	${newsCommonObj.content }
</s:if>
<s:else>
	${noDataMessage }
</s:else>