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
					<div id="noticeList">
						<ticket:common type="newsList" value="${newsClass.id }" size="100"/>
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList">
								<%-- <s:if test="#news.title == '国内购票须知'">
									<div class="tit">
										<a href="/airport_buyTicketNotice.action?flag=1">${news.title }</a>
									</div>
								</s:if>
								<s:else>
								</s:else> --%>
								<div class="tit" style="cursor:pointer;" onClick="location.href='/airport/${news.status.visitUrl }.ticket'">
									${news.title }
								</div>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="tit">${noDataMessage }</div>
						</s:else>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>