<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="旅游咨询" name="title"/>
				</jsp:include>
		        <div class="mobile-main">
		            <div class="pic_kv">
		                <img src="/template/wap/images/pic/83.jpg">
		            </div>
		            <div class="mr40">
		                <ul class='viewport_ls'>
		                    <li>
		                        <img src="/template/wap/images/pic/82.jpg" >
		                        <p>丽江古城 <span class='icon-heart-o'></span></p>
		                    </li>
		                    <li>
		                        <img src="/template/wap/images/pic/82.jpg" >
		                        <p>丽江古城 <span class='icon-heart-o'></span></p>
		                    </li>
		                    <li>
		                        <img src="/template/wap/images/pic/82.jpg" >
		                        <p>丽江古城 <span class='icon-heart-o'></span></p>
		                    </li>
		                </ul>
		            </div>
		            <div class="tit_tab">
		                <a href="#" class="b_yello c_grey">国内</a>
		                <a href="#" class="b_yello c_grey">国外</a>
		            </div>
		            <div class="tit b_blue"><a href="#" class="c_white">机场旅游服务</a></div>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>