<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file='../common/headAjax.jsp'%>
<div class="line text-center">
	<s:if test="#session.sessionMember == null">
		<ticket:common type="visiorMenuList" value="2"/>
		<s:if test="#request.visiorMenuList != null">
			<s:iterator id="visitorMenu" value="#request.visiorMenuList">
				<div class="x3">
					<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
					<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
				</div>
			</s:iterator>
		</s:if>
		<s:if test="#request.defaultList != null">
			<s:iterator id="default" value="#request.defaultList">
				<div class="x3">
					<a href="#" class='custom_menu_btn' defaultPosition="2"></a>
				</div>
			</s:iterator>
		</s:if>
	</s:if>
	<s:else>
	<!-- 会员默认显示的快捷菜单  value 显示的位置 1 出发 2 中转 3 到达 size 1默认显示-->
		<ticket:common type="memberMenuList" value="2" size="1"/>
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
	<!-- 会员自定义菜单 -->
		<ticket:common type="memberMenuList" value="2" size="0"/>
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
		<ticket:common type="memberNoSetQuickMenuList" value="2"/>
		<s:if test="#request.memberNoSetQuickMenuList != null">
			<s:iterator id="default" value="#request.memberNoSetQuickMenuList">
				<div class="x3">
					<a href="#" class='custom_menu_btn' defaultPosition="2"></a>
				</div>
			</s:iterator>
		</s:if>
	</s:else>
</div>