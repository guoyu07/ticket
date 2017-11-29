<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/template/pc/common/head.jsp"%>
		<title>便捷登机 - 云南省昆明市长水机场</title>
		<script type="text/javascript" src="/template/pc/js/convenientBoarding/bjdj.js"></script>
	</head>
	
	<body>
		<%@ include file="/WEB-INF/jsp/template/pc/common/top.jsp"%>
		<%@ include file="/WEB-INF/jsp/template/pc/common/nav.jsp"%>
	
		<div class="place w980 mt30">
			当前位置: <a href="/airportPc.action">首页</a> > <a href="javascript:;">便捷登机</a>
		</div>
	
		<div class="ddc w980 mt30">
			<form action="bjdjOrderTemplate_generateOrder.action" method="post" id="pcForm">
				<a href="pcOrder_payMethod.action" id="manageLink" style="display: none;"></a>
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td align="right" width="130">名称：</td>
						<td width="780">便捷登机</td>
					</tr>
					<tr>
						<td align="right" width="130">套餐：</td>
						<td width="780">
							<select name="id" id="package">
								<s:iterator var="p" value="bjdjServicePackages">
								<option price="${p.price }" value="${p.id }" description="${p.description }">${p.name }</option>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">价格：</td>
						<td class=bjdj_price>${package1.price }元</td>
					</tr>
					<tr>
						<td align="right">数量：</td>
						<td>
							<input type="button" value="-" class="Jia icon-minus-square-o" />
							<input type="text" id="number" name="count" value="${count == null ? 1 : count }" class="Jia" datatype="n"/>
							<input type="button" value="+" class="Jia icon-plus-square-o" />
							<span class="Validform_checktip" style="display:inline; color:red"></span>
						</td>
					</tr>
					<s:if test="#session.sessionMember == null">
					<tr>
						<td align="right"></td>
						<td>
							<div>
								<label for="notLoginBuy" style="height: 200px;width: 500px;">
									<input type="radio" id="notLoginBuy" value="notLogin" checked="checked" name="purchaseType" style="width: 20px;" />
									直接购买
								</label> 
								<label for="loginBuy" style="height: 200px;width: 500px;"> 
									<input type="radio" id="loginBuy" value="login" name="purchaseType" style="width: 20px;" onclick="location.href='pcBjdjMember_loginIndexAjax.action?bjdjPage_id=${bjdjPage_id}'"/> 
									登陆购买 
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right">手机号：</td>
						<td>
							<input class="mobile" name="mobile" type="text" placeholder="请输入手机号码" datatype="*"/>
							<p class="Validform_checktip" style="display:inline; color:red"></p>
						</td>
					</tr>
					<tr>
						<td align="right">输入验证码：</td>
						<td>
							<input type="text" name="authCode" class="bhh inputCaptcha" style="width:125px;" placeholder='请输入您的验证码' datatype="n"/> 
							<p class="bhh">
								<img src="/template/pc/images/YZM.gif" class="getCaptcha"/>
								<input type="button" class="getCaptcha" value="正在获取验证码" style="display: none;" disabled="disabled"/>
							</p>
							<div class="Validform_checktip" style="display:inline; color:red"></div>
						</td>
					</tr>
					</s:if>
					<tr>
						<td colspan="2">
							<small>订单总额：<b class="total_price"></b></small> 
							<a><input type="image" src="/template/pc/images/go_pay.gif" style="border: none;"/></a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@ include file="../common/left.jsp" %>
		<%@ include file="/WEB-INF/jsp/template/pc/common/bottom.jsp"%>
	</body>
</html>
