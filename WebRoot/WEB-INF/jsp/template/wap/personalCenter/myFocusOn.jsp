<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<s:if test="flightNumber != null">
							<a href="javascript:history.go(-1);" class='FLAPP goBackToflight'><i class="icon-angle-left"></i></a>
						</s:if>
						<s:else>
							<a href="javascript:;" class='FL'><i class="icon-angle-left"></i></a>
						</s:else>
							我的关注
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
						</a>
					</div>
					<div class="header_search">
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" placeholder='你要搜索的内容' datatype="*" id="keyword" name="keyword">
							</label>
							<button class="button bg-sub" id="commonSearchBtn">
								搜索
							</button>
						</div>
					</div>
				</div>
				<div class="mobile-main">
					<ticket:common type="memberFocusFlightList"/>
					<s:if test="#request.memberFocusFlightList != null">
                        <div class="search_fly">
						<s:iterator id="memberFocusFlight" value="#request.memberFocusFlightList">
							<ticket:common type="IsDateInPlan" value="${memberFocusFlight.flightDate}"/>
							<%-- 计划航班 --%>
							<s:if test="#request.IsDateInPlan == true">
								<ticket:common type="trangeFlightNoFromTwoToThree" value="${memberFocusFlight.flightNumber }"/>
								<s:if test="#request.trangeFlightNoFromTwoToThree != null">
									<ticket:common type="planFlightInfo" value="${trangeFlightNoFromTwoToThree }"/>
									<s:if test="#request.planFlightInfo != null">
										<div class="c_content">
											<a href="#" class="planFlightDetail" flightNumber="${memberFocusFlight.flightNumber }" flightDate="<s:date name="#memberFocusFlight.flightDate" format="yyyy-MM-dd"/>" flag="${memberFocusFlight.flightState }">
												<p class="text-center fz26 clearfix">
													<span class="float-left">
														<ticket:common type="queryCompanyByFlightNoTwo" value="${memberFocusFlight.flightNumber }"/>
														<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNoTwo.id }" annexType="1" size="1"/>
														<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
														&nbsp;&nbsp;
														${queryCompanyByFlightNoTwo.name }
														&nbsp;&nbsp;
														<font class='c_black'>${memberFocusFlight.flightNumber }</font>
													</span>
													<span class="float-right c_blue">
														<!-- 显示是否是共享航班 -->
													</span>
												</p>
	                                            <div class="fly_line clearfix fz26">
													<span class="float-left">
														<em class="c_black"><s:date name="#memberFocusFlight.flightDate" format="yyyy-MM-dd"/>  ${planFlightInfo.std }</em>
														<font class="fz26"><ticket:common type="airportInfoByFourCode" value="${planFlightInfo.dept }"/>${airportInfoByFourCode.name }</font>
													</span>
													<i style="padding-top:70px;font-size:16px;font-style:normal;" class="c_blue">
														
													</i>
													<span class="float-right">
														<em class="c_black">
															<s:date name="#memberFocusFlight.flightDate" format="yyyy-MM-dd"/>${planFlightInfo.eta }
														</em>
														<font class="fz26">
															<!-- 终点站 -->
						                            		<ticket:common type="airportInfoByFourCode" value="${planFlightInfo.arrive }"/>${airportInfoByFourCode.name }
														</font>
													</span>
												</div>
											</a>
											<div class="text-left padding-big-top"> 
												<a href="#" class="planFlightDetail" flightNumber="${memberFocusFlight.flightNumber }" flightDate="<s:date name="#memberFocusFlight.flightDate" format="yyyy-MM-dd"/>" flag="${memberFocusFlight.flightState }"class="btnlc button bg-blue fz20 margin-bottom">查看详情</a>
												<a href="#" class="btnlc button bg-blue fz20 margin-bottom flightNavigation" id="${memberFocusFlight.id }">旅程助手</a>
												<a href="#" class="removeFocus" id="${memberFocusFlight.id }"><img src="/template/wap/images/close_btn1.jpg"  alt=""></a>
											</div>
										</div>
									</s:if>
								</s:if>
							</s:if>
							<s:else>
								<div class="c_content">
								<%-- 离港航班 --%>
								<s:if test="#memberFocusFlight.flightState == 'depart'">
									<s:set var="departFlightInfo" value="departFromPortService.queryByFlightNoAndDate(#memberFocusFlight.flightNumber,#memberFocusFlight.flightDate,'site')" />
									<a href="javascript:;">
										<p class="text-center fz26 clearfix">
											<span class="float-left">
												<ticket:common type="queryCompanyByFlightNoTwo" value="${memberFocusFlight.flightNumber }"/>
												<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNoTwo.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
												&nbsp;&nbsp;
												${queryCompanyByFlightNoTwo.name }
												&nbsp;&nbsp;
												<font class='c_black'>${memberFocusFlight.flightNumber }</font>
											</span>
											<span class="float-right c_blue">
												<!-- 显示是否是共享航班 -->
											</span>
										</p>
										<div class="fly_line clearfix fz26">
											<span class="float-left">
												<em class="c_black">
													<s:if test="#departFlightInfo.abt != null">
														<s:date name="#departFlightInfo.abt" format="HH:mm"/>
						                        	</s:if>
													<s:elseif test="#departFlightInfo.etd != null">
														<s:date name="#departFlightInfo.etd" format="HH:mm"/>
						                        	</s:elseif>
						                        	<s:else>
														<s:date name="#departFlightInfo.std" format="HH:mm"/>
						                        	</s:else>
												</em>
												<font class="fz26">
													<ticket:common type="airportInfoByThreeCode" value="${departFlightInfo.org }"/>${airportInfoByThreeCode.name }
												</font>
											</span>
											<i style="padding-top:70px;font-size:16px;font-style:normal;" class="c_blue">
											</i>
											<span class="float-right">
												<em class="c_black">
													<s:if test="#departFlightInfo.aan != null">
				                            			<s:date name="#departFlightInfo.aan" format="HH:mm"/>
						                        	</s:if>
													<s:elseif test="#departFlightInfo.ean != null">
				                            			<s:date name="#departFlightInfo.ean" format="HH:mm"/>
						                        	</s:elseif>
						                        	<s:else>
				                            			<s:date name="#departFlightInfo.stn" format="HH:mm"/>
						                        	</s:else>
												</em>
												<font class="fz26">
													<c:choose>
													<c:when test="${memberFocusFlight.stopover == '1' }">
													<ticket:common type="airportInfoByThreeCode" value="${departFlightInfo.vi1 }"/>${airportInfoByThreeCode.name }
													</c:when>
													<c:otherwise>
													<ticket:common type="airportInfoByThreeCode" value="${departFlightInfo.des }"/>${airportInfoByThreeCode.name }
													</c:otherwise>
													</c:choose>
												</font>
											</span>
										</div>
										<div class="text-left padding-big-top">
											<a href="/airport_queryDepartFlightNoAndDate.action?flightNumber=${memberFocusFlight.flightNumber }&flightDate=${memberFocusFlight.flightDate }&flightState=${memberFocusFlight.flightState }&stopover=${memberFocusFlight.stopover }" class="btnlc button bg-blue fz20 margin-bottom">查看详情</a>
											<a href="#" class="btnlc button bg-blue fz20 margin-bottom flightNavigation" id="${memberFocusFlight.id }">旅程助手</a>
											<a href="#" class="removeFocus" id="${memberFocusFlight.id }"><img src="/template/wap/images/close_btn1.jpg"  alt=""></a>
										</div>
									</a>
								</s:if>
								<%-- 进港航班 --%>
								<s:elseif test="#memberFocusFlight.flightState == 'arrival'">
									<s:set var="arrivalFlightInfo" value="arrivalAtPortService.queryByFlightNoAndDate(#memberFocusFlight.flightNumber,#memberFocusFlight.flightDate,'site')" />
									<a href="javascript:;">
                                       	<p class="text-center fz26 clearfix">
                                       		<span class="float-left">
												<ticket:common type="queryCompanyByFlightNoTwo" value="${memberFocusFlight.flightNumber }"/>
												<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNoTwo.id }" annexType="1" size="1"/>
												<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
												&nbsp;&nbsp;
												${queryCompanyByFlightNoTwo.name }
												&nbsp;&nbsp;
												<font class='c_black'>${memberFocusFlight.flightNumber }</font>
											</span>
											<span class="float-right c_blue">
												<!-- 显示是否是共享航班 -->
											</span>
										</p>
										<div class="fly_line clearfix fz26">
											<span class="float-left">
												<em class="c_black">
													<s:if test="#arrivalFlightInfo.abp != null">
						                        		<s:date name="#arrivalFlightInfo.abp" format="HH:mm" />
						                        	</s:if>
							                		<s:elseif test="#arrivalFlightInfo.edp != null">
						                        		<s:date name="#arrivalFlightInfo.edp" format="HH:mm" />
						                        	</s:elseif>
						                        	<s:else>
						                        		<s:date name="#arrivalFlightInfo.stp" format="HH:mm" />
						                        	</s:else>
												</em>
												<font class="fz26">
													<ticket:common type="airportInfoByThreeCode" value="${arrivalFlightInfo.org }"/>${airportInfoByThreeCode.name }
												</font>
											</span>
											<i style="padding-top:70px;font-size:16px;font-style:normal;" class="c_blue">
											</i>
											<span class="float-right">
												<em class="c_black">
													<s:if test="#arrivalFlightInfo.ata != null">
														<s:date name="#arrivalFlightInfo.ata" format="HH:mm" />
						                        	</s:if>
													<s:elseif test="#arrivalFlightInfo.eta != null">
														<s:date name="#arrivalFlightInfo.eta" format="HH:mm" />
						                        	</s:elseif>
						                        	<s:else>
														<s:date name="#arrivalFlightInfo.sta" format="HH:mm" />
						                        	</s:else>
												</em>
												<font class="fz26">
													<!-- 终点站 -->
				                            		<ticket:common type="airportInfoByThreeCode" value="${arrivalFlightInfo.des }"/>${airportInfoByThreeCode.name }
												</font>
											</span>
										</div>
										<div class="text-left padding-big-top">
											<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${memberFocusFlight.flightNumber }&flightDate=${memberFocusFlight.flightDate }&stopover=${memberFocusFlight.stopover }&flightState=${memberFocusFlight.flightState }" class="btnlc button bg-blue fz20 margin-bottom">查看详情</a>
											<a href="#" class="btnlc button bg-blue fz20 margin-bottom flightNavigation" id="${memberFocusFlight.id }">旅程助手</a>
											<a href="#" class="removeFocus" id="${memberFocusFlight.id }"><img src="/template/wap/images/close_btn1.jpg"  alt=""></a>
										</div>
									</a>
								</s:elseif>
								<%-- 中转航班 --%>
								<s:elseif test="#memberFocusFlight.flightState == 'transfer'">
									<s:set var="transferFlightInfo" value="arrivalAtPortService.queryTrasferByFlightNoAndDate(#memberFocusFlight.flightNumber,#memberFocusFlight.flightDate,'site')" />
									<a href="javascript:;">
                                       	<p class="text-center fz26 clearfix">
											<ticket:common type="queryCompanyByFlightNoTwo" value="${memberFocusFlight.flightNumber }"/>
											<ticket:commonAnnex type="annex" entityUuid="${queryCompanyByFlightNoTwo.id }" annexType="1" size="1"/>
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="24" width="24">
											&nbsp;&nbsp;
											${queryCompanyByFlightNoTwo.name }
											&nbsp;&nbsp;
											<font class='c_black'>${memberFocusFlight.flightNumber }</font>
										</p>
										<div class="fly_line clearfix fz26">
											<span class="float-left">
												<em class="c_black">
													<s:if test="#transferFlightInfo.abp != null">
						                        		<s:date name="#transferFlightInfo.abp" format="HH:mm" />
						                        	</s:if>
							                		<s:elseif test="#transferFlightInfo.edp != null">
						                        		<s:date name="#transferFlightInfo.edp" format="HH:mm" />
						                        	</s:elseif>
						                        	<s:else>
						                        		<s:date name="#transferFlightInfo.stp" format="HH:mm" />
						                        	</s:else>
												</em>
												<font class="fz26">
													<ticket:common type="airportInfoByThreeCode" value="${transferFlightInfo.org }"/>${airportInfoByThreeCode.name }
												</font>
											</span>
											<i style="padding-top:70px;font-size:16px;font-style:normal;" class="c_blue"></i>
											<span class="float-right">
											<em class="c_black">
												<s:if test="#transferFlightInfo.ata != null">
													<s:date name="#transferFlightInfo.ata" format="HH:mm" />
					                        	</s:if>
												<s:elseif test="#transferFlightInfo.eta != null">
													<s:date name="#transferFlightInfo.eta" format="HH:mm" />
					                        	</s:elseif>
					                        	<s:else>
													<s:date name="#transferFlightInfo.sta" format="HH:mm" />
					                        	</s:else>
											</em>
											<font class="fz26">
												<!-- 终点站 -->
			                            		<ticket:common type="airportInfoByThreeCode" value="${transferFlightInfo.des }"/>${airportInfoByThreeCode.name }
											</font>
											</span>
										</div>
										<div class="text-left padding-big-top">
											<a href="/airport_queryArrivalFlightNoAndDate.action?flightNumber=${memberFocusFlight.flightNumber }&flightDate=${memberFocusFlight.flightDate }&stopover=${memberFocusFlight.stopover }&flightState=${memberFocusFlight.flightState }" class="btnlc button bg-blue fz20 margin-bottom">查看详情</a>
											<a href="#" class="btnlc button bg-blue fz20 margin-bottom flightNavigation" id="${memberFocusFlight.id }">旅程助手</a>
											<a href="#" class="removeFocus" id="${memberFocusFlight.id }"><img src="/template/wap/images/close_btn1.jpg"  alt=""></a>
										</div>
									</a>
								</s:elseif>
								</div>
							</s:else>
						</s:iterator>
						</div>
					</s:if>
					<s:else>
						<div class="search_fly">
							<div class="c_content line">
								${noDataMessage }
							</div>
						</div>
					</s:else>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>