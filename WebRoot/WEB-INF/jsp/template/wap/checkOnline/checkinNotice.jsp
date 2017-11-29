<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="c_content text-center">
						<img src="/template/wap/images/zjxz.jpg">
					</div>
					<div class="c_content text-center b_blue fz20 c_white">
						选座成功后，可直接使用电子登机牌，如
						<br>
						不支持电子登机牌，需要到机场打印
					</div>
					<div class="c_content text-left">
						<dl class='border-bottom padding-big-bottom'>
							<dt class='fz24 c_grey padding-big-bottom'>
								<i class='zjxz_warn'></i>取消选座注意事项
							</dt>
							<dd class='fz18 c_l_grey' style='padding-left: 50px;'>
								需要选座时间最晚不晚于航班起飞前2小时 如遇机场控制等情况下不能取消手机选的 可前往机场柜台办理或联系航空公司
							</dd>
						</dl>
						<dl class='border-bottom padding-big-bottom padding-big-top'>
							<dt class='fz24 c_grey'>
								<i class='zjxz_ok'></i>我已阅读<a href="/airport_checkinServiceNotice.action">乘机手续服务旅客须知</a>
							</dt>
						</dl>
						<dl class='padding-big-bottom padding-big-top'>
							<dt class='fz24 c_grey'>
								<i class='zjxz_ok'></i>我已阅读值机危险品须知
							</dt>
						</dl>
					</div>
					<div class="tit b_blue">
						<a href="#" class="fz36 c_white">下一步</a>
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