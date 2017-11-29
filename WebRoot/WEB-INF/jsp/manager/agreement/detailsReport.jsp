<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >合同汇总表</td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="100">部门</td>
								    <td width="100">销售账号</td>
								    <td width="100">申请时间</td>	
								    <td width="100">申请员工</td>
								    <td width="100">申请合同类型</td>
								    <td width="100">渠道</td>
								    <td width="100">客户账号</td>
								    <td width="100">公司名称</td>
								    <td width="100">审批时间</td>
								    <td width="100">审批账号</td>
								    <td width="100">合同编号</td>
								    <td width="100">发放时间</td>
								    <td width="100">合同签回时间</td>
								    <td width="100">签约金额</td>
								    <td width="100">归档日期</td>
								  </tr>
								  <s:if test="list.size() != 0">
								  <s:iterator id="c" value="list" status="st">
								  	<tr>
								  		<td><s:property value="departmentInfoService.getByEmployee(#c.employeeInfo_id2).getName()"/></td>
								  		<td><s:property value="employeeInfoService.get(@com.ticket.pojo.EmployeeInfo@class, #c.employeeInfo_id2).getName()"/></td>
								  		<td><s:date name="#c.applyDate" format="yyyy-MM-dd"></s:date></td>
								  		<td><s:property value="employeeInfoService.get(@com.ticket.pojo.EmployeeInfo@class, #c.employeeInfo_id2).getName()"/></td>
								  		<td><s:property value="departmentInfoService.get(@com.ticket.pojo.ApplayClass@class, #c.applayClassId).getName()"/></td>
								  		<td><s:property value="channelTypeService.getByChannelCustomer(#c.channelCustomerInfo_id).getName()"/></td>
								  		<td><s:property value="employeeInfoService.get(@com.ticket.pojo.ChannelCustomerInfo@class, #c.channelCustomerInfo_id).getLoginId()"/></td>
								  		<td><s:property value="employeeInfoService.get(@com.ticket.pojo.ChannelCustomerInfo@class, #c.channelCustomerInfo_id).getContact()"/></td>
								  		<td><s:date name="#c.auditDate" format="yyyy-MM-dd"></s:date></td>
								  		<td>${c.auditEmployee.name }</td>
								  		<td>${c.name }</td>
								  		<td><s:date name="#c.returnDate" format="yyyy-MM-dd"></s:date></td>
								  		<td><s:date name="#c.signingDate" format="yyyy-MM-dd"></s:date></td>
								  		<td>${c.firstRenewMoney }</td>
								  		<td><s:date name="#c.receiveDate" format="yyyy-MM-dd"></s:date></td>
									</tr>
								  </s:iterator>
								  </s:if>
								  <s:else>
								  	<tr>
								  		<td colspan="100">未查询到数据</td>
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