<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="head.jsp" %>
	<body  style=" background:url(/manager/images/loginbg2.jpg)center no-repeat;background-size: cover;" >
		<form action="/systemUser_login.action" id="commonForm" name="commonForm" alertConfirm="false">
			<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
			<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
			<div class="login_warp">
				<div class="login_box">
					<div class="login_bg">
						<div class="login">
							<li>
								<input type="text" name="loginId" id="loginId" class="login_input_ico1" datatype="*2-18" placeholder="请输入登录名"/>
								<p class="Validform_checktip"></p>
							</li>
							<li>
								<input type="password" name="password" id="password" class="login_input_ico2" datatype="*6-16" placeholder="请输入密码"/>
								<p class="Validform_checktip"></p>
							</li>
							<li>
								<input type="text" name="verificateCode" id="verificateCode" maxlength="4" class="login_input_ico3" datatype="*4-4" placeholder="请输入验证码">
								<span><img src="/verificateCode.action" width="94" height="32" id="verificateCodeSrc"></span>
								<p class="Validform_checktip"></p>
							</li>
							<li>
								<input type="submit" value="登陆" class="login_btn">
								<s:if test="#request.loginRedirectUrl != null">
									<a id="manageLink" href="${loginRedirectUrl }"></a>
								</s:if>
								<s:else>
									<a id="manageLink" href="/systemUser.action"></a>
								</s:else>
							</li>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>