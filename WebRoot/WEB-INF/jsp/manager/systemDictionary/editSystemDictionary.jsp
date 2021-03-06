<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">var actionName = '${actionName}';</script>
	<script type="text/javascript" src="/manager/js/systemDictionary.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑系统字典表</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${systemDictionary.id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									父字典：
								</td>
								<td>
									<input id="parent_id" class="easyui-combotree my_input" 
										data-options="url:'${actionName }_traverse.action'" 
										name="parent_id" value="${systemDictionary.parent.id}">
									<p class="Validform_checktip" style="display:inline;"> 请选择父字典</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									字典名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${systemDictionary.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写字典名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									字典值：
								</td>
								<td>
									<input id="value" name="value" class="my_input" datatype="*"
									       value="${systemDictionary.value}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写字典值</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input name="orderValue" class="my_input" datatype="n" 
									value="${systemDictionary.status.orderValue}"  ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写排序值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea name="description" class="my_input">${systemDictionary.description}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>
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