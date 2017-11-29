<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
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
		            <div class="kv_pics">
		                <div class="swiper-container">
		                    <ul class="swiper-wrapper">
		                        <li class="swiper-slide"><img src="/template/wap/images/pic/83.jpg" ></li>
		                    </ul>
		                </div>
		            </div>
		
		            <div class="order_form mr40">
		                <form action="employeeCRM_login.action" method="post" id="commonForm" name="commonForm" >
		                    <div class="line clearfix margin-large-bottom">
		                        <div class="x4 height-large fz30">用户名：</div>
		                        <div class="x8">
		                            <input name="mix" id="mix" class="input d_input block" datatype="*2-18" placeholder='请输入您的用户名/电话/邮箱'>
		                        </div>
		                        <div class="x4 height-large fz30"></div>
		                        <div class="x8">
	                            	<p class="Validform_checktip"></p>
	                            </div>
		                    </div>
		                    <div class="line clearfix margin-large-bottom">
		                        <div class="x4 height-large fz30">密码：</div>
		                        <div class="x8">
		                        	<input name="password" id="password" class="input d_input" datatype="*6-16" type="password" placeholder='请输入您的密码'>
		                        </div>
		                        <div class="x4 height-large fz30"></div>
		                        <div class="x8">
	                            	<p class="Validform_checktip"></p>
	                            </div>
		                    </div>
		                    <button type="submit" class='button d_button b_blue c_white' style="width:200px;margin:0 auto;">登录</button>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>