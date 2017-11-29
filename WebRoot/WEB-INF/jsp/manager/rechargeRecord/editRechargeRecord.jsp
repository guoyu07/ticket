<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑充值记录</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											记录编号：
										</td>
										<td>
											<input id="recordNo" name="recordNo" class="my_input" datatype="*"
											       value="${rechargeRecord.recordNo}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写记录编号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											充值金额：
										</td>
										<td>
											<input id="amountOfMoney" name="amountOfMoney" class="my_input" datatype="*"
											       value="${rechargeRecord.amountOfMoney}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写充值金额</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											充值客户：
										</td>
										<td>
											<input id="channelCustomerInfoId" name="channelCustomerInfoId" class="my_input" datatype="*"
											       value="${rechargeRecord.channelCustomerInfoId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写充值客户</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付时间：
										</td>
										<td>
											<input id="payTime" name="payTime" class="my_input" datatype="*"
											       value="${rechargeRecord.payTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付方式：
										</td>
										<td>
											<input id="payWay" name="payWay" class="my_input" datatype="*"
											       value="${rechargeRecord.payWay}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付方式</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											回单号：
										</td>
										<td>
											<input id="receiptNo" name="receiptNo" class="my_input" datatype="*"
											       value="${rechargeRecord.receiptNo}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写回单号</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>