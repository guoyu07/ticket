<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="值机须知" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="c_content text-left fz20">
						<p class="fz28">
							网上办理乘机手续服务协议
						</p>
						<br>
						<ticket:common type="systemDicObjByValue" value="checkinServiceNotice"/>
						${systemDicObjByValue.description }
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