<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/employeeOutVisit.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>外出拜访记录详情</span>
					</div>
						<table class="my_table100 margin_top10">
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
						</table>
						<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >该拜访记录的历史备注</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							    <td width="80">备注人</td>
							    <td width="80">备注时间</td>
							    <td width="80">备注内容</td>
							  </tr>
							  <s:if test="remarkVisitRecordList != null && remarkVisitRecordList.size() !=0">
							  	<s:iterator id="remarkRecord" value="remarkVisitRecordList">
							  		<tr>
							  			<td>
									    	<ticket:systemCommon type="employeeInfoObj" value="${remarkRecord.employee_id }"/>
									    		${employeeInfoObj.name}
									    </td>
									    <td><s:date name="#remarkRecord.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									    <td>
									    	${remarkRecord.remark }
									    </td>
									</tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="10">该拜访记录暂无相关备注</td>
								 </tr>
							  </s:else>
							</table>
					       </td>
					    </tr>
					  </table>
						<div class="my_table_list_r">
						    <input id="returnBtn" type="button" value="返回" class="btn_submit">
						</div>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>