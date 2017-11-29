<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td>销售CRM-客户列表</td>
					    <tr>
					      <td>
							<table id="table" class="my_table100">
							  <tr class="my_table_top1">
							    <td width="100">客户账号</td>
							    <td width="100">公司名称</td>
							    <td width="100">部门</td>
							    <td width="100">销售账号</td>
							    <td width="100">渠道</td>
							    <td width="100">联系人</td>
							    <td width="100">联系电话</td>
							    <td width="100">开户日期</td>
							    <td width="100">最近充值日期</td>
							    <td width="100">最近维护日期</td>
							    <td width="100">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
									    <td>${c.loginId }</td>
									    <td>${c.name }</td>
									    <td><s:property value="departmentInfoService.getByEmployee(#c.employeeInfo_id).name"></s:property></td>
									    <td><s:property value="employeeInfoService.get(@com.ticket.pojo.EmployeeInfo@class, #c.employeeInfo_id).name"></s:property></td>
									    <td><s:property value="channelTypeService.get(@com.ticket.pojo.ChannelType@class, #c.channelTypeId).name"></s:property></td>
									    <td>${c.contact }</td>
									    <td>${c.contactPhone }</td>
									    <td><s:date name="#c.openAccountDate" format="yyyy-MM-dd"/></td>
									    <td><s:date name="rechargeRecordService.lastForChannelCustomer(#c.id).status.createTime" format="yyyy-MM-dd"/></td>
									    <td><s:date name="channelCustomerInfoService.getLastDate(#c.id)" format="yyyy-MM-dd"/></td>
									    <td><a href="cRMReport_customerDetails.action?id=${c.id }">查看</a></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="711">${noDataMessage }</td>
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