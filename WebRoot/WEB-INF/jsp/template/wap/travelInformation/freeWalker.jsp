<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="自由行" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<p class="mr40 fz20">
						目前景点：丽江
						<a href="#" class='c_blue'>点击切换</a>
					</p>
					<div class="tit b_blue c_white">
						航班信息
					</div>
					<ul class='mr40 viewport_ls'>
						<li>
							<img src="/template/wap/images/pic/82.jpg">
							<div class="title">
								丽江古城
								<hr>
								<div class="clearfix">
									<span class='float-left'>含往返车费</span>
									<span class='float-right'><font class="c_orange">￥980</font>元起</span>
								</div>
							</div>
						</li>
						<li>
							<img src="/template/wap/images/pic/82.jpg">
							<div class="title">
								丽江古城
								<hr>
								<div class="clearfix">
									<span class='float-left'>含往返车费</span>
									<span class='float-right'><font class="c_orange">￥980</font>元起</span>
								</div>
							</div>
						</li>
						<li>
							<img src="/template/wap/images/pic/82.jpg">
							<div class="title">
								丽江古城
								<hr>
								<div class="clearfix">
									<span class='float-left'>含往返车费</span>
									<span class='float-right'><font class="c_orange">￥980</font>元起</span>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>