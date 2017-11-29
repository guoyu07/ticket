<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="VIP会员" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="c_content text-center">
						<h1 class="c_black">
							VIP会员与规则
						</h1>
						<p class="text-big text-left padding-big-top fz22">
							会员积分计划规则
							如果您使用机场APP，您就参加了会员积分计划，您须接受并遵守我们会员积分计划的条款和条件，会员积分计划的最终解释权归本APP所有。
						</p>
					</div>
					<div class="c_content clearfix">
						<span class='float-left'><i class='my_blue_head'></i></span>
						<span class='text-dot float-right' style='font-size: 40px;'>
							VIP1
							<p style='font-size: 18px;color:#757575'>
								目前我的等级
							</p>
						</span>
					</div>
					<div class="c_content text-center" style='font-size: 22px;'>
						可升级服务推送
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