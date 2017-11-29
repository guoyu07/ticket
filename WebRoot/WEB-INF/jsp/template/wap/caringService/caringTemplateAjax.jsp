<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>
<ticket:common type="newsCommonObj" value="${id }"/>
<s:if test="#request.newsCommonObj != null">
	<div class="c_content">
		${newsCommonObj.content }
			<ticket:common type="locationListById" value="${newsCommonObj.infoPosition_id }"/>
			<s:if test="#request.locationListById != null">
				<s:iterator id="location" value="#request.locationListById">
					<s:if test="#location.mobile != null && #location.mobile !='' ">
						<dd class='padding-bottom fz22 border-small border-bottom'>
							${location.name } 
							<a href="tel:${location.mobile }" class='c_blue float-right'>
							<em class='quick_mobile'></em>一键拨号</a>
						</dd>
					</s:if>
					<s:else>
						<s:if test="#session.fromApp == null && #parameters.fromApp == null">
							<dd class='padding-bottom fz22'>
								${location.name }
								<a href="http://api.map.baidu.com/marker?location=${location.latitude },${location.longitude }&title=${location.name }&content=下载App能享受室内导航服务&output=html" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:else>
							<dd class='padding-bottom fz22'>
								${location.name }
								<a href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber}" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:else>
					</s:else>
				</s:iterator>
			</s:if>
			<br>
			<s:if test="#request.newsCommonObj.id == '402fb597-d437-41c0-8486-081c0169f9a1'">
				<dd class='padding-bottom fz22'>
					母婴室
					<a href="<ticket:common type="positionUrl2" value="母婴室"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
				</dd>
				<%-- <a href="<ticket:common type="positionUrl2" value="母婴室"/>">
					<div class="tit b_blue c_white">
						导航
					</div>
				</a> --%>
			</s:if>
	</div>
</s:if>
