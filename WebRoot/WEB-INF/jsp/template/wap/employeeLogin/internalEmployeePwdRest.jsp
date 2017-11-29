<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="内部员工密码重置"/>
			   </jsp:include>
				<div class="mobile-main">
				<form action="/airport_employeeResetPwd.action" id="memberForm" name="memberForm" alertConfirm="false">
					<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
					<div class="mr40">
						<div class="panel margin-big-bottom">
							<div class="panel-body line">
								<span class="x3 height-big fz22">员工姓名</span>
								<div class="x9">
									<input type="text" name="name" id="name" datatype="*" class='input' />
									<p class="Validform_checktip" style="display:inline;"></p>
								</div>
							</div>
							
						</div>
						<div class="panel margin-big-bottom">
							<div class="panel-body line">
								<span class="x3 height-big fz22">手机号码</span>
								<div class="x9">
									<input type="text" id="phone" name="phone" datatype="m" class='input input-auto' />
									<a href="#" type="" sendType="findPassword" elementId="phone" class='button bg-sub sendSmsClass'>
										发送验证码
									</a>
									<p class="Validform_checktip" style="display:inline;"></p>
								</div>
							</div>
							
						</div>
						<div class="panel margin-big-bottom">
							<div class="panel-body line">
								<span class="x3 height-big fz22">员工工号</span>
								<div class="x9">
									<input type="text" id="employeeId" name="employeeId" datatype="*" class='input' />
									<p class="Validform_checktip" style="display:inline;"></p>
								</div>
							</div>
							
						</div>
						<div class="panel margin-big-bottom">
							<div class="panel-body line">
								<span class="x3 height-big fz22">验证码</span>
								<div class="x9">
									<input type="text" name="smsCode" datatype="*" class='input' />
									<p class="Validform_checktip" style="display:inline;"></p>
								</div>
							</div>
							
						</div>
						<div class='tit_tab text-center'>
							<div class="x4"></div>
							<button type="" class="x5 button bg-yello d_button" id="submitBtn">
								确定
							</button>
							<a id="manageLink" href="/airport_EmployeePwdResetNewPwd.action?versionFlag=${versionFlag}"></a>
						</div>
					</div>
				</form>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>