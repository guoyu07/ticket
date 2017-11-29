<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="保险"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="swiper-container">
						<ul class="swiper-wrapper">
							<li class="swiper-slide">
								<img src="/template/wap/images/pic/63.jpg">
							</li>
							<li class="swiper-slide">
								<img src="/template/wap/images/pic/63.jpg">
							</li>
							<li class="swiper-slide">
								<img src="/template/wap/images/pic/63.jpg">
							</li>
						</ul>
					</div>

					<div class="tit">
						延误险
					</div>
					<div class="tit">
						意外险
					</div>
					<div class="tit">
						旅游险
					</div>
					<div class="c_content">
						<p class="c_black text-large">
							机场保险服务资讯台：
						</p>
						<p class="text-big padding-top">
							昆明长水国际机场出发大厅（F3层）318号柜台
						</p>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>