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
					<ticket:common type="newsList" value="${newsClass.id }" size="1"/>
					<s:if test="#request.newsList != null">
						<s:iterator id="disclaimer" value="#request.newsList" status="st">
							<s:if test="#st.first">
								<div class="c_content text-left">
									${disclaimer.content }
								</div>
							</s:if>
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