<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>购买服务码</span>
					</div>
					<form action="/channelCustomerAdmin_buyBjdjServiceCode.action" id="commonForm" name="commonForm"
					>
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									便捷登机服务码价格：
								</td>
								<td>
									<ticket:systemCommon type="getSystemDictionatyObjByName" value="bjdj_price"/>
									${getSystemDictionatyObjByName.value}元
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									购买数量：
								</td>
								<td>
									<input id="buyCount" name="buyCount" class="my_input" datatype="n"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入购买数量</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									支付方式：
								</td>
								<td>
									<input id="payWay" name="payWay" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写支付方式</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_l">
						    <input id="submitBtn" type="submit" value="确认购买" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/channelCustomerAdmin_buyBjdjServiceCode.action"></a>
						    <a id="manageLink" href="/channelCustomerAdmin_manageOrderInfo.action"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>