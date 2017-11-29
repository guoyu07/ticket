<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>机票验证 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_11"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a>
			<a href="#">机票验证</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="JPYZ_article other_L bhh">
				<h4>
					您购买的电子票真实有效
				</h4>
				<table cellspacing="0" cellpadding="0" frame="border" rules="all">
					<tr>
						<td>
							电子客票票号
						</td>
						<td colspan="2"></td>
						<td colspan="2">
							购票航空公司
						</td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td>
							乘机旅客姓名
						</td>
						<td colspan="2"></td>
						<td colspan="2">
							身份证/证件号
						</td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td>
							订座记录编号
						</td>
						<td colspan="6"></td>
					</tr>
					<tr>
						<td colspan="5" align="left">
							第一航段
						</td>
						<td colspan="2">
							客票已使用
						</td>
					</tr>
					<tr>
						<td>
							航空公司
						</td>
						<td>
							航班号
						</td>
						<td>
							起飞机场
						</td>
						<td>
							起飞时间
						</td>
						<td>
							到达机场
						</td>
						<td>
							到达时间
						</td>
						<td>
							舱位
						</td>
					</tr>
					<tr>
						<td>
							使用限制
						</td>
						<td colspan="6">
							不得签转更改退票收费
						</td>
					</tr>
				</table>
			</div JPYZ_article other_L bhh>

			<%@ include file="common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
