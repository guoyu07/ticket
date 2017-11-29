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
								    <td width="100">员工姓名</td>
								    <td width="100">客户账号</td>
								    <td width="100">渠道</td>
								    <td width="100">充款时间</td>
								    <td width="100">充款金额</td>
								    <td width="100">充款方式</td>
								    <td width="100">开票金额</td>
								    <td width="100">开票时间</td>
								    <td width="100">未开票金额</td>
								  </tr>
								  <s:if test="list.size() != 0">
								  <s:iterator id="c" value="list" status="st">
								  	<tr>
								  		<td><s:property value="departmentInfoService.getByEmployee(#c.systemUser_id).getName()"/></td>
								  		<td><s:property value="employeeInfoService.get(@com.ticket.pojo.ChannelCustomerInfo@class, #c.channelCustomerInfoId).contact"/></td>
								  		<td><s:property value="employeeInfoService.get(@com.ticket.pojo.EmployeeInfo@class, #c.systemUser_id).getName()"/></td>
								  		<td><s:property value="departmentInfoService.get(@com.ticket.pojo.ChannelCustomerInfo@class, #c.channelCustomerInfoId).loginId"/></td>
								  		<td><s:property value="channelTypeService.getByChannelCustomer(#c.channelCustomerInfoId).getName()"/></td>
								  		<td><s:date name="#c.status.createTime" format="yyyy-MM-dd"></s:date></td>
								  		<td>${c.amountOfMoney }</td>
								  		<td>${c.payWay }</td>
								  		<td></td>
								  		<td></td>
								  		<td></td>
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