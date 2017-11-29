<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
					<jsp:param value="服务预订" name="title"/>
				</jsp:include>
		        <div class="mobile-main">
		            <p class="mr40 fz20">目前景点：丽江<a href="#" class='c_blue'>点击切换</a></p>
		            <div class="tit">航班信息</div>
		            <div class='search_fly'>
		                <div class="c_content">
		                    <div class="">
		                        <span class='c_blue'>出发航班</span>
		                        <p class="text-center float-right"><img src="/template/wap/images/pic/62.png" height="24" width="24">&nbsp;&nbsp;昆明航空&nbsp;&nbsp;<font class='c_black'>CA4171</font></p>
		                    </div>
		                    <div class="fly_line clearfix">
		                        <span class='float-left'><em class='c_black'>07:30</em><font>出发城市</font></span>
		                        <div class="use_time"><p>1小时20分</p><p>2014/07/02 周四</p></div>
		                        <span class='float-right'><em class='c_black'>07:30</em><font>到达城市</font></span>
		                    </div>
		                    <hr>
		                    <p><a href="#" class='c_blue fz20'>跟换航班</a></p>
		                </div>
		            </div>
		            <div class='search_fly'>
		                <div class="c_content">
		                    <div class="">
		                        <span class='c_blue'>出发航班</span>
		                        <p class="text-center float-right"><img src="/template/wap/images/pic/62.png" height="24" width="24">&nbsp;&nbsp;昆明航空&nbsp;&nbsp;<font class='c_black'>CA4171</font></p>
		                    </div>
		                    <div class="fly_line clearfix">
		                        <span class='float-left'><em class='c_black'>07:30</em><font>出发城市</font></span>
		                        <div class="use_time"><p>1小时20分</p><p>2014/07/02 周四</p></div>
		                        <span class='float-right'><em class='c_black'>07:30</em><font>到达城市</font></span>
		                    </div>
		                    <hr>
		                    <p><a href="#" class='c_blue fz20'>跟换航班</a></p>
		                </div>
		            </div>
		            <div class="tit">入住酒店</div>
		
		            <div class="order_ls">
		                <ul>
		                    <li class="c_content line">
		                        <div class="clearfix">
		                            <div class="x5"><img src="/template/wap/images/pic/81.jpg" ></div>
		                            <div class="x7">
		                                <h4 class='fz20 padding-bottom c_grey'>昆明出发  度假特价、好礼相送</h4>
		                                <p class='fz18'>丽江丽人居客栈位于七一街兴文巷，
		    紧邻主街，步行进入古城约4分钟
		    即可到达客栈门口。</p>
		                            </div>
		                        </div>
		                        <hr>
		                        <div class="text-center" >
		                            <a href="" class='c_blue fz20'>更换酒店</a>
		                        </div>
		                    </li>
		                </ul>
		            </div>
		        </div>
		        <%@ include file="../common/quickNav.jsp" %>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>