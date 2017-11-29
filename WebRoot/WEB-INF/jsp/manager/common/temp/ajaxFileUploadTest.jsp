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
		<script type="text/javascript" src="/common/ajaxfileupload/ajaxfileupload.js"></script>
		<script type="text/javascript">
		function ajaxFileUpload() {
			$("#loading")
			.ajaxStart(function(){
				$(this).show();
			})//开始上传文件时显示一个图片
			.ajaxComplete(function(){
				$(this).hide();
			});//文件上传完成将图片隐藏起来
			$.ajaxFileUpload
			(
				{
					url:'/ajaxFileUpload.action',//用于文件上传的服务器端请求地址
					secureuri:false,//一般设置为false
					fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
					dataType: 'json',//返回值类型 一般设置为json
					success: function (data, status)  //服务器成功响应处理函数
					{
						alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中定义的成员变量
						
						if(typeof(data.error) != 'undefined')
						{
							if(data.error != '')
							{
								alert(data.error);
							}else
							{
								alert(data.message);
							}
						}
					},
					error: function (data, status, e)//服务器响应失败处理函数
					{
						alert(data);
						alert(status);
						alert(e);
					}
				}
			)
			return false;
		}
		jQuery(function(){
			jQuery("#uploadImage").bind("click", function(){
				jQuery("#file").click();
			});
		});
		</script>
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
							<img src="/common/ajaxfileupload/loading.gif" id="loading" style="display: none;">
							<img src="/manager/images/upload.png" id="uploadImage"/> 
							<input type="file" id="file" name="file" style="display: none;"/>
							<input type="button" value="上传" onclick="return ajaxFileUpload();">
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