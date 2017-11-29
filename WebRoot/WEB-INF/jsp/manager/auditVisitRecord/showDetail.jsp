<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/auditVisitRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>查看回访结果</span>
					</div>
					<form action="#">
						<table class="my_table100 margin_top10">
							<s:if test="phoneOrOut == 'phone'">
								<tr>
									<td class="text_align_right" width="150">
										出访人：
									</td>
									<td>
										<ticket:systemCommon type="employeeInfoObj" value="${phoneVisit.employee_id}"/>
										${employeeInfoObj.name}
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										拜访日期：
									</td>
									<td>
										<s:date name="phoneVisit.visitDate" format="yyyy-MM-dd HH:mm:ss"/>       
									</td>
								</tr>
							
								<tr>
									<td class="text_align_right" width="150">
										公司名称：
									</td>
									<td>
										<ticket:systemCommon type="channelCustomerInfoObj" value="${phoneVisit.customer_id }"/>
										   ${channelCustomerInfoObj.name }
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人：
									</td>
									<td>
										${phoneVisit.contact}      
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系电话：
									</td>
									<td>
										${phoneVisit.contactPhone}
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										沟通内容：
									</td>
									<td>
										${phoneVisit.content}
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td class="text_align_right" width="150">
										出访人：
									</td>
									<td>
										<ticket:systemCommon type="employeeInfoObj" value="${employeeOutVisit.employee_id}"/>
										${employeeInfoObj.name}
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										拜访日期：
									</td>
									<td>
										<s:date name="employeeOutVisit.visitDate" format="yyyy-MM-dd"/>    
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										公司名称：
									</td>
									<td>
										<ticket:systemCommon type="channelCustomerInfoObj" value="${employeeOutVisit.customer_id }"/>
										   ${channelCustomerInfoObj.name }
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人：
									</td>
									<td>
										${employeeOutVisit.contact}   
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系电话：
									</td>
									<td>
										${employeeOutVisit.contactPhone}      
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										拜访目的：
									</td>
									<td>
										${employeeOutVisit.visitPurpose}    
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										陪同人：
									</td>
									<td>
										${employeeOutVisit.accompanyPerPerson}      
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										出发时间：
									</td>
									<td>
										<s:date name="employeeOutVisit.setoutTime" format="yyyy-MM-dd HH:mm:ss"/>     
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										返回时间：
									</td>
									<td>
										<s:date name="employeeOutVisit.backTime" format="yyyy-MM-dd HH:mm:ss"/>      
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										拜访结果：
									</td>
									<td>
										${employeeOutVisit.visitResult }
									</td>
								</tr>
							</s:else>
							<tr>
								<td class="text_align_right" width="150">
									回访结果：
								</td>
								<td>
									<textarea style="width: 480px;height: 280px;resize: none;font-family: 宋体;font-size: 14" readonly="readonly" >${auditVisitRecord.remark}
									</textarea>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input onclick="javascript:history.go(-1);" type="button" value="返回" class="btn_submit">
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>