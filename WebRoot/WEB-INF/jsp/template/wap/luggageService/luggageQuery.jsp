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
						<ticket:common type="newsList" value="${newsClass.id }" size="100" />
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList" status="st">
					<div class="select_tit">
						<div class="button-group no-background">
							<button type="button" class="button  bg dropdown-toggle fz22"
								style="border: 1px solid #ddd">
										<%-- <s:if test="#st.first"> --%>
											<font style="color: #00AAFF">${news.title }</font>
										<%-- </s:if> --%>
									
								<%-- <s:else>
									<font style="color: #00AAFF">请选择你乘坐的航空公司</font>
								</s:else> --%>
								<!-- <span class="downward" style="color: #00AAFF"></span> -->
							</button>
							<%-- <ul class="drop-menu luggageQuery">
								<s:if test="#request.newsList != null">
									<s:iterator id="news" value="#request.newsList" status="st">
										<li news_id="${news.id }" style="color: #00AAFF">
											${news.title }
										</li>
									</s:iterator>
								</s:if>
							</ul> --%>
						</div>
					</div>
					<div class="c_content">
						<%-- <s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList" status="st">
								<s:if test="#st.first"> --%>${news.content}
								<br />
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
														<a href="/airport_daohang.action?longitude=${location.longitude }&latitude=${location.latitude }&name=${location.name}&floorNumber=${location.floorNumber}" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
													</dd>
												</s:else>
											</s:else>
										</s:iterator>
									<%-- </s:if> --%>
								<%-- </s:if>
							</s:iterator> --%>
						</s:if>
					</div>
					</s:iterator>
								</s:if>
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