<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/dataAuthoritys.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑数据权限</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											权限名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"
											       value="${dataAuthoritys.name}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写权限名称</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											权限描述：
										</td>
										<td>
											<input id="descript" name="descript" class="my_input" datatype="*"
											       value="${dataAuthoritys.descript}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写权限描述</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											权限内容：
										</td>
										<td>
											<input id="content" name="content" class="my_input" datatype="*"
											       value="${dataAuthoritys.content}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写权限内容</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											模块名称：
										</td>
										<td>
											<input id="systemModulId" class="easyui-combotree my_input" 
												data-options="url:'${actionName }_traverse.action'" 
												name="systemModulId" value="${dataAuthoritys.systemModulId }">
											<p class="Validform_checktip" style="display:inline;"> 请填写权限内容</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											调用方法：
										</td>
										<td>
											<input id="inMethod" name="inMethod" class="my_input" datatype="*"
											       value="${dataAuthoritys.inMethod}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写权限内容</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="editLink" href="/${actionName}_edit.action?versionFlag=${versionFlag}"></a>
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