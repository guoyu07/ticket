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
						<span>收据发票详情</span>
					</div>
						<table class="my_table100 margin_top10">
									
									<tr>
										<td class="text_align_right" width="150">
											收据名称：
										</td>
										<td>
											${receipt.name}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											开具时间：
										</td>
										<td>
											${receipt.issueTime}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											付款客户：
										</td>
										<td>
										<ticket:systemCommon type="channelCustomerInfoObj" value="${receipt.channelCustomerInfoId }"/>
											${channelCustomerInfoObj.name }
											     
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											开具金额：
										</td>
										<td>
											${receipt.amountOfMoney}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											申请人：
										</td>
										<td>
												<ticket:systemCommon type="employeeInfoObj" value="${receipt.employeeInfoId }"/>
											    ${employeeInfoObj.name }
											    
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											申请类型：
										</td>
										<td>
											<s:if test="receipt.type==0">
										    	预开申请
										    </s:if>
										    <s:else>
										    	到账申请
										    </s:else>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											备注：
										</td>
										<td>
											${receipt.remarks}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											收据编号：
										</td>
										<td>
											${receipt.receiptNo}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											审核备注：
										</td>
										<td>
											${receipt.auditRemarks}
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_detail.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>