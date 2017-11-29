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
					      <td >意向客户明细</td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100" style="display: ">
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="60">客户账号</td>
								    <td width="120">客户名称</td>
								    <td width="120">部门</td>
								    <td width="80">销售账号</td>
								    <td width="80">渠道类型</td>
								    <td width="40">是否保护</td>
								    <td width="40">电话回访数</td>
								    <td width="40">外出拜访数</td>
								  </tr>
								  	<s:if test="#request.intentionCustomerList != null">
									  	<s:iterator id="intentionCustomerObj" value="#request.intentionCustomerList">
										  <tr>
										  	<td>${intentionCustomerObj[0] }</td>
										  	<td>${intentionCustomerObj[1] }</td>
										  	<td>
										  		<ticket:systemCommon type="departmentInfoObj" value="${intentionCustomerObj[2] }"/>
									    		${departmentInfoObj}
										  	</td>
										  	<td>${intentionCustomerObj[3] }</td>
										  	<td>${intentionCustomerObj[4] }</td>
										  	<td>${intentionCustomerObj[5] }</td>
										  	<td><a href="/channelCustomerInfo_phoneVisitDetail.action?id=${intentionCustomerObj[8] }">${intentionCustomerObj[6] }</a></td>
										  	<td><a href="/channelCustomerInfo_outVisitDetail.action?id=${intentionCustomerObj[8] }">${intentionCustomerObj[7] }</a></td>
										  </tr>
									  	</s:iterator>
								</s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="7">${noDataMessage }</td>
									  </tr>
								  </s:else>
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