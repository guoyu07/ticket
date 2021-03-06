<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rolePrivilege.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑角色权限</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											角色id：
										</td>
										<td>
											<input id="roleId" name="roleId" class="my_input" datatype="*"
											       value="${rolePrivilege.roleId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写角色id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											模块id：
										</td>
										<td>
											<input id="systemModuleId" name="systemModuleId" class="my_input" datatype="*"
											       value="${rolePrivilege.systemModuleId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写模块id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											权限id：
										</td>
										<td>
											<input id="privilegeId" name="privilegeId" class="my_input" datatype="*"
											       value="${rolePrivilege.privilegeId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写权限id</p>       
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