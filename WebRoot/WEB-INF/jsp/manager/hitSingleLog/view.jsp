<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/hitSingleLog.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>查看撞单日志</span>
					</div>
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									客户及合同信息：
								</td>
								<td>
									客户信息<br/>
									客户名称:${channelCustomerInfo.name}<br/>
									渠道分类:<ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfo.channelTypeId}"/>
									${channelTypeObj.name }<br/>
									客户所属员工:
									<ticket:systemCommon type="employeeInfoObj" value="${channelCustomerInfo.employeeInfo_id}"/>
									${employeeInfoObj.name}<br/>
									合同信息<br/>
									<s:if test="agreement != null">
									合同编号:${agreement.name}<br/>
									合同客户:<ticket:systemCommon type="channelCustomerInfoObj" value="${agreement.channelCustomerInfo_id}"/>
									${channelCustomerInfoObj.name }<br/>
									是否有效:<s:if test="agreement.effective == 0">无效</s:if><s:else>有效</s:else><br/>
									合同状态:
									<s:if test="agreement.agreementState == 0">
									合同未审批
									</s:if>
									<s:if test="agreement.agreementState == 1">
									合同审批通过
									</s:if>
									<s:if test="agreement.agreementState == 2">
									合同审批未通过
									</s:if>
									<s:if test="agreement.agreementState == 3">
									合同已签回,未发放合同
									</s:if>
									<s:if test="agreement.agreementState == 4">
									合同已签回并发放合同
									</s:if>
									<s:if test="agreement.agreementState == 5">
									合同已归档
									</s:if>
									<br/>
									签约金额:${agreement.firstRenewMoney}元<br/>
									签约时间:<s:date name="agreement.signingDate" format="yyyy-MM-dd"/><br/>
									</s:if>
									<s:else>
									无合同信息
									</s:else>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									审核状态：
								</td>
								<td>
									<s:if test="hitSingleLog.state == 0">未审批</s:if>
									<s:if test="hitSingleLog.state == 1">通过</s:if>
									<s:if test="hitSingleLog.state == 2">未通过</s:if>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									审批意见：
								</td>
								<td>
									${hitSingleLog.remark}
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>