<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="自由行套餐" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="order_ls">
						<ul>
							<li class="c_content line">
								<div class="clearfix">
									<div class="x5">
										<img src="/template/wap/images/pic/81.jpg">
									</div>
									<div class="x7">
										<h4 class='fz20 padding-bottom c_grey'>
											昆明出发 度假特价、好礼相送
										</h4>
										<p class='fz18 padding-bottom'>
											丽江丽人居客栈位于七一街兴文巷， 紧邻主街，步行进入古城约4分钟 即可到达客栈门口。
										</p>
										<p class='fz18 padding-bottom'>
											价格：
											<span class='c_red'>￥480</span>
										</p>
										<p class='fz18'>
											服务提供商：云南光大
										</p>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<div class="c_content">

						<div class="">
							<img src="/template/wap/images/pic/84.jpg">
						</div>
					</div>
					<div class="tit_tab">
						<a href="">咨询</a>
						<a href="">预订</a>
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