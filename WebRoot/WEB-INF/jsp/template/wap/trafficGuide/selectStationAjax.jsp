<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="trafficStationList != null">
	<ul>
		<s:iterator id="trafficStation" value="trafficStationList">
			<li value="${trafficStation.id }">${trafficStation.name } <span></span></li>
		</s:iterator>
	</ul>
</s:if>
<s:else>
	<ul>
		<li>抱歉，查无结果！</li>
	</ul>
</s:else>