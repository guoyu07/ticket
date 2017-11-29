<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<div class="header">
						销售后台页面
					</div>
				</div>
				<div class="mobile-main">
					<%@ include file="top.jsp"%>
					<div class="mr40">
						<hr>
						<form id="agreementForm" name="agreementForm">
						<input type="hidden" name="operationFlag" value="true"/>
						<div class="default_form">
							<div class="line clearfix">
								<div class="x12">
									请输入领取信息：
								</div>
							</div>
							<div class="line clearfix">
								<div class="x3 text-center">
									公司名称：
								</div>
								<div class="x9">
									<input name='name' id="name" class="input d_input block" placeholder='请输入公司名称'/>
								</div>
							</div>
							<div class="line clearfix">
								<div class="x3 text-center">
									事由：
								</div>
								<div class="x9">
									<input name='remark' id="remark" class="input d_input block" placeholder='事由'/>
								</div>
							</div>
							<div class="line clearfix">
								<div class="x3"></div>
								<div class="x4">
									<button type="button" id="applyAgreemrntBtn" class="input d_input b_blue c_white fz26">
										申领
									</button>
								</div>
								<div class="x1"></div>
								<div class="x4">
									<button type="button"  id="getAgreemrntListBtn" class="input d_input b_blue c_white fz26">
										查询
									</button>
								</div>
							</div>
						</div>
						</form>
						
					</div>
					<div class="mr40">
						<hr>
						<div class="panel no-border">

							<div class="mr20 text-left" id="getAgreementList">
								
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="/template/employeeInfoWap/js/getAgreement.js"></script>
	</body>
</html>