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
			<div class="JPYZ2_article other_L bhh">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td align="right">
							电子客票/行程单号
						</td>
						<td colspan="4">
							<input name="" type="text" style="width: 490px;" />
						</td>
					</tr>
					<tr>
						<td align="right">
							旅客姓名
						</td>
						<td>
							<input name="" type="text" />
						</td>
						<td>
							验证码
						</td>
						<td>
							<input name="" type="text" />
						</td>
						<td>
							<a href="javascript:;"><img src="images/YZM2.gif" />
							</a>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="center">
							<hr />
							<a href="javascript:;"><img src="images/button_YZ.gif" class="mt30" />
							</a>
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
