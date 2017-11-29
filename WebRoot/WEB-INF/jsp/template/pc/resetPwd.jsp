<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp"%>
		<link rel="stylesheet" href="/template/pc/css/animate.min.css" />
		<link rel="stylesheet" href="/template/pc/css/pintuer.css" />
		<link rel="stylesheet" href="/template/pc/css/public.css" />
		<link rel="stylesheet" href="/template/pc/css/subpage.css" />
		<script type="text/javascript" src="/template/pc/js/resetPwd.js"></script>
		<title>重置密码- 云南省昆明市长水机场</title>
	</head>
	<body>
		<% String url = request.getHeader("Referer"); %>
		<div class="header">
        <div class="wp">
            <div class="min_header">
                <a class="logo" href="#"></a>
                <div class="header_right">
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
			<div class="reg_box" style="width:600px;">
				<div class="reg_left">
					<h3>重置密码</h3>
					<form action="/pcMembers_resetPassword.action" method="post" id="memberForm2" ajaxPost="true">
						<label class='phone_input'> 
							<input id="mobile" name='mobile' datatype="m" nullmsg="请输入手机号" class="text_input" placeholder='请输入注册手机号'/>
							<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<div class="clearfix" style="line-height: 75px; height: 75px;">
							<label class='email_input' style="width:245px;float:left">
								<input name='smsCode' class="text_input" datatype="*" placeholder='请输入手机动态码' dataType="n4-4"/>
								<p class="Validform_checktip" style="display:inline;"></p>
							</label>
							<a href="javascript:;" class='button bg-sub sendSmsClass' style="margin: 30px 0 0 15px" sendType="resetPassword" elementId="mobile">发送验证码</a>
						</div>
						<label class='pwd_input'> 
							<input type="password" name='password' class="text_input" datatype="s6-16" placeholder='输入新密码' nullmsg="请输入新密码" />
							<p class="Validform_checktip" style="display:inline;"></p>
						</label> 
						<label class='pwd_input'> 
							<input name='recheckPwd' type="password" datatype="s6-16" class="text_input" 
								placeholder='确认新密码' nullmsg="请输入确认密码" recheck="password" errorMsg="两次输入的密码不一样"/>
							<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<button id="submitBtn" type="submit" class="yello_btn" style="margin-top:20px;">修改密码</button>
						<a id="manageLink" href="/airportPc_personalCenter.action"></a>
					</form>
					<button class="yello_btn" style="margin-top:20px;" id="quxiao">取消</button>
					<input type="hidden" value="<%=url%>" id="url" />
				</div>
			</div>
		</div>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp"%>
	</body>
</html>
