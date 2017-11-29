<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<div class="line text-center">
<!-- 会员自定义快捷菜单 -->
	<ticket:common type="memberMenuList" value="a"/>
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
	<s:if test="#request.defaultList != null">
		<s:iterator id="default" value="#request.defaultList">
			<div class="x3">
				<a href="#" class='custom_menu_btn' defaultPosition="a"></a>
			</div>
		</s:iterator>
	</s:if>
</div>