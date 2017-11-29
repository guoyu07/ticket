<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">${news.title }</s:param>
				</s:include>
				<div class="mobile-main">
					<ul class="mr20">
						${news.content }
					</ul>
				</div>
				<div class="mobile-foot">
					<!--<a href="electromobile_confirmPage.action" class="fz36">立即购买</a>-->
					<s:set var="info" value="infoPositionService.queryByName('电瓶车', 'site')"></s:set>
					<a href="/airport_daohang.action?longitude=${info.longitude }&latitude=${info.latitude }&name=${info.name }&floorNumber=${info.floorNumber}" class="fz36">导航</a>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>