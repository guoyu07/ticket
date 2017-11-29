<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="common/head.jsp" %>
		<title>服务热线 - 云南省昆明市长水机场</title>
	</head>

	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>

		<div class="banner_21"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> 
			<a href="#">服务热线</a>
		</div place w980 mt30>

		<div class="w980 mt30">
			<div class="other_article_article bhh" style="width:750px;">
			<h2>
				服务热线
			</h2>
			<ul>
				<ticket:pcCommon type="hotLineList"/>
				<s:if test="#request.hotLineList != null">
					<s:iterator id="hotLine" value="#request.hotLineList">
						<li>
							<b>${hotLine.name }：</b>${hotLine.phone }
						</li>
					</s:iterator>
				</s:if>
			</ul>
			</div>
			<%@ include file="common/rightMenu.jsp" %>
		</div other_article_article w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
