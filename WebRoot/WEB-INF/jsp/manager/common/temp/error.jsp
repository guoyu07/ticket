<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${systemConfig.name }</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<meta http-equiv="Content-Language" content="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<%@ include file="/WEB-INF/jsp/manager/common/tags.jsp"%>
		<link href="/manager/frame/style/sub.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="fixed_top"></div>
		<div id="fixed_body">
			${text }权限不足，请联系管理员！o(∩_∩)o 
		</div>
		<div id="fixed_down">
			<div class="panelBar">
				<div class="toolBar_right">
					<a class="btn_save" href="#" id="returnBtn">返回</a>
				</div>
			</div>
		</div>
	</body>
</html>