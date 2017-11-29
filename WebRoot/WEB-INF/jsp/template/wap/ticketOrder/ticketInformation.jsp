<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="航班查询" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		            <div class='search_fly'>
		                <div class="c_content">
		                    <p class="text-center"><img src="/template/wap/images/pic/62.png" height="24" width="24">&nbsp;&nbsp;昆明航空&nbsp;&nbsp;<font class='c_black'>CA4171</font></p>
		                    <div class="fly_line clearfix">
		                        <span class='float-left'><em class='c_black'>07:30</em><font>出发城市</font></span>
		                        <i></i>
		                        <span class='float-right'><em class='c_black'>07:30</em><font>到达城市</font></span>
		                    </div>
		                </div>
		            </div>
		            <div class="order_ls">
		                <div class="tit_tab line mr20">
		                    <div class="x4"><a href="#" class="selected"><i class="icon-caret-down"></i>全部</a></div>
		                    <div class="x4"><a href="#"><i class="icon-caret-down"></i>经济舱</a></div>
		                    <div class="x4"><a href="#"><i class="icon-caret-down"></i>两舱</a></div>
		                </div>
		                <div class='c_content clearfix'>
		                    <span class='float-left fz18'>天泰空</span>
		                    <span class='float-right fz18 c_red'>四折￥ <font class='fz22'>756</font></span>
		                </div>
		                <div class='c_content clearfix'>
		                    <span class='float-left fz18'>天泰空</span>
		                    <span class='float-right fz18 c_red'>四折￥ <font class='fz22'>756</font></span>
		                </div>
		                <div class='c_content clearfix'>
		                    <span class='float-left fz18'>天泰空</span>
		                    <span class='float-right fz18 c_red'>四折￥ <font class='fz22'>756</font></span>
		                </div>
		                <div class='c_content clearfix'>
		                    <span class='float-left fz18'>天泰空</span>
		                    <span class='float-right fz18 c_red'>四折￥ <font class='fz22'>756</font></span>
		                </div>
		            </div>
		        </div>
	            <%@ include file="../common/quickNav.jsp"%>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>