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
						<ticket:common type="newsList" value="${newsClass.id }" size="100" />
						<s:if test="#request.newsList != null">
							<s:iterator id="newsPub" value="#request.newsList" status="st">
								<s:if test="#st.first">
									<ticket:commonAnnex type="queryAnnexList"
										entityUuid="${newsPub.id}" annexType="1" size="1" />
									<s:if test="#request.queryAnnexList != null">
										<s:iterator id="annex" value="#request.queryAnnexList"
											status="st">
											<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="500" height="320">
										</s:iterator>
									</s:if>
									<ticket:common type="locationListById" value="${newsCommonObj.infoPosition_id }"/>
										<s:if test="#request.locationListById != null">
										<dl class='llist'>
											<dd class='padding-bottom fz22'>
												${newsPub.content }
											</dd>
											<dd class='padding-bottom fz22'>
												航站楼内设有儿童娱乐室，分别位于：
											</dd>
											<s:iterator id="location" value="#request.locationListById"
												status="st">
												<dd class='padding-bottom fz22'>
													${st.count }、${location.name }
													<s:if test="#session.fromApp == null && #parameters.fromApp == null">
														<a style="display:none;" href="http://api.map.baidu.com/marker?location=${location.latitude },${location.longitude }&title=${location.name }&content=${location.name }&output=html" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
														<a href="/template/wap/images/tipPic.png" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
													</s:if>
													<s:else>
														<a href="airport_daohang.action" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
													</s:else>
												</dd>
											</s:iterator>
										</dl>
									</s:if>
								</s:if>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
	</ticket:cache>
</html>