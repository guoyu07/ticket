<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/manager/js/quickMenu.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增快捷菜单</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									菜单类型：
								</td>
								<td>
									<input id="quickMenuType_id" name="quickMenuType_id" class="my_input easyui-combobox" datatype="*"
									       data-options="url:'/systemDictionary_querySubByParentValue.action?value=quick_menu',valueField:'id',textField:'name'"/>
									<p class="Validform_checktip" style="display:inline;"> 请选择菜单分类</p>  
								</td>
							</tr>
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
									菜单链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*" style="width: 70%"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写菜单链接</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									菜单图标：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
										<input type="file" id="icon" name="icon" style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150" rowspan="3">
									默认显示位置：
								</td>
								<td>
									<input type="checkbox" name="defaultShowPosition" value='a'>左侧国内出发
									<input type="checkbox" name="defaultShowPosition" value='b'>左侧国际出发
									<input type="checkbox" name="defaultShowPosition" value='c'>左侧国内中转
									<input type="checkbox" name="defaultShowPosition" value='d'>左侧国际中转
									<input type="checkbox" name="defaultShowPosition" value='e'>左侧国内到达
									<input type="checkbox" name="defaultShowPosition" value='f'>左侧国际到达
								</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" name="defaultShowPosition" value='g'>个人中心
								</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" name="defaultShowPosition" value='h'>服务菜单国内出发
									<input type="checkbox" name="defaultShowPosition" value='i'>服务菜单国际出发
									<input type="checkbox" name="defaultShowPosition" value='j'>服务菜单国内中转
									<input type="checkbox" name="defaultShowPosition" value='k'>服务菜单国际中转
									<input type="checkbox" name="defaultShowPosition" value='l'>服务菜单国内到达
									<input type="checkbox" name="defaultShowPosition" value='m'>服务菜单国际到达
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
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