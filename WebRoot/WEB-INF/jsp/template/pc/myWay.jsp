<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>我的行程 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>
		
		<%@ include file="common/personalData.jsp" %>

		<div class="menu1">
			<ul>
				<li class="bhh">
					<a href="javascript:void(0);"><img src="/template/pc/images/ico1_2.gif" />
					</a>
				</li>
				<li class="bhh">
					<a href="/airportPc_checkOnLine.action"><img src="/template/pc/images/ico2_1.gif"
							onmouseover="this.src='/template/pc/images/ico2_2.gif'"
							onmouseout="this.src='/template/pc/images/ico2_1.gif'" />
					</a>
				</li>
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

		<div class="No1 w980 mt30">
			<ul>
				<li class="bhh">
					<img src="/template/pc/images/No_1.gif" align="absmiddle" />
					总飞行时长：3小时20分
				</li>
				<li class="bhh">
					<img src="/template/pc/images/No_2.gif" align="absmiddle" />
					总飞行里程：3000公里
				</li>
				<li class="bhh">
					<img src="/template/pc/images/No_3.gif" align="absmiddle" />
					未飞行次数：0次
				</li>
				<li class="bhh">
					<img src="/template/pc/images/No_4.gif" align="absmiddle" />
					总飞行次数：4次
				</li>
			</ul>
		</div No1 w980 mt30>

		<div class="MyWay w980 mt30">
			<ul class="bhh">
				<dd>
					已选乘的航空公司列表次数
				</dd>
				<li>
					<small>3次</small>中国东方航空
				</li>
				<li>
					<small>3次</small>中国东方航空
				</li>
				<li>
					<small>3次</small>中国东方航空
				</li>
				<li>
					<small>3次</small>中国东方航空
				</li>
			</ul>

			<ul class="bhh">
				<dt>
					已完成飞行的目的地次数列表
				</dt>
				<li>
					<small>3次</small>昆明
				</li>
				<li>
					<small>3次</small>昆明
				</li>
				<li>
					<small>3次</small>昆明
				</li>
				<li>
					<small>3次</small>昆明
				</li>
			</ul>
		</div MyWay w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
