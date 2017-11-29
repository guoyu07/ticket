<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <script src="/template/wap/layer/layer.js"></script>
	<script type="text/javascript">
	
$(function(){
					layer.alert('开发中，敬请期待。', {
						  skin: 'layui-layer-lan' //样式类名
						  ,closeBtn: 0
						});
				});
		/*
		window.onload = function(){
			XW.alert("员工通道暂未开启使用。");
		};*/
		
	</script>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="内部员工登录"/>
				</jsp:include>
				<div class="mobile-main">
					<form action="/airport_employeeLogin.action" id="memberForm" name="memberForm" alertConfirm="false">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
			            <div class="logo_form mr40">
			                <label><i class='user_icon'></i><input type="text" class='' name="phone" id="phone" datatype="m" value="" placeholder='请输入手机号码' style='width:350px'>
			                		<p class="Validform_checktip" style="display:inline;"></p>
			                </label>
			                <label><i class='user_code'></i>
			                <input type="text" class='' name="smsCode" value="" datatype="*" placeholder='请输入验证码' nullmsg="验证码不能为空" datatype="*" style='width:280px;'>
			                <a href="#" class='button bg-sub sendSmsClass' sendType="login" elementId="phone">发送验证码</a>
			                <p class="Validform_checktip" style="display:inline;"></p></label>
			                <label><i class='user_passwd'></i><input type="password" class='' id="password" name="password" value="" placeholder='输6-30位数字或字母密码'></label><br>
			                <p class="Validform_checktip" style="display:inline;"></p>
			                <button class='button bg-yello d_button block margin-big-top' id="submitBtn">登录</button>
			                <span class="c_blue padding-large-top fz20" id="resetPwd">密码重置</span>
			                <a id="manageLink" href="/airport_loginSuccessIndex.action?"></a>
			            </div>
		            </form>
		        </div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
	</body>
	</ticket:cache>
</html>