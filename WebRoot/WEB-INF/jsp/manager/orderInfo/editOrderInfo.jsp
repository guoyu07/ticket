<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/orderInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑订单数据</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											订单号：
										</td>
										<td>
											<input id="orderId" name="orderId" class="my_input" datatype="*"
											       value="${orderInfo.orderId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写订单号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品名称：
										</td>
										<td>
											<input id="goodsId" name="goodsId" class="my_input" datatype="*"
											       value="${orderInfo.goodsId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品名称</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											商品售价：
										</td>
										<td>
											<input id="goodsPrice" name="goodsPrice" class="my_input" datatype="*"
											       value="${orderInfo.goodsPrice}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写商品售价</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											购买数量：
										</td>
										<td>
											<input id="buyCount" name="buyCount" class="my_input" datatype="*"
											       value="${orderInfo.buyCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写购买数量</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付方式：
										</td>
										<td>
											<input id="payWay" name="payWay" class="my_input" datatype="*"
											       value="${orderInfo.payWay}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付方式</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付时间：
										</td>
										<td>
											<input id="payTime" name="payTime" class="my_input" datatype="*"
											       value="${orderInfo.payTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付账号：
										</td>
										<td>
											<input id="payAccount" name="payAccount" class="my_input" datatype="*"
											       value="${orderInfo.payAccount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付账号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											下单账号：
										</td>
										<td>
											<input id="orderAccount" name="orderAccount" class="my_input" datatype="*"
											       value="${orderInfo.orderAccount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写下单账号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付状态：
										</td>
										<td>
											<input id="payStatus" name="payStatus" class="my_input" datatype="*"
											       value="${orderInfo.payStatus}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写支付状态</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											实付金额：
										</td>
										<td>
											<input id="paidAmount" name="paidAmount" class="my_input" datatype="*"
											       value="${orderInfo.paidAmount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写实付金额</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											优惠金额：
										</td>
										<td>
											<input id="discountAmount" name="discountAmount" class="my_input" datatype="*"
											       value="${orderInfo.discountAmount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写优惠金额</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											消耗积分：
										</td>
										<td>
											<input id="useIntegral" name="useIntegral" class="my_input" datatype="*"
											       value="${orderInfo.useIntegral}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写消耗积分</p>       
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