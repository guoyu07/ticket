<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
    <script src="/template/wap/layer/layer.js"></script>
	<script type="text/javascript">
	
$(function(){
					layer.alert('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机票数据和服务由携程提供，售后请拨打：400-828-7558', {
						  skin: 'layui-layer-lan' //样式类名
						  ,closeBtn: 0
						});
				});
	
		/*window.onload = function(){
			XW.alert("机票购买信息来自携程。");
		};*/
	</script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="机票预订" name="title" />
				</jsp:include>
				<div class="mobile-main">
					<iframe src="http://m.ctrip.com/html5/flight/matrix.html" width="100%" height="100%" style="border: 0"></iframe>
					<%-- <div class="order_ls">
						<div class="tit_tab line mr20">
							<div class="x4" style="width:25%;">
								<a href="#" class="selected"><i class="icon-caret-down"></i>国内单程</a>
							</div>
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder_goAndBack.action?direct=true"><i class="icon-caret-down"></i>国内往返</a>
							</div>
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder_international.action?direct=true"><i class="icon-caret-down"></i>国际单程</a>
							</div>
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder_internationalGoAndBack.action?direct=true"><i class="icon-caret-down"></i>国际往返</a>
							</div>
						</div>
					</div>
					<div class="new_fly_ls search_fly_ls">
						<ul class="">
							<li style="padding: 20px;">
								<div class="fly_line clearfix">
									<s:if test="#session.orgCity != null">
										<span id="city1" class='float-left selectCityClass' ticketType="domestic" buyType="single" cityType="departure" cityCode="${session.orgCityCode }"><ticket:format value="${session.orgCity }" size="3" /><em>出发城市</em></span>
									</s:if>
									<s:else>
										<span id="city1" class='float-left selectCityClass' ticketType="domestic" buyType="single" cityType="departure" cityCode="KMG">昆明<em>出发城市</em></span>
									</s:else>
									<s:if test="#session.desCity != null">
										<span id="city2" class='float-right selectCityClass' ticketType="domestic" buyType="single" cityType="arrival" cityCode="${session.desCityCode }"><ticket:format value="${session.desCity }" size="3" /><em>到达城市</em></span>
									</s:if>
									<s:else>
										<span id="city2" class='float-right selectCityClass' ticketType="domestic" buyType="single" cityType="arrival" cityCode="PEK">北京<em>到达城市</em></span>
									</s:else>
								</div>
							</li>
						</ul>
						<div class="search_time c_content">
							<i class="icon-calendar text-sub margin-large-right"></i>
							选择日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" id="date1" name="date1" value="<ticket:common type="currentDate"/>" class='no-border c_l_grey fz22'>&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<button class="button bg-sub radius-big ticketOrderBtnClass">
							搜索
						</button>
					</div> --%>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
		<script type="text/javascript" src="/template/wap/js/ticketOrder/ticketOrder.js"></script>
	</body>
</html>