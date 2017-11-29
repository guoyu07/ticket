<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<s:if test="orderInfoDetails != null && orderInfoDetails.size >0">
<s:iterator id="c" value="orderInfoDetails" status="st">
<ticket:systemCommon type="bjdjServiceCodeObj" value="${c.bjdjServiceCode_id}"/>
<p class='fz22 <s:if test="#st.first">clearfix</s:if>'>
	${bjdjServiceCodeObj.code}
	<a href="javascript:;" class='float-right fz22'>${bjdjServiceCodeObj.state.value}</a>
</p>
<s:if test="!#st.last">
<hr/>
</s:if>
</s:iterator>
</s:if>
<s:else>
<p class='fz22'>
	该用户没有激活的服务码
</p>
</s:else>