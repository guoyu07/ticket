<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 值机选座
						<a href="" class="FR">
						<ticket:common type="newMessages"/>
					<s:if test="#request.newMessages > 0">
						<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
					</s:if>
					<s:else>
						<i class="icon-bell"></i>
					</s:else>
						</a>
					</div>
				</div>
				<div class="mobile-main">
					<div class="c_content">
						<h5 class="fz22 c_grey padding-large-bottom">
							您查询的航班号有：
						</h5>
						<div class="line">
							<div class="x6 text-center">
								<label class='fz22'>
									<i class='icon-check-square c_blue'></i>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" style='display: none' name="" value="">
									MU348
								</label>
							</div>
							<div class="x1">
								|
							</div>
							<div class="x5 text-center">
								<label class='fz22'>
									<i class='icon-check-square c_blue'></i>&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="checkbox" style='display: none' name="" value="">
									MU348
								</label>
							</div>
						</div>
					</div>
					<div class="c_content">
						<h5 class="fz22 c_grey padding-large-bottom">
							请选择座位：
						</h5>
						<p>
							<img src="../images/pic/77.jpg">
						</p>
					</div>
					<div class="tit_tab">
						<a href="#" class="button">提交</a>
					</div>
					<%@ include file="../common/quickNav.jsp" %>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>
