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
	                	<jsp:param value="邮箱找回" name="title"/>
	                </jsp:include>
		        <div class="mobile-main">
		            <div class="order_form">
		                <form action="bjdjMember_modifyPasswordForEmail.action?versionFlag=site" method="post">
		                    <div class="line clearfix mr40">
		                        <div class="x12">
		                        	<input name='loginPwd' type="password" class="input d_input  block" placeholder='请输入新的密码，密码包含大小写字母与数字，不少于6位'>
		                        	<s:fielderror>
		                        		<s:param>loginPwd</s:param>
		                        	</s:fielderror>
		                        </div>
		                        <div class="x12 mrtb20">
		                            <input name='rloginPwd' type="password" class="input d_input block" placeholder='请再次输入密码'>
		                            <s:fielderror>
		                        		<s:param>rloginPwd</s:param>
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