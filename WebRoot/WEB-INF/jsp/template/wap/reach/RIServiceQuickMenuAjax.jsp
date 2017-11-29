<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<div class="line text-center">
	<ticket:common type="flightQuickMenuList" value="${memberSelfMenuId }&m" size="8"/>
	<s:if test="#request.flightQuickMenuList != null">
		<s:iterator id="quickMenu" value="#request.flightQuickMenuList">
			<div class="x3">
				<ticket:common type="quickMenuObj" value="${quickMenu.quickMenu_id }"/>
				<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
				
				<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
			</div>
		</s:iterator>
	</s:if>
	<s:set var="count" value="#request.flightQuickMenuList.size()"></s:set>
	<c:forEach begin="${count }" end="7" step="1">
		<div class="x3">
			<a href="#" class='custom_menu_btn serviceQuickMenuBtn' flightQuickMenu="${memberSelfMenuId }" menuCount="${defaultCount }" defaultPosition="m"></a>
		</div>
	</c:forEach>
</div>
