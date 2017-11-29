<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>航班动态 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>
		
		<%@ include file="common/personalData.jsp" %>

		<div class="menu1">
			<ul>
				<li class="bhh"><a href="/airportPc_myWay.action"><img src="/template/pc/images/ico1_1.gif" onmouseover="this.src='/template/pc/images/ico1_2.gif'" onmouseout="this.src='/template/pc/images/ico1_1.gif'" /></a></li>
		        <li class="bhh"><a href="/airportPc_checkOnLine.action"><img src="/template/pc/images/ico2_1.gif" onmouseover="this.src='/template/pc/images/ico2_2.gif'" onmouseout="this.src='/template/pc/images/ico2_1.gif'" /></a></li>
		        <li class="bhh"><a href="#"><img src="/template/pc/images/ico3_2.gif" /></a></li>
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

		<div class="hbdt w980 mt30">
			<dl id="tb1_">
				<dd class="hovertab_1" id="tb1_1" onclick="x:HoverLi1(1);">
					<a href="javascript:void(0);">进港</a>
				</dd>
				<dd class="normaltab_1" id="tb1_2" onclick="i:HoverLi1(2);">
					<a href="javascript:void(0);">出港</a>
				</dd>
			</dl>

			<ul class="dis" id="tbc1_01">
				<table cellspacing="0" cellpadding="0" frame="border" rules="all">
					<tr>
						<th>
							LOGO
						</th>
						<th>
							航班
						</th>
						<th>
							始发
						</th>
						<th>
							计划到达
						</th>
						<th>
							实际到达
						</th>
						<th>
							计划出发
						</th>
						<th>
							实际出发
						</th>
						<th>
							状态
						</th>
					</tr>
					<tr>
						<td width="120">
							<img src="/template/pc/images/airport_logo.gif" />
						</td>
						<td>
							GW20132
						</td>
						<td>
							昆明
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							安全到达
						</td>
					</tr>
				</table>
				<table cellspacing="0" cellpadding="0" frame="border" rules="all">
					<tr>
						<th>
							LOGO
						</th>
						<th>
							航班
						</th>
						<th>
							始发
						</th>
						<th>
							计划到达
						</th>
						<th>
							实际到达
						</th>
						<th>
							计划出发
						</th>
						<th>
							实际出发
						</th>
						<th>
							状态
						</th>
					</tr>
					<tr>
						<td width="120">
							<img src="/template/pc/images/airport_logo.gif" />
						</td>
						<td>
							GW20132
						</td>
						<td>
							昆明
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							安全到达
						</td>
					</tr>
				</table>
			</ul>

			<ul class="undis" id="tbc1_02">
				<table cellspacing="0" cellpadding="0" frame="border" rules="all">
					<tr>
						<th>
							LOGO
						</th>
						<th>
							航班
						</th>
						<th>
							始发
						</th>
						<th>
							计划到达
						</th>
						<th>
							实际到达
						</th>
						<th>
							计划出发
						</th>
						<th>
							实际出发
						</th>
						<th>
							状态
						</th>
					</tr>
					<tr>
						<td width="120">
							<img src="/template/pc/images/airport_logo.gif" />
						</td>
						<td>
							GW20132
						</td>
						<td>
							昆明
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							2015/10/10
							<br />
							16:00
						</td>
						<td>
							安全到达
						</td>
					</tr>
				</table>
			</ul>
		</div hbdt w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
