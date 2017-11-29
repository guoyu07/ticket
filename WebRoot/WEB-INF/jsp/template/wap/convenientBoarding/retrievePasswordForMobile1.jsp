<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	                	<jsp:param value="手机找回" name="title"/>
	                </jsp:include>
		        <div class="mobile-main">
		            <div class="order_form">
		                <form action="bjdjMember_checkAuthForMobile.action" method="post">
		                    <div class="line clearfix mr40">
		                        <div class="x7">
		                        	<input id="phone" name='phone' class="input d_input b_l_grey " placeholder='请输入您的手机号码'>
		                        	<s:fielderror>
		                        		<s:param>phone</s:param>
		                        	</s:fielderror>
		                        </div>
		                        <div class="x1"></div>
		                        <div class="x4">
		                        	<input type="button" sendType="register" elementId="phone"  class="sendSmsClass button d_button bg-yello block" value="获取验证码">
		                        </div>
		
		                        <div class="x12 mrtb20">
		                            <input id="authCode" name='authCode' type="tel" class="input d_input b_l_grey block" placeholder='请输入您的验证码'>
		                        	<s:fielderror>
		                        		<s:param>authCode</s:param>
		                        	</s:fielderror>
		                        </div>
		                        <div class="x12"><button class="button d_button bg-yello block">确定</button></div>
		                    </div>
		                </form>
		            </div>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		            <%@ include file="../common/quickNav.jsp"%>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>