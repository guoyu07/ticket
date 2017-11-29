<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp"%>
<ul>
   <s:if test="session.sessionMember != null">
       <ticket:common type="memberSetQuickMenuList" size="6"/>
          <s:if test="#request.memberSetQuickMenuList != null">
          		<s:iterator id="memberQuickMenu" value="#request.memberSetQuickMenuList">
          			<li>
          				<ticket:common type="quickMenuObj" value="${memberQuickMenu.quickMenu_id }"/>
          				<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
						<a href="${quickMenuObj.url }"> <img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
						<a href="#" memberQuickMenuId="${memberQuickMenu.id }" class="del deleteSetMenu">删除</a>
					</li>
          		</s:iterator>
         </s:if>
         <s:if test="#request.defaultList != null">
			<s:iterator id="default" value="#request.defaultList">
				<li><a href="#" class="icons3 custom_menu_btn" defaultPosition="4"></a></li>
			</s:iterator>
		</s:if>
    </s:if>
</ul>