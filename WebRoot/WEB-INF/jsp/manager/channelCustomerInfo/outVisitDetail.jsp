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
					      <td >客户外出拜访记录明细</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100" style="display: ">
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">出访人</td>
								    <td width="80">陪同人</td>
								    <td width="80">拜访日期</td>
								    <td width="80">客户类型</td>
								    <td width="80">公司名称</td>
								    <td width="80">联系人</td>
								    <td width="80">联系电话</td>
								    <td width="80">拜访目的</td>
								    <td width="80">出发时间</td>
								    <td width="80">返回时间</td>
								    <td width="80">拜访结果</td>
								  </tr>
								  	<s:if test="#request.outVisitList != null && #request.outVisitList.size != 0">
									  	<s:iterator id="outVisitObj" value="#request.outVisitList ">
										  <tr>
										  	<td><ticket:systemCommon type="employeeInfoObj" value="${outVisitObj.employee_id }"/>
										  		${employeeInfoObj.name }
										  	</td>
										  	<td>
										  		<s:if test="#outVisitObj.accompanyPerPerson == null || #outVisitObj.accompanyPerPerson==''">
										  			无
										  		</s:if>
										  		<s:else>
												  	${outVisitObj.accompanyPerPerson }
										  		</s:else>
										  	</td>
										  	<td><s:date name="#outVisitObj.visitDate" format="yyyy-MM-dd"/></td>
										  	<ticket:systemCommon type="channelCustomerInfoObj" value="${outVisitObj.customer_id }"/>
										  	<td>
										  		<ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfoObj.channelTypeId }"/>
											  		${channelTypeObj.name }
										  	</td>
										  	<td>${channelCustomerInfoObj.name }</td>
										  	<td>${outVisitObj.contact }</td>
										  	<td>${outVisitObj.contactPhone }</td>
										  	<td>${outVisitObj.visitPurpose }</td>
										  	<td><s:date name="#outVisitObj.setoutTime" format="yyyy-MM-dd hh:mm:ss"/></td>
										  	<td><s:date name="#outVisitObj.backTime" format="yyyy-MM-dd hh:mm:ss"/></td>
										  	<td>${outVisitObj.visitResult }</td>
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
							<div class="my_table_list_r">
								<input type="button" onclick="javascript:history.go(-1);" value="返回" class="btn_submit">
					       	</div>
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