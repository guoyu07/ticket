<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/changePwd.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		    <s:if test="#session.fromApp == null && #parameters.fromApp == null">
		        <div class="mobile-top">
		            <div class="header">
		                便捷登机机场CRM后台
		            </div>
		        </div>
		        </s:if>
		        <s:else>
		        <div class="mobile-top" style="display: none;">
		            <div class="header">
		                便捷登机机场CRM后台
		            </div>
		        </div>
		        </s:else>
		        <div class="mobile-main">
		
		            <div class="order_form mr40">
		                <form action="" method="post">
		                    <div class="border-bottom margin-big-bottom padding-big-bottom fz24">工号：${systemUser.employeeId }</div>
		                    <div class="border-bottom margin-big-bottom padding-big-bottom fz24">账号：${systemUser.loginId }</div>
		                    <div class="border-bottom margin-big-bottom padding-big-bottom fz24">姓名：${systemUser.name }</div>
		                    <div class="border-bottom margin-big-bottom padding-big-bottom fz24">绑定手机号：<p id="phone" style="display: inline;">${systemUser.phone }</p></div>
		                    <div class="line"></div>
		                    <div class="tit_tab">
		                        <a href="javascript:;" class="change_pwd_btn">密码修改</a>
		                        <a href="employeeCRM_logout.action">退出登录</a>
		                    </div>
		                </form>
		            </div>
		            <div class="tit_tab">
		                <a href="employeeCRM_workOrderPage.action" class="b_yello c_grey">工单记录</a>
		                <a href="employeeCRM_personInfoPage.action" class="b_yello c_grey">个人中心</a>
		                
		            </div>
		            <s:if test="#session.isOrdinary != null">
		            <div class="tit_tab">
		            	<a href="/wapBjdjAppointment_Index.action" class="b_yello c_grey">返回首页</a>
		            </div>
		            </s:if>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
		    <div class="change_pwd_dialog box_dialog">
		        <div class="c_content b_white">
		            <div class="order_form mr40">
		                <form action="" method="post" id="changeForm">
		                    <div class="line clearfix margin-large-bottom">
		                        <div class="x4 height-large fz30">原密码：</div>
		                        <div class="x8">
		                            <input type="password" name='' class="input d_input block" placeholder='请输入您的原密码' id="oldPwd" dataType="s6-30">
		                        </div>
		                    </div>
		                    <div class="line clearfix margin-large-bottom">
		                        <div class="x4 height-large fz30">新密码：</div>
		                        <div class="x8"><input type="password" name='password1' class="input d_input " placeholder='请输入您的新密码' id="newPwd" dataType="s6-30"></div>
		                    </div>
		                    <div class="line clearfix margin-large-bottom">
		                        <div class="x4 height-large fz30">确认密码：</div>
		                        <div class="x8"><input type="password" name='' class="input d_input " placeholder='请再次输入您的新密码' dataType="s6-30" recheck="password1"></div>
		                    </div>
		                    <button type="submit" class='button d_button b_blue c_white' style="width:200px;margin:0 auto;" id="change">修改</button>
		                    <a href="${loginUrl }" id="loginUrl" style="display: none;"></a>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		<script type="text/javascript" src="../js/script.js"></script>
		<script type="text/javascript">
		    $(function(){
		        $('.change_pwd_btn').on('tap',function(){
		            $.do_dialog.open({'msg':$('.change_pwd_dialog'),'initBefore':function(){
		            }});
		        })
		    })
		</script>
	</body>
</html>