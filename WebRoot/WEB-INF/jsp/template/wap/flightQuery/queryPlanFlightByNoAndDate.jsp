<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <style type="text/css">
    .tit_tab a{ width:120px !important; height:120px !important; line-height:120px; border-radius:60px !important;}
    
    </style>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">航班查询结果</s:param>
					<s:param name="url">/airport_backToQuery.action?direct=true&flightNumber=${flightNumber }&flightDate=${flightDate }&flag=queryByNo</s:param>
				</s:include>
				<div class="mobile-main">
					<input type="hidden" id="mid" name="mid" value="${mid }"/>
					<input type="hidden" id="sessionMember" name="sessionMember" value="${session.sessionMember }">
					<br>
					<br>
					<div class="page">
						<a href="#"  class="float-left preDayFlightByNo" flightDate="<ticket:common type="preDayDate" value="${flightDate }"/>" flightNo="${flightNumber }"><i class='icon-angle-left'></i>&nbsp;&nbsp;前一天</a>
						${flightDate }
						<a href="#" class="float-right nextDayFlightByNo" flightDate="<ticket:common type="nextDayDate" value="${flightDate }"/>" flightNo="${flightNumber }">后一天&nbsp;&nbsp;<i
							class='icon-angle-right'></i>
						</a>
					</div>
					<s:if test="#request.arrivePlanFlightList != null">
						<s:iterator id="planArrivalAtPort" value="arrivePlanFlightList">
							<div class='search_fly'>
								<div class="c_content">
									<s:set var="transferFlt" value="flightCompanyService.transferFlightNumber(#planArrivalAtPort.flt)"></s:set>
									<a href="#" class="planFlightDetail" flightNumber="${transferFlt }" cycle="${planDepartFromPort.cycle }" flightDate="${flightDate }" flag="arrive">
										<p class="text-center fz26">
											<ticket:common type="queryCompanyByFlightNo" value="${planArrivalAtPort.flt }"/>
											<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNo.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
											&nbsp;&nbsp;
											${queryCompanyByFlightNo.name }
											&nbsp;&nbsp;
											<font class='c_blue'>${transferFlt }</font>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'><em class='c_black'>${planArrivalAtPort.std }</em>
											<font class='fz26'><ticket:common type="airportInfoByFourCode" value="${planArrivalAtPort.dept }"/>${airportInfoByFourCode.name }</font>
											</span>
											<i></i>
											<span class='float-right'><em class='c_black'>${planArrivalAtPort.eta }</em><font class='fz26'>昆明</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${transferFlt }" flightDate="${flightDate }" flightState="arrival" fromList="true" plan="plan">关注</a><br>
								</div>
							</div>
						</s:iterator>
					</s:if>
					<s:if test="#request.departPlanFlightList != null">
						<s:iterator id="planDepartFromPort" value="#request.departPlanFlightList">
							<div class='search_fly'>
								<div class="c_content">
									<s:set var="transferFlt" value="flightCompanyService.transferFlightNumber(#planDepartFromPort.flt)"></s:set>
									<a href="#" class="planFlightDetail" flightNumber="${transferFlt }" cycle="${planDepartFromPort.cycle }" flightDate="${flightDate }" flag="depart">
										<p class="fz26" style="text-align:left;">
											<ticket:common type="queryCompanyByFlightNo" value="${planDepartFromPort.flt }"/>
											<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNo.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
											&nbsp;&nbsp;
											${queryCompanyByFlightNo.name }
											&nbsp;&nbsp;
											<font class='c_blue'>${transferFlt }</font>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'><em class='c_black'>${planDepartFromPort.std }</em><font class='fz26'>昆明</font>
											</span>
											<i></i>
											<span class='float-right'><em class='c_black'>${planDepartFromPort.eta }</em>
											<font class='fz26'><ticket:common type="airportInfoByFourCode" value="${planDepartFromPort.arrive }"/>${airportInfoByFourCode.name }</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${transferFlt }" flightDate="${flightDate } ${planDepartFromPort.std }:00" flightState="depart" fromList="true">关注</a><br>
									</div>
							</div>	
						</s:iterator>
					</s:if>
					<s:if test="#request.departPlanFlightList == null && #request.arrivePlanFlightList == null">
						<div class='search_fly'>
							<div class="c_content">
								${noDataMessage }
							</div>
						</div>
					</s:if>
                    
                   
				</div>
                 <a href="#"><img src="/upload/site/image/201609/20160930053112.bmp" alt=" " width="100%" ></a>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
	<div class="dialog" style="display:none;">
	    <div class="fly_dialog">
	        <div class="fly_dialog_con">
	        	<input type="hidden" value="" name="flightNumber" id="flightNumber"/>
	        	<input type="hidden" value="" name="flightDate" id="flightDate"/>
	        	<input type="hidden" value="" name="flightState" id="flightState"/>
	            <a href="#" class="button focusFlight" personRole="seat">乘机人</a>
	            <a href="#" class="button focusFlight" personRole="send">送机人</a>
	            <a href="#" class="button focusFlight" personRole="recept">接机人</a>
	            <a href="/ticketOrder.action" class="button">特价机票</a>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
	    $(function(){
		    $('.focusOnFlight').on('click',function(){
		    	focusFlightInit();
			    $("#flightNumber").val($(this).attr("flightNumber"));
			    $("#flightDate").val($(this).attr("flightDate"));
			    $("#flightState").val($(this).attr("flightState"));
		    	$.do_dialog.open({'msg':$('.fly_dialog')});
			 });
	    });
	</script>
    
</html>