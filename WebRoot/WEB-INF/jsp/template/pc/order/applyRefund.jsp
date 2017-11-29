<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/orderInfo.js"></script>
<title>申请转增 - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/nav.jsp"%>
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
		var rollText_k = 3; //菜单总数
		var rollText_i = 1; //菜单默认值
		//setFocus1(0);
		rollText_tt = setInterval("rollText(1)", 8000);
		function rollText(a) {
			clearInterval(rollText_tt);
			rollText_tt = setInterval("rollText(1)", 8000);
			rollText_i += a;
			if (rollText_i > rollText_k) {
				rollText_i = 1;
			}
			if (rollText_i == 0) {
				rollText_i = rollText_k;
			}
			//alert(i)
			for ( var j = 1; j <= rollText_k; j++) {
				document.getElementById("rollTextMenu" + j).style.display = "none";
			}
			document.getElementById("rollTextMenu" + rollText_i).style.display = "block";
		}
	//-->
	</SCRIPT>

	<%@ include file="../common/personalData.jsp"%>

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
				<li><a href="javascript:void(0)"><img
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
				<!-- <h3>1.选择退款方式</h3> -->
				<form action="pcOrder_orderRefund.action" method="post" id="pcForm">
					<a id="manageLink" href="pcOrder_allOrder.action" style="display: none;"></a>
					<input type="hidden" name="orderId" value="${order.id }" />
					<!-- <div class="order_input">
						<select name="">
							<option value="">原渠道退回</option>
							<option value="">退至资金池</option>
						</select>
					</div> -->
					<h3>选择退款原因</h3>
					<table>
						<s:iterator value="dicts" var="dict" status="st">
							<tr>
								<td align="left">${dict.description }</td>
								<td align="right">
									<input name="reason" value="${dict.description }" class='d_checkbox' 
										type="radio" <s:if test="#st.first">checked="checked"</s:if> />
								</td>
							</tr>
						</s:iterator>
					</table>
					<input type="submit" value="申请退款" class="order_info_btn" />
				</form>
				<s:fielderror>
					<s:param>order</s:param>
				</s:fielderror>
			</div>
		</div>
	</div w980>
	<%@ include file="../common/left.jsp" %>
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>
