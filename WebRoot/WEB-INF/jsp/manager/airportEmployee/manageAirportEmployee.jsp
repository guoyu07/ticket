<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/airportEmployee.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理员工信息</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
						    <form action="${actionName }_manage.action?versionFlag=site" method="get">
					    		创建时间：<input type="text" name="startTime" onclick="new WdatePicker();" value='<s:date name="startTime" format="yyyy-MM-dd" />'/>&nbsp;&nbsp;~&nbsp;&nbsp;
					    		<input type="text" name="endTime" onclick="new WdatePicker();" value='<s:date name="endTime" format="yyyy-MM-dd" />'/>
					    		部门：<input id="parent_id" class="easyui-combotree my_input" data-options="url:'airportDepartment_traverse.action'" name="department_id" value="${id}">
					    		<%-- 岗位：<input type="text" name="keyword" value="${keyword }"/> --%><br/>
					    		姓名：<input type="text" name="name" value="${name }"/>
					    		电话：<input type="text" name="phone" value="${phone }"/>
					    		用户名：<input type="text" name="loginId" value="${loginId }"/>
					    		身份证号：<input type="text" name=" IDCard" value="${IDCard }"/><br/>
					  			<input type="submit" value="查询"/>
					    	</form>
				    	</td>
					    <tr class="text_align_left">
					      <td><span>
					      	<a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a>
					      	<a target="_blank" href="${actionName }_downReport.action?name=${name }&phone=${phone }&loginId=${loginId }&IDCard=${IDCard }&startTime=<s:date name="startTime" format="yyyy-MM-dd" />&endTime=<s:date name="endTime" format="yyyy-MM-dd" />">下载</a>
					      	<a><input id="isChecked" type="checkbox"/></a>
					      </span></td>
					    </tr>
					    <tr>
					      <td>
							<table class="my_table100">
							  <tr class="my_table_top1">
							  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
							    <td width="80">创建时间</td>
							    <td width="80">所属部门</td>
							    <td width="80">所属服务厅</td>
							    <td width="80">姓名</td>
							    <td width="80">手机号</td>
							    <td width="80">工号</td>
							    <td width="80">用户名</td>
							    <td>身份证号</td>
							    <td width="150" class="text_align_center">操作</td>
							  </tr>
							  <s:if test="pageModule != null && pageModule.totalCount > 0">
							  	<s:iterator id="c" value="pageModule.pageList" status="st">
							  		<tr>
							  			<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
									    <td><s:date name="#c.status.createTime" format="yyyy-MM-dd"/></td>
									    <td><s:property value="airportEmployeeService.get(@com.ticket.pojo.AirportDepartment@class, #c.department_id).name"/></td>
									    <td>${c.hall == null ? '' : c.hall.number }</td>
									    <td>${c.name }</td>
									    <td>${c.phone }</td>
									    <td>${c.employeeId }</td>
									    <td>${c.loginId }</td>
									    <td>${c.IDCard }</td>
									    <td><span>
									    	<a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a>
									    	<a href="/${actionName}_edit.action?id=${c.id }&versionFlag=${versionFlag}">编辑</a></span></td>
									 </tr>
							  	</s:iterator>
							  </s:if>
							  <s:else>
							  	 <tr>
								    <td colspan="10">${noDataMessage }</td>
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