<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="newsClassChilds" value="${newsClass.alias }"/>
<s:if test="#request.newsClassChilds != null">
	<s:iterator id="nc" value="#request.newsClassChilds">
		<div class="tit">
			<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
		</div>
	</s:iterator>
</s:if>
<s:else>
	${noDataMessage }
</s:else>