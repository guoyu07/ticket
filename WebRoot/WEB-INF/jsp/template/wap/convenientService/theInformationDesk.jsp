<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content fz26">
						机场询问电话：0871-96566
						<span class='text-sub text-large float-right'><i
							class='icon-mobile-phone margin-big'></i><a href="tel:0871-96566">一键拨号</a></span>
					</div>
					<ticket:common type="newsList" value="${newsClass.id }" size="100" />
					<s:if test="#request.newsList != null">
						<s:iterator id="newsQuery" value="#request.newsList" status="st">
							<div class="c_content">
								<dl class="clearfix">
									<dt class="float-left margin-large-right">
									<ticket:commonAnnex type="queryAnnexList" entityUuid="${newsQuery.id}" annexType="1" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="160" width="240">
											</s:iterator>
										</s:if>
									</dt>
									<dd class='float-left' style='width: 220px;'>
										<h3 class="fz24" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis; width:217px;">
											${newsQuery.title }
										</h3>
										<hr>
										<p>
											${newsQuery.content }
										</p>
									</dd>
								</dl>
							</div>
							<ticket:common type="locationListById" value="${newsQuery.infoPosition_id }"/>
							<s:if test="#request.locationListById != null">
								<s:iterator id="location" value="#request.locationListById">
									<s:if test="#session.fromApp == null && #parameters.fromApp == null">
										<a href="http://api.map.baidu.com/marker?location=${location.latitude },${location.longitude }&title=${location.name }&content=下载App能享受室内导航服务&output=html"><div class="tit">
											${location.name }
										</div></a>
									</s:if>
									<s:else>
										<a href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber }"><div class="tit">
											${location.name }
										</div></a>
									</s:else>
								</s:iterator>
							</s:if>
						</s:iterator>
					</s:if>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>