<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
	<div class="mobile-view">
	    <div class="mobile-page">
	        <jsp:include page="../common/title.jsp"></jsp:include>
	        <div class="mobile-main">
	           <%@ include file="../common/favoriteAndShare.jsp" %>
	            <div class="c_content">
                <ticket:common type="newsList" value="${newsClass.id }"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="news" value="#request.newsList">
							<div class="tit">
								<a href="/airport/${news.status.visitUrl }.ticket">${news.title }</a>
							</div>
						</s:iterator>
					</s:if>
					<s:else>
					<div class="tit">${noDataMessage }</div>
					</s:else>
	            </div>
	            <div class="tit">导航到这</div>
	        </div>
	        <%@ include file="../common/quickNav.jsp" %>
	    </div>
	</div>
	<div class="dialog" style="display:none;">
	<%@ include file="../common/left.jsp" %>
	</div>
	</body>
</html>