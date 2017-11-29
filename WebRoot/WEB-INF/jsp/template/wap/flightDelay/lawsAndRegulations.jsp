<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<jsp:include page="../common/title.jsp">
				<jsp:param name="title" value="法律法规"/>
			</jsp:include>
			<div class="mobile-page">
				<div class="mobile-main">
					<div class="c_content">
						<div class="fz20">
							${news.content }
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>