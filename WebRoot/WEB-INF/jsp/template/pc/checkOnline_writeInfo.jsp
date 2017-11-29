<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>网上值机 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_3"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a>
			<a href="#">网上值机</a>
		</div place w980 mt30>




		<div class="WSZJ_article">
			<form action="" method="post" class='form-x my_form'>
				<dl>
					<dd>
						<b>证件号码</b>
						<select name="">
							<option value="身份证">
								身份证
							</option>
						</select>
						<input name="" type="text" style="background-color: #f2f2f2;" />
						请选择并填写您的证件号码
						<font>（必选）*</font>
					</dd>
					<dd>
						<b>您的手机</b>
						<input name="" type="text" />
						<img src="images/YZM.gif" align="absmiddle" />
						请正确填写您的手机号码
						<font>（必选）*</font>
					</dd>
					<dd>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<b>验证码</b>
						<input name="" type="text" />
						填写您的手机获取的验证码
						<font>（必选）*</font>
					</dd>
					<dd>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="" type="checkbox" class="TY" value=""
							checked="checked" />
						我已经阅读并同意值机协议
						<font>（必选）*</font>
					</dd>
				</dl>
			</form>

			<center>
				<a href="javascript:;"><img src="images/ENTER.gif" /> </a>
			</center>

			<ul>
				<h2>
					值机须知
				</h2>
				1. 目前仅支持国内航班办理值机；
				<br />
				2. 携带儿童、婴儿，请直接至柜台办理值机；
				<br />
				3. 如发生临时更换机型情况，航空公司将为你重新安排座位。如与原座位安排不符，请以航空公司安排为准；
				<br />
				4. 已办理值机后，如需办理退票，需提前取消值机。
			</ul>
		</div WSZJ_article mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
