<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/manager/js/frontMenu.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增系统菜单表</span>
					</div>
					<form method="post" action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									父菜单：
								</td>
								<td>
									<input id="parent_id" class="easyui-combotree my_input" 
										data-options="url:'${actionName }_traverse.action'" 
										name="parent_id" value="${id}">
									<p class="Validform_checktip" style="display:inline;"> 请选择父菜单</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									菜单名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写菜单名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									菜单值：
								</td>
								<td>
									<input name="value" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写菜单值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否加载子菜单：
								</td>
								<td>
									<select name="loadSub" class="my_input" datatype="*">
										<option value="false">不显示</option>
										<option value="true">显示</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择是否加载子菜单</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input name="orderValue" class="my_input" datatype="n" value="0"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写排序值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea name="description" class="my_input"></textarea>
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