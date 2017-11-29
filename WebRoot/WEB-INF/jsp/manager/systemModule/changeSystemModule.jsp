<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
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