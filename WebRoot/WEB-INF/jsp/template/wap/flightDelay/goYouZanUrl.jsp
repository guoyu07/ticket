<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="${name }" name="title" />
				</jsp:include>
				<div class="mobile-main">
					<iframe src="${url }" width="100%" height="100%" style="border: 0"></iframe>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<script type="text/javascript" src="/template/wap/js/ticketOrder/ticketOrder.js"></script>
	</body>
</html>