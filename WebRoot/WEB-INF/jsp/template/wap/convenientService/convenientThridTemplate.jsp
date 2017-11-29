<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content">
						<ticket:common type="newsCommonObj" value="${news.id }" />
						<s:if test="#request.newsCommonObj != null">
							<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1" />
								<s:if test="#request.queryAnnexList != null">
									<s:iterator id="annex" value="#request.queryAnnexList" status="st">
										<img id="annex${st.count }" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="500" height="320">
									</s:iterator>
								</s:if>
							${news.content }<br />
							<dl class='llist'>
							<ticket:common type="locationListById" value="${news.infoPosition_id }"/>
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
												<a href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber }" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
											</dd>
										</s:else>
									</s:else>
								</s:iterator>
							</s:if>
						</dl>
						</s:if>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>