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
						<form action="/employeeInfoWap_channelCustomerInfoAdd.action" name="memberStoreForm" id="memberStoreForm" method="POST" 
							alertTip="true" alertConfirm="true" redirectLink="/employeeInfoWap_channelCustomerInfoAdd.action">
						<input name="operationFlag" type="hidden" value="true"/>
						<div class="default_form">
							<div class="line clearfix">
								<div class="x3 text-right">
									客户名称：
								</div>
								<div class="x9">
									<input name='name' datatype="*1-40" class="input d_input block" placeholder='请输入客户名称'>
								</div>
							</div>
							<div class="line">
								<div class="x3 text-right">
									客户账号：
								</div>
								<div class="x9">
									<input name='loginId' datatype="*1-40" class="input d_input block" placeholder='请输入客户名称'>
								</div>
							</div>
							<div class="line">
								<div class="x3 text-right">
									登录密码：
								</div>
								<div class="x9">
									<input name='password' type="password" datatype="*1-40" class="input d_input block" placeholder='请输入客户名称'>
								</div>
							</div>
							<div class="line">
								<div class="x3 text-right">
									联系人：
								</div>
								<div class="x9">
									<input name='contact' type="text" datatype="*1-40" class="input d_input block" placeholder='请输入客户名称'>
								</div>
							</div>
							<div class="line">
								<div class="x3 text-right">
									联系人电话：
								</div>
								<div class="x9">
									<input name='contactPhone' type="text" datatype="m" class="input d_input block" placeholder='请输入客户名称'>
								</div>
							</div>
							<div class="line clearfix">
								<div class="x3"></div>
								<div class="x9">
									<button id="memberStore_submit_btn" class="input d_input b_blue c_white fz26">
										提交
									</button>
								</div>
							</div>
						</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="/template/employeeInfoWap/js/validate.js"></script>
	</body>
</html>