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
					    <tr  class="text_align_left my_table_top">
					      <td >已签约部门报表</td>
					    </tr>
					    <tr  style="text-align: center">
					      <td>
					      	<form action="" method="post" >
					      		时间:<input name="startDate" type="text" onclick="new WdatePicker();" value="<s:date name="#startDate " format="yyyy-MM-dd"/>"/>
							  				&nbsp;&nbsp;~&nbsp;&nbsp;
							  			<input name="endDate" type="text" onclick="new WdatePicker();" value="<s:date name="#endDate" format="yyyy-MM-dd"/>"/>
							  			<br/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
					      	</form>
					      </td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="80">部门</td>
								    <td width="80">销售账号</td>
								    <td width="80">渠道类型</td>
								    <td width="80">客户类型【新/老客户数】</td>
								    <td width="80">客户数</td>
								    <td width="80">新增客户数</td>
								    <td width="80">充值金额</td>
								    <td width="80">服务码购买数</td>
								    <td width="80">购买金额</td>
								    <td width="80">服务码激活数</td>
								    <td width="80">服务码验证数</td>
								    <td width="80">外出拜访数</td>
								    <td width="80">电话拜访数</td>
								  </tr>
								  <s:if test="#employeeInfos != null">
								  	<s:iterator id="c" value="#employeeInfos" status="st">
								  		<ticket:common type="customerTypeCount" value="${c.id }"/>
								  		<s:iterator id="ct" value="#request.customerTypeCount" status="cts">
								  		<tr>
										   	<ticket:common type="department" value="${c.id }"/>
										    <td>${department.name }</td>
										    <td>${c.name }</td>
										    <%-- <ticket:common type="customerType" value="${c.id }"/> --%>
										    <td>${ct }</td>
										    <ticket:common type="customerTypeId" value="${ct }"/>
										    <ticket:common type="newCustomerCount" value="${c.id }" startDate="${startDate }" endDate="${endDate }" cTypeId="${customerTypeId.id }"/>
										    <ticket:common type="oldCustomerCount" value="${c.id }" startDate="${startDate }" endDate="${endDate }" cTypeId="${customerTypeId.id }"/>
										    <td>${newCustomerCount }/${oldCustomerCount }</td>
										    <td>${newCustomerCount + oldCustomerCount }</td>
										    <td>${newCustomerCount }</td>
										    <ticket:common type="czMoney" value="${c.id }" startDate="${startDate }" endDate="${endDate }" cTypeId="${customerTypeId.id }"/>
										    <td>${czMoney }</td>
										    <ticket:common type="buyCount" value="${c.id }" startDate="${startDate }" endDate="${endDate }" cTypeId="${customerTypeId.id }"/>
										    <td>${buyCount }</td>
										    <td>${paidAmount }</td>
										    <ticket:common type="activeCount" value="${c.id }" cTypeId="${customerTypeId.id }" startDate="${startDate }" endDate="${endDate }"/>
										    <td>${activeCount }</td>
										    <ticket:common type="validationCount" value="${c.id }" cTypeId="${customerTypeId.id }" startDate="${startDate }" endDate="${endDate }"/>
										    <td>${validationCount }</td>
										    <ticket:common type="visitCount" value="${c.id }" cTypeId="${customerTypeId.id }" startDate="${startDate }" endDate="${endDate }"/>
										    <td>${visitCount }</td>
										    <ticket:common type="phoneCount" value="${c.id }" cTypeId="${customerTypeId.id }" startDate="${startDate }" endDate="${endDate }"/>
										    <td>${phoneCount }</td>
										 </tr>
										 </s:iterator>
									   </s:iterator>
									</s:if>
									<s:else>
								  	 <tr>
									    <td colspan="70">请输入时间段进行查询~</td>
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