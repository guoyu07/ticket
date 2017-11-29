<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="trafficTypeObj" value="${id }"/>
<div class="c_content clearfix">
	<div class="fz16">
		${trafficTypeObj.introduce }
	</div>
</div>
<s:if test="#request.trafficTypeObj.name == '停车楼'">
	<c:forEach var="info" items="${positions }">
	<a 
		<s:if test="#session.fromApp == null && #parameters.fromApp == null">
		href="http://api.map.baidu.com/marker?location=${info[0]},${info[1]}&title=${info[2]}&content=${info[2]}&output=html&floorNumber=${info[3]}"
		</s:if>
		<s:else>
		href="/airport_daohang.action?longitude=${info[0]}&latitude=${info[1] }&name=${info[2]}&floorNumber=${info[3]}"
		</s:else>
		>
		<div class="tit b_blue c_white">
			${info[2] }
			<%-- <s:if test="#request.trafficTypeObj.name == '自驾车'">
				导航
			</s:if>
			<s:else>
				乘车点导航
			</s:else> --%>
		</div>
	</a>
	</c:forEach>
</s:if>
<s:else>
<a 
	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
	href="http://api.map.baidu.com/marker?location=${trafficTypeObj.latitude },${trafficTypeObj.longitude }&title=${trafficTypeObj.name }&content=${trafficTypeObj.name }&output=html&floorNumber=${trafficTypeObj.floorNumber}"
	</s:if>
	<s:else>
	href="/airport_daohang.action?longitude=${trafficTypeObj.longitude }&latitude=${trafficTypeObj.latitude }&name=${trafficTypeObj.name}&floorNumber=${trafficTypeObj.floorNumber}"
	</s:else>
	>
	<div class="tit b_blue c_white">
		<s:if test="#request.trafficTypeObj.name == '自驾车'">
			导航
		</s:if>
		<s:if test="#request.trafficTypeObj.name == '出租车'">
			出租车乘车点
		</s:if>
		<s:if test='#request.trafficTypeObj.name == "机场公交（919）"'>
			机场公交乘车点
		</s:if>
		<s:if test="#request.trafficTypeObj.name == '地铁'">
			地铁乘坐点
		</s:if>
		<s:if test="#request.trafficTypeObj.name == '空港快线'">
			空港快线乘车点
		</s:if>
	</div>
</a>
</s:else>