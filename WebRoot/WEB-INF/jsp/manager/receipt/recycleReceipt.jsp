<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/receipt.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理收据发票</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span></span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								   <td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="100">收据发票编号</td>
								    <td width="80">创建时间</td>
								    <td width="80">收据名称</td>
								    <td width="80">申请时间</td>
								    <td width="80">付款客户</td>
								    <td width="80">申请金额</td>
								    <td width="80">申请人</td>
								    <td width="80">申请类型</td>
								    <td width="80">备注</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:iterator id="c" value="pageModule.pageList" status="st">
								  <tr>
								   <td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${c.id }</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
										    <td>${c.name }</td>
										    <td><s:date name="#c.issueTime" format="yyyy-MM-dd"/></td>
										    <td>
										    <ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfoId}"/>
									    	 ${channelCustomerInfoObj.name }
										    
										    </td>
										    <td>${c.amountOfMoney }</td>
										    <td>
										    <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfoId}"/>
									    	 ${employeeInfoObj.name }
										    </td>
										    <td>
										    <s:if test="#c.type==0">
										    	预开申请
										    </s:if>
										    <s:else>
										    	到账申请
										    </s:else>
										    </td>
										    <td>${c.remarks }</td>
								    <td><span>
								   		<a href="javascript:;" class="realDeleteBtn" entityName="${entityName}" value="${c.id}">删除</a>
										<a href="javascript:;" class="restoreBtn" entityName="${entityName}" value="${c.id }">还原</a>
								    </span></td>
								  </tr>
								  </s:iterator>
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