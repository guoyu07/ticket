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
					      <td>销售CRM-电话拜访详细信息</td>
					    <tr>
					      <td>
							<table id="table" class="my_table100">
							  <tr class="my_table_top1">
							    <td width="100">回访人</td>
							    <td width="100">回访日期</td>
							    <td width="100">客户账号</td>
							    <td width="100">公司名称</td>
							    <td width="100">联系人</td>
							    <td width="100">联系电话</td>
							    <td width="100">沟通内容</td>
							    <td width="100">沟通结果</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
									    <td><s:property value="employeeInfoService.get(@com.ticket.pojo.EmployeeInfo@class, #c.employee_id).name"></s:property></td>
									    <td><s:date name="#c.visitDate" format="yyyy-MM-dd"/></td>
									    <s:set var="customer" value="channelCustomerInfoService.get(@com.ticket.pojo.ChannelCustomerInfo@class, #c.customer_id)"></s:set>
									    <td>${customer.loginId }</td>
									    <td>${customer.name }</td>
									    <td>${c.contact }</td>
									    <td>${c.contactPhone }</td>
									    <td>${c.content }</td>
									    <td></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="711">${noDataMessage }</td>
								  </tr>
							  </s:else>
							</table>
							<div class="my_table_list_r">
							    <input id="returnBtn" type="button" value="返回" class="btn_submit margin_left20">
							</div>
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