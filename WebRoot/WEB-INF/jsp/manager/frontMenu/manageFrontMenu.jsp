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
				<div id="toolbar" style="padding:2px 5px;">
					<a href="javascript:toAdd()" class="easyui-linkbutton easyui-tooltip" 
						iconCls="icon-add" plain="true" title="新增菜单"></a>
					<a href="javascript:toEdit()" class="easyui-linkbutton easyui-tooltip" 
						iconCls="icon-edit" plain="true" title="编辑菜单"></a>
					<a href="javascript:toDelete()" class="easyui-linkbutton easyui-tooltip" 
						iconCls="icon-remove" plain="true" title="删除菜单"></a>
				</div>
				<table id="tg" style="width:100%;height:100%;" ></table>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>