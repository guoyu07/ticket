<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<script type="text/javascript">
		var bjdjPage_id;
		$(function(){
			bjdjPage_id = $("#bjdjPage_id").val();
		});
		function qqLogin(){
			JM.goUrl("/IndexServlet.do?pc=true&bjdjYinCanUrl=bjdj_indexAjax.action?bjdjPage_id=" + bjdjPage_id);
		}
		function weibologin(){
			JM.goUrl("/memberWeiBo_weiBoLogin.action?pc=true&bjdjYinCanUrl=bjdj_indexAjax.action?bjdjPage_id=" + bjdjPage_id);
		}
	</script>
	<head>
		<%@ include file="common/head.jsp" %>
		<title>便捷登机首页 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<div class="head">
		<ul class="w980">
			<a href="javascript:void(0);"><h1>云南省昆明市长水机场</h1> </a>
		</div head>
		
		<%@ include file="common/nav.jsp" %>
		<% String url = request.getHeader("Referer"); 
		   String bjdjPage_id = request.getParameter("bjdjPage_id");
		%>
		<div class="place w980 mt30">当前位置: <a href="airportPc.action">首页</a> > <a href="javascript:;">便捷登机登录</a></div place w980 mt30>
		
		<div class="bjdj_door w980 mt30">
			<ul id="tb7_">
		        <li class="hovertab_6" id="tb7_1" onclick="x:HoverLi7(1);"><a href="javascript:void(0);">普通登录</a></li>
		        <li class="normaltab_6" id="tb7_2" onclick="x:HoverLi7(2);"><a href="javascript:void(0);">手机验证登录</a></li>
		    </ul>
	
			<form action="" method="post" id="memberForm1">
				<dl class="dis" id="tbc7_01">
					<p>
						登录名： <input name="loginName" type="text"
							placeholder='请输入手机号' dataType="m" id="loginName" errorMsg="请填写正确的手机号码"/>
							<span class="Validform_checktip" style="display:inline;"></span>
					</p>
					<p>
						密&nbsp;&nbsp;&nbsp;码： <input name="loginPwd" type="password"
							placeholder='请输入6-30位密码' dataType="s6-30" id="loginPwd" />
							<span class="Validform_checktip" style="display:inline;"></span>
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="/template/pc/images/other_login_button.gif" id="login"/>
					</p>
					<p>
						<a href="/pcMembers_resetPwd.action">忘记密码？</a>
					</p>
				</dl>
			</form>
			<form action="" method="post" id="mobileForm">
				<dl class="undis" id="tbc7_02">
					<p>
						手机号码： <input name="mobile" type="text" placeholder='请输入手机号'
							dataType="m" id="mobileLogin" />
							<span class="Validform_checktip" style="display:inline;"></span>
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;
						<img src="/template/pc/images/YZM.gif" id="yzm" />
						<input type="button" value="获取验证码" style="display: none;"
							id="getCaptchaLogin" />
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;验证码： <input name="yzm" type="text"
							placeholder='请输入验证码' dataType="s6-6" errormsg="请输入6位数字"
							id="authcode" /> 
							<span class="Validform_checktip" style="display:inline;"></span>
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="/template/pc/images/other_login_button.gif" id="login"/>
						<input type="hidden" value="/pcOrder_indexAjax.action?bjdjPage_id=<%=bjdjPage_id %>" id="url" />
						<a id="linkIndex" href="pcBjdjMember_loginPensonCenter.action" style="display: none"></a>
					</p>
				</dl>
			</form>
			
			<h3>使用第三方登录</h3>
			<dd>
				<dt class="bhh"><a href="javascript:qqLogin();"><img src="template/pc/images/login_QQ_1.gif" onmouseover="this.src='template/pc/images/login_QQ_2.gif'" onmouseout="this.src='template/pc/images/login_QQ_1.gif'" /></a></dt>
				<dt class="bhh"><a href="javascript:;"><img src="template/pc/images/login_WeiXin_1.gif" onmouseover="this.src='template/pc/images/login_WeiXin_2.gif'" onmouseout="this.src='template/pc/images/login_WeiXin_1.gif'" /></a></dt>
				<dt class="bhh"><a href="javascript:weibologin();"><img src="template/pc/images/login_WeiBo_1.gif" onmouseover="this.src='template/pc/images/login_WeiBo_2.gif'" onmouseout="this.src='template/pc/images/login_WeiBo_1.gif'" /></a></dt>
				<dt class="bhh"><a href="javascript:;"><img src="template/pc/images/login_BaiDu_1.gif" onmouseover="this.src='template/pc/images/login_BaiDu_2.gif'" onmouseout="this.src='template/pc/images/login_BaiDu_1.gif'" /></a></dt>
			</dd>
		</div bjdj_door w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
