<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="计时休息室"/>
				</jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content">
						<img src="/template/wap/images/pic/15.jpg">
					</div>
					<div class="tit">
						0817-67085806（点击拨号）
					</div>
					<div class="tit">
						导航到这
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>