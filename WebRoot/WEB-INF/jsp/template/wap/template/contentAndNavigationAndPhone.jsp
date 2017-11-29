<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
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
							<dl class='llist'>
								<dd class='padding-bottom fz22'>
									${newsCommonObj.content }
								</dd>
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
							</dl>
						</s:if>
						<s:if test="news.id=='f969f055-9156-495b-b857-9dca96f6887e'">
							<dd class='padding-bottom fz22'>
								卫生间
								<a href="<ticket:common type="positionUrl2" value="卫生间"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='b1081fec-7b5d-410f-bac6-584dd0d74cec'">
							<dd class='padding-bottom fz22'>
								残障人士专用卫生间
								<a href="<ticket:common type="positionUrl2" value="残障人士卫生间"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='5645f12b-ecfb-4b85-8711-d4f251091219'">
							<dd class='padding-bottom fz22'>
								吸烟室
								<a href="<ticket:common type="positionUrl2" value="吸烟室"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='4fabba9f-a5a3-43b8-b698-55106fc00711'">
							<dd class='padding-bottom fz22'>
								公用电话
								<a href="<ticket:common type="positionUrl2" value="公用电话"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='50e3a9a8-d43e-430c-a5a2-4a6f117c1554'">
							<dd class='padding-bottom fz22'>
								自动售货机
								<a href="<ticket:common type="positionUrl2" value="自动售货机"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='20de02c5-d02c-4a66-ae4a-25322bd5d629'">
							<dd class='padding-bottom fz22'>
								自助充电站
								<a href="<ticket:common type="positionUrl2" value="自助充电站"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='330d7370-7669-463e-b668-d722c7ccf3cc'">
							<dd class='padding-bottom fz22'>
								饮水机
								<a href="<ticket:common type="positionUrl2" value="饮水机"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='8b303e97-f66c-4974-b42e-155f1aaa83c7'">
							<dd class='padding-bottom fz22'>
								手推车
								<a href="<ticket:common type="positionUrl2" value="手推车"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
						</s:if>
						<s:if test="news.id=='789eeb19-ed08-4559-a443-781576a2c1d0'">
						</s:if>
						<s:if test="news.id=='402fb597-d437-41c0-8486-081c0169f9a1'">
							<dd class='padding-bottom fz22'>
								母婴室
								<a href="<ticket:common type="positionUrl2" value="母婴室"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
							</dd>
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
</html>