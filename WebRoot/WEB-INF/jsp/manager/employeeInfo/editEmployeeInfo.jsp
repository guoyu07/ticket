<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/employeeInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑员工信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									部门：
								</td>
								<td>
									<select id="department_id" name="department_id" class="my_input" datatype="*">
										<option value="">请选择部门</option>
										${departmentInfoHtml}
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写部门</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									岗位：
								</td>
								<td>
									<select name="position_id" id="position_id" class="my_input">
										<s:iterator id="p" value="positions">
											<option <s:if test="employeeInfo.position_id == #p.id">selected</s:if> value="${p.id}">${p.name}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									姓名：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${employeeInfo.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写姓名</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									入职日期：
								</td>
								<td>
									<input id="entryDate" onclick="new WdatePicker();" name="entryDate" class="my_input" datatype="*"
									       value="${employeeInfo.entryDate}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写入职日期</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									员工状态：
								</td>
								<td>
									<select id="state" name="state" class="my_input">
										<option <s:if test="employeeInfo.state == 0">selected</s:if> value="0">在职</option>
										<option <s:if test="employeeInfo.state == 1">selected</s:if> value="1">离职</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									身份证：
								</td>
								<td>
									<input id="IDCard" name="IDCard" class="my_input" datatype="*"
									       value="${employeeInfo.IDCard}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写身份证</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" datatype="m"
									       value="${employeeInfo.phone}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系人：
								</td>
								<td>
									<input id="emergencyContact" name="emergencyContact" class="my_input" datatype="*"
									       value="${employeeInfo.emergencyContact}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系人</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									紧急联系电话：
								</td>
								<td>
									<input id="emergencyPhone" name="emergencyPhone" class="my_input" datatype="m"
									       value="${employeeInfo.emergencyPhone}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写紧急联系电话</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登陆名称：
								</td>
								<td>
									<input id="loginId" name="loginId" value="${employeeInfo.loginId}" class="my_input" datatype="*1-30"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登陆名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登陆密码：
								</td>
								<td>
									<input id="password" name="password" value="${employeeInfo.password}"  type="password" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登陆密码</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
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