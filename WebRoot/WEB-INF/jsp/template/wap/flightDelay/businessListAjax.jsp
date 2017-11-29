<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="businessListByType" cTypeId="${var}" wz="${wz }" order="${order }"/>
<ul>
<s:if test="#request.businessListByType != null">
	<s:iterator	id="businessInfo" value="#request.businessListByType" status="st">
		<li>
			<ticket:common type="businessLinkList" value="${businessInfo.id}"/>
			<s:if test="#request.businessLinkList != null">
				<s:iterator value="#request.businessLinkList" var="link">
					<a href="/airport_goYouZanUrl.action?visitUrl=${link.url }&name=${link.name}">
				</s:iterator>
			</s:if>
			<s:else>
				<a href="/airport_businessDetail.action?id=${businessInfo.id }">
			</s:else>
				<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
				<img src="${image_server_url}${annex.annexPath}" width="149" height="148" alt=" " class="sy_pro" />
			</a>
			<div class="sj_info">
				<h1>
					<ticket:common type="businessLinkList" value="${businessInfo.id}"/>
			<s:if test="#request.businessLinkList != null">
				<s:iterator value="#request.businessLinkList" var="link">
					<a href="/airport_goYouZanUrl.action?visitUrl=${link.url }&name=${link.name}">
				</s:iterator>
			</s:if>
			<s:else>
				<a href="/airport_businessDetail.action?id=${businessInfo.id }" class="dianname">
			</s:else>${businessInfo.name }</a>
					<a href="tel:${businessInfo.phone }">
						<img src="/template/wap/images/sy_tel.png" width="50" height="30" alt=" " class="sy_tel" />
					</a>
				</h1>
                <h2>分类：<ticket:systemCommon type="airportBusinessTypeObj" value="${businessInfo.businessType_id}"/>
									    	${airportBusinessTypeObj.name }</h2>
				<h2>主营：${businessInfo.mainSale }</h2>
				
			</div> 
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<a href="http://api.map.baidu.com/marker?location=${businessInfo.latitude },${businessInfo.longitude }&title=${businessInfo.name }&content=下载App能享受室内导航服务&output=html"> 
					<img src="/template/wap/images/sy_pos.png" width="111" height="111" alt=" " class="sj_nav" />
				</a>
			</s:if>
			<s:else>
				<a href="/airport_daohang.action?longitude=${businessInfo.longitude }&latitude=${businessInfo.latitude }&name=${businessInfo.name }&floorNumber=${businessInfo.floorNumber}"> 
					<img src="/template/wap/images/sy_pos.png" width="111" height="111" alt=" " class="sj_nav" />
				</a>
			</s:else>
            <h3>${businessInfo.address }</h3>
			<div class="clear"></div>
		</li>
	</s:iterator>
</s:if>
</ul>
<s:else>
	<div class="c_content">
		${noDataMessage }
	</div>
</s:else>
