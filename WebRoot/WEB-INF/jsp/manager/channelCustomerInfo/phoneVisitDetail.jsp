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
					      <td >客户电话回访记录明细</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100" style="display: ">
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">回访人</td>
								    <td width="80">回访日期</td>
								    <td width="80">客户类型</td>
								    <td width="80">公司名称</td>
								    <td width="80">联系人</td>
								    <td width="80">联系电话</td>
								    <td width="80">沟通内容</td>
								  </tr>
								  	<s:if test="#request.phoneVisitList != null && #request.phoneVisitList.size !=0">
									  	<s:iterator id="phoneVisitObj" value="#request.phoneVisitList">
										  <tr>
										  	<td><ticket:systemCommon type="employeeInfoObj" value="${phoneVisitObj.employee_id }"/>
										  		${employeeInfoObj.name }
										  	</td>
										  	<td><s:date name="#phoneVisitObj.visitDate" format="yyyy-MM-dd"/></td>
										  	<ticket:systemCommon type="channelCustomerInfoObj" value="${phoneVisitObj.customer_id }"/>
										  	<td><s:if test="#request.channelCustomerInfoObj != null">
										  		<ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfoObj.channelTypeId }"/>
											  		${channelTypeObj.name }
											  	</s:if>
										  	</td>
										  	<td>${channelCustomerInfoObj.name }</td>
										  	<td>${phoneVisitObj.contact }</td>
										  	<td>${phoneVisitObj.contactPhone }</td>
										  	<td>${phoneVisitObj.content }</td>
										  </tr>
									  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="11">${noDataMessage }</td>
									  </tr>
								  </s:else>
								</table>
							</table>
					       </td>
					    </tr>
					  </table>
					  <div class="my_table_list_r">
						  <input type="button" onclick="javascript:history.go(-1);" value="返回" class="btn_submit">
			       	  </div>
					  <%@ include file="customerPage.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>