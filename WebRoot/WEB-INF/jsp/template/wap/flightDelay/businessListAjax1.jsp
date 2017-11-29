<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="businessListByType" value="${id}"/>
<s:if test="#request.businessListByType != null">
	<s:iterator	id="businessInfo" value="#request.businessListByType" status="st">
		<div class="c_content">
			<div class="media media-x">
				<a class="float-left" href="/airport_businessDetail.action?id=${businessInfo.id }">
				<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
						<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" width='100' height="100">
				</a>
				<div class="media-body line businessDetail" businessId="${businessInfo.id }">
					<div class="x12 fz24 c_black">
						${st.count }. ${businessInfo.name }
					</div>
					<%-- <div class="x6 padding-top">
						<font class="c_orange fz22">${businessInfo.score }分</font>
					</div> --%>
					<div class="fz22 padding-top">
						主营:
						<font class="c_orange fz22">${businessInfo.mainSale }</font>
					</div>
					<div class="x6 fz16 c_l_grey padding-top">
						
					</div>
					<div class="x6 fz16 c_l_grey padding-top">
						
					</div>
				</div>
				<br>
				<hr>
				<div class="media-foot line height-large">
					<s:if test="#session.fromApp == null && #parameters.fromApp == null">
						<a href="http://api.map.baidu.com/marker?location=${businessInfo.latitude },${businessInfo.longitude }&title=${businessInfo.name }&content=下载App能享受室内导航服务&output=html" class="x6 text-center"><font class='inline_block'><i
							class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
					</s:if>
					<s:else>
						<a href="/airport_daohang.action?longitude=${businessInfo.longitude }&latitude=${businessInfo.latitude }&name=${businessInfo.name }&floorNumber=${businessInfo.floorNumber}" class="x6 text-center"><font class='inline_block'><i
							class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
					</s:else>
					<a href="tel:${businessInfo.phone }" class="x6 text-center"><font class='inline_block'><i
							class='tel_icon float-left'></i>电话</font>
					</a>
				</div>
			</div>
		</div>
	</s:iterator>
</s:if>
<s:else>
	<div class="c_content">
		${noDataMessage }
	</div>
</s:else>
