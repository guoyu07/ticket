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
						<form action="/employeeInfoWap_employeeInfoZengLogAdd.action" name="memberStoreForm" id="memberStoreForm" method="POST" 
							alertTip="true" alertConfirm="true" redirectLink="/employeeInfoWap_employeeInfoZengLogAdd.action">
						<input name="operationFlag" type="hidden" value="true"/>
						<div class="default_form">
							<div class="line clearfix">
								<div class="x3 text-right">
									渠道客户：
								</div>
								<div class="x9">
									<select name='channelCustomerInfoLoginId' datatype="*1-40" class="input d_input block" >
										<s:iterator id="c" value="channelCustomerInfos">
											<option value="${c.id}">${c.name}</option>
										</s:iterator>
									</select>
								</div>
							</div>
							<div class="line">
								<div class="x3 text-right">
									销售数量：
								</div>
								<div class="x9">
									<input name='count' type="text" datatype="n" class="input d_input block" placeholder='请输入赠送数量'/>
								</div>
							</div>
							<!-- 
							<div class="line">
								<div class="x3 text-right">
									做赠送理由：
								</div>
								<div class="x9">
									<input name='remark' type="text" datatype="*1-250" class="input d_input block" placeholder='请输入赠送理由'/>
								</div>
							</div>
							 -->
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