<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp" %>
		<title>${news.title} - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="../common/top.jsp" %>

		<%@ include file="../common/nav.jsp" %>

		<%@ include file="../common/search.jsp" %>

		<div class="banner_25"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> 
			<a href="javascript:;">网上值机</a>
		</div place w980 mt30>

		<div class="other_article_article w980 mt30">
			<h2>
				值机须知
			</h2>
			<h3>
				<ticket:common type="systemDicObjByValue" value="checkOnlineNotice"/>
				${systemDicObjByValue.description }
				
			</h3>
			<div class="tit_tab" style='text-align:center;padding-bottom:40px;'>
					<a href="#"  style="background:#F3B512;padding:10px 10px;display:inline-block"><img
							src="/template/pc/image/ios_app_down.png">
					</a>
					<a href="#"  style="background:#00aaff;padding:10px 10px;display:inline-block"><img
							src="/template/pc/image/android_app_down.png">
					</a>
				</div>
		</div other_article_article w980 mt30>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
	</body>
</html>
