<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<s:if test="#request.newsList != null">
	<s:iterator id="news" value="#request.newsList">
		<li>[公告栏]<a href="/airport/${news.status.visitUrl }.ticket">${news.title }</a></li>
	</s:iterator>
</s:if>