<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			   <jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="内部员工密码重置"/>
			   </jsp:include>
				<div class="mobile-main">
					<form action="/airport_EmployeePwdResetNewPwd.action" id="memberForm" name="memberForm" alertConfirm="false">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="id" id="id" value="${airportEmployee.name}" />
						<div class="mr40">
							<div class="panel margin-big-bottom">
								<div class="panel-body line">
									<span class="x3 height-big fz22">新密码</span>
									<div class="x9">
										<input type="password" id="password" datatype="*" name="password" class='input' />
										<p class="Validform_checktip" style="display:inline;"></p>
									</div>
								</div>
							</div>
							<div class="panel margin-big-bottom">
								<div class="panel-body line">
									<span class="x3 height-big fz22">确认新密码</span>
									<div class="x9">
										<input type="password" datatype="*" recheck="password" class='input' />
										<p class="Validform_checktip" style="display:inline;"></p>
									</div>
								</div>
							</div>
							<div class='tit_tab'>
								<button type="" class="x5 button bg-yello d_button" id="submitBtn">
									确认
								</button>
								<div class="x2"></div>
								<button type="reset" class="x5 button bg-yello d_button">
									密码重置
								</button>
								<a id="manageLink" href="/airport_employeeLogin.action?versionFlag=${versionFlag}"></a>
							</div>
						</div>
					</form>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>