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
			
				<div>
					<div id="commomUploadBtn">
						上传
					</div>
				</div>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<br/>
				<div>
					<div id="commomUploadBtn2">
						上传2
					</div>
				</div>
				
		</div>
		<div id="fixed_down">
			<div class="panelBar">
				<div class="toolBar_right">
					<a class="btn_save" href="#" id="submitBtn">保存</a>
					<a class="btn_save" href="#" id="resetBtn">清空</a>
				</div>
			</div>
		</div>
		
		<!-- 初始化上传组件 -->
		<%@ include file="uploadFrameTags.jsp"%>
		<script type="text/javascript">
			$(function(){
				//initUpload(上传按钮id, 附件类型, 附件大小-k, 附件格式, 附件格式描述)
				initUpload("commomUploadBtn", 1, 10*1024, "*.jpg", "图片"); //初始化上传控件
				initUpload("commomUploadBtn2", 2, 10*1024, "*.*", "所有文件"); //初始化上传控件
			})
		</script>
		
	</body>
</html>