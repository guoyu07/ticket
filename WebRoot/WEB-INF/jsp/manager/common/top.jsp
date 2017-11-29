<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="site_right_top">
	<div class="top_right">
		<li>
			<a href="/ticket_logout.action">安全退出</a>
		</li>
		<li>
			|
		</li>
		<li class="setting">
			<a href="#" id="showdiv">安全退出</a>
		</li>
		<li>
			|
		</li>
		<li>
			欢迎您！【${systemUser.name }】 点击旁边图标可有弹出框
		</li>
	</div>
	<div class="top_left manageTopNavClass">
		<s:if test="#session.adminTopModuleList != null">
			<s:iterator id="m" value="#session.adminTopModuleList" status="st">
				<li id="mt_${st.count }">
					<a href="#" value="${m.id }" markIndex="${st.count }">${m.name }</a>
				</li>
			</s:iterator>
		</s:if>	
	</div>
</div>