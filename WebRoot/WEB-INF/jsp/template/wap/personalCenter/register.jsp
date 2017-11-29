<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
     <style type="text/css">
    .logo_form label{ border:none; padding:20px 10px 0px;}

	.Validform_checktip{ display:block; margin-top:10px; font-size:26px; height:30px;}
    </style>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="注册" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="logo_form mr40">
						<form action="/airportm_regist.action" id="memberForm" name="memberForm">
							<label>
								<i class='user_icon'></i>
								<input type="text" class='' id="mobile" name="mobile" value="" errormsg="请输入电话号码" nullmsg="电话号码不能为空" datatype="m" placeholder='请输入手机号码' style='width: 350px'><br>
                                 <hr style="border-top:1px solid #ccc; margin:20px 0px 0px; display:block;">
								<p class="Validform_checktip"></p>
							</label>
							<label>
								<i class='user_code'></i>
								<input type="text" class='' name="smsCode" value="" placeholder='请输入验证码' nullmsg="验证码不能为空" datatype="*" style='width: 260px;'>
								<a href="#" class='button bg-sub sendSmsClass' sendType="register" elementId="mobile">发送验证码</a><br>
                                 <hr style="border-top:1px solid #ccc; margin:20px 0px 0px; display:block;">
								<p class="Validform_checktip" ></p>
							</label>
							<label>
								<i class='user_passwd'></i>
								<input type="password" class='' id="password" name="password" errormsg="密码必须为6-30位数字或字母" nullmsg="密码不能为空" value="" datatype="s6-30" placeholder='输6-30位数字或字母密码' ><br>
                                 <hr style="border-top:1px solid #ccc; margin:20px 0px 0px; display:block;">
								<p class="Validform_checktip" ></p>
							</label>
							<br>
							<label>
								<i class='user_passwd'></i>
								<input type="password" class='' recheck="password" errormsg="两次输入的密码不一致" nullmsg="确认密码不能为空" datatype="s6-30" value="" placeholder='再次输入您的密码' ><br>
                                 <hr style="border-top:1px solid #ccc; margin:20px 0px 0px; display:block;">
								<p class="Validform_checktip" ></p>
							</label>
							<p style="margin-top:30px ">
	                        	<input id="agreeRegister" type="checkbox" name="agreeRegister" checked="checked" style="margin-bottom: 10px"/>
	                        	<font style="font-size: 26px;margin-left: 5px">已阅读并同意</font><a href="/airport/1468833979496.ticket" class='c_blue' style="font-size: 20px;color: blue">《用户注册协议》</a>
	                        	<span class="Validform_checktip"></span>
	                        </p>
							<br>
							<button class='button bg-yello d_button block margin-big-top' id="submitBtn">
								提交
							</button>
							<a id="manageLink" href="/airport.action"></a>
						</form>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>