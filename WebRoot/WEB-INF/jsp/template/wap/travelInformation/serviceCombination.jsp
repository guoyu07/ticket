<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
					<jsp:param value="服务组合" name="title"/>
				</jsp:include>
		        <div class="mobile-main">
		            <p class="mr40 fz20">目前景点：丽江<a href="#" class='c_blue'>点击切换</a></p>
		            <div class="tit">机票</div>
		            <div class="new_fly_ls search_fly_ls" style="padding-bottom:0px;">
		                <ul class="">
		                    <li style="padding:20px;">
		                        <div class="fly_line clearfix">
		                            <div class="x5">
		                                <span class="select_tit">
		                                    <em>出发城市</em>
		                                    <div class="button-group">
		                                        <button type="button" class="button  bg dropdown-toggle text-left">
		                                            <font>北京</font> <i class="icon-sort-desc"></i>
		                                        </button>
		                                        <ul class="drop-menu">
		                                            <li>昆明</li>
		                                            <li>重庆</li>
		                                        </ul>
		                                    </div>
		                                </span>
		                            </div>
		                            <div class="x2"></div>
		                            <div class="x5">
		                                <span class="select_tit">
		                                    <em>出发城市</em>
		                                    <div class="button-group">
		                                        <button type="button" class="button  bg dropdown-toggle fz22">
		                                            <font>北京</font> <i class="icon-sort-desc"></i>
		                                        </button>
		                                        <ul class="drop-menu">
		                                            <li>昆明</li>
		                                            <li>重庆</li>
		                                        </ul>
		                                    </div>
		                                </span>
		                            </div>
		                        </div>
		                    </li>
		                </ul>
		            </div>
		            <div class="mr40">
		                <div class="line fz22">
		                    <div class="x5 border padding-big radius-big"><i class="icon-calendar text-sub margin-large-right"></i>出发日期
		                        <p class="padding-top">2015/11/11 周四</p>
		                    </div>
		                    <div class="x2 text-center"></div>
		                    <div class="x5 border padding-big radius-big"><i class="icon-calendar text-sub margin-large-right"></i>出发日期
		                        <p class="padding-top">2015/11/11 周四</p>
		                    </div>
		                </div>
		            </div>
		            <div class="tit">酒店</div>
		            <div class="c_content fz22">目的地<span class='c_black float-right fz40' style='position:relative;top:-13px;'>昆明<i class='margin-large-left icon-angle-right c_l_grey'></i></span></div>
		            <div class="mr40">
		                <div class="line fz22">
		                    <div class="x5 border padding-big radius-big"><i class="icon-calendar text-sub margin-large-right"></i>入住
		                        <p class="padding-top">2015/11/11 周四</p>
		                    </div>
		                    <div class="x2 text-center"><p style='padding-top:40px;'>2晚</p></div>
		                    <div class="x5 border padding-big radius-big"><i class="icon-calendar text-sub margin-large-right"></i>离店
		                        <p class="padding-top">2015/11/11 周四</p>
		                    </div>
		                </div>
		            </div>
		            <div class="tit">租车</div>
		            <div class="mr40">
		                <div class="line fz22">
		                    <div class="x5 border padding-big radius-big">取车地<span>昆明</span></div>
		                    <div class="x2 text-center"></div>
		                    <div class="x5 border padding-big radius-big">2015/11/11 周四</div>
		                </div>
		                <div class="line fz22 margin-large-top">
		                    <div class="x5 border padding-big radius-big">换车地<span>昆明</span></div>
		                    <div class="x2 text-center"></div>
		                    <div class="x5 border padding-big radius-big">2015/11/11 周四</div>
		                </div>
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