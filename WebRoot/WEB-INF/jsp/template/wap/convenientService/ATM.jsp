<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<s:if test="news == null ">
				暂无文章信息
			</s:if>
			<s:else>
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">${news.title }</s:param>
				</s:include>
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
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>