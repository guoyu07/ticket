<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="金融服务"/>
				</jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<ticket:common type="newsList" value="${newsClass.id }" size="100"/>
						<s:if test="#request.newsList != null">
							<s:iterator id="businessNews" value="#request.newsList">
								<a href="/airport/${businessNews.status.visitUrl }.ticket">
									<div class="tit">
										${businessNews.title }
									</div>
								</a>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="tit">${noDataMessage }</div>
						</s:else>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>