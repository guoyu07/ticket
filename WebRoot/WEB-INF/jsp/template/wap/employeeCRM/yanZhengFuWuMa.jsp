<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						服务码
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						服务码
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr40">
						<div class="qr_box border"
							style="width: 560px; height: 400px; margin: 0 auto; border: 1px solid #344">
							<p class='t_c' style="padding-top: 70px;">
								<img src="../images/success_icon.png" height="194" width="288">
							</p>
						</div>
					</div>
					<div class="tit_tab">
						<a href="#" class="b_yello c_grey">验证记录</a>
						<a href="#" class="b_yello c_grey">个人中心</a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>