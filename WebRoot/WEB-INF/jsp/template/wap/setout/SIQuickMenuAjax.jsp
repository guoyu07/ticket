<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<div class="line text-center">
	<!-- 游客默认显示的快捷菜单 -->
	<s:if test="#session.sessionMember == null">
		<ticket:common type="visiorMenuList" value="b"/>
		<s:if test="#request.visiorMenuList != null">
			<s:iterator id="visitorMenu" value="#request.visiorMenuList" status="st">
				<div class="x3">
					<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
					<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
				</div>
			</s:iterator>
		</s:if>
		<s:if test="#request.defaultList != null">
			<s:iterator id="default" value="#request.defaultList">
				<div class="x3">
					<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="b"></a>
				</div>
			</s:iterator>
		</s:if>
	</s:if>
	<s:else>
	<!-- 会员自定义菜单 -->
		<ticket:common type="memberMenuList" value="b"/>
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
					<a href="#" class='custom_menu_btn toSetQuickMenu' defaultPosition="b"></a>
				</div>
			</s:iterator>
		</s:if>
	</s:else>
</div>
