<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="机票预订" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		
		            <div class="order_ls">
		                <div class="tit_tab line mr20">
		                    <div class="x4"><a href="#" class="selected"><i class="icon-caret-down"></i>单程</a></div>
		                    <div class="x4"><a href="#"><i class="icon-caret-down"></i>往返</a></div>
		                    <div class="x4"><a href="#"><i class="icon-caret-down"></i>国际多程</a></div>
		                </div>
		            </div>
		            <div class="new_fly_ls search_fly_ls">
		                <ul class="">
		                    <li style="padding:20px;">
		                        <div class="fly_line clearfix">
		                            <span class='float-left'>昆明<font>出发城市</font></span>
		                            <span class='float-right'>重庆<font>到达城市</font></span>
		                        </div>
		                    </li>
		                </ul>
		                <div class="search_time c_content">
		                    <i class="icon-calendar text-sub margin-large-right"></i> 选择日期&nbsp;&nbsp;&nbsp;&nbsp;2015/07/02&nbsp;&nbsp;&nbsp;&nbsp;周四
		                </div>
		                <ul class="">
		                    <li style="padding:20px;">
		                        <div class="fly_line clearfix">
		                            <span class='float-left'>昆明<font>出发城市</font></span>
		                            <span class='float-right'>重庆<font>到达城市</font></span>
		                        </div>
		                    </li>
		                </ul>
		                <div class="search_time c_content">
		                    <i class="icon-calendar text-sub margin-large-right"></i> 选择日期&nbsp;&nbsp;&nbsp;&nbsp;2015/07/02&nbsp;&nbsp;&nbsp;&nbsp;周四
		                </div>
		                <button type="" class="button bg-sub radius-big">搜索</button>
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