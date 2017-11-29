<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>激活成功 - 云南省昆明市长水机场</title>
		<!--
		<style type="text/css">
			body {width:600px; height:440px; overflow:hidden;}
			*{margin:0; padding:0;}
			a{outline:none; text-decoration:none;}				
			*html a{blr:expression(this.onFocus=this.blur());}
			*+html a{blr:expression(this.onFocus=this.blur());}
			body{font-family:"Microsoft YaHei"!important;}
			a:hover{text-decoration:underline;}
			a:active{outline:none; blr:expression(this.onFocus=this.blur());}
			img,div,input{border:none;}
			
			table{padding-top:40px;}
			td{font-size:20px; color:#787878;}
			a{color:#00AAFF;}
			p{font-size:14px;}
		</style>
		-->
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>
		<%@ include file="../common/nav.jsp" %>
		<center>
		<table cellspacing="0" cellpadding="0" width="600" height="290" >
			<tr><td height="200" align="center"><img src="/template/pc/images/face.gif" /></td></tr>
			<tr><td height="50" align="center">您的服务码已成功激活，便捷登机服务已预约成功！</td></tr>
			<tr><td height="40" align="center">
				<a href="<ticket:common type="positionUrl" value="shangwuzhongxin"/>" target="_blank"><img src="/template/pc/images/button_dh.gif" /></a> &nbsp;&nbsp;
				<a href="airportPc_checkOnLine.action" target="_blank"><img src="/template/pc/images/online.gif" /></a>
			</td></tr>
		</table>
		</center>
		<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
	</body>
</html>
