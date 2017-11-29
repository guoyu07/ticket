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
						<form action="/employeeInfoWap_batchGetServiceCode.action" name="memberStoreForm" id="memberStoreForm" method="POST" 
							alertTip="true" alertConfirm="true" redirectLink="/employeeInfoWap_batchGetServiceCode.action">
						<input name="operationFlag" type="hidden" value="true"/>
						<div class="default_form">
							<div class="line">
								<div class="x4">
									输入领取数量
								</div>
								<div class="x9">
									<input name='count' id="count" datatype="n" class="input d_input block" placeholder='请输入领取数量'/>
								</div>
							</div>
							<div class="line clearfix">
								<div class="x12">
									<button id="memberStore_submit_btn" class="input d_input b_blue c_white fz26">
										提交
									</button>
								</div>
							</div>
						</div>
						</form>
					</div>
					<div class="panel mr40 border10">
						<div class="mr20 text-left">
							<s:iterator id="c" value="orderInfoDetails" status="st">
							<ticket:systemCommon type="bjdjServiceCodeObj" value="${c.bjdjServiceCode_id}"/>
							<p class='fz22 <s:if test="#st.first">clearfix</s:if>'>
								${bjdjServiceCodeObj.code}
								<span class='float-right '>
									<a href="javascript:;" class='float-right fz22'>${bjdjServiceCodeObj.state.value}</a>
									<!-- 
									<a href="javascript:;"
									class="button b_blue c_white fz22">复制</a><a href="javascript:;"
									class="button b_blue c_white fz22">打印</a>
									 -->
								</span>
							</p>
							<s:if test="!#st.last">
							<br/>
							</s:if>
							</s:iterator>
						</div>
					</div>
					<div class="mr40">
						
						<div class="default_form">
						
							<form id="bindMemberForm" name="bindMemberForm">
							<div class="line clearfix">
								<div class="x3 text-right">
									昵称：
								</div>
								<div class="x6">
									<input name='name' id="name" class="input d_input block" placeholder='输入昵称'/>
								</div>
								<br/>
								<div class="x3 text-right">
									手机号码：
								</div>
								<div class="x6">
									<input name='mobile' id="mobile" class="input d_input block" placeholder='输入手机号码'/>
								</div>
								<div class="x3 text-right">
									<button id="memberBindServiceCodeBtn"
										class="b_blue d_button button d_input b_blue c_white fz26">
										发送
									</button>
								</div>
							</div>
							</form>
							
						</div>
						
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="/template/employeeInfoWap/js/validate.js"></script>
		<script type="text/javascript" src="/template/employeeInfoWap/js/batchGetServiceCode.js"></script>
	</body>
</html>