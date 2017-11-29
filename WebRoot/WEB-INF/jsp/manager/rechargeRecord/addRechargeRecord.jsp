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
						<span>新增充值记录</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									输入客户名或客户用户名：
								</td>
								<td>
									<input type="text" name="keyword" id="searchKeyword" class="my_input"/>
									<input type="button" id="searchKeywordBtn" value="搜索"/>
									<select name="channelCustomerInfoId" id="channelCustomerInfoId" class="my_select" ignore="ignore" datatype="*" >
										<s:iterator id="channelCustomerInfo" value="channelCustomerInfos">
											<option value="${channelCustomerInfo.id }">${channelCustomerInfo.name}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									充值金额：
								</td>
								<td>
									<input id="amountOfMoney" name="amountOfMoney" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写充值金额</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									支付方式：
								</td>
								<td>
									<select id="payWay" name="payWay" class="my_input">
										<s:iterator var="way" value="#payWay">
											<option>${way.name }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写支付方式</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									优惠政策：
									<input type="hidden" id="chongzhibi" value="0" />
									<input type="hidden" id="zuidijine" value="0" />
								</td>
								<td id="showDetail">
									
								</td>
							</tr>
						</table>
						<div class="my_table_list_l">
						    <input id="submitBtn" type="submit" value="确认充值" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=site"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=site"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>