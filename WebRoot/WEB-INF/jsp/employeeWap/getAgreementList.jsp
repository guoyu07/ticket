<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<s:if test="agreements != null && agreements.size > 0">
<s:iterator id="c" value="agreements" status="st">
<p class='fz22 clearfix'>
	<ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfo_id}" />
	${channelCustomerInfoObj.name}
	<span class='float-right '>
	<ticket:commonAnnex type="queryAnnexList" entityUuid="${c.id}" annexType="1" size="1"/>
	<s:if test="#request.queryAnnexList != null && #request.queryAnnexList.size >0">
		<a href="/downloadAnnex.action?id=${queryAnnexList[0].id}"
		class="b_blue c_white fz18 button">下载</a>
	</s:if>
	<s:else>
		<a href="javascript:;"
		class="c_orange fz22">合同领取中...</a>
	</s:else>
	</span>
</p>
<s:if test="!#st.last">
<br>
</s:if>
</s:iterator>
</s:if>
<s:else>
<p class='fz22 clearfix'>
	公司名称错误或无合同信息
</p>
</s:else>
