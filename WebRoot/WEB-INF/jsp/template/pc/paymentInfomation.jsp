<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="common/head.jsp"%>
<title>我的订单 - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>
	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh">
				<p>站内公告：</p></li>
			<li id="rollTextMenu1" style="display: block">
				长水机场平台正式上线，欢迎各位用户监督指导1</li>
			<li id="rollTextMenu2" style="display: none">
				长水机场平台正式上线，欢迎各位用户监督指导2</li>
			<li id="rollTextMenu3" style="display: none">
				长水机场平台正式上线，欢迎各位用户监督指导3</li>
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
	<%@ include file="common/personalData.jsp"%>

	<%@ include file="common/personalMenu.jsp"%>
	<div class="MemberCenter w980">
		<div class="L bhh">
			<ul>
				<li><a href="pcOrder_allOrder.action"><img src="/template/pc/images/button1_1.gif"
						onmouseover="this.src='/template/pc/images/button1_2.gif'"
						onmouseout="this.src='/template/pc/images/button1_1.gif'" />
				</a>
				</li>
				<li><a href="/pcMembers_personalSetting.action"><img src="/template/pc/images/button2_1.gif"
						onmouseover="this.src='/template/pc/images/button2_2.gif'"
						onmouseout="this.src='/template/pc/images/button2_1.gif'" />
				</a>
				</li>
				<li><a href="javascript:void(0);"><img src="/template/pc/images/button3_2.gif" />
				</a>
				</li>
				<li><a href="airportPc_myFavorite.action"><img
						src="/template/pc/images/button4_1.gif"
						onmouseover="this.src='/template/pc/images/button4_2.gif'"
						onmouseout="this.src='/template/pc/images/button4_1.gif'" />
				</a>
				</li>
			</ul>
		</div>

		<div class="payXX hh">
			<dl>
				<dd class="bhh">
					账户余额：<b>0元</b> <span><a href="javascript:;">充值</a>
					</span>
				</dd>
				<dt class="hh">
					我的积分：<b>0分</b> <span><a href="javascript:;">兑换积分</a>
					</span> <span><a href="javascript:;">如何兑换积分</a>
					</span>
				</dd>
			</dl>

			<ul>
				<!-- <li><small><a href="javascript:;"><img src="/template/pc/images/payQX.gif" />
					</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;"><img
							src="/template/pc/images/payJB.gif" />
					</a>
				</small><img src="/template/pc/images/img.jpg" width="120" height="75" align="absmiddle" />
					&nbsp;中国建设银行 &nbsp;&nbsp;尾号5435 &nbsp;&nbsp;储蓄卡 &nbsp;&nbsp;姓名：曾山</li>
				<li><small><a href="javascript:;"><img src="/template/pc/images/payQX.gif" />
					</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;"><img
							src="/template/pc/images/payJB.gif" />
					</a>
				</small><img src="/template/pc/images/img.jpg" width="120" height="75" align="absmiddle" />
					&nbsp;中国建设银行 &nbsp;&nbsp;尾号5435 &nbsp;&nbsp;储蓄卡 &nbsp;&nbsp;姓名：曾山</li> -->
			</ul>
			<ol>
				<a href="#"><img src="/template/pc/images/payJia.gif" align="absmiddle" />
				</a>添加银行卡
			</ol>
		</div>
	</div>
			<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
