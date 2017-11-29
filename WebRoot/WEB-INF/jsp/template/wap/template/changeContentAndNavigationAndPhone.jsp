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
					<div class="tit_tab2">
						<ticket:common type="newsList" value="${newsClass.id }" size="100" />
						<s:if test="#request.newsList != null">
							<s:iterator id="nn" value="#request.newsList" status="st">
								<s:if test="#st.first">
									<a href="#" class="selected changeFacilities" news_id="${nn.id }">${nn.title
										}</a>
								</s:if>
								<s:else>
									<a href="#" class="changeFacilities" news_id="${nn.id }">${nn.title
										}</a>
								</s:else>
							</s:iterator>
						</s:if>
					</div>
					<div class="c_content">
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList" status="st">
								<s:if test="#st.first">
										${news.content }
									</s:if>
							</s:iterator>
						</s:if>
					</div>
					<div class="tit">
						导航到这
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