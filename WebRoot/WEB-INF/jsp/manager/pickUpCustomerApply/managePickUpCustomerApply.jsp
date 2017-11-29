<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/pickUpCustomerApply.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理捡单客户申请</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchAuditBtn" value="${actionName}CheckBox" entityName="${entityName}">批量审核</a>
					      	<a href="#" class="batchLogicDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">申请时间</td>
								    <td width="80">申请员工</td>
								    <td width="80">申请客户</td>
								    <td width="80">客户所属员工</td>
								    <td width="80">审核状态</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>
										     <ticket:systemCommon type="employeeInfoObj" value="${c.employee_id}"/>
									    	 ${employeeInfoObj.name }
										    </td>
										    <td>
										      <ticket:systemCommon type="channelCustomerInfoObj" value="${c.customer_id }"/>
									    	 ${channelCustomerInfoObj.name }
										    </td>
										    <td>
										     <ticket:systemCommon type="employeeInfoObj" value="${c.oldemployee_id}"/>
									    	 ${employeeInfoObj.name }
										    </td>
										    <td>
									 	    	   <s:if  test="#c.applyState==0">
										    			未审核
										    		</s:if>
										    		<s:else>
										    			审核通过
										    		</s:else>
										 
										     
										    </td>
										    <td><span>
										    	<a href="#" class="logicDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a  class="audit"  entityName="${entityName}"  value="${c.id }"  customer_id="${c.customer_id }" employee_id="${c.employee_id}"  href="#">审核通过</a></span></td>
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