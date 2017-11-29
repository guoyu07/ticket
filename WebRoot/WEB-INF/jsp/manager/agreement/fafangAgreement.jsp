<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/agreement.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>发放客户合同</span>
					</div>
					<form action="/${actionName}_fafang.action" id="commonForm"
						name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag"
							value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag"
							value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<input type="hidden" name="name" id="name" value="${agreement.name} " />
						<input type="hidden" name="applayClassId" id="applayClassId" value="${agreement.applayClassId}" />
						<input type="hidden" name="applyDate" id="applyDate" value="${agreement.applyDate}" />
						<input type="hidden" name="content" id="content" value="${agreement.content}" />
						<input type="hidden" name="employeeInfo_id" id="employeeInfo_id" value="${agreement.employeeInfo_id2 }"/>
						
						<table class="my_table100 margin_top10">
						<tr>
								<tr>
								<td class="text_align_right" width="150">
									合同编号：
								</td>
								<td>
									${agreement.name} 
									
								</td>
							</tr>
						
							<tr>
								<td class="text_align_right" width="150">
									申请类别：
								</td>
								<td>
								    	<ticket:systemCommon type="applayClassObj" value="${agreement.applayClassId }"/>
									    	 ${applayClassObj.name }
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									申请员工：
								</td>
								<td>
								
								    <ticket:systemCommon type="employeeInfoObj" value="${agreement.employeeInfo_id2 }"/>
									    		${employeeInfoObj.name}
								
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									客户名称：
								</td>
								<td>
								
								  <ticket:systemCommon type="channelCustomerInfoObj" value="${agreement.channelCustomerInfo_id}"/>
									${channelCustomerInfoObj.name }
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									申请时间：
								</td>
								<td>
								<s:date name="agreement.applyDate" format="yyyy-MM-dd HH:mm:ss"/>
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									${agreement.content}
									
								</td>
						    </tr>
						    <tr>
								<td class="text_align_right" width="150">
									审核日期：
								</td>
								<td>
									<s:date name="agreement.auditDate" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						   <tr>
								<td class="text_align_right" width="150">
									签订日期：
								</td>
								<td>
									<s:date name="agreement.signingDate" format="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									签订金额：
								</td>
								<td>
									${agreement.firstRenewMoney}"
								</td>
							</tr>
							
							<tr>
								<td class="text_align_right" width="150">
									支付方式：
								</td>
								<td>
									${agreement.payWay}
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									合同副本：
								</td>
								<td>
									<ticket:commonAnnex type="queryAnnexList" entityUuid="${agreement.id}" annexType="2" size="1"/>
									<s:if test="#request.queryAnnexList != null && #request.queryAnnexList.size > 0">
										<a href="/downloadAnnex.action?id=${queryAnnexList[0].id}">合同副本文件下载</a>
									</s:if>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									签回备注：
								</td>
								<td>
									${agreement.qhRemark}
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									审核状态：
								</td>
								<td>
									<select name="secondAuditState">
										<option <s:if test="agreement.secondAuditState == 1">selected</s:if> value="1">通过</option>
										<option <s:if test="agreement.secondAuditState == 0">selected</s:if> value="0">未通过</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									未批复原因：
								</td>
								<td>
									<textarea id="secondAuditRemark" name="secondAuditRemark" class="my_input"  style="width: 19%;height: 100px;">${agreement.secondAuditRemark}</textarea>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
							<input id="returnBtn" type="button" value="返回"
								class="btn_remove margin_left20">
							<a id="addLink"
								href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
							<a id="manageLink"
								href="/${actionName}_manageQh.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>