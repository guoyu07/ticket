<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="景点详情" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="pic_kv">
						<img src="/template/wap/images/pic/83.jpg">
					</div>
					<div class="c_content fz20 c_grey clearfix">
						<span class='float-left padding-top'>丽江丽江</span>
						<span class='float-right'><i
							class='icon-heart-o  txt radius-circle b_blue c_white'></i>&nbsp;&nbsp;123</span>
					</div>
					<div class="c_content">
						<img src="/template/wap/images/pic/84.jpg">
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