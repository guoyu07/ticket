<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/receipt.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑收据发票</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											(收据名称)付款客户：
										</td>
										<td>
											<select name="channelCustomerInfoId" id="channelCustomerInfoId" class="my_select" ignore="ignore" datatype="*" >
												<s:iterator id="channelCustomerInfo" value="channelCustomerInfos">
													<option <s:if test="#channelCustomerInfo.id == receipt.channelCustomerInfoId">selected=</s:if> value="${channelCustomerInfo.id }">${channelCustomerInfo.name}</option>
												</s:iterator>
											</select>
										    <p class="Validform_checktip" style="display:inline;"> 请选择付款客户</p>      
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											申请时间：
										</td>
										<td>
											<input onclick="WdatePicker();" id="issueTime" name="issueTime" class="my_input" datatype="*"
											       value="<s:date name="receipt.issueTime" format="yyyy-MM-dd"/>"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写开具时间</p>       
										</td>
									</tr>
									
									<tr>
										<td class="text_align_right" width="150">
											申请金额：
										</td>
										<td>
											<input id="amountOfMoney" name="amountOfMoney" class="my_input" datatype="*"
											       value="${receipt.amountOfMoney}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写开具金额</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											大写金额：
										</td>
										<td>
											<input readonly id="money" name="money" style="width:400px;" value="${receipt.money}" class="my_input"/>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											申请人：
										</td>
										<td>
												<ticket:systemCommon type="employeeInfoObj" value="${receipt.employeeInfoId }"/>
											    ${employeeInfoObj.name }
											    <input type="hidden" name="employeeInfoId" value="${employeeInfoObj.id}"/>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											申请类型：
										</td>
										<td>
											<select name="applyClass_id" id="applyClass_id" class="my_select" ignore="ignore" datatype="*" >
											<s:iterator id="a" value="applayClasses">
												<option <s:if test="#a.id == receipt.applyClass_id">selected</s:if> value="${a.id}">${a.name}</option>
											</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请填写申请类型</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											备注：
										</td>
										<td>
											<input id="remarks" name="remarks" class="my_input"
											       value="${receipt.remarks}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>       
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