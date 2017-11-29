<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>网上值机 - 云南省昆明市长水机场</title>
	</head>

	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>
		
		<%@ include file="common/personalData.jsp" %>

		<div class="menu1">
			<ul>
				<li class="bhh"><a href="/airportPc_myWay.action"><img src="/template/pc/images/ico1_1.gif" onmouseover="this.src='/template/pc/images/ico1_2.gif'" onmouseout="this.src='/template/pc/images/ico1_1.gif'" /></a></li>
       	 		<li class="bhh"><a href="#"><img src="/template/pc/images/ico2_2.gif" /></a></li>
				<li class="bhh">
					<a href="/airportPc_flightDynamic.action"><img src="/template/pc/images/ico3_1.gif"
							onmouseover="this.src='/template/pc/images/ico3_2.gif'"
							onmouseout="this.src='/template/pc/images/ico3_1.gif'" />
					</a>
				</li>
				<li class="bhh">
					<a href="/airportPc_flightQuery.action"><img src="/template/pc/images/ico4_1.gif"
							onmouseover="this.src='/template/pc/images/ico4_2.gif'"
							onmouseout="this.src='/template/pc/images/ico4_1.gif'" />
					</a>
				</li>	
				<li class="bhh">
					<a href="/airportPc_myMessage.action"><img src="/template/pc/images/ico5_1.gif"
							onmouseover="this.src='/template/pc/images/ico5_2.gif'"
							onmouseout="this.src='/template/pc/images/ico5_1.gif'" />
					</a>
				</li>
				<li class="bhh">
					<a href="/airportPc_find.action"><img src="/template/pc/images/ico6_1.gif"
							onmouseover="this.src='/template/pc/images/ico6_2.gif'"
							onmouseout="this.src='/template/pc/images/ico6_1.gif'" />
					</a>
				</li>
			</ul>
		</div menu1>

		<div class="net_1 w980 mt30">
			<dl id="tb1_">
				<dd>
					<a href="javascript:void(0);"><img src="/template/pc/images/net_1_1.gif" />
					</a>
				</dd>
				<dd>
					<a href="javascript:void(0);"><img src="/template/pc/images/net_1_2.gif" />
					</a>
				</dd>
			</dl>
		</div net_1 w980 mt30>

		<div class="net_2">
			值机须知：
			<br />
			1、可通过微信办理乘机手续的航班为东航、上航实际承运的国内航班（除经停航班、内部代号共享航班、国际航班国内段）、在航班延误等特殊情况下，东航将关闭。
			<br />
			2、微信值机与传统值机拥有一致的效力，旅客一经办理，即被认为完成值机手续，如因旅客个人原因未能搭乘相应航班出行，将按照误机处理。
			<br />
			3、微信值机办理及补打登机牌截止时间为航班起飞前45分钟；登机口于起飞前15分钟关闭若旅客未能在指定时间前办理值机手续和及时登机将可能无法按时成行。
		</div net_2>

		<div class="net_3 w980 mt30">
			<dl class="bhh">
				<dd>
					<h2>
						昆明长水
					</h2>
					出发城市
				</dd>
				<dt>
					晴朗 18-22℃
				</dt>
			</dl>
			<dl class="hh">
				<dd>
					<h2>
						昆明长水
					</h2>
					到达城市
				</dd>
				<dt>
					晴朗 18-22℃
				</dt>
			</dl>
		</div net_3 w980 mt30>

		<div class="net_4 w980 mt30">
			<ul>
				<li class="bhh">
					登机口
					<b>58</b>
				</li>
				<li class="bhh" style="border-right: none;">
					座位号
					<b>20A</b>
				</li>
			</ul>
		</div net_4 w980 mt30>

		<div class="net_5 w980 mt30">
			<ul>
				<li class="bhh">
					登机开始时间：2015-07-02 12:00
					<br />
					咨询热线：400-8000-700
				</li>
				<li class="bhh">
					登机截止时间：2015-07-02 12:00
					<br />
					机型：波音737
				</li>
				<li class="bhh" style="border-right: none;">
					预计到达时间：2015-07-02 12:00
				</li>
			</ul>
		</div net_5 w980 mt30>

		<div class="net_6 mt30">
			温馨提示：提前
			<b>40分钟值机</b>，请合理安排时间，建议您尽早到机场
			<b>避免13：00 - 15：00 值机高峰时期</b>。目的地天气为阵雨，请
			<b>携带雨具</b>，请提前
			<b>1小时</b>到达机场办理行李托运手续，值机信息已发送至13888888888手机上，请注意查收。
		</div net_6 w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
