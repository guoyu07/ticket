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
						${news.content }
					</div>
					<s:if test="news.id == '25e84877-eb96-4cab-a1fb-574e67382096'">
						<div class="tit_tab">
							<a target="_blank" href="http://m.ctrip.com/webapp/ticket/index?&allianceid=303758&sid=779220" class="selected">门票预定</a>
							<a target="_blank" href="http://m.ctrip.com/webapp/hotel/?&allianceid=303758&sid=776936">酒店预定</a>
						</div>
					</s:if>
					<s:else>
						<s:if test="#session.fromApp == null && #parameters.fromApp == null">
							<div class="tit">
								<a href="http://api.map.baidu.com/marker?location=25.102384,102.934956&title=机场设施&content=下载App能享受室内导航服务&output=html">导航到这</a>
							</div>
							<%--<div class="tit">
								<a href="/template/wap/images/tipPic.png">导航到这</a>
							</div>
						--%></s:if>
						<s:else>
							<div class="tit">
								<a href="/airport_daohang.action?name=${news.title }">导航到这</a>
							</div>
						</s:else>
					</s:else>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>