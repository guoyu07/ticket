<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">var actionName = '${actionName}';</script>
	<script type="text/javascript" src="/manager/js/airportEmployee.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增员工信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									请选择部门
								</td>
								<td>
								<input id="parent_id" class="easyui-combotree my_input" 
										data-options="url:'airportDepartment_traverse.action'" 
										name="department_id" value="${id}">
								<p class="Validform_checktip" style="display:inline;"> 请选择上级部门</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									请选择服务厅
								</td>
								<td>
									<select name="hall_id">
										<option value="">所有大厅</option>
										<s:if test="#halls != null">
											<s:iterator value="#halls" var="hall">
												<option value="${hall.id }">${hall.number }</option>
											</s:iterator>
										</s:if>
									</select>
								<p class="Validform_checktip" style="display:inline;"> 请选择服务厅</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									姓名：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									手机号：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写手机号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									工号：
								</td>
								<td>
									<input id="employeeId" name="employeeId" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写工号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									用户名：
								</td>
								<td>
									<input id="loginId" name="loginId" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写用户名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									密码：
								</td>
								<td>
									<input id="password" name="password" type="password" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写密码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									身份证号：
								</td>
								<td>
									<input id="IDCard" name="IDCard" class="my_input" datatype="*18-18"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写身份证号</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="resetBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>