<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="airportInfoList != null">
	<ul>
		<s:iterator id="airport" value="airportInfoList">
			<dd class='fz22 margin-big border-bottom padding-big-bottom' value="${airport.threeCode }" cityCode="${airport.threeCode }" cityName="${airport.name }">${airport.name }（${airport.threeCode }）<p class='fz14'>${airport.englishName }</p></dd>
		</s:iterator>
	</ul>
</s:if>