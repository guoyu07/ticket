<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>员工通道 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_22"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a>
			<a href="#">员工通道</a>
		</div place w980 mt30>

		<div class="other_article_article w980 mt30">
			<h2>
				员工通道
			</h2>
			<dl>
				<dd>
					<b>您的手机</b>
					<input name="" type="text" />
					<span>请输入您的手机号码<font>(必选)*</font>
					</span>
				</dd>
				<dd>
					<b>&nbsp;&nbsp;&nbsp;验证码</b>
					<input name="" type="text" />
					<span>填写您的手机获得的验证码<font>(必选)*</font>
					</span>
				</dd>
				<dd>
					<b>您的密码</b>
					<input name="" type="text" />
					<span>请输入您的6-30位数字或字母的密码<font>（必选）*</font>
					</span>
				</dd>
				<dd>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:;">密码重置</a>
				</dd>
			</dl>
			<center>
				<a href="javascript:;"><img src="images/other_login_button.gif"
						style="margin-bottom: 50px;" />
				</a>
			</center>
		</div other_article_article w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
