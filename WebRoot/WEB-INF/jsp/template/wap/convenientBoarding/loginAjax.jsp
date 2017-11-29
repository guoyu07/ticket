<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<% String url = request.getHeader("Referer"); 
	   String bjdjPage_id = request.getParameter("bjdjPage_id");
	%>
	<script type="text/javascript">
		window.onload = function(){
			XW.alert("为了更好的提供相应服务，请先进行登录操作。");
		};
		var bjdjPage_id;
		$(function(){
			var url = $("#url").val();
			
			bjdjPage_id = $("#bjdjPage_id").val();
		});
			function qqLogin(){
				JM.goUrl("/IndexServlet.do?wap=true&bjdjYinCanUrl=bjdj_indexAjax.action?bjdjPage_id=" + bjdjPage_id);
			}
			function weibologin(){
				JM.goUrl("/memberWeiBo_weiBoLogin.action?wap=true&bjdjYinCanUrl=bjdj_indexAjax.action?bjdjPage_id=" + bjdjPage_id);
			}
	</script>
	<ticket:cache group="便捷登机">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 会员登录
						<a href="" class="FR">
						<ticket:common type="newMessages"/>
					<s:if test="#request.newMessages > 0">
						<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
					</s:if>
					<s:else>
						<i class="icon-bell"></i>
					</s:else>
						</a>
					</div>
					<div class="header_search">
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" placeholder='你要搜索的内容' datatype="*" id="keyword" name="keyword">
							</label>
							<button class="button bg-sub" id="commonSearchBtn">
								搜索
							</button>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 会员登录
						<a href="" class="FR">
						<ticket:common type="newMessages"/>
					<s:if test="#request.newMessages > 0">
						<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
					</s:if>
					<s:else>
						<i class="icon-bell"></i>
					</s:else>
						</a>
					</div>
					<div class="header_search">
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" placeholder='你要搜索的内容' datatype="*" id="keyword" name="keyword">
							</label>
							<button class="button bg-sub" id="commonSearchBtn">
								搜索
							</button>
						</div>
					</div>
				</div>
			</s:else>
				<div class="mobile-main">
                    <div class="main_body">
    					<div class="logo_form mr40">
    					<form action="/${actionName }_login.action" id="memberForm" name="memberForm" alertConfirm="false">
    						<input type="hidden" name="destUrl" value="${destUrl }"/>
    						<input type="hidden" id="operationFlag" name="operationFlag" value="1">
    						<label>
    							<i class='user_icon'></i>
    							<input type="text" name="loginName" value="" datatype="*" placeholder='请输入您的用户名/电话/邮箱'/><br>
    							<p class="Validform_checktip" style="display:inline;"></p>
    						</label>
    						<label>
    							<i class='user_passwd'></i>
    							<input type="password" name="loginPwd" datatype="s6-30" errormsg="输入6-30位数字或字码的密码"
    								placeholder='输入6-30位数字或字码的密码'/><br>
    								<p class="Validform_checktip" style="display:inline;"></p>
    						</label>
    						<br>
    						<button class='button bg-yello d_button block margin-big-top' id="submitBtn">
    							登录
    						</button>
   					    	<a id="manageLink" href="<%=url %>"></a>
    						
    						<a type="button" class='button bg-sub d_button block margin-big-top' 
    							style="text-align: center; font-size: 28px; padding: 30px 20px;" 
    							href="/airportm_register.action">注册</a>
    						<p class='margin-big-top'>
    							<a href="/airportm_toResetPassword.action" class='c_blue fz22'>忘记密码</a>
    						</p>
    					</form>
    					</div>
    					<div class="other_login">
    						<!-- <div class="line mr40">
    							<div class="x12 text-center">
    								<img src="/template/wap/images/baidudl.jpg">
    							</div>
    						</div> -->
    						<input type="hidden" value="<%=bjdjPage_id %>" id="bjdjPage_id"/>
    						<div class="x12 text-center">
    								<img src="/template/wap/images/QQ230-48.JPG" onclick="qqLogin();">
    						</div>
    						<div class="x12 text-center">
    							<img src="/template/wap/images/weibo200-50.png" onclick="weibologin();">
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
	</ticket:cache>
</html>