<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">var actionName = '${actionName}';</script>
	<script type="text/javascript" src="/manager/js/airportDepartment.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑机场部门</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="department.id" id="id" value="${department.id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									父部门：
								</td>
								<td>
									<input id="parent" class="easyui-combotree my_input" 
										data-options="url:'${actionName}_traverse.action'" 
										name="department.parent.id" value="${department.parent.id}">
									<p class="Validform_checktip" style="display:inline;"> 请填写父部门</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									部门名称：
								</td>
								<td>
									<input id="name" name="department.name" class="my_input" datatype="*"
									       value="${department.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写部门名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									部门编号：
								</td>
								<td>
									<input id="name" name="department.departmentId" class="my_input" datatype="*"
									       value="${department.departmentId}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写部门编号</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input name="department.status.orderValue" class="my_input" datatype="n" 
									value="${department.status.orderValue}"  ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写部门排序值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea name="department.description" class="my_input">${department.description}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写部门说明</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="resetBtn" type="reset" value="重置" class="btn_remove">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove">
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