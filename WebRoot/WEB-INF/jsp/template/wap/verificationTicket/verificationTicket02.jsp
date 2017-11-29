<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="机票验真"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="tit b_blue c_white">
						恭喜您！您购买的电子票真实有效
					</div>
					<div class="mr40">
						<table class="table table-bordered">
							<tr>
								<td class="fz18" width="150">
									电子客票票号
								</td>
								<td class="fz18"></td>
							</tr>
							<tr>
								<td class="fz18">
									购票航空公司
								</td>
								<td class="fz18"></td>
							</tr>
							<tr>
								<td class="fz18">
									乘机旅客姓名
								</td>
								<td class="fz18"></td>
							</tr>
							<tr>
								<td class="fz18">
									身份证/证件号
								</td>
								<td class="fz18"></td>
							</tr>
							<tr>
								<td class="fz18">
									订座记录编号
								</td>
								<td class="fz18"></td>
							</tr>
						</table>
						<table class="table table-bordered">
							<tr>
								<table class="table table-bordered">
									<tr>
										<td class="fz18">
											第一航段
										</td>
										<td class='fz18' width="150">
											第客票已使用
										</td>
									</tr>
								</table>
							</tr>
							<tr>
								<table class="table table-bordered">
									<tr>
										<td class="fz18">
											航空公司
										</td>
										<td class="fz18">
											航班号
										</td>
										<td class="fz18">
											起飞机场
										</td>
										<td class="fz18">
											起飞时间
										</td>
										<td class="fz18">
											到达机场
										</td>
										<td class="fz18">
											到达时间
										</td>
										<td class="fz18">
											舱位
										</td>
									</tr>
									<tr>
										<td class="fz18"></td>
										<td class="fz18"></td>
										<td class="fz18"></td>
										<td class="fz18"></td>
										<td class="fz18"></td>
										<td class="fz18"></td>
										<td class="fz18"></td>
									</tr>
								</table>
							</tr>
							<tr>
								<table class="table table-bordered">
									<tr>
										<td class="fz18" width="150">
											使用限制
										</td>
										<td class="fz18">
											不得签转更改退票收费
										</td>
									</tr>
								</table>
							</tr>
						</table>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>