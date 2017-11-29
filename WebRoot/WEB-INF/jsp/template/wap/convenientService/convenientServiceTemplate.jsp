<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content">
						${news.content }
					</div>
					<ticket:common type="locationListById" value="${news.infoPosition_id }"/>
					<s:if test="#request.locationListById != null">
						<s:iterator id="location" value="#request.locationListById">
							<div class="tit">
								<s:if test="location.mobiles != null">
									<div class="tit">
										<a href="tel:${location.mobiles }">${location.name }</a>
									</div>
								</s:if>
								<s:elseif test="location.url != null">
									<div class="tit">
										<a href="${location.mobiles }">${location.name }</a>
									</div>
								</s:elseif>
								<s:else>
									<div class="tit">
										<a href="#">${location.name }</a>
									</div>
								</s:else>
							</div>
						</s:iterator>
					</s:if>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>