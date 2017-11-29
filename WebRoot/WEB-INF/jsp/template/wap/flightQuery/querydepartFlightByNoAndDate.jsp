<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ticket.mq.FlightStatus"%>
<%@page import="com.ticket.mq.BoardStatus"%>
<%@page import="com.ticket.pojo.DepartFromPort"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<style type="text/css">
    .tit_tab a{ width:120px !important; height:120px !important; line-height:120px; border-radius:60px !important;}
	.mobile-main{ padding-bottom:0px !important;}
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
										<ticket:common type="flightCompanyByTwoCode" value="${departFromPort.acw }"/>
										<ticket:commonAnnex type="annex" entityUuid="${flightCompanyByTwoCode.id }" annexType="1" size="1"/>
										<img src="<%=request.getScheme()%>://${image_server_url}${annex.annexPath }" height="24" width="24" style='position:relative;top:-2px;'>
			                            &nbsp;${flightCompanyByTwoCode.name }&nbsp;
			                            <em class='c_black fz40 inline_block'>${departFromPort.flt }</em>
		                            </span>
		                        </div>
		                        <div class="fly_time clearfix text-center fz20">
		                        	<div class='padding-bottom'>
				                        <s:if test="departFromPort.got != null">
				                        	登机时间: <font><s:date name="departFromPort.got" format="yyyy-MM-dd HH:mm"/></font>
					                        &nbsp;&nbsp; 
				                        </s:if>
				                        登机口状态:
				                        <s:if test="departFromPort.boardStaus != null && departFromPort.boardStaus !='' ">
								           <font ><%=Enum.valueOf(BoardStatus.class, ((DepartFromPort)request.getAttribute("departFromPort")).getBoardStaus().toUpperCase()).getText() %></font>
				                        </s:if>
				                        <s:else>
				                        	<font >未开放</font>
				                        </s:else>
				                        &nbsp;&nbsp; 
										<s:if test="departFromPort.frs != null && departFromPort.frs !=''">  
											航班状态: <font><%=Enum.valueOf(FlightStatus.class, ((DepartFromPort)request.getAttribute("departFromPort")).getFrs().toUpperCase()).getText() %></font>
				                        </s:if>
				                        &nbsp;&nbsp; 飞机编号：<font >${departFromPort.airnum }</font>
			                        </div>
		                        </div>
		                    </li>
		                </ul>
		            </div>
					<s:if test="departFromPort != null">
						<div class='from_or_to_fly'>
							<div class="c_content">
		                        <s:if test="departFromPort.abt != null">
                        			<s:set var="setoutTime" value="departFromPort.abt"></s:set>
	                        		<s:set var="planeFlag" value="'实际起飞'"></s:set>
	                        	</s:if>
								<s:elseif test="departFromPort.etd != null">
                        			<s:set var="setoutTime" value="departFromPort.etd"></s:set>
	                        		<s:set var="planeFlag" value="'预计起飞'"></s:set>
	                        	</s:elseif>
	                        	<s:else>
	                        		<s:set var="setoutTime" value="departFromPort.std"></s:set>
	                        		<s:set var="planeFlag" value="'计划起飞'"></s:set>
	                        	</s:else>
			                    <dl>
			                        <dt>
			                            <i class='from_fly'></i>昆明
			                           	<span><s:date name="#setoutTime" format="MM-dd HH:mm"/></span>
			                        </dt>
			                        <dd class='line'>
			                            <div class="xl4"><s:property value="#planeFlag"/><p style="font-size: 48px;"><s:date name="#setoutTime" format="HH:mm"/></p></div>
			                            <div class="xl4">值机柜台<p>
			                            	<c:choose>
			                            	<c:when test="${param.stopover == '1' && !empty departFromPort.CD2 }">
			                            	<a href="<ticket:common type="infoPositionListByCounter2" value="${departFromPort.CD2 }"/>" style="color: #00AAFF">${departFromPort.CD2 }</a>
			                            	</c:when>
			                            	<c:when test="${!empty departFromPort.cid }">
			                            	<a href="<ticket:common type="infoPositionListByCounter2" value="${departFromPort.cid }"/>" style="color: #00AAFF">${departFromPort.cid }</a>
			                            	</c:when>
			                            	<c:otherwise>
			                            	--
			                            	</c:otherwise>
			                            	</c:choose>
			                            </p></div>
			                            <div class="xl4">登机口<p>
			                            	<s:if test="departFromPort.gat != null">
			                            		<a href="<ticket:common type="flightPositionUrl" value="${departFromPort.gat }djk"/>" style="color: #00AAFF">${departFromPort.gat }</a>
			                            	</s:if>
			                            	<s:else>
			                            		--
			                            	</s:else>
			                            </p></div>
			                        </dd>
			                       
			                    </dl>
			                </div>
							<s:set var="vi1" value="airportInfoService.queryByThreeCode(departFromPort.vi1,'site')"/>
			                <c:if test="${!empty departFromPort.CD2 && param.stopover != '1' }">
	                        <div style="  margin-left:40px; margin-top: -32px;margin-bottom: 40px;">
			                	<font color="red" style="font-size:22px;">温馨提示：到&nbsp;${vi1.name }&nbsp;的旅客请至&nbsp;${departFromPort.CD2 }&nbsp;值机柜台办理！</font>
	                        </div>
			                </c:if>

			                <c:if test="${!empty departFromPort.vi1 }">
			                	<div class="c_content">
									<s:if test="departFromPort.aan != null">
	                        			<s:set var="setoutTime" value="departFromPort.aan"></s:set>
		                        		<s:set var="planeFlag" value="'实际到达'"></s:set>
		                        	</s:if>
									<s:elseif test="departFromPort.ean != null">
	                        			<s:set var="setoutTime" value="departFromPort.ean"></s:set>
		                        		<s:set var="planeFlag" value="'预计到达'"></s:set>
		                        	</s:elseif>
		                        	<s:else>
		                        		<s:set var="setoutTime" value="departFromPort.stn"></s:set>
		                        		<s:set var="planeFlag" value="'计划到达'"></s:set>
		                        	</s:else>
				                    <dl>
				                        <dt>
				                            <i class='${stopover != "1" ? "from_fly" : "from_to" }'></i>
				                        	   ${stopover != '1' ? '经停地:' : null }	${vi1.name }
				                            <span><s:date name="#setoutTime" format="MM-dd HH:mm"/></span></dt>
				                        <dd class='line'>
				                            <div class="xl4"><s:property value="#planeFlag"/><p style="font-size: 48px;"><s:date name="#setoutTime" format="HH:mm"/></p></div>
				                            <div class="xl4">行李转盘<p>--</p></div>
				                            <div class="xl4">到达口<p>--</p></div>
				                        </dd>
				                    </dl>
				                </div>
			                </c:if>
			                <c:if test="${param.stopover != '1' }">
				                <c:if test="${!empty departFromPort.vi2 }">
				                	<div class="c_content">
					                    <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="vi2" value="airportInfoService.queryByThreeCode(departFromPort.vi2,'site')"/>
					                        	   经停地:	${vi2.name }
					                            <span></span>
											</dt>
					                        <dd class='line'>
					                            <div class="xl4"><p style="font-size: 48px;"></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </c:if>
				                <c:if test="${!empty departFromPort.vi3 }">
				                	<div class="c_content">
					                    <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="vi3" value="airportInfoService.queryByThreeCode(departFromPort.vi3,'site')"/>
					                        	   经停地:	${vi3.name }
					                            <span></span>
											</dt>
					                        <dd class='line'>
					                            <div class="xl4"><p style="font-size: 48px;"></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </c:if>
				                <c:if test="${!empty departFromPort.vi4 }">
				                	<div class="c_content">
					                    <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="vi4" value="airportInfoService.queryByThreeCode(departFromPort.vi4,'site')"/>
					                        	   经停地:	${vi4.name }
					                            <span></span>
											</dt>
					                        <dd class='line'>
					                            <div class="xl4"><p style="font-size: 48px;"></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </c:if>
				                <c:if test="${!empty departFromPort.vi5 }">
				                	<div class="c_content">
					                    <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="vi5" value="airportInfoService.queryByThreeCode(departFromPort.vi5,'site')"/>
					                        	   经停地:	${vi5.name }
					                            <span></span>
											</dt>
					                        <dd class='line'>
					                            <div class="xl4"><p style="font-size: 48px;"></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </c:if>
				                <c:if test="${!empty departFromPort.vi6 }">
				                	<div class="c_content">
					                    <dl>
					                        <dt>
					                            <i class='from_fly'></i>
					                           	<s:set var="vi6" value="airportInfoService.queryByThreeCode(departFromPort.vi6,'site')"/>
					                        	   经停地:	${vi6.name }
					                            <span></span>
											</dt>
					                        <dd class='line'>
					                            <div class="xl4"><p style="font-size: 48px;"></p></div>
					                            <div class="xl4">行李转盘<p>--</p></div>
					                            <div class="xl4">到达口<p>--</p></div>
					                        </dd>
					                    </dl>
					                </div>
				                </c:if>
				                <div class="c_content">
				                    <dl>
				                        <dt>
				                            <i class='from_to'></i>
			                            	<ticket:common type="airportInfoByThreeCode" value="${departFromPort.des }"/>
			                            	${airportInfoByThreeCode.name }
				                             <span>
		                            			<s:date name="departFromPort.tdes" format="MM:dd HH:mm"/>
											</span>
				                        </dt>
				                        <dd class='line'>
				                            <div class="xl4">
				                            	<c:choose>
					                            	<c:when test="${empty departFromPort.vi1 && !empty departFromPort.aan }">
														实际到达<p style="font-size: 48px;"><s:date name="departFromPort.aan" format="HH:mm"/></p>
					                            	</c:when>
					                            	<c:when test="${empty departFromPort.vi1 && !empty departFromPort.ean }">
														预计到达<p style="font-size: 48px;"><s:date name="departFromPort.ean" format="HH:mm"/></p>
					                            	</c:when>
					                            	<c:when test="${empty departFromPort.vi1 && !empty departFromPort.stn }">
														计划到达<p style="font-size: 48px;"><s:date name="departFromPort.stn" format="HH:mm"/></p>
					                            	</c:when>
					                            	<%-- <c:when test="${!empty departFromPort.tdes }">
														计划到达<p style="font-size: 48px;"><s:date name="departFromPort.tdes" format="HH:mm"/></p>
					                            	</c:when> --%>
					                            	<c:otherwise>
					                            		计划到达<p style="font-size: 48px;">--</p>
													</c:otherwise>
												</c:choose>
											</div>
				                            <div class="xl4">行李转盘<p>--</p></div>
				                            <div class="xl4">到达口<p>--</p></div>
				                        </dd>
				                    </dl>
				                </div>
			                </c:if>
						</div>
						<div class="tit_tab">
							<a href="#" class="journeyHelper" flightNumber="${departFromPort.flt }" flightDate="${departFromPort.flightDate }" flightState="depart" flag="detail" <s:if test="stopover != '1'">stopover="1"</s:if>>旅程助手</a>
							<s:if test='focusFlightFlag == "hadFocus"'>
								<a href="/airport/bianjiedengji.ticket">便捷登机</a>
							</s:if>
							<s:else>
								<a href="#" flightNumber="${departFromPort.flt }" flightDate="${departFromPort.flightDate }" flightState="depart" class='memberFocusFlight' <s:if test="stopover != '1'">stopover="1"</s:if>>关注航班</a>
							</s:else>
							<a href="/airport_restaurant.action?flag=canyin&fromQuickMenu=true">餐饮服务</a>
							<s:set var="checkinInfos" value="checkinInfoService.query(departFromPort.flt, departFromPort.flightDate)"></s:set>
							<s:if test="#checkinInfos.size() > 0">
								<a href="/airportm_checkinInfo.action?flightNumber=${departFromPort.flt }&flightDate=${departFromPort.flightDate }">网上值机</a>
							</s:if>
							<s:else>
								<a href="/airport/wangshangzhiji.ticket">网上值机</a>
							</s:else>
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
                    <ticket:common type="airportInfoByThreeCode" value="${departFromPort.des }"/>
					<ticket:common type="advertFlightListByCity" value="${airportInfoByThreeCode.name }"/>
					<s:if test="#request.advertFlightListByCity != null">
						<s:iterator value="#request.advertFlightListByCity" var="advertFlight">
							<ticket:common type="advertObjByName" value="${advertFlight.advert.name }"/>
							<s:if test="#request.advertObjByName != null">
								<ticket:commonAnnex type="queryAnnexList" entityUuid="${advertObjByName.id }" annexType="1" size="10"/>
				            	<s:if test="#request.queryAnnexList != null">
				            		<a href="${advertFlight.advert.url }"><img src="${queryAnnexList[0].annexPath }" alt=" " width="100%" height="80"/></a>
				            	</s:if>
							</s:if>
						</s:iterator>
					</s:if>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>