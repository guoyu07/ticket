<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<s:if test="pageModule != null && pageModule.totalCount > 0">
<s:iterator id="m" value="pageModule.pageList" status="st">
	<p class='fz22 clearfix'>
	<a href="javascript:;" class="fz22" attrValue="${m.id}">${m.title}<span
		class='float-right '><s:date name="#m.status.createTime" format="yyyy/MM/dd"/></span>
	</a>
	</p>
	<s:if test="!#st.last">
		<br/>
	</s:if>
</s:iterator>
</s:if>
<s:else>
<p class='fz22 clearfix'>
	无记事内容
</p>
</s:else>