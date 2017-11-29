<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<s:if test="news != null ">
				<div class="mobile-page">
					<jsp:include page="../common/title.jsp"></jsp:include>
					<div class="mobile-main">
						<div class="c_content text-center">
							<img src="/template/wap/images/pic/qr1.jpg">
							<p>
								微信号：kmcsjc
							</p>
							<br>
							<p>
								<button class="button bg-sub text-big"
									style='width: 270px; padding: 10px 0px;'>
									一键关注
								</button>
							</p>
							<br>
							<img src="/template/wap/images/pic/qr1.jpg">
							<p>
								微博号：昆明长水国际机场服务平台
							</p>
							<br>
							<p>
								<button class="button bg-sub text-big"
									style='width: 270px; padding: 10px 0px;'>
									一键关注
								</button>
							</p>
						</div>
						<div class="c_content text-content">
							<p class="fz20">
								机场APP火热下载中，安装后可了解最新长水机场动态可获取第一手信息，现在下载安装，赠送5积分。
							</p>
							<a href="#" class="tit block1">机场APP下载活动介绍</a>
						</div>
					</div>
                    <%@ include file="../common/quickNav.jsp" %>
				</div>
			</s:if>
			<s:else>
				<div class="mobile-page">
					<jsp:include page="../common/title.jsp"></jsp:include>
					<div class="mobile-main">
						<div class="c_content text-center">
							<img src="/template/wap/images/pic/qr1.jpg">
							<p>
								微信号：kmcsjc
							</p>
							<br>
							<p>
								<button class="button bg-sub text-big"
									style='width: 270px; padding: 10px 0px;'>
									一键关注
								</button>
							</p>
							<br>
							<img src="/template/wap/images/pic/qr1.jpg">
							<p>
								微博号：昆明长水国际机场服务平台
							</p>
							<br>
							<p>
								<button class="button bg-sub text-big"
									style='width: 270px; padding: 10px 0px;'>
									一键关注
								</button>
							</p>
						</div>
						<div class="c_content text-content">
							<p class="fz20">
								机场APP火热下载中，安装后可了解最新长水机场动态可获取第一手信息，现在下载安装，赠送5积分。
							</p>
							<a href="http://www.baidu.com" class="tit">机场APP下载活动介绍</a>
						</div>
					</div>
                    <%@ include file="../common/quickNav.jsp" %>
				</div>
			</s:else>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>