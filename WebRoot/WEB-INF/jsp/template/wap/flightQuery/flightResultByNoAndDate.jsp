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
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="航班查询结果"/>
				</jsp:include>
				<div class="mobile-main">
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
					<s:if test="arrivalAtPort != null">
						<div class='search_fly'>
							<div class="c_content">
								<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${arrivalAtPort.flt }&flightDate=${arrivalAtPort.flightDate }&flightState=arrival">
									<p class="text-center fz26 clearfix">
										<span class="float-left">
											<ticket:common type="flightCompanyByTwoCode" value="${arrivalAtPort.acw }"/>
											<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24">
											${flightCompanyByTwoCode.name }&nbsp;
											<font class='c_black'>${arrivalAtPort.flt }</font>
										</span>
										<span class='float-right c_blue'>
											<!-- 显示是否是共享航班 -->
											${arrivalAtPort.mFlightNo != null && arrivalAtPort.mFlightNo != '' ? '共享航班' : '' }
										</span>
									</p>
									<div class="fly_line clearfix fz26">
										<span class='float-left'>
											<em class='c_black'>
												<s:if test="arrivalAtPort.pft != null">
													<s:date name="arrivalAtPort.torg" format="HH:mm"/>
												</s:if>
												<s:elseif test="arrivalAtPort.abp != null">
													<s:date name="arrivalAtPort.abp" format="HH:mm"/>
					                        	</s:elseif>
						                		<s:elseif test="arrivalAtPort.edp != null">
													<s:date name="arrivalAtPort.edp" format="HH:mm"/>
					                        	</s:elseif>
					                        	<s:else>
													<s:date name="arrivalAtPort.stp" format="HH:mm"/>
					                        	</s:else>
											</em>
											<font class='fz26'><ticket:common type="airportInfoByThreeCode" value="${arrivalAtPort.org }"/>${airportInfoByThreeCode.name }</font>
										</span>
										<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
										<!-- 显示航班状态 -->
										<s:if test="arrivalAtPort.frs != null && arrivalAtPort.frs !=''">
											<%=Enum.valueOf(FlightStatus.class, ((ArrivalAtPort)request.getAttribute("arrivalAtPort")).getFrs().toUpperCase()).getText() %>
										</s:if>
										</i>
										<span class='float-right'>
											<s:if test="arrivalAtPort.ata != null">
												<s:date name="arrivalAtPort.ata" format="HH:mm"/>
				                        	</s:if>
					                		<s:elseif test="arrivalAtPort.eta != null">
												<s:date name="arrivalAtPort.eta" format="HH:mm"/>
				                        	</s:elseif>
				                        	<s:else>
												<s:date name="arrivalAtPort.sta" format="HH:mm"/>
				                        	</s:else>
											<font class='fz26'>昆明</font>
										</span>
									</div>
								</a>
								<a href="#" class="button_gz memberFocusFlight" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival" fromList="true">关注</a><br>
							</div>
						</div>
						<s:if test="arrivalAtPort.pft != null && arrivalAtPort.pft !=''">
							<div class='search_fly'>
								<div class="c_content">
									<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${arrivalAtPort.flt }&flightDate=${arrivalAtPort.flightDate }&flightState=arrival&stopover=1">
										<p class="text-center fz26 clearfix">
											<span class="float-left">
												<ticket:common type="flightCompanyByTwoCode" value="${arrivalAtPort.acw }"/>
												<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24">
												${flightCompanyByTwoCode.name }&nbsp;
												<font class='c_black'>${arrivalAtPort.flt }</font>
											</span>
											<span class='float-right c_blue'>
												<!-- 显示是否是共享航班 -->
												${arrivalAtPort.mFlightNo != null && arrivalAtPort.mFlightNo != '' ? '共享航班' : '' }
											</span>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'>
												<s:if test="arrivalAtPort.abp != null">
													<s:date name="arrivalAtPort.abp" format="HH:mm"/>
					                        	</s:if>
						                		<s:elseif test="arrivalAtPort.edp != null">
													<s:date name="arrivalAtPort.edp" format="HH:mm"/>
					                        	</s:elseif>
					                        	<s:else>
													<s:date name="arrivalAtPort.stp" format="HH:mm"/>
					                        	</s:else>
												<font class='fz26'><ticket:common type="airportInfoByThreeCode" value="${arrivalAtPort.pft }"/>${airportInfoByThreeCode.name }</font>
											</span>
											<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
												<!-- 显示航班状态 -->
												<s:if test="arrivalAtPort.frs != null && arrivalAtPort.frs !=''">
													<%=Enum.valueOf(FlightStatus.class, ((ArrivalAtPort)request.getAttribute("arrivalAtPort")).getFrs().toUpperCase()).getText() %>
												</s:if>
											</i>
											<span class='float-right'>
												<s:if test="arrivalAtPort.ata != null">
													<s:date name="arrivalAtPort.ata" format="HH:mm"/>
					                        	</s:if>
						                		<s:elseif test="arrivalAtPort.eta != null">
													<s:date name="arrivalAtPort.eta" format="HH:mm"/>
					                        	</s:elseif>
					                        	<s:else>
													<s:date name="arrivalAtPort.sta" format="HH:mm"/>
					                        	</s:else>
												<font class='fz26'>昆明</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival" stopover="1" fromList="true">关注</a><br>
								</div>
							</div>
						</s:if>
					</s:if>
					<s:if test="departFromPort != null">
						<div class='search_fly'>
							<div class="c_content">
								<a href="/airport_queryDepartFlightNoAndDate.action?flightNumber=${departFromPort.flt }&flightDate=${departFromPort.flightDate }&flightState=depart">
									<p class="text-center fz26 clearfix">
										<span class="float-left">
											<ticket:common type="flightCompanyByTwoCode" value="${departFromPort.acw }"/>
											<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24">
											${flightCompanyByTwoCode.name }&nbsp;
											<font class='c_black'>${departFromPort.flt }</font>
										</span>
										<span class='float-right c_blue'>
											<!-- 显示是否是共享航班 -->
											${departFromPort.mFlightNo != null && departFromPort.mFlightNo != '' ? '共享航班' : ''}
										</span>
									</p>
									<div class="fly_line clearfix fz26">
										<span class='float-left'>
											<em class='c_black'>
												<s:if test="departFromPort.abt != null">
													<s:date name="departFromPort.abt" format="HH:mm"/>
					                        	</s:if>
												<s:elseif test="departFromPort.etd != null">
													<s:date name="departFromPort.etd" format="HH:mm"/>
					                        	</s:elseif>
					                        	<s:else>
													<s:date name="departFromPort.std" format="HH:mm"/>
					                        	</s:else>
											</em>
											<font class='fz26'>昆明</font>
										</span>
										<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
											<!-- 显示航班状态 -->
											<s:if test="departFromPort.frs != null && departFromPort.frs !=''">
												<%=Enum.valueOf(FlightStatus.class, ((DepartFromPort)request.getAttribute("departFromPort")).getFrs().toUpperCase()).getText() %> 
											</s:if>
										</i>
										<span class='float-right'>
											<em class='c_black'>
												<s:if test="departFromPort.vi1 != null">
													<s:date name="departFromPort.tdes" format="HH:mm"/>
												</s:if>
												<s:elseif test="departFromPort.aan != null">
													<s:date name="departFromPort.aan" format="HH:mm"/>
												</s:elseif>
												<s:elseif test="departFromPort.ean != null">
													<s:date name="departFromPort.ean" format="HH:mm"/>
												</s:elseif>
												<s:else>
													<s:date name="departFromPort.stn" format="HH:mm"/>
												</s:else>
											</em>
											<font class='fz26'>
												<ticket:common type="airportInfoByThreeCode" value="${departFromPort.des }"/>${airportInfoByThreeCode.name }
											</font>
										</span>
									</div>
								</a>
								<a href="#" class="button_gz memberFocusFlight" flightNumber="${departFromPort.flt }" flightDate="${departFromPort.flightDate }" flightState="depart" fromList="true">关注</a><br>
							</div>
						</div>	
						<s:if test="departFromPort.vi1 != null && departFromPort.vi1 !=''">
							<div class='search_fly'>
								<div class="c_content">
									<a href="/airport_queryDepartFlightNoAndDate.action?flightNumber=${departFromPort.flt }&flightDate=${departFromPort.flightDate }&stopover=1&flightState=depart">
										<p class="text-center fz26 clearfix">
											<span class="float-left">
												<ticket:common type="flightCompanyByTwoCode" value="${departFromPort.acw }"/>
												<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24">
												${flightCompanyByTwoCode.name }&nbsp;
												<font class='c_black'>${departFromPort.flt }</font>
											</span>
											<span class='float-right c_blue'>
												<!-- 显示是否是共享航班 -->
												${departFromPort.mFlightNo != null && departFromPort.mFlightNo != '' ? '共享航班' : ''}
											</span>
										</p>
										<div class="fly_line clearfix fz26">
											<span class='float-left'>
												<s:if test="departFromPort.abt != null">
													<s:date name="departFromPort.abt" format="HH:mm"/>
					                        	</s:if>
												<s:elseif test="departFromPort.etd != null">
													<s:date name="departFromPort.etd" format="HH:mm"/>
					                        	</s:elseif>
					                        	<s:else>
													<s:date name="departFromPort.std" format="HH:mm"/>
					                        	</s:else>
												<font class='fz26'>昆明</font>
											</span>
											<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
												<!-- 显示航班状态 -->
												<s:if test="departFromPort.frs != null && departFromPort.frs !=''">
													<%=Enum.valueOf(FlightStatus.class, ((DepartFromPort)request.getAttribute("departFromPort")).getFrs().toUpperCase()).getText() %> 
												</s:if>
											</i>
											<span class='float-right'>
												<em class='c_black'>
													<s:if test="departFromPort.aan != null">
														<s:date name="departFromPort.aan" format="HH:mm"/>
													</s:if>
													<s:elseif test="departFromPort.ean != null">
														<s:date name="departFromPort.ean" format="HH:mm"/>
													</s:elseif>
													<s:else>
														<s:date name="departFromPort.stn" format="HH:mm"/>
													</s:else>
												</em>
												<font class='fz26'>
													<ticket:common type="airportInfoByThreeCode" value="${departFromPort.vi1 }"/>${airportInfoByThreeCode.name }
												</font>
											</span>
										</div>
									</a>
									<a href="#" class="button_gz memberFocusFlight" flightNumber="${departFromPort.flt }" flightDate="${departFromPort.flightDate }" flightState="depart" stopover="1" fromList="true">关注</a><br>
								</div>
							</div>	
						</s:if>
					</s:if>
					<!-- transfer -->
					<s:if test="transferPort != null">
						<div class='search_fly'>
							<div class="c_content">
								<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${transferPort.flt }&flightDate=${transferPort.flightDate }&flightState=transfer">
									<p class="text-center fz26 clearfix">
										<span class="float-left">
											<ticket:common type="flightCompanyByTwoCode" value="${transferPort.acw }"/>
											<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24">
											${flightCompanyByTwoCode.name }&nbsp;
											<font class='c_black'>${transferPort.flt }</font>
										</span>
										<span class='float-right c_blue'>
											<!-- 显示是否是共享航班 -->
											<s:if test="transferPort.mFlightNo != null && transferPort.mFlightNo != ''">
												共享航班
											</s:if>
										</span>
									</p>
									<div class="fly_line clearfix fz26">
										<span class='float-left'>
											<em class='c_black'><s:date name="transferPort.torg" format="HH:mm"/></em>
											<font class='fz26'>
												<ticket:common type="airportInfoByThreeCode" value="${transferPort.org }"/>${airportInfoByThreeCode.name }
											</font>
										</span>
										<i style='padding-top:70px;font-size:16px;font-style:normal;' class='c_blue'>
										<!-- 显示航班状态 -->
										<s:if test="transferPort.frs != null && transferPort.frs !=''">
											<%=Enum.valueOf(FlightStatus.class, ((ArrivalAtPort)request.getAttribute("transferPort")).getFrs().toUpperCase()).getText() %>
										</s:if>
										</i>
										<span class='float-right'>
											<em class='c_black'>
												<s:date name="transferPort.sta" format="HH:mm"/>
											</em>
											<font class='fz26'>
												<ticket:common type="airportInfoByThreeCode" value="${transferPort.des }"/>${airportInfoByThreeCode.name }
											</font>
										</span>
									</div>
								</a>
								<a href="#" class="button_gz memberFocusFlight" flightNumber="${transferPort.flt }" flightDate="${transferPort.flightDate }" flightState="transfer" fromList="true">关注</a><br>
							</div>
						</div>
					</s:if>
					<!-- transfer end -->
					<s:if test="#request.departFromPort == null && #request.arrivalAtPort == null">
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
		    	focusFlightInit();
			    $("#flightNumber").val($(this).attr("flightNumber"));
			    $("#flightDate").val($(this).attr("flightDate"));
			    $("#flightState").val($(this).attr("flightState"));
			    $("#stopover").val($(this).attr("stopover"));
		    	$.do_dialog.open({'msg':$('.fly_dialog')});
			 });
	    });
	</script>
</html>