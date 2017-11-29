<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/customerProtectLog.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理客保日志</td>
					    </tr>
					    <tr  class="text_align_left">
					      <td><span>
					      	
					      </span></td>
					    </tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="80">客户名称</td>
								    <td width="80">客户类型</td>
								    <td width="80">保护开始时间</td>
								    <td width="80">保护结束时间</td>
								    <td width="80">员工名字</td>
								    <td width="80">员工联系电话</td>
								    <td width="60">状态</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
								  			<ticket:systemCommon type="channelCustomerInfoObj" value="${c.channelCustomerInfo_id}"/>
										    <td>${channelCustomerInfoObj.name}</td>
										    <ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfoObj.channelTypeId}"/>
										    <td>${channelTypeObj.name }</td>
										    <td><s:date name="#c.startTime" format="yyyy-MM-dd"/></td>
										    <td><s:date name="#c.endTime" format="yyyy-MM-dd"/></td>
										    <ticket:systemCommon type="employeeInfoObj" value="${c.employeeInfo_id}"/>
										    <td>${employeeInfoObj.name}</td>
										 	<td>${employeeInfoObj.phone}</td>
										 	<td><s:if test="#c.isExpire == 0">
										 	<span>
										 	<a href="javascript:;" class="changeExpireStatus" attrValue="${c.id}">取消保护</a>
										 	<a href="/customerProtectLog_changeEndTime.action?id=${c.id}">延长保护时间</a>
										 	</span></s:if>
										 		<s:else>已过期</s:else>
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