<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="mr40 line">
						<div class="x6">
							<img src="/template/wap/images/pic/72.png">
						</div>
						<div class="x6">
							<div class="c_content"
								style="margin-top: 0px; margin-right: 0px;">
								<h2 class='padding-big-bottom'>
									<ticket:format value="${news.title }" size="8"/>
								</h2>
								<div class="fz16">
									<ticket:format value="${news.introduce}" size="75"/>
								</div>
							</div>
						</div>
					</div>
					<div class="c_content">
						<h2 class='padding-big-bottom'>
							${news.title }
						</h2>
						<div class="fz16">
							${news.content }
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
	</body>
</html>