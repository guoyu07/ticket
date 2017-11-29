<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="机场服务" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="" class='b_yello c_grey'>便捷登机</a>
						<a href="" class='b_yello c_grey'>行李服务</a>
					</div>
					<div class="tit_tab">
						<a href="" class='b_yello c_grey'>电瓶车</a>
						<a href="" class='b_yello c_grey'>失物招领</a>
					</div>
					<div class="tit_tab">
						<a href="" class='b_yello c_grey'>餐饮零食</a>
						<a href="" class='b_yello c_grey'>爱心服务</a>
					</div>
					<div class="tit_tab">
						<a href="" class='b_yello c_grey'>设置</a>
						<a href="" class='b_yello c_grey'>其他设施</a>
					</div>
					<div class="c_content">
						<br>
						<br>
						<br>
						<p class='c_grey fz30 text-center'>
							可推送服务消息
						</p>
						<br>
						<br>
						<br>
					</div>
					<%@ include file="../common/quickNav.jsp"%>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>