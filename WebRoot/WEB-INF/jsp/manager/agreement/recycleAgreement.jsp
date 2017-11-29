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
					      <td >管理客户合同</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span></span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								    <td width="20"><input name="" type="checkbox" value=""></td>
								    <td width="100">创建时间</td>
								    <td width="80">申请类别</td>
								     <td width="80">员工名称</td>
								    <td width="100">申请时间</td>
								  
								    
								    <td width="80">签订金额</td>
								    
								    <td width="80">客户名称</td>
								    
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								 <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
							
										    <td>
										     <ticket:systemCommon type="applayClassObj" value="${c.applayClassId}"/>
									    	 ${applayClassObj.name }
										    </td>
										    <td>
										    <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfo_id}"/>
									    	 ${employeeInfoObj.name }
										    </td>
										    <td><s:date name="#c.applyDate" format="yyyy-MM-dd"/></td>
								
										    <td>${c.firstRenewMoney}</td>
										    
										    <td>
										     <ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfo_id}"/>
									    	 ${channelCustomerInfoObj.name }
										    </td>
										    
										    <td><span>
										    	<a href="javascript:;" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
										    	<a href="javascript:;" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
												</span>
												</td>		
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
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>