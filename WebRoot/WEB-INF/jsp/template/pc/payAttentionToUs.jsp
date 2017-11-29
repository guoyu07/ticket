<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<title>关注我们- 云南省昆明市长水机场</title>
</head>
<body style="background-color:#FFF;">
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<%@ include file="common/search.jsp"%>

	<div class="banner_24"></div banner>
	<div class="place w980 mt30">
		当前位置: <a href="/airportPc.action">首页</a> <a href="javascript:;">二维码</a>
	</div place w980 mt30>

	<div class="other_article_article w980 mt30">
		<h2>关注我们</h2>
		<center>
			<ticket:common type="advertListNew" value="关注我们" size="20" />
			<s:if test="#request.advertListNew != null">
				<s:iterator id="advert" value="#request.advertListNew">
					<ticket:commonAnnex type="annex" entityUuid="${advert.id}"
						annexType="1" size="1" />
						<div style="padding:20px 0 0 130px; float:left"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /><br />
						${advert.name }</div>
				</s:iterator>
			</s:if>
		</center>
	</div other_article_article w980 mt30>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
