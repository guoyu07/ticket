<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld"%>
<s:if test="departList != null || arrivalList != null">
	<table cellspacing="0" cellpadding="0" width="650">
		<tr>
		<th colspan="2" width="190">航空公司</th>
		<th width="100">航班号</th>
		<th width="100">出发城市</th>
		<th width="100">到达城市</th>
		<th width="80">出发时间</th>
		<th width="80">到达时间</th>
		</tr>
	<s:if test="departList != null">
		
		<s:iterator id="d" value="departList">
			<tr>
				<ticket:common type="flightCompanyByTwoCode" value="${d.acw}"/>
				<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
				<td><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></td>
				<td>${flightCompanyByTwoCode.name }</td>
				<td>${d.flt}</td>
				<td><ticket:common type="systemDicObjByName" value="${d.org}"/>${systemDicObjByName.description }</td>
				<td><ticket:common type="systemDicObjByName" value="${d.des}"/>${systemDicObjByName.description }</td>
				<td><s:date name="#d.std " format="yyyy-MM-dd"/><br /><s:date name="#d.std " format="HH:mm"/></td>
				<td><s:date name="#d.sta " format="yyyy-MM-dd"/><br /><s:date name="#d.sta " format="HH:mm"/></td>
			</tr>
			<tr><td colspan="7" style="height:1px; background:url(/template/pc/images/border_dotted.gif) repeat-x;"></td></tr>
		</s:iterator>
		
	</s:if>
	<s:if test="arrivalList != null">
		<s:iterator id="d" value="arrivalList">
			<tr>
				<ticket:common type="flightCompanyByTwoCode" value="${d.acw}"/>
				<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
				<td><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></td>
				<td>${flightCompanyByTwoCode.name }</td>
				<td>${d.flt}</td>
				<td><ticket:common type="systemDicObjByName" value="${d.org}"/>${systemDicObjByName.description }</td>
				<td><ticket:common type="systemDicObjByName" value="${d.des}"/>${systemDicObjByName.description }</td>
				<td><s:date name="#d.std " format="yyyy-MM-dd"/><br /><s:date name="#d.std " format="HH:mm"/></td>
				<td><s:date name="#d.sta " format="yyyy-MM-dd"/><br /><s:date name="#d.sta " format="HH:mm"/></td>
			</tr>
			<tr><td colspan="7" style="height:1px; background:url(/template/pc/images/border_dotted.gif) repeat-x;"></td></tr>
		</s:iterator>
	</s:if>
	</table>
</s:if>
<s:else>
	<table cellspacing="0" cellpadding="0" width="650">
	<tr>
		<th colspan="7">无航班信息</th>
	</tr>
	</table>
</s:else>
