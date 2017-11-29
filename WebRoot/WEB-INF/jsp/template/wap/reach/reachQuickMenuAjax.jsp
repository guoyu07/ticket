<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<div class="line text-center">
	<!-- 会员自定义菜单 -->
	<ticket:common type="memberMenuList" value="e"/>
	<s:if test="#request.memberMenuList != null">
		<s:iterator id="menuSet" value="#request.memberMenuList">
			<ticket:common type="quickMenuObj" value="${menuSet.quickMenu_id }"/>
				<s:if test="#request.quickMenuObj != null">
					<div class="x3">
						<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
							<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
					</div>
				</s:if>
		</s:iterator>
	</s:if>
	<ticket:common type="memberNoSetQuickMenuList" value="e"/>
	<s:if test="#request.memberNoSetQuickMenuList != null">
		<s:iterator id="default" value="#request.memberNoSetQuickMenuList">
			<div class="x3">
				<a href="#" class='custom_menu_btn' defaultPosition="e"></a>
			</div>
		</s:iterator>
	</s:if>
</div>