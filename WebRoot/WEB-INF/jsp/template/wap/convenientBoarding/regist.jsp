<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<script type="text/javascript" src="/template/wap/js/convientBording/regist.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	                	<jsp:param value="注册" name="title"/>
	                </jsp:include>
		        <div class="mobile-main">
		            <div class="order_form">
		                <form action="bjdjMember_regist.action" method="post" id="commonForm" name="commonForm">
		                    <div class="line clearfix mr40">
		                        <div class="x7">
									<input name="phone" id="phone" type="tel" class="input d_input b_l_grey " placeholder='请输入您的手机号码'/>
									<div id="mobileSms">
									<s:fielderror>
										<s:param>phone</s:param>
									</s:fielderror>
									</div>
		                        </div>
		                        <div class="x1"></div>
		                        <div class="x4">
		                        	<input id="getCaptcha" type="button" class="button d_button bg-yello block" value="获取验证码">
		                        </div>
		                        <div class="x12 mrtb20">
		                            <input name='authCode' type="tel" class="input d_input b_l_grey block" placeholder='请输入您的验证码'>
									<s:fielderror cssStyle="float:left;">
										<s:param>authCode</s:param>
									</s:fielderror>
		                        </div>
		                        <div class="x12 mrtb20">
		                        	<input id="agree" type="checkbox" checked="checked" name="agree" value="agree">
		                        	<label for="agree" style="padding: 0; border: 0;">阅读并接受</label>
		                        	<a href="airport/mianzeshengming.ticket" class='c_red'>《昆明长水机场用户注册协议》</a>
		                        </div>
		                        <div class="x12"><button class="button d_button bg-yello block" id="submitBtn">确定</button>
		                        </div>
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