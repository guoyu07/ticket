<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <style type="text/css">
    .tit_tab a{ width:120px !important; height:120px !important; line-height:120px; border-radius:60px !important;}
    
    </style>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">航班查询结果</s:param>
					<s:param name="url">/airport/hangbanchaxun.ticket?direct=true</s:param>
				</s:include>
				<div class="text-center fz30 padding">
						<span class='c_blue'><font>${setoutCity }</font>-<font>${arrivalCity }</font>
						</span>
						<p class='c_l_grey fz20 padding-top'>
							共搜索到 ${flightCount }条航班
						</p>
				</div>
				<div class="page">
					<a href="#" class="float-left preDayPlanFlight" flightDate="<ticket:common type="preDayDate" value="${flightDate }"/>" org="${orgCode }" des="${desCode }"><i class='icon-angle-left'></i>&nbsp;&nbsp;前一天</a>
					${flightDate }
					<a href="#" class="float-right nextDayPlanFlight" flightDate="<ticket:common type="nextDayDate" value="${flightDate }"/>" org="${orgCode }" des="${desCode }">后一天&nbsp;&nbsp;<i
						class='icon-angle-right'></i>
					</a>
				</div>
				<div class="mobile-main">
					<ticket:common type="memberFocusFlightObj" value="${flightNumber }" var="${flightDate }" cTypeId="${flightState }"/>
					<s:if test="#request.memberFocusFlightObj != null">
						<input type="hidden" id="forFocus" name="forFocus" value=""/>
					</s:if>
					<s:else>
						<input type="hidden" id="forFocus" name="forFocus" value="${forFocus }"/>
					</s:else>
					<input type="hidden" id="mid" name="mid" value="${mid }"/>
					<input type="hidden" id="sessionMember" name="sessionMember" value="${session.sessionMember }">
					<s:if test="departPlanFlightList != null">
						<s:iterator id="planDepartFlight" value="departPlanFlightList">
							<div class='search_fly'>
								<div class="c_content">
									<s:set var="transferFlt" value="flightCompanyService.transferFlightNumber(#planDepartFlight.flt)"></s:set>
									<a href="#" class="planFlightDetail" flightNumber="${transferFlt }" flightDate="${flightDate }" flag="depart">
										<p class="fz26" style=" text-align:left">
											<s:set var="flightCompany" value="flightCompanyService.queryCompanyByFlt(#planDepartFlight.flt)"/>
											<%--<ticket:common type="queryCompanyByFlightNo" value="${planDepartFlight.flt }"/>
											--%><ticket:commonAnnex type="annex" entityUuid="${flightCompany.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
											&nbsp;&nbsp;
											${flightCompany.name }
											&nbsp;&nbsp;
											<font class='c_blue'>${transferFlt }</font>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'>
												<em class='c_black' style="font-size: 48px;">${planDepartFlight.std }</em>
												<font class='fz26'>${setoutCity }</font>
											</span>
											<i></i>
											<span class='float-right'>
											<em class='c_black' >${planDepartFlight.eta }</em>
											<font class='fz26'>${arrivalCity }</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${transferFlt }" fromList="true" flightDate="${flightDate }" flightState="depart" plan="plan">关注</a><br/>
								</div>
							</div>	
						</s:iterator>
					</s:if>
					<s:if test="arrivePlanFlightList != null">
						<s:iterator id="planArriveFlight" value="arrivePlanFlightList">
							<div class='search_fly'>
								<div class="c_content">
									<ticket:common type="trangeFlightNoByCompanyCode" value="${planArriveFlight.flt }"/>
									<a href="#" class="planFlightDetail" flightNumber="${trangeFlightNoByCompanyCode }"  flightDate="${flightDate }" flag="arrive">
										<p class="fz26" style="text-align:left;">
											<ticket:common type="queryCompanyByFlightNo" value="${planArriveFlight.flt }"/>
											<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNo.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
											&nbsp;&nbsp;
											${queryCompanyByFlightNo.name }
											&nbsp;&nbsp;
											<font class='c_blue'>${trangeFlightNoByCompanyCode }</font>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'><em class='c_black' style="font-size: 48px;">${planArriveFlight.std }</em>
											<font class='fz26'>${setoutCity }</font>
											</span>
											<i></i>
											<span class='float-right'><em class='c_black' style="font-size: 48px;">${planArriveFlight.eta }</em><font class='fz26'>${arrivalCity }</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${trangeFlightNoByCompanyCode }" flightDate="${flightDate }" flightState="arrival" fromList="true" plan="plan">关注</a>
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
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	<script type="text/javascript">
	    $(function(){
		    $('.focusOnFlight').on('click',function(){
		    	$("#flightNumber").val($(this).attr("flightNumber"));
			    $("#flightDate").val($(this).attr("flightDate"));
			    $("#flightState").val($(this).attr("flightState"));
		    	$.do_dialog.open({'msg':$('.fly_dialog')});
		    	focusFlightInit();
			 });
	    });
	</script>
	<script type="text/javascript"> 
		window.onload=function(){ 
		 var forFocus = $("#forFocus").val();
		 var flightNumber = $("#flightNumber").val();
		 var flightDate = $("#flightDate").val();
		 var flightState = $("#flightState").val();
		 var stopover = $("#stopover").val();
		 if(!JM.isNull(forFocus)&&forFocus!=""){
			 $("#forFocus").val("");
			 $.ajax({
				type:'POST',
				datatype:'json',
				url:'airportm_memberFocusFlight.action',
				data:'flightNumber='+flightNumber+'&flightDate='+flightDate+'&flightState='+flightState+'&stopover='+stopover,
				success:function(data){
					var val = $.parseJSON(data);
					if(val.status == JM.YES){
						window.location.reload();
						JM.alert('关注成功!');
						return;
					}else{
						window.location.reload();
						JM.alert(val.info);
						return;
					}
				}
			});
		 }
		};
	</script> 
</html>