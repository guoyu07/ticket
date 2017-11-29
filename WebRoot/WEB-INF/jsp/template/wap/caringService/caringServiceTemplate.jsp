<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="${news.title }" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content">
						${news.content }
					</div>
					<s:if test="news.id=='b1081fec-7b5d-410f-bac6-584dd0d74cec' || news.id=='f969f055-9156-495b-b857-9dca96f6887e'">
						<a href="<ticket:common type="positionUrl2" value="卫生间"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
					</s:if>
					<s:if test="news.id=='5645f12b-ecfb-4b85-8711-d4f251091219'">
						<a href="<ticket:common type="positionUrl2" value="吸烟室"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
					</s:if>
					<s:if test="news.id=='4fabba9f-a5a3-43b8-b698-55106fc00711'">
						<a href="<ticket:common type="positionUrl2" value="公用电话"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
					</s:if>
					<s:if test="news.id=='50e3a9a8-d43e-430c-a5a2-4a6f117c1554'">
						<a href="<ticket:common type="positionUrl2" value="自动售货机"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
					</s:if>
					<s:if test="news.id=='20de02c5-d02c-4a66-ae4a-25322bd5d629'">
						<a href="<ticket:common type="positionUrl2" value="自助充电站"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
					</s:if>
					<s:if test="news.id=='330d7370-7669-463e-b668-d722c7ccf3cc'">
						<a href="<ticket:common type="positionUrl2" value="饮水机"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
					</s:if>
					<s:if test="news.id=='8b303e97-f66c-4974-b42e-155f1aaa83c7'">
						<a href="<ticket:common type="positionUrl2" value="手推车"/>">
							<div class="tit b_blue c_white">
								导航
							</div>
						</a>
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