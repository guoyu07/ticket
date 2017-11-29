<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>配置销售任务比重</span>
					</div>
					<form action="/${actionName}_configTaskPercent.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
								<tr>
									<td class="text_align_right" width="150">
										签约数占比：
									</td>
									<td>
										<input id="signCount" name="signCount" class="my_input" datatype="n1-2"
										       value='<s:property value="saleTaskService.getSignCountPercent()"/>'/>
										<p class="Validform_checktip" style="display:inline;"> 请填写签约数占比</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										充值金额占比：
									</td>
									<td>
										<input id="recharge" name="recharge" class="my_input" datatype="n1-2"
										       value='<s:property value="saleTaskService.getRechargePercent()"/>'/>
										<p class="Validform_checktip" style="display:inline;"> 请填写充值金额占比</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										电话拜访量占比：
									</td>
									<td>
										<input id="phoneCount" name="phoneCount" class="my_input" datatype="n1-2"
										       value='<s:property value="saleTaskService.getPhoneCountPercent()"/>'/>
										<p class="Validform_checktip" style="display:inline;"> 请填写电话拜访量占比</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										外出拜访量占比：
									</td>
									<td>
										<input id="visitCount" name="visitCount" class="my_input" datatype="n1-2"
										       value='<s:property value="saleTaskService.getVisitCountPercent()"/>'/>
										<p class="Validform_checktip" style="display:inline;"> 请填写外出拜访量占比</p>       
									</td>
								</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="editLink" href="/${actionName}_edit.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_configTaskPercent.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>