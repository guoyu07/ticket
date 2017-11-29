<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="/template/pc/css/animate.min.css">
<link rel="stylesheet" href="/template/pc/css/pintuer.css">
<link rel="stylesheet" href="/template/pc/css/public.css">
<link rel="stylesheet" href="/template/pc/css/subpage.css">
<%@ include file="common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/register.js"></script>
<title>注册- 云南省昆明市长水机场</title>
</head>
<body>
	<div class="header">
        <div class="wp">
            <div class="min_header">
                <a class="logo" href="#"></a>
                <div class="header_right">
                    <a href="/pcMembers_toLoginPage.action" class="login_btn"></a>
                    <a href="/pcMembers_register.action" class="reg_btn"></a>
                </div>
            </div>
            <div class="menu">
                <ul>
                    <li><a href="/airportPc.action">长水首页<p>Home</p></a><em></em></li>
                    <li><a href="/airportPc_flightCompanyInfo.action">航班信息<p>Flight</p></a><em></em></li>
                    <li><a href="/airportPc_airportDynamic.action">长水动态<p>News</p></a><em></em></li>
                    <li><a href="/airportPc_trafficGuide.action">交通指南<p>Traffic guide</p></a><em></em></li>
                    <li><a href="#">票务指南<p>Air tickets</p></a><em></em></li>
                    <li><a href="/airportPc_passengerService.action?alias=xinglichaxun&&alias2=xinglituoyun">旅客服务<p>Service</p></a><em></em></li>
                    <li><a href="/airportPc_businessDisplay.action">机场商业<p>Business</p></a></li>
                </ul>
            </div>
        </div>
    </div>


	<div class="reg_login_box">
		<div class="reg_box">
			<div class="reg_left">
				<h3>新用户注册</h3>
				<form action="/pcMembers_regist.action" method="post" id="memberForm2" name="memberForm2">
					<label class='phone_input'><input id="mobile" name="mobile"
						class="text_input" value="" placeholder='请输入注册手机号' errormsg="请输入正确的电话号码" nullmsg="电话号码不能为空" datatype="m"/>
						<p class="Validform_checktip" style="display:inline;"></p>
					</label>
					<div class="clearfix">
						<label class='email_input' style="width:245px;float:left"><input
							 name="smsCode"datatype="n6-6" placeholder='请输入验证码' nullmsg="验证码不能为空" datatype="*4-4" class="text_input" value="" placeholder='请输入动态验证码'>
						<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<button type='button' class="code_btn" id="yzm1">获取手机动态码</button>
						<button type="button" class="code_btn" value="获取验证码" style="display: none;"
								id="getCaptchaLogins" ></button>
					</div>
					<label class='pwd_input'><input id="password" name="password" errormsg="密码必须为6-30位数字或字母" nullmsg="密码不能为空" datatype="s6-30" class="text_input"
						value="" placeholder='请输入登录密码' type="password"/>
						<p class="Validform_checktip" style="display:inline;"></p>
					</label> 
					<label class='pwd_input'><input name='' class="text_input"
						value="" placeholder='确认密码' type="password" recheck="password" errormsg="两次输入的密码不一致" nullmsg="确认密码不能为空" datatype="s6-30"/>
						<p class="Validform_checktip" style="display:inline;"></p>
					</label>
					<div class="msg">
						<input id="agree" type='checkbox' class="checkbox_input"/>我已阅读并同意<a
							href="/airport/newsList/7cd1ade3-28c5-4b31-ae4e-3b24cd229c89.html">《 长水机场服务条款 》</a>
							<a href="/airport/newsList/7cd1ade3-28c5-4b31-ae4e-3b24cd229c89.html" style="display: none;"></a>
					</div>
					<input type="submit" class="yello_btn" id="submitBtn" value="注册"/>
					<a id="manageLink" href="/pcMembers_toLoginPage.action"></a>
				</form>
			</div>
			<div class="reg_qrcode">
				<a href="javascript:;"><img src="/template/pc/image/reg/iphone_qrcode.jpg">
				</a> <a href="javascript:;"><img src="/template/pc/image/reg/android_qrcode.jpg">
				</a>
			</div>
		</div>
	</div>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
