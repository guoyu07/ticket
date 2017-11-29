<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<s:if test="news == null ">
				暂无文章信息
			</s:if>
			<s:else>
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="label_bar">
						<a href="#" class="selected">卫生间</a>
						<a href="#">问讯台</a>
						<a href="#">更衣室</a>
						<a href="#">吸烟室</a>
						<a href="#">公用电话</a>
						<a href="#">自动售货机</a>
						<a href="#">手推车取用点</a>
						<a href="#">自助充电站</a>
						<a href="#">饮水机</a>
						<a href="#">WIFI取号机</a>
						<a href="#">儿童娱乐区</a>
						<a href="#">更多</a>
					</div>

					<div class="c_content">
						${news.content }
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
			</s:else>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>