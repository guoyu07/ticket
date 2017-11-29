<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<style type="text/css">
    .tit_tab a{ width:120px !important; height:120px !important; line-height:120px; border-radius:60px !important;}
    </style>
	<body class="mobile" onload="initFocusFlight();">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">航班查询</s:param>
					<%-- <s:param name="url">javascript:window.location.assign($.cookie('listUrl'));</s:param> --%>
				</s:include>
				<div class="mobile-main">
				<ticket:common type="memberFocusFlightObj" value="${flightNumber }" var="${flightDate }" flightState="${flightState }" stopover="${stopover }"/>
				<s:if test="#request.memberFocusFlightObj != null">
					<input type="hidden" id="forFocus" name="forFocus" value=""/>
				</s:if>
				<s:else>
					<input type="hidden" id="forFocus" name="forFocus" value="${forFocus }"/>
				</s:else>
				<input type="hidden" value="${flightNumber }" name="flightNumber" id="flightNumber"/>
	        	<input type="hidden" value="${flightDate }" name="flightDate" id="flightDate"/>
	        	<input type="hidden" value="${flightState }" name="flightState" id="flightState"/>
	        	<input type="hidden" value="${stopover }" name="stopover" id="stopover"/>
				<input type="hidden" id="mid" name="mid" value="${mid }"/>
				<input type="hidden" id="sessionMember" name="sessionMember" value="${session.sessionMember }">
					<div class="new_fly_ls">
		                <ul class="">
		                    <li style='margin-bottom:0px;'>
		                        <div class="fly_line clearfix no-background">
		                            <span class='text-center'>
		                            	<em class='c_black fz30'>
		                            	<s:if test="arrivalAtPort.flt != null">
		                            		${arrivalAtPort.flt }
		                            	</s:if>
		                            	<s:else>
		                            		${flightNumber }
		                            	</s:else>
		                            	</em>
		                            </span>
		                        </div>
		                        <div class="fly_time clearfix text-center">
		                            <ticket:common type="flightCompanyByTwoCode" value="${arrivalAtPort.acw }"/>
									<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
									<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24" style='position:relative;top:5px;'>
		                            &nbsp;&nbsp;
									${flightCompanyByTwoCode.name }
									&nbsp;&nbsp;
		                        </div>
		                    </li>
		                </ul>
		            </div>
					<s:if test="arrivalAtPort != null">
						<div class='from_or_to_fly'>
							<div class="c_content">
				               <dl>
				               		<c:choose>
				               			<%-- <c:when test="${!empty arrivalAtPort.pft && !empty arrivalAtPort.torg}">
			                        		<s:set var="reachTime" value="arrivalAtPort.torg"></s:set>
			                        		<s:set var="planeFlag" value="'计划起飞'"></s:set>
				               			</c:when> --%>
				               			<c:when test="${!empty arrivalAtPort.abp}">
			                        		<s:set var="reachTime" value="arrivalAtPort.abp"></s:set>
		                        			<s:set var="planeFlag" value="'实际起飞'"></s:set>
				               			</c:when>
				               			<c:when test="${!empty arrivalAtPort.edp}">
			                        		<s:set var="reachTime" value="arrivalAtPort.edp"></s:set>
		                        			<s:set var="planeFlag" value="'预计起飞'"></s:set>
				               			</c:when>
				               			<c:otherwise>
			                        		<s:set var="reachTime" value="arrivalAtPort.stp"></s:set>
		                        			<s:set var="planeFlag" value="'计划起飞'"></s:set>
				               			</c:otherwise>
		                        	</c:choose>
			                        <dt>
			                            <i class='from_fly'></i>
		                            	<s:set var="setoutCity" value="airportInfoService.queryByThreeCode(arrivalAtPort.org,'site')"/>
		                            	${setoutCity.name }
										<span><s:date name="#reachTime" format="MM-dd HH:mm"/></span>
			                        </dt>
			                        <dd class='line'>
			                            <div class="xl4"><s:property value="#planeFlag"/><p style="font-size: 48px;"><s:date name="#reachTime" format="HH:mm"/></p></div>
			                            <div class="xl4">值机柜台<p>--</p></div>
			                            <div class="xl4">登机口<p>--</p></div>
			                        </dd>
			                    </dl>
			                </div>
			                <s:if test="arrivalAtPort.pft != null && arrivalAtPort.pft != ''">
			                	<div class="c_content">
									<s:if test="departFromPort.ata != null">
	                        			<s:set var="preStationTime" value="arrivalAtPort.abp"></s:set>
		                        		<s:set var="planeFlag" value="'实际起飞'"></s:set>
		                        	</s:if>
									<s:elseif test="departFromPort.edp != null">
	                        			<s:set var="preStationTime" value="arrivalAtPort.edp"></s:set>
		                        		<s:set var="planeFlag" value="'预计起飞'"></s:set>
		                        	</s:elseif>
		                        	<s:else>
		                        		<s:set var="preStationTime" value="arrivalAtPort.stp"></s:set>
		                        		<s:set var="planeFlag" value="'计划起飞'"></s:set>
		                        	</s:else>
				                    <dl>
				                        <dt>
				                            <i class='${stopover != "1" ? "from_fly" : "from_to" }'></i>
				                           	<s:set var="pft" value="airportInfoService.queryByThreeCode(arrivalAtPort.pft,'site')"/>
				                        	   ${stopover != '1' ? '经停地:' : null }	${pft.name }
				                            <span><s:date name="#preStationTime" format="MM-dd HH:mm"/></span></dt>
				                        <dd class='line'>
				                            <div class="xl4"><s:property value="#planeFlag"/><p style="font-size: 48px;"><s:date name="#preStationTime" format="HH:mm"/></p></div>
				                            <div class="xl4">行李转盘<p>--</p></div>
				                            <div class="xl4">到达口<p>--</p></div>
				                        </dd>
				                    </dl>
				                </div>
			                </s:if>
			                <c:if test="${param.stopover != '1' }">
				                <s:if test="arrivalAtPort.pft2 != null && arrivalAtPort.pft2 != ''">
				                	<div class="c_content">
						               <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="pft2" value="airportInfoService.queryByThreeCode(arrivalAtPort.pft2,'site')"/>
					                        	   经停地:	${pft2.name }
					                            <span></span></dt>
					                        <dd class='line'>
					                            <div class="xl4"><p class='c_blue'></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </s:if>
				                <s:if test="arrivalAtPort.pft3 != null && arrivalAtPort.pft3 != ''">
				                	<div class="c_content">
						               <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="pft3" value="airportInfoService.queryByThreeCode(arrivalAtPort.pft3,'site')"/>
					                        	   经停地:	${pft3.name }
					                            <span></span></dt>
					                        <dd class='line'>
					                            <div class="xl4"><p class='c_blue'></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </s:if>
				                <s:if test="arrivalAtPort.pft4 != null && arrivalAtPort.pft4 != ''">
				                	<div class="c_content">
						               <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="pft4" value="airportInfoService.queryByThreeCode(arrivalAtPort.pft4,'site')"/>
					                        	   经停地:	${pft4.name }
					                            <span></span></dt>
					                        <dd class='line'>
					                            <div class="xl4"><p class='c_blue'></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </s:if>
				                <s:if test="arrivalAtPort.pft5 != null && arrivalAtPort.pft5 != ''">
				                	<div class="c_content">
						               <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="pft5" value="airportInfoService.queryByThreeCode(arrivalAtPort.pft5,'site')"/>
					                        	   经停地:	${pft5.name }
					                            <span></span></dt>
					                        <dd class='line'>
					                            <div class="xl4"><p class='c_blue'></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </s:if>
				                <s:if test="arrivalAtPort.pft6 != null && arrivalAtPort.pft6 != ''">
				                	<div class="c_content">
						               <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="pft6" value="airportInfoService.queryByThreeCode(arrivalAtPort.pft6,'site')"/>
					                        	   经停地:	${pft6.name }
					                            <span></span></dt>
					                        <dd class='line'>
					                            <div class="xl4"><p class='c_blue'></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </s:if>
				                <div class="c_content">
			                		<s:if test="arrivalAtPort.tdt != null">
		                        		<s:set name="reachTime" var="reachTime" value="arrivalAtPort.tdt"></s:set>
		                        		<s:set name="planeflag" var="planeFlag" value="'实际到达'"></s:set>
		                        	</s:if>
			                		<s:elseif test="arrivalAtPort.eta != null">
		                        		<s:set name="reachTime" var="reachTime" value="arrivalAtPort.eta"></s:set>
		                        		<s:set name="planeflag" var="planeFlag" value="'预计到达'"></s:set>
		                        	</s:elseif>
		                        	<s:else>
		                        		<s:set name="reachTime" var="reachTime" value="arrivalAtPort.sta"></s:set>
		                        		<s:set name="planeflag" var="planeFlag" value="'计划到达'"></s:set>
		                        	</s:else>
				                    <dl>
				                        <dt>
				                            <i class='from_to'></i>
			                            	<s:set var="desCity" value="airportInfoService.queryByThreeCode(arrivalAtPort.des,'site')"/>
			                            	${desCity.name }
			                             	<span><s:date name="#reachTime " format="MM-dd HH:mm"/></span>
										</dt>
				                        <dd class='line'>
				                            <div class="xl4"><s:property value="#planeFlag"/><p style="font-size: 48px;"><s:date name="#reachTime" format="HH:mm"/></p></div>
				                            <div class="xl4">
				                            	行李转盘
				                            	<p>
				                            	<c:choose>
				                            	<c:when test="${!empty arrivalAtPort.btt }">
				                            		<a href="<ticket:common type="flightPositionUrl" value="${arrivalAtPort.btt }xlzp"/>" style="color: #00AAFF">${arrivalAtPort.btt }</a>
				                            	</c:when>
				                            	<c:otherwise>
					                            	--
				                            	</c:otherwise>
				                            	</c:choose>
				                            	</p>
				                            </div>
				                            <div class="xl4">
				                            	机位
				                            	<p>
					                            <s:if test="arrivalAtPort.cedn != null">
						                            <a href="<ticket:common type="flightPositionUrl" value="${arrivalAtPort.cedn }ddk"/>" style="color: #00AAFF">${arrivalAtPort.cedn }</a>
					                            </s:if>
					                            <s:else>
					                            	--
					                            </s:else>
				                            	</p>
				                            </div>
				                        </dd>
				                    </dl>
				                </div>
			                </c:if>
						</div>
					<div class="tit_tab">
						<s:if test="arrivalAtPort.type =='transfer'">
							<a href="#" class="journeyHelper" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="transfer">旅程助手</a>
						</s:if>
						<s:else>
							<a href="#" class="journeyHelper" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival">旅程助手</a>
						</s:else>
						<s:if test='focusFlightFlag == "hadFocus"'>
							<a href="/airport/airport_restaurant.action?flag=lingshou">购物中心</a>
						</s:if>
						<s:else>
							<s:if test="arrivalAtPort.type =='transfer'">
								<a href="#" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="transfer" class='memberFocusFlight'>关注航班</a>
							</s:if>
							<s:else>
								<s:if test="stopover == '1'">
									<a href="#" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival" class='memberFocusFlight' stopover='1'>关注航班</a>
								</s:if>
								<s:else>
									<s:if test='flightState == "transfer"'>
										<a href="#" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="transfer" class='memberFocusFlight' >关注航班</a>
									</s:if>
									<s:else>
										<a href="#" flightNumber="${arrivalAtPort.flt }" flightDate="${arrivalAtPort.flightDate }" flightState="arrival" class='memberFocusFlight' >关注航班</a>
									</s:else>
								</s:else>
							</s:else>
						</s:else>
						<a href="/airport_restaurant.action?flag=canyin&fromQuickMenu=true">餐饮服务</a>
						<a href="/airport/1455874140786.ticket">自助旅游</a>
					</div>
					</s:if>
					<s:else>
						<div class="c_content">
							${noDataMessage }
						</div>
						<div class="tit_tab">
							<a href="javascript:return_prepage();">返回</a>
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
</html>