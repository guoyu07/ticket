<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="消息提醒" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="msg_ls">
						<li></li>
					</div>
					<div class="c_ft clearfix">
						<a href="#" class="ft_more"></a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
</html>