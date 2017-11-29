<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="便捷登机">
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	                	<jsp:param value="消费提示" name="title"/>
	                </jsp:include>
		        <div class="mobile-main">
		            <div class="c_content">
		                <h1 class="c_blue">电瓶车服务介绍</h1>
		                <br>
		                <div class="padding-bottom">服务区域：国内出发F3层，国内到达F2层</div>
		                <div class="padding-bottom">乘车地点：国内出发F3层</div>
		                <div class="padding-bottom">服务时间：06:00-23:00</div>
		                <div class="padding-bottom">服务电话：0871-12345678</div>
		                <div class="padding-bottom">有偿服务：收费标准10元/人/次</div>
		                <div class="padding-bottom">服务区域：国内出发F3层，国内到达F2层</div>
		                <div class="padding-bottom">服务时间：06:00-23:00</div>
		            </div>
		        </div>
		       	<%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>