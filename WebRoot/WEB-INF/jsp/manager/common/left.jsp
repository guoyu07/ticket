<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="site_left">
	<div class="logo">
		<a href="/ticket.action">LOGO</a>
	</div>
	<div class="my_left">
		<div id="firstpane" class="menu_list manageLeftNavClass">
			<s:if test="#session.adminLeftModuleList != null">
				<s:iterator id="m" value="#session.adminLeftModuleList" status="st">
					<ticket:systemCommon type="childSystemModuleList" value="${m.id }"/>
					<s:if test="#request.childSystemModuleList != null">
						<p class="menu_head" id="ml_${st.count}">
							${m.name }
						</p>
						<div class="menu_body">
							<s:iterator id="child" value="#request.childSystemModuleList">
								<a href="${child.url }&leftIndex=${st.count}">${child.name }</a>
							</s:iterator>
						</div>
					</s:if>
					<s:else>
						<p class="menu_head" id="ml_${st.count}">
							${m.name }
						</p>
						<div class="menu_body">
							<a href="${m.url }&leftIndex=${st.count}">${m.name }</a>
						</div>
					</s:else>
				</s:iterator>
			</s:if>
		</div>
	</div>
</div>