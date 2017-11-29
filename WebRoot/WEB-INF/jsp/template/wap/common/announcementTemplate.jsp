<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">${fn:substring(news.title,0,5) }...</s:param>
				</s:include>
				<%@ include file="../common/favoriteAndShare.jsp"%>
				<input type="hidden" value="${session.fromApp }" id="sessionApp"/>
				<input type="hidden" value="${parameters.fromApp }" id="parametersApp"/>
				<div class="mobile-main">
					<div class="c_content">
						${news.content }
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
</body>
</html>