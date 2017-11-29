<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="../common/head.jsp"%>
		<title>${newsClass.name} - 云南省昆明市长水机场</title>
		<script type="text/javascript" src="/template/pc/js/door.js"></script>
	</head>
	<body>
		<%@ include file="../common/top.jsp"%>
		<%@ include file="../common/nav.jsp"%>
		<%@ include file="../common/search.jsp"%>
		<div class="banner_29"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> >
			<a href="javascript:;">${news.title}</a>
		</div place w980 mt30>
			<div class="w980 mt30">
				<div class="other_article other_L bhh">
				<h2>${news.title}</h2>
				<div class="text_content">
					<div class="article">
						<s:if test="news.pcContent != null && news.pcContent != ''">
						${news.pcContent}
						</s:if>
						<s:else>
						${news.content}
						</s:else>
					</div>
				</div>
			</div other_article other_L bhh>	
			<%@ include file="../common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="../common/left.jsp" %>
		<%@ include file="../common/bottom.jsp" %>
	</body>
</html>
