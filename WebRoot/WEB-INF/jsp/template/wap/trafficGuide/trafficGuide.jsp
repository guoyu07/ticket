<%@page import="java.net.URLDecoder"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String commonKeyword = (String)session.getAttribute("commonKeyword");
%>
<!DOCTYPE html>
<html>
	<%@include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">交通指南</s:param>
				</s:include>
				<div class="mobile-main">
                    <div class="searchForm" style="display:none;">
                        <label class='button'><i class='icon-search c_l_grey'></i>
                        <input type="text" name="stationKeyword" id="stationKeyword" placeholder='请输入站点关键词' value="<%=commonKeyword == null ? "" : URLDecoder.decode(commonKeyword, "utf-8") %>" ></label>
                        <button class="button bg-sub searchTrafficType">搜索</button>
                        <div id="selectStationDiv" class="tagMatches" ></div>
                    </div>
					<div class="mr40 trafficTypeAjax">
						<ul>
							<ticket:common type="trafficTypeListAll"/>
							<s:if test="#request.trafficTypeListAll != null">
								<s:iterator id="trafficType" value="#request.trafficTypeListAll">
									<li class='c_text line margin-large-bottom'>
										<div class='x9 fz26 padding-top'>
											${trafficType.name }
										</div>
										<div class='x3'>
											<s:if test="#trafficType.status.hot == 0">
												<a href="/airport_trafficGuideDetail.action?id=${trafficType.id }" class='b_blue c_white button fz26 padding block text-center' style='padding:20px 0px;'>详情</a>
											</s:if>
											<s:else>
												<a href="/airport_trafficGuideLine.action?id=${trafficType.id }" class='b_blue c_white button fz26 padding block text-center' style='padding:20px 0px;' >详情</a>
											</s:else>
										</div>
									</li>
								</s:iterator>
							</s:if>
						</ul>
							<a 
								<s:if test="flag != null">
								href="<ticket:common type="positionUrl" value="${flag }"/>"
								</s:if>
								<s:else>
								href="<ticket:common type="positionUrl" value="chufadating"/>"
								</s:else>
								>
								<div class="tit b_blue c_white">
									进入地图
								</div>
							</a>
					</div>
				</div>
				<%@include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>