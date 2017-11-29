<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelCustomerInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >意向客户统计</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100" style="display: ">
							  <tr><td>总意向客户数</td><td colspan="2"><s:property value="channelCustomerInfoService.countTodayIntentionCustomer('site')"/></td></tr>
							  <tr><td>总客保数</td><td colspan="2"><s:property value="customerProtectLogService.countToday('site')"/></td></tr>
							  <tr class="text_align_left my_table_top"><td colspan="3">根据选定条件统计</td></tr>
								<tr>
									<td style="width: 120">开始时间
									<input onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="my_input" id="startTime" /></td>
									<td style="width: 80">截止时间<input onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="my_input" id="deadline" /><input type="hidden" id="department_id" value="${department_id }"/></td>
									<td style="width: 160"><input type="button" value="开始统计" id="statisticBtn" /></td>
								</tr>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="320">部门</td>
								    <td width="160">新增意向客户数</td>
								    <td width="80" colspan="2">新增客保数</td>
								  </tr>
								  <tbody id="statisticDetail" >
								  	<s:if test="department_id != null">
									  <ticket:systemCommon type="departmentInfoListByCurrentId" value="${department_id }"/>
									  <s:if test="#request.departmentInfoListByCurrentId != null">
										  	<s:iterator id="deptObj" value="#request.departmentInfoListByCurrentId">
											  <tr>
											  	<td>
											  		<ticket:systemCommon type="departmentInfoObj" value="${deptObj.id }"/>
										    		${departmentInfoObj}
											  	</td>
											  	<td><s:property value="channelCustomerInfoService.countIntentionByDept(#deptObj.id)"/></td>
											  	<td><s:property value="customerProtectLogService.countIntentionByDept(#deptObj.id)"/></td>
											  </tr>
										  	</s:iterator>
										</s:if>
									  <s:else>
									  	 <tr>
										    <td colspan="7">${noDataMessage }</td>
										  </tr>
									  </s:else>
								  	</s:if>
								  	<s:else>
									  <ticket:systemCommon type="departmentInfoList"/>
									  <s:if test="#request.departmentInfoList != null">
										  	<s:iterator id="deptObj" value="#request.departmentInfoList">
											  <tr>
											  	<td>
											  		<ticket:systemCommon type="departmentInfoObj" value="${deptObj.id }"/>
										    		${departmentInfoObj}
											  	</td>
											  	<td><s:property value="channelCustomerInfoService.countIntentionByDept(#deptObj.id)"/></td>
											  	<td><s:property value="customerProtectLogService.countIntentionByDept(#deptObj.id)"/></td>
											  </tr>
										  	</s:iterator>
										</s:if>
									  <s:else>
									  	 <tr>
										    <td colspan="7">${noDataMessage }</td>
										  </tr>
									  </s:else>
								  	</s:else>
								  </tbody>
								</table>
							</table>
					       </td>
					    </tr>
					  </table>
					  <%@ include file="customerPage.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>