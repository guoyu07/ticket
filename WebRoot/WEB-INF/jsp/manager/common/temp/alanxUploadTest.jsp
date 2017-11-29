<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="ax" uri="/WEB-INF/classes/alanXUpload.tld" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>聚名统一资源管理后台</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<meta http-equiv="Content-Language" content="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<%@ include file="/WEB-INF/jsp/manager/common/tags.jsp"%>
		<link href="/manager/frame/style/sub.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="fixed_top">
			<div class="panelBar">
				<div class="toolBar_left"></div>
				<div class="toolBar_right"></div>
			</div>
		</div>
		<div id="fixed_body">
			<form action="#" id="commonForm" name="commonForm">
				<table class="aykj_tab1" cellspacing="0" cellpadding="0"
					width="100%" border="0">
					<tr>
						<td width="13%" height="41" align="right" class="td_bg">
							文件选择：
						</td>
						<td class="td_bg" width="87%">
							<ax:head/>
							<ax:body extensionName="*.*" extensionDisp="请选择要上传的文件"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="fixed_down">
			<div class="panelBar">
				<div class="toolBar_right">
					<a class="btn_save" href="#" id="submitBtn">保存</a>
					<a class="btn_save" href="#" id="resetBtn">清空</a>
				</div>
			</div>
		</div>
	</body>
</html>