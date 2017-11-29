<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/hitSingleLog.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理撞单日志</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">客户名称</td>
								    <td width="80">客户类型</td>
								    <td width="80">员工名字</td>
								    <td width="80">员工联系电话</td>
								    <td width="80">添加时间</td>
								    <td width="80">状态</td>
								    <td width="80">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
								  			<ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfo_id}"/>
										    <td>${channelCustomerInfoObj.name}</td>
										    <ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfoObj.channelTypeId}"/>
										    <td>${channelTypeObj.name }</td>
										    <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfo_id}"/>
										    <td>${employeeInfoObj.name}</td>
										 	<td>${employeeInfoObj.phone}</td>
										    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>
										    	<s:if test="#c.state == 0">未审批</s:if>
										    	<s:if test="#c.state == 1">已通过</s:if>
										    	<s:if test="#c.state == 2">未通过</s:if>
										    </td>
										    <td>
										    	<span>
										    	<s:if test="#c.state == 0 || #c.state == 2">
										    		<a href="/hitSingleLog_changeState.action?id=${c.id}">审批</a>
										    	</s:if>
										    	<a href="/hitSingleLog_view.action?id=${c.id}">查看</a>
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
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>