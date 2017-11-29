<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta property="qc:admins" content="247747144456516711637571120724036134176" />
<!-- <link rel="stylesheet" href="/template/pc/css/animate.min.css"> -->
<link rel="stylesheet" href="/template/pc/css/pintuer.css">
<link rel="stylesheet" href="/template/pc/css/public.css">
<link rel="stylesheet" href="/template/pc/css/subpage.css">
<%@ include file="common/head.jsp"%>
<script type="text/javascript" src="/common/TuYou.js"></script>
<script type="text/javascript" src="/template/pc/js/pcMembersLogin.js"></script>
<!-- <script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script> -->
<script type="text/javascript">
	$(function(){
		$("#tb7_2").click(function(){
			$("#tb7_2 a").attr("class","");
			$("#tb7_1 a").attr("class","selected");
		});
		$("#tb7_1").click(function(){
			$("#tb7_1 a").attr("class","");
			$("#tb7_2 a").attr("class","selected");
		});
		/* var obj = new WxLogin({
                              id:"login_container", 
                              appid: "wxa40a0792f5b8ea1c", 
                              scope: "snsapi_login", 
                              redirect_uri: "http%3a%2f%2fm.kmcsia.com%2fairportPc.action",
                              state: JM.randomNumber,
                              style: "black",
                              href: ""
                            }); */
		$(".weixin").on("click",weixinlogin);
	});
	var lastUrl;
	function qqLogin(){
		lastUrl = $("#lastUrl").val()
		if(lastUrl){
			JM.goUrl("/IndexServlet.do?pc=true&lastUrl="+lastUrl);
		}else{
			JM.goUrl("/IndexServlet.do?pc=true");
		}
	}
	function weibologin(){
		lastUrl = $("#lastUrl").val()
		if(lastUrl){
			JM.goUrl("/memberWeiBo_weiBoLogin.action?pc=true$lastUrl="+lastUrl);
		}else{
			JM.goUrl("/memberWeiBo_weiBoLogin.action?pc=true");
		}
	}
	/* function weixinlogin(){
		var one = $("#one");
		var two = $("#login_container");
		one.hide();
		two.show();
	} */
</script>
<title>登录- 云南省昆明市长水机场</title>
</head>
<body>
	<div class="header">
        <div class="wp">
            <div class="min_header">
                <a class="logo" href="#"></a>
                <div class="header_right">
                    <!-- <a href="/pcMembers_toLoginPage.action" class="login_btn"></a> -->
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
	<%
		String interceptorUrl = (String)request.getAttribute("prevUrl");  //被拦截的情况，这个变量就存在
		String url = StringUtils.isBlank(interceptorUrl) ? request.getHeader("Referer") : interceptorUrl;  //如果不是被拦截的，说明是程序指定要跳转到登录界面的情况
	%>
	<div class="reg_login_box" id="one">
		<div class="wp posr">
			<div class="login_box">
				<div class="title_tabs">
					<ul id="tb7_">
						<li class="hovertab_6" id="tb7_1" onclick="x:HoverLi7(1);"><a
							href="javascript:;" >登录</a>
						</li>
						<li class="normaltab_6" id="tb7_2" onclick="x:HoverLi7(2);"><a
							href="javascript:;" class="selected">手机登录</a>
						</li>
					</ul>
				</div>
				<form action="" method="post" id="memberForm">
					<input type="hidden" id="lastUrl" value="<%=url %>"/>
					<div class="dis" id="tbc7_01">
						<label class='name_input'><input name='loginName'
							class="text_input" value="" placeholder='请输入手机号' dataType="m" id="loginName"/>
							<span class="Validform_checktip" style="display:inline;"></span>
						</label> <label class='pwd_input'><input name='loginPwd' type="password"
							class="text_input" value="" placeholder='请输入密码' dataType="s6-30" id="loginPwd"/>
							<span class="Validform_checktip" style="display:inline;"></span>
						</label>
						<div class="msg">
							<input type='checkbox' id='autoLogin' class="checkbox_input" />30天内自动登录<a
								href="/pcMembers_resetPwd.action" class="float-right">忘记密码 ？</a>
						</div>
						<button type="" class="yello_btn" id="login">登录</button>
						<a id="linkIndex" href="pcBjdjMember_loginPensonCenter.action" style="display: none"></a>
						<div type="" class="d_btn">
							还不是会员 ？ <a href="pcMembers_register.action">立即注册</a>
						</div>
							其他方式登录</br>
							<a href="javascript:;"><img
									src="/template/pc/images/footer_QQ1.gif"
									onmouseover="this.src='/template/pc/images/footer_QQ2.gif'"
									onmouseout="this.src='/template/pc/images/footer_QQ1.gif'" 
									onclick="qqLogin();"/>
							</a>
							<a href="javascript:;"><img
									src="/template/pc/images/footer_weibo1.gif"
									onmouseover="this.src='/template/pc/images/footer_weibo2.gif'"
									onmouseout="this.src='/template/pc/images/footer_weibo1.gif'" 
									onclick="weibologin();"/>
							</a>
							<!-- <a href="javascript:void(0);" class="weixin"><img
									src="/template/pc/images/footer_weixin1.gif"
									onmouseover="this.src='/template/pc/images/footer_weixin2.gif'"
									onmouseout="this.src='/template/pc/images/footer_weixin1.gif'" />
							</a> -->
					</div>
				</form>
				<form action="" method="post" id="mobileForm">
				<input type="hidden" id="lastUrl1" value="<%=url %>"/>
					<div class="undis" id="tbc7_02">
						<label class='name_input'> <input name='mobile'
							class="text_input" value="" placeholder='请输入手机号' dataType="m" id="mobileLogin" errorMsg="请填写正确的手机号码"/> 
							<span class="Validform_checktip" style="display:inline;"></span>
							</label>
						<div class="clearfix">
							<label class='email_input' style="width:245px;float:left">
								<input name='yzm' class="text_input" value=""
								placeholder='请输入动态验证码' dataType="s6-6" errormsg="请输入6位数字"
								id="authcode" /> 
								<span class="Validform_checktip" style="display:inline;"></span>
							</label>
							<button type='button' class="code_btn" id="yzm">获取手机动态码</button>
							<button type="button" value="获取验证码" style="display: none;"
								id="getCaptchaLogin"></button>
						</div>
						<br />
						<button type="" class="yello_btn" id="login">登录</button>
						<input type="hidden" value="<%=url%>" id="url" />
						<a id="linkIndex" href="pcBjdjMember_loginPensonCenter.action" style="display: none"></a>
						<div type="" class="d_btn">
							还不是会员 ？ <a href="pcMembers_register.action">立即注册</a>
						</div>
							其他方式登录</br>
							<a href="javascript:;"><img
									src="/template/pc/images/footer_QQ1.gif"
									onmouseover="this.src='/template/pc/images/footer_QQ2.gif'"
									onmouseout="this.src='/template/pc/images/footer_QQ1.gif'" 
									onclick="qqLogin();"/>
							</a>
							<a href="javascript:;"><img
									src="/template/pc/images/footer_weibo1.gif"
									onmouseover="this.src='/template/pc/images/footer_weibo2.gif'"
									onmouseout="this.src='/template/pc/images/footer_weibo1.gif'" 
									onclick="weibologin();"/>
							</a>
							<!-- <a href="javascript:void(0);" class="weixin"><img
									src="/template/pc/images/footer_weixin1.gif"
									onmouseover="this.src='/template/pc/images/footer_weixin2.gif'"
									onmouseout="this.src='/template/pc/images/footer_weixin1.gif'" 
									/>
							</a> -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="login_container" style="display: none;"></div>
	<%@ include file="common/left.jsp" %>
    <jsp:include page="common/bottom.jsp">
        <jsp:param value="1" name="no_help"/>
    </jsp:include>
</body>
</html>
