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
					<ticket:common type="newsList" value="${newsClass.id }" size="4" />
					<s:if test="#request.newsList != null">
						<s:iterator id="newsQuery" value="#request.newsList" status="st">
							<div class="c_content">
								<dl class="clearfix">
									<dt class="float-left margin-large-right">
									<ticket:commonAnnex type="queryAnnexList" entityUuid="${newsQuery.id}" annexType="1" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="160" width="240">
											</s:iterator>
										</s:if>
									</dt>
									<dd class='float-left' style='width: 220px;'>
										<h3>
											${newsQuery.title }
										</h3>
										<hr>
										<p>
											${newsQuery.content }
										</p>
									</dd>
								</dl>
							</div>
							<ticket:common type="locationListById" value="${newsQuery.infoPosition_id }"/>
								<s:if test="#request.locationListById != null">
									<s:iterator id="location" value="#request.locationListById">
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
								</s:iterator>
							</s:if>
						</s:iterator>
					</s:if>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>