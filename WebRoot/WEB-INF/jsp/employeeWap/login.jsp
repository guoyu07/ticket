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
				<div class="mobile-main"><div align="center"> 
 
					</div><div class="order_form mr40"><div align="center"> 
						</div><form action="/employeeInfoWap_login.action" name="memberStoreForm" id="memberStoreForm" method="POST" 
							alertConfirm="false" redirectLink="/employeeInfoWap_index.action"><div align="center"> 
							<input type="hidden" name="operationFlag" value="true"> 
							</div><div class="line clearfix margin-large-bottom">
								<div class="x3 height-large fz30 text-right">
									用户名：
								</div>
								<div class="x9">
									<input name="loginId" id="loginId" datatype="*4-40" type="text" class="input d_input block"
										placeholder='请输入用户名'/>
								</div>
							</div>
							<div class="line clearfix margin-large-bottom">
								<div class="x3 height-large fz30 text-right">
									密码：
								</div>
								<div class="x9">
									<input type="password"  name="password" id="password"　 datatype="*4-40"
										class="input d_input" placeholder='请输入登录密码' />
								</div>
							</div>
							<button type="button" id="memberStore_submit_btn" class='button d_button b_blue c_white'
								style="width: 200px; margin: 0 auto;">
								登录
							</button>
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