<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="密码重置" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<form action="/airportm_resetPassword.action" id="memberForm" alertConfirm="false">
					<div class="logo_form mr40">
						<label>
							<i class='user_icon'></i>
							<input type="text" class='' name="mobile" id="mobile" value="" datatype="m" nullmsg="请输入手机号" 
								placeholder='请输入手机号码' style='width: 350px'>
								<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<label>
							<i class='user_code'></i>
							<input type="text" class='' name="smsCode" value="" datatype="*" placeholder='请输入验证码' dataType="n4-4"
								style='width: 330px;'>
							<a href="javascript:;" class='button bg-sub sendSmsClass' sendType="resetPassword" elementId="mobile">发送验证码</a><br>
							<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<label>
							<i class='user_passwd'></i>
							<input type="password" class='' id="password" name="password" value="" datatype="s6-16" placeholder='输入新密码'>
							<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<br>
						<label>
							<i class='user_passwd'></i>
							<input type="password" class='' name="recheckPwd" recheck="password" value="" datatype="*" 
							errormsg="两次输入的密码不一致" placeholder='确认新密码'>
							<p class="Validform_checktip" style="display:inline;"></p>
						</label>
						<br>
						<button class='button bg-yello d_button block margin-big-top' id="submitBtn">
							修改密码
						</button>
						<a id="manageLink" href="/airport.action"></a>
					</div>
					</form>
				</div>
				<%@include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
	</body>
</html>