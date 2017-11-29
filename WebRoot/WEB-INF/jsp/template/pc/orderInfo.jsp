<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/orderInfo.js"></script>
<title>订单详情 - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="common/top.jsp"%>
	<%@ include file="common/nav.jsp"%>
	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh"><p>站内公告：</p>
			</li>
			<li id="rollTextMenu1" style="display:block">长水机场平台正式上线，欢迎各位用户监督指导1</li>
			<li id="rollTextMenu2" style="display:none">长水机场平台正式上线，欢迎各位用户监督指导2</li>
			<li id="rollTextMenu3" style="display:none">长水机场平台正式上线，欢迎各位用户监督指导3</li>
		</ul>

		<dl class="hh">
			<dd class="bhh">
				<a href="javascript:rollText(-1);"><img
					src="/template/pc/images/pre1.gif"
					onmouseover="this.src='/template/pc/images/pre2.gif'"
					onmouseout="this.src='/template/pc/images/pre1.gif'"> </a>
			</dd>
			<dd class="bhh">
				<a href="javascript:rollText(1);"><img
					src="/template/pc/images/next1.gif"
					onmouseover="this.src='/template/pc/images/next2.gif'"
					onmouseout="this.src='/template/pc/images/next1.gif'"> </a>
			</dd>
		</dl>
	</div gg w980 mt30>
	<SCRIPT type=text/javascript>
			<!--
			var rollText_k=3; //菜单总数
			var rollText_i=1; //菜单默认值
			//setFocus1(0);
			rollText_tt=setInterval("rollText(1)",8000);
			function rollText(a){
			clearInterval(rollText_tt);
			rollText_tt=setInterval("rollText(1)",8000);
			rollText_i+=a;
			if (rollText_i>rollText_k){rollText_i=1;}
			if (rollText_i==0){rollText_i=rollText_k;}
			//alert(i)
			 for (var j=1; j<=rollText_k; j++){
			 document.getElementById("rollTextMenu"+j).style.display="none";
			 }
			 document.getElementById("rollTextMenu"+rollText_i).style.display="block";
			} 
			//-->
		</SCRIPT>

	<%@ include file="common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="/airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_myMessage.action"><img
					src="/template/pc/images/ico5_1.gif"
					onmouseover="this.src='/template/pc/images/ico5_2.gif'"
					onmouseout="this.src='/template/pc/images/ico5_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="/airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" /> </a>
			</li>
		</ul>
	</div menu1>

	<div class="w980">
		<div class="L bhh">
			<ul>
				<li><a href="pcOrder_allOrder.action"><img
						src="/template/pc/images/button1_2.gif" /> </a>
				</li>
				<li><a href="airportPc_myAccount.action"><img
						src="/template/pc/images/button2_1.gif"
						onmouseover="this.src='/template/pc/images/button2_2.gif'"
						onmouseout="this.src='/template/pc/images/button2_1.gif'" /> </a>
				</li>
				<li><a href="payXX.html"><img
						src="/template/pc/images/button3_1.gif"
						onmouseover="this.src='/template/pc/images/button3_2.gif'"
						onmouseout="this.src='/template/pc/images/button3_1.gif'" /> </a>
				</li>
				<li><a href="airportPc_myFavorite.action"><img
						src="/template/pc/images/button4_1.gif"
						onmouseover="this.src='/template/pc/images/button4_2.gif'"
						onmouseout="this.src='/template/pc/images/button4_1.gif'" /> </a>
				</li>
			</ul>
		</div L bhh>
		<div class="cfzz hh">
			<div class="order_info">
				<table>
					<tr>
						<th width="100px">订单名称：</th>
						<td>${order.name }</td>
					</tr>
					<tr>
						<th>订单号：</th>
						<td>${order.number }</td>
					</tr>
					<tr>
						<th>状态：</th>
						<td>${order.state.value }</td>
					</tr>
					<tr>
						<th>总价：</th>
						<td><span>￥${order.price * order.count }</span></td>
					</tr>
					<tr>
						<th>数量：</th>
						<td>${order.count }</td>
					</tr>
					<tr>
						<th>手机号：</th>
						<td>${order.mobile }</td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0"
					style="font-size:13px; text-align:center;">
					<tr style="background-color:#eee;">
						<td width="200">服务码</td>
						<td width="160">过期时间</td>
						<td width="140">状态</td>
						<td width="83">信息</td>
					</tr>
					<s:iterator value="order.serviceCodes" var="c">
						<tr>
							<td>${c.code }</td>
							<td><s:date name="#c.expireTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td><s:if
									test="#c.expireTime.getTime() < @java.lang.System@currentTimeMillis()">
		                                		已过期
		                                	</s:if> <s:else>
		                                		${c.state.value }
		                                	</s:else></td>
							<td><s:if test="!serviceCodeService.isMine(#c)">
		                                		已赠送给：
		                                		<s:if
										test="#c.member.realName == null || #c.member.realName.trim() == ''">
		                                			${c.member.phone }
		                                		</s:if>
									<s:else>
		                                			${c.member.realName }
		                                		</s:else>
								</s:if> <s:elseif test="serviceCodeService.canComment(#c)">
									<a href="javascript:;"
										class="button fz20 margin-bottom block text-center">评价</a>
								</s:elseif> <s:elseif
									test="#c.state.name == 'expired' || #c.expireTime.getTime() < @java.lang.System@currentTimeMillis()">
									<a href="pcOrder_serviceCodeDelayPage.action?codeId=${c.id }"
										class="button fz20 margin-bottom block text-center">延期</a>
								</s:elseif>
								<s:if test="#c.state.name == 'activated'">
									<a href="javascript:unactived('${c.id }');"
													class="button fz20 margin-bottom block text-center">取消激活</a>
								</s:if>
							</td>
						</tr>
					</s:iterator>
				</table>
				<button class="order_info_btn" onclick="back()">返回订单列表</button>
			</div>
		</div>
	</div w980>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
