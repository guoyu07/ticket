<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>免责声明 - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>
		<%@ include file="../common/nav.jsp" %>
		<%@ include file="../common/search.jsp" %>

		<div class="banner_25"></div>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> > <a href="javascript:;">免责声明</a>
		</div>

		<div class="other_article_article w980 mt30">
			${use.content }
		</div>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
	</body>
</html>
