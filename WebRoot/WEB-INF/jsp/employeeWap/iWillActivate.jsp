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
					<form id="iWillActivateForm" name="iWillActivateForm">
					<input name="operationFlag" type="hidden" value="true"/>
					<div class="mr40">
						<div class="default_form">
							<div class="line clearfix">
								<div class="x3 text-right">
									渠道客户账号：
								</div>
								<div class="x9">
									<input name='channelCustomerInfoLoginId' id="channelCustomerInfoLoginId" 
									class="input d_input block" placeholder='请输入渠道客户账号'/>
								</div>
							</div>
						
							<div class="line clearfix">
								<div class="x12">
									<button id="searchUnusedCodeBtn" type="button" class="input d_input b_blue c_white fz26">
										提交
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="panel mr40 border10">
						<div class="panel-head fz22 padding-big-top padding-big-bottom">
							已购买服务码
							<a href="javascript:;" class='float-right fz22'>状态</a>
						</div>
						<div class="mr20 text-left" id="serviceCodeList">
							
						</div>
					</div>
					<div class="mr40">
						<div class="default_form">
							<div class="line clearfix">
								<div class="x4">
									身份证：
								</div>
								<div class="x5">
									<input name='idCard' id="idCard" class="input d_input block" placeholder='请输入身份证'/>
								</div>
							</div>
							<div class="line clearfix">
								<div class="x4">
									使用时间：
								</div>
								<div class="x5">
									<input name='useTime' id="useTime" type="datetime-local" class="input d_input block" />
								</div>
							</div>
							<div class="line clearfix">
								<div class="x4">
									航班号：
								</div>
								<div class="x5">
									<input name='flightNo'id="flightNo"  class="input d_input block" placeholder='请输入航班号'/>
								</div>
								<div class="x3 text-right">
									<button id="iWillActivateBtn" type="button"
										class="b_blue d_button button d_input b_blue c_white fz26">
										激活
									</button>
								</div>
							</div>
						</div>
					</div>

					<div class="mr40 c_red fz30 text-center" id="activateResult">
		
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="/template/employeeInfoWap/js/iWillActivate.js"></script>
	</body>
</html>