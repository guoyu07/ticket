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
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >拜访记录审核管理</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<select class="my_select" id="queryByAuditState" name="queryByAuditState">
					      		<option value="">请选择</option>
					      		<option value="0">回访未通过</option>
					      		<option value="1">回访已通过</option>
					      	</select>
					      	<input type="hidden" name="phoneOrOut" id="phoneOrOut" value="${phoneOrOut }"/>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">审核时间</td>
								    <td width="80">拜访员工</td>
								    <td width="80">公司名称</td>
								    <td width="80">拜访时间</td>
								    <td width="80">审核人</td>
								    <td width="80">审核状态</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
								  			<s:if test="#c.phoneOrOut=='phone'">
								  				<s:set var="phoneVisitObj" value="phoneVisitService.queryById('PhoneVisit',#c.record_id)"></s:set>
								  				 <td>
								  				 	<ticket:systemCommon type="employeeInfoObj" value="${phoneVisitObj.employee_id }"/>
									    			${employeeInfoObj.name}
								  				 </td>
										    	 <td>
										    	 	<ticket:systemCommon type="channelCustomerInfoObj" value="${phoneVisitObj.customer_id }"/>
									    			${channelCustomerInfoObj.name }
										    	 </td>
										    	 <td>
										    	 	<s:date name="#phoneVisitObj.visitDate" format="yyyy-MM-dd"/>
										    	 </td>
								  			</s:if>
								  			<s:else>
								  				<s:set var="outVisitObj" value="outVisitService.queryById('EmployeeOutVisit',#c.record_id)"></s:set>
								  				<td>
								  				 	<ticket:systemCommon type="employeeInfoObj" value="${outVisitObj.employee_id }"/>
									    			${employeeInfoObj.name}
								  				 </td>
										    	 <td>
										    	 	<ticket:systemCommon type="channelCustomerInfoObj" value="${outVisitObj.customer_id }"/>
									    			${channelCustomerInfoObj.name }
										    	 </td>
										    	 <td>
										    	 	<s:date name="#outVisitObj.visitDate" format="yyyy-MM-dd"/>
										    	 </td>
								  			</s:else>
										    <td>
										    	<ticket:systemCommon type="employeeInfoObj" value="${c.employee_id }"/>
									    			${employeeInfoObj.name}
										    	</td>
										    <td>
										    	<s:if test="#c.status.audit==1">通过</s:if>
										    	<s:else>未通过</s:else>
										    </td>
										    <td><span>
										    	<a href="/${actionName}_showDetail.action?id=${c.id }&versionFlag=${versionFlag}&record_id=${c.record_id }&phoneOrOut=${c.phoneOrOut}">查看详情</a>
										    	</span></td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="7">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
					        </td>
					    </tr>
					  </table>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>