<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
    <script src="/template/wap/layer/layer.js"></script>
	<script type="text/javascript">
	
		$(function(){
			layer.alert('为了给您提供更好的服务，请您进行登录操作。', {
				  skin: 'layui-layer-lan' //样式类名
				  ,closeBtn: 0
				});
		});
		/*window.onload = function(){
			XW.alert("为了更好的提供相应服务，请先进行登录操作。");
		};*/
		function qqLogin(){
			JM.goUrl("/IndexServlet.do?wap=true");
		}
		function weibologin(){
			JM.goUrl("/memberWeiBo_weiBoLogin.action?wap=true");
		}
	</script>
    <style type="text/css">
    .logo_form label{ border:none; padding:20px 10px 0px;}

	.Validform_checktip{ display:block; margin-top:10px; font-size:26px; height:30px;}
    </style>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="会员登录" name="title"/>
				</jsp:include>
				<div class="mobile-main">
                    <div class="main_body">
    					<div class="logo_form mr40">
    					<form action="/airportm_login.action" id="memberForm" name="memberForm" alertConfirm="false">
    						<input type="hidden" id="operationFlag" name="operationFlag" value="1">
    						<label>
    							<i class='user_icon'></i>
    							<input type="text" class='' id="mobile" name="mobile" value="" datatype="m" placeholder='请输入手机号码'/><br>
                                <hr style="border-top:1px solid #ccc; margin:20px 0px 0px; display:block;">
    							<p class="Validform_checktip" ></p>
    						</label>
    						<label>
    							<i class='user_passwd'></i>
    							<input type="password" class='' id="password" name="password" datatype="s6-30" errormsg="输入6-30位数字或字码的密码" value=""
    								placeholder='输入6-30位数字或字码的密码'/><br>
                                    <hr style="border-top:1px solid #ccc;margin:20px 0px 0px;">
    								<p class="Validform_checktip" ></p>
    						</label>
    						<br>
                             
    						<button class='button bg-yello d_button block margin-big-top' id="submitBtn">
    							登录
    						</button>
    						<s:if test="#session.prevUrlSession != null">
    					    	<a id="manageLink" href="${prevUrlSession }"></a>	
    					    </s:if>
    					    <s:elseif test='flag=="focus"'>
    					    	<a id="manageLink" href="javascript:location.replace(decodeURI('${cookie.preUrl.value }'))"></a>
    					    </s:elseif> 
    						<s:elseif test="#request.prevUrl == null">
    					    	<a id="manageLink" href="${empty header.referer ? '/airport.action' : header.referer }"></a>
    					    </s:elseif>
    					    <s:else>
    					    	<a id="manageLink" href="${prevUrl}"></a>	
    					    </s:else>
    						
    						<a type="button" class='button bg-sub d_button block margin-big-top' 
    							style="text-align: center; font-size: 28px; padding: 30px 20px;" 
    							href="/airportm_register.action">注册</a>
    						<p class='margin-big-top'>
    							<a href="/airportm_toResetPassword.action" class='c_blue fz22'>忘记密码</a>
    						</p>
    					</form>
    					</div>
    					<div class="other_login">
    						<div class="line mr40">
    							<!-- <div class="x12 text-center">
    								<img src="/template/wap/images/baidudl.jpg">
    							</div> -->
    							<div class="x12 text-center">
    								<img src="/template/wap/images/QQ230-48.JPG" onclick="qqLogin();">
    							</div>
    							<div class="x12 text-center">
    								<img src="/template/wap/images/weibo200-50.png" onclick="weibologin();">
    							</div>
    							<!-- <a href="IndexServlet.do">QQ登录</a> -->
    							<!-- <a href="memberWeiBo_weiBoLogin.action">新浪微博登录</a> -->
    						</div>
    						<!-- <div class="text-center">
    							<a href="" class='c_blue fz20'><i
    								class='icon-chevron-circle-down margin-right '></i>查看详情</a>
    						</div> -->
    					</div>
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