<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="便捷登机">
	<script type="text/javascript" src="/template/wap/js/electromobile/orderConfirm.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="订单确认" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="order_form">
						<form action="/${actionName}_constructOrder.action" method="post" id="submitForm">
							<input type="hidden" name="count" value="1" />
							<input type="hidden" name="destUrl" value="electromobile_confirmPage2.action" />
							<div class='label'>
								<i class='fly_icon'></i>电瓶车 
								<span>
									<font class="c_red" id="electromobile_price">${electromobile_price}</font>RMB
								</span>
							</div> 
							<div class='label'>
								数量 
								<span class='text-center line-large' style='width:100px'>

									<i class='icon-minus-square-o x4'></i>
                                    <div class="x4">
										<font id="number" class="c_red">1</font>
                                    </div>
									<i class='icon-plus-square-o x4'></i>
								</span>
							</div> 
							<div class='label'>
								<i class='count_icon'></i>总价 
								<span><font id="total_price" class="c_red">${electromobile_price}</font>RMB</span>
							</div>
							<div>
                                <p>
    								<label for="notLoginBuy" style="padding: 0; border: 0;"><input type="radio" id="notLoginBuy" value="notLogin" checked="checked" name="purchaseType">免登陆直接购买</label>&nbsp;&nbsp;&nbsp;&nbsp;
    								<label for="loginBuy" style="padding: 0; border: 0;"><input type="radio" id="loginBuy" value="login" name="purchaseType">登陆购买</label>
                                </p>
							</div>
							<div class="line clearfix mr40">
								<div class="x7">
									<input id="mobile" name="mobile" type="tel" class="input d_input b_l_grey" placeholder='请输入您的手机号'>
									<div id="mobileSms">
									<s:fielderror cssStyle="float:left;">
										<s:param>mobile</s:param>
									</s:fielderror>
									</div>
								</div>
								<div class="x1"></div>
								<div class="x4">
									<input type="button" id="getCaptcha" class="button d_button bg-yello block" value="获取验证码"/>
								</div>
								<div class="x12 mrtb20">
									<input id='inputCaptcha' type="tel" name="authCode" class="input d_input b_l_grey block" placeholder='请输入您的验证码'>
									<s:fielderror cssStyle="float:left;">
										<s:param>authCode</s:param>
									</s:fielderror>
								</div>
								<div class="x12">
									<input type="submit" id="submitBtn" class="button d_button bg-yello block" value="确认">
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="mobile-foot">
					<p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN
						AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
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