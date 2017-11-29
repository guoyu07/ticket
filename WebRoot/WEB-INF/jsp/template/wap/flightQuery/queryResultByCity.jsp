<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ticket.mq.FlightStatus"%>
<%@page import="com.ticket.pojo.DepartFromPort"%>
<%@page import="com.ticket.pojo.ArrivalAtPort"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
    <style type="text/css">
    .tit_tab a{ width:120px !important; height:120px !important; line-height:120px; border-radius:60px !important;}
    
    </style>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<jsp:include page="../common/title.jsp">
						<jsp:param value="航班查询结果" name="title" />
						<jsp:param value="/airport/hangbanchaxun.ticket?direct=true" name="url" />
					</jsp:include>
					<div class="text-center fz30 padding">
						<span class='c_blue'><font>${setoutCity }</font>-<font>${arrivalCity }</font></span>
						<p class='c_l_grey fz20 padding-top'>
							共搜索到 ${flightCount }条航班
						</p>
					</div>
					<div class="page">
						<a href="#" class="float-left preDayFlight" flightDate="<ticket:common type="preDayDate" value="${flightDate }"/>" org="${orgCode }" des="${desCode }"><i class='icon-angle-left'></i>&nbsp;&nbsp;前一天</a>
						${flightDate }
						<a href="#" class="float-right nextDayFlight" flightDate="<ticket:common type="nextDayDate" value="${flightDate }"/>" org="${orgCode }" des="${desCode }">后一天&nbsp;&nbsp;<i class='icon-angle-right'></i></a>
					</div>
				</div>
				<div class="mobile-main">
					<input type="hidden" id="mid" name="mid" value="${mid }"/>
					<input type="hidden" id="sessionMember" name="sessionMember" value="${session.sessionMember }">
					<s:if test="#request.departFromPortList != null">
						<s:iterator id="dept" value="#request.departFromPortList">
							<%-- 中转地等于目的地 --%>
							<s:if test="#dept.vi1 == desCode">
								<s:if test="#dept.vi1 != null && #dept.vi1 !=''">
									<div class='search_fly'>
										<div class="c_content">
											<a href="/airport_queryDepartFlightNoAndDate.action?flightNumber=${dept.flt }&flightDate=${dept.flightDate }&stopover=1&flightState=depart">
												<p class="text-center fz26 clearfix">
													<span class="float-left">
														<ticket:common type="flightCompanyByTwoCode" value="${dept.acw }"/>
														<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
														<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
														${flightCompanyByTwoCode.name }&nbsp;
														<font class='c_blue'>${dept.flt }</font>
													</span>
													<span class='float-right c_blue'>
														<!-- 显示是否是共享航班 -->
														${!empty dept.mFlightNo ? '共享航班' : ''}
													</span>
												</p>
												<div class="fly_line clearfix fz26">
													<span class='float-left'><em class='c_black' style="font-size: 48px;"><s:date name="#dept.std " format="HH:mm"/></em><font class='fz26'>昆明</font></span>
													<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
														<!-- 显示航班状态 -->
														<%=Enum.valueOf(FlightStatus.class, ((DepartFromPort)request.getAttribute("dept")).getFrs().toUpperCase()).getText() %> 
													</i>
													<span class='float-right'>
														<em class='c_black' style="font-size: 48px;">
															<s:if test="#dept.aan != null">
																<s:date name="#dept.aan" format="HH:mm"/>
															</s:if>
															<s:elseif test="#dept.ean != null">
																<s:date name="#dept.ean" format="HH:mm"/>
															</s:elseif>
															<s:else>
																<s:date name="#dept.stn" format="HH:mm"/>
															</s:else>
														</em>
														<font class='fz26'><ticket:common type="airportInfoByThreeCode" value="${dept.vi1 }"/>${airportInfoByThreeCode.name }</font>
													</span>
												</div>
											</a>
											<%-- <a href="#" class="button_gz focusOnFlight" flightNumber="${dept.flt }" flightDate="${dept.flightDate }" flightState="depart" stopover="1">关注</a><br> --%>
											<a href="#" class="button_gz memberFocusFlight" flightNumber="${dept.flt }" flightDate="${dept.flightDate }" flightState="depart" stopover="1"  fromList="true">关注</a><br>
										</div>
									</div>
								</s:if>
							</s:if>
							<s:else>
								<div class='search_fly'>
									<div class="c_content">
										<a href="/airport_queryDepartFlightNoAndDate.action?flightNumber=${dept.flt }&flightDate=${dept.flightDate }&flightState=depart">
											<p class="text-center fz26 clearfix">
												<span class="float-left">
													<ticket:common type="flightCompanyByTwoCode" value="${dept.acw }"/>
													<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
													<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
													${flightCompanyByTwoCode.name }&nbsp;
													<font class='c_blue'>${dept.flt }</font>
												</span>
												<span class='float-right c_blue'>
													<!-- 显示是否是共享航班 -->
													${!empty dept.mFlightNo ? '共享航班' : ''}
												</span>
											</p>
											<div class="fly_line clearfix fz26">
												<span class='float-left'>
													<em class='c_black' style="font-size: 48px;"><s:date name="#dept.std" format="HH:mm"/></em>
													<font class='fz26'>${setoutCity }</font>
												</span>
												<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
													<!-- 显示航班状态 -->
													<%=Enum.valueOf(FlightStatus.class, ((DepartFromPort)request.getAttribute("dept")).getFrs().toUpperCase()).getText() %>
												</i>
												<span class='float-right'>
													<em class='c_black' style="font-size: 48px;">
														<%-- <s:if test="#dept.vi1 != '' && #dept.vi1 != null && #dept.stn == #dept.tdes">
															--
														</s:if>
														<s:else> --%>
														<s:if test="#dept.aan != null">
															<s:date name="#dept.aan" format="HH:mm"/>
														</s:if>
														<s:elseif test="#dept.ean != null">
															<s:date name="#dept.ean" format="HH:mm"/>
														</s:elseif>
														<s:else>
															<s:date name="#dept.stn" format="HH:mm"/>
														</s:else>
														<%-- </s:else> --%>
													</em>
													<font class='fz26'>
														<!-- 终点站 -->
														<s:set var="d" value="airportInfoService.queryByThreeCode(#dept.des,'site')"/>
					                            		${d.name }
													</font>
												</span>
											</div>
										</a>
										<a href="#" class="button_gz memberFocusFlight" flightNumber="${dept.flt }" flightDate="${dept.flightDate }" flightState="depart"  fromList="true">关注</a><br/>
									</div>
								</div>
							</s:else>
						</s:iterator>
					</s:if>
					<s:elseif test="arrivalAtPortList != null">
						<s:iterator id="arrivalAtPort" value="arrivalAtPortList">
							<s:if test="#arrivalAtPort.pft == orgCode">
								<s:if test="#arrivalAtPort.pft != null && #arrivalAtPort.pft !=''">
									<div class='search_fly'>
										<div class="c_content">
											<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${arrivalAtPort.flt }&flightDate=${arrivalAtPort.flightDate }&flightState=arrival&stopover=1">
												<p class="text-center fz26 clearfix">
													<span class="float-left">
														<ticket:common type="flightCompanyByTwoCode" value="${arrivalAtPort.acw }"/>
														<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
														<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
														${flightCompanyByTwoCode.name }&nbsp;
														<font class='c_blue' >${arrivalAtPort.flt }</font>
													</span>
													<span class='float-right c_black'>
														<!-- 显示是否是共享航班 -->
														${!empty arrivalAtPort.mFlightNo ? '共享航班' : '' }
													</span>
												</p>
												<div class="fly_line clearfix fz26">
													<span class='float-left'><em class='c_black' style="font-size: 48px;">
														<s:if test="#arrivalAtPort.abp != null">
															<s:date name="#arrivalAtPort.abp " format="HH:mm"/>
														</s:if>
														<s:else>
															<s:date name="#arrivalAtPort.stp " format="HH:mm"/>
														</s:else>
													
													</em>
													<font class='fz26'><ticket:common type="airportInfoByThreeCode" value="${arrivalAtPort.pft }"/>${airportInfoByThreeCode.name }</font>
													</span>
													<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
													<!-- 显示航班状态 -->
														<%=Enum.valueOf(FlightStatus.class, ((ArrivalAtPort)request.getAttribute("arrivalAtPort")).getFrs().toUpperCase()).getText() %>
													</i>
													<span class='float-right'><em class='c_black' style="font-size: 48px;"><s:date name="#arrivalAtPort.sta " format="HH:mm"/></em><font class='fz26'>昆明</font>
													</span>
												</div>
											</a>
											<a href="#" class="button_gz memberFocusFlight" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival" stopover="1"  fromList="true">关注</a><br>
										</div>
									</div>
								</s:if>
							</s:if>
							<s:else>
							<div class='search_fly'>
								<div class="c_content">
									<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${arrivalAtPort.flt }&flightDate=${arrivalAtPort.flightDate }&flightState=arrival">
										<p class="text-center fz26 clearfix">
											<span class='float-left'>
												<ticket:common type="flightCompanyByTwoCode" value="${arrivalAtPort.acw }"/>
												<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
												${flightCompanyByTwoCode.name }&nbsp;
												<font class='c_blue'>${arrivalAtPort.flt }</font>
											</span>
											<span class='float-right c_blue'>
												<!-- 显示是否是共享航班 -->
												${!empty arrivalAtPort.mFlightNo ? '共享航班' : '' }
											</span>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'><em class='c_black' style="font-size: 48px;"><s:date name="#arrivalAtPort.stp " format="HH:mm"/></em>
											<font class='fz26'>
												<s:set var="sCity" value="airportInfoService.queryByThreeCode(#arrivalAtPort.org,'site')"/>
				                            	${sCity.name }
			                            	</font>
											</span>
											<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
												<!-- 显示航班状态 -->
												<%=Enum.valueOf(FlightStatus.class, ((ArrivalAtPort)request.getAttribute("arrivalAtPort")).getFrs().toUpperCase()).getText() %>
											</i>
											<span class='float-right'><em class='c_black' style="font-size: 48px;"><s:date name="#arrivalAtPort.sta " format="HH:mm"/></em><font class='fz26'>${arrivalCity }</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival"  fromList="true">关注</a>
								</div>
							</div>
							</s:else>
						</s:iterator>
					</s:elseif>
					<s:else>
						<div class='search_fly'>
							<div class="c_content">
								${noDataMessage }
							</div>
						</div>
					</s:else>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	<div class="dialog" style="display:none;">
	    <div class="fly_dialog">
	        <div class="fly_dialog_con">
	        	<input type="hidden" value="" name="flightNumber" id="flightNumber"/>
	        	<input type="hidden" value="" name="flightDate" id="flightDate"/>
	        	<input type="hidden" value="" name="flightState" id="flightState"/>
	        	<input type="hidden" value="" name="stopover" id="stopover"/>
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
		    	$("#flightNumber").val($(this).attr("flightNumber"));
			    $("#flightDate").val($(this).attr("flightDate"));
			    $("#flightState").val($(this).attr("flightState"));
			    $("#stopover").val($(this).attr("stopover"));
		    	$.do_dialog.open({'msg':$('.fly_dialog')});
		    	focusFlightInit();
			 });
	    });
	</script>
</html>