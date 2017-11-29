<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/template/pc/common/head.jsp"%>
<title>${electromobile } - 云南省昆明市长水机场</title>
<script type="text/javascript"
	src="/template/pc/js/convenientBoarding/eletromobile.js"></script>
<script type="text/javascript"
	src="/template/pc/js/convenientBoarding/payEletromobile.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/template/pc/common/top.jsp"%>

	<%@ include file="/WEB-INF/jsp/template/pc/common/nav.jsp"%>

	<div class="place w980 mt30">
		当前位置: <a href="/pcElectromobile.action">首页</a> > <a
			href="javascript:void(0);">${electromobile }</a>
	</div place w980 mt30>

	<div class="ddc w980 mt30">
		<form action="" method="post" id="payForm">
			<input type="hidden" value="${sessionMember.id }" id="memberId" />
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td align="right" width="130">名称：</td>
					<td width="780">${electromobile }</td>
				</tr>
				<tr>
					<td align="right">价格：</td>
					<td id="priceid">${electromobile_price }元</td>
				</tr>
				<tr>
					<td align="right">数量：</td>
					<td><input type="button" id="cutSum" value="-" class="Jia" /><input
						type="text" id="num" value="${count }" class="Jia" /><input
						type="button" value="+" id="addSum" class="Jia" /></td>
				</tr>
				<s:if test="#session.sessionMember == null">
					<tr>
						<td>
							<div>
							<p>
								<label for="notLoginBuy" style="height: 200px;width: 1000px;">
									<input type="radio" id="notLoginBuy" value="notLogin"
									checked="checked" name="purchaseType" style="width: 20px;" />
									直接购买 </label><label for="loginBuy"
									style="height: 200px;width: 1000px;"> <input
									type="radio" id="loginBuy" value="login" name="purchaseType"
									style="width: 20px;" /> 登陆购买 </label>
							</p>
							</div></td>
					</tr>
					<tr id="isLogin">
						<td align="right">手机号：</td>
						<td><input id="mobile" name="mobile" type="tel	"
							placeholder="请输入手机号码" dataType="m" id="mobile" />
							<p class="Validform_checktip" style="display:inline;"></p>
						</td>
					</tr>
					<tr>
						<td align="right">输入验证码：</td>
						<td><input id='inputCaptcha' type="tel" name="authCode"
							class="bhh" style="width:125px;" placeholder='请输入您的验证码'
							dataType="n6-6" errormsg="请输入6位数字" />
							<p class="Validform_checktip" style="display:inline;"></p>
							<p class="bhh">
								<a id="hqyzm"> <img src="/template/pc/images/YZM.gif" /> </a> <input
									type="button" id="getCaptcha" value="获取验证码"
									style="display: none;" />
							</p>
						</td>
					</tr>
				</s:if>

				<tr>
					<td colspan="2"><h2>请选择付款方式：</h2></td>
				</tr>
				<tr>
					<td colspan="2">
						<dl>
							<dt class="bhh">
								<a href=""><img src="/template/pc/images/login_QQ_1.gif"
									onmouseover="this.src='/template/pc/images/login_QQ_2.gif'"
									onmouseout="this.src='/template/pc/images/login_QQ_1.gif'" />
								</a>
							</dt>
							<dt class="bhh">
								<a href=""><img src="/template/pc/images/login_WeiXin_1.gif"
									onmouseover="this.src='/template/pc/images/login_WeiXin_2.gif'"
									onmouseout="this.src='/template/pc/images/login_WeiXin_1.gif'" />
								</a>
							</dt>
							<dt class="bhh">
								<a href=""><img src="/template/pc/images/login_WeiBo_1.gif"
									onmouseover="this.src='/template/pc/images/login_WeiBo_2.gif'"
									onmouseout="this.src='/template/pc/images/login_WeiBo_1.gif'" />
								</a>
							</dt>
							<dt class="bhh">
								<a><img src="/template/pc/images/login_BaiDu_1.gif"
									onmouseover="this.src='/template/pc/images/login_BaiDu_2.gif'"
									onmouseout="this.src='/template/pc/images/login_BaiDu_1.gif'"
									id="baiduPay" /> </a>
							</dt>
						</dl>
					</td>
				</tr>
				<tr>
					<td colspan="2"><small> 订单总额：<b id="total"></b> </small> <a
						id="go_pay"> <img src="/template/pc/images/go_pay.gif" /> </a>
					</td>
				</tr>
			</table>
		</form>
	</div ddc w980 mt30>
		<%@ include file="../common/left.jsp" %>
	<%@ include file="/WEB-INF/jsp/template/pc/common/bottom.jsp"%>
</body>
</html>
