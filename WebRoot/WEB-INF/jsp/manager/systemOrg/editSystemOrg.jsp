<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript">var actionName = '${actionName}';</script>
	<script type="text/javascript" src="/manager/js/systemOrg.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑系统组织机构</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="org.commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="org.id" id="id" value="${org.id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									父机构：
								</td>
								<td>
									<input id="parent" class="easyui-combotree my_input" 
										data-options="url:'${actionName}_traverse.action'" 
										name="org.parent.id" value="${org.parent.id}">
									<p class="Validform_checktip" style="display:inline;"> 请填写父机构</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									机构名称：
								</td>
								<td>
									<input id="name" name="org.name" class="my_input" datatype="*"
									       value="${org.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写机构名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input name="org.status.orderValue" class="my_input" datatype="n" 
									value="${org.status.orderValue}"  ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写机构值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea name="org.description" class="my_input">${org.description}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写机构值</p>
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