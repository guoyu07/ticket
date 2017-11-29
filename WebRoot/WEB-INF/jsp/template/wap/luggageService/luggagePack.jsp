<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<s:if test="news == null ">
				<div class="mobile-page">
					<jsp:include page="../common/title.jsp"></jsp:include>
					<div class="mobile-main">
						<%@ include file="../common/favoriteAndShare.jsp" %>
						<div class="c_content">
							${noDataMessage }
						</div>
					</div>
					<%@ include file="../common/quickNav.jsp" %>
				</div>
			</s:if>
			<s:else>
				<div class="mobile-page">
					<jsp:include page="../common/title.jsp">
						<jsp:param value="title" name="${news.title }"/>
					</jsp:include>
					<div class="mobile-main">
						<%@ include file="../common/favoriteAndShare.jsp" %>
						<div class="c_content">
							${news.content }
						</div>
					</div>
					<%@ include file="../common/quickNav.jsp" %>
				</div>
			</s:else>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		
	</body>
	</ticket:cache>
</html>