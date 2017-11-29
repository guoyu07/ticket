<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="机票预订" name="title" />
				</jsp:include>
				<div class="mobile-main">
					<div class="order_ls">
						<div class="tit_tab line mr20">
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder.action?direct=true"><i class="icon-caret-down"></i>国内单程</a>
							</div>
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder_goAndBack.action?direct=true"><i class="icon-caret-down"></i>国内往返</a>
							</div>
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder_international.action?direct=true" class="selected"><i class="icon-caret-down"></i>国际单程</a>
							</div>
							<div class="x4" style="width:25%;">
								<a href="/ticketOrder_internationalGoAndBack.action?direct=true"><i class="icon-caret-down"></i>国际往返</a>
							</div>
						</div>
					</div>
					<div class="new_fly_ls search_fly_ls" >
                     <script type="application/javascript">
                    function cityChang(){
						var city1=document.getElementById("city1");
						var cith2=document.getElementById("city2");
						var citycode1=city1.getAttribute("citycode");
						var citycode2=city2.getAttribute("citycode");
						var city1_innerHtml=city1.innerHTML;
						var city2_innerHtml=city2.innerHTML;
						//交换
						city1.setAttribute("citycode",citycode2);
						city2.setAttribute("citycode",citycode1);
						
						city1.innerHTML=city2_innerHtml;
						city2.innerHTML=city1_innerHtml;
						
						
						
						}
                    </script>
						<ul class="" onClick="cityChang()">
							<li style="padding: 20px;">
								<div class="fly_line clearfix">
									<s:if test="#session.internationalDepartureName != null">
										<span id="city1" class='float-left selectCityClass' ticketType="international" buyType="single" cityType="departure" cityCode="${internationalDepartureCode }"><ticket:format value="${internationalDepartureName }" size="3" /><em>出发城市</em></span>
									</s:if>
									<s:else>
										<span id="city1" class='float-left selectCityClass' ticketType="international" buyType="single" cityType="departure" cityCode="KMG">昆明<em>出发城市</em></span>
									</s:else>
									<s:if test="#session.internationalArrivalName != null">
										<span id="city2" class='float-right selectCityClass' ticketType="international" buyType="single" cityType="arrival" cityCode="${internationalArrivalCode }"><ticket:format value="${internationalArrivalName }" size="3" /><em>到达城市</em></span>
									</s:if>
									<s:else>
										<span id="city2" class='float-right selectCityClass' ticketType="international" buyType="single" cityType="arrival" cityCode="HKG">香港<em>到达城市</em></span>
									</s:else>
								</div>
							</li>
						</ul>
						<div class="search_time c_content">
							<i class="icon-calendar text-sub margin-large-right"></i>
							选择日期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="date" id="date1" name="date1" value="<ticket:common type="currentDate"/>" class='no-border c_l_grey fz22'>&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<button type="" class="button bg-sub radius-big ticketOrderBtnClass">
							搜索
						</button>
					</div>
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