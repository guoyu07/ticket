<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<%@include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp"></s:include>
				<div class="mobile-main">
					<s:if test="#session.trafficTypeList != null">
						<s:iterator id="trafficType" value="#session.trafficTypeList">
							<div class="select_tit">
			                	<button type="button" class="button  bg dropdown-toggle fz22 block d_button" style="border:1px solid #ddd">
								${trafficType.name }
								</button>
				            </div>
							<s:if test="#session.trafficLineList != null">
								<s:iterator id="trafficLine" value="#session.trafficLineList">
									<s:if test="#trafficType.id == #trafficLine.trafficType_id">
										<div class="mr40 tab_ls">
							                <dl>
							                	<dt class='fz20 line'>
													<div class='x11'>
														<font class='fz26' style="color:#151515">${trafficLine.name }</font>
		                                                   <p class='padding-top' style="color:#696969">${trafficLine.startStation }-${trafficLine.endStation }</p> 
													</div>
													<div class="x1 text-right">
														<span class='icon-plus'></span>
													</div>
												</dt>
												<dd class='fz22'>		
												<ul>
													<li style="	border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #CCCCCC; padding-bottom:20px; padding-top:20px;">
													<ticket:common type="trafficStationListByLineAndGo" value="${trafficLine.id }"/>
													<s:if test="#request.trafficStationListByLineAndGo != null">
														去程：
														<s:iterator id="trafficLineAndStation" value="#request.trafficStationListByLineAndGo" status="st">
															<ticket:common type="trafficStationObj" value="${trafficLineAndStation.trafficStation_id }"/>
															<s:if test="#st.last">
																<b>
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:else>
																</b>
																<s:if test="#trafficStationObj.descript != null">
																	(${trafficStationObj.descript })
																</s:if>
															</s:if>
															<s:else>
																<b>
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:else>											
																</b>
																<s:if test="#trafficStationObj.descript != null">
																	(${trafficStationObj.descript })
																</s:if>——
															</s:else>
														</s:iterator>
													</s:if>
													<li style="	border-bottom-width: 1px;border-bottom-style: solid;border-bottom-color: #CCCCCC; padding-bottom:20px;padding-top:20px;">
													<ticket:common type="trafficStationListByLineAndBack" value="${trafficLine.id }"/>
													<s:if test="#request.trafficStationListByLineAndBack != null">
														回程：
														<s:iterator id="trafficLineAndStation" value="#request.trafficStationListByLineAndBack" status="st">
															<ticket:common type="trafficStationObj" value="${trafficLineAndStation.trafficStation_id }"/>
															<s:if test="#st.last">
																<b>
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:else>
																</b>
																<s:if test="#trafficStationObj.descript != null">
																	(${trafficStationObj.descript })
																</s:if>
															</s:if>
															<s:else>
																<b>
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }&name=${trafficStationObj.name }" style="color:#0099FF">${trafficStationObj.name }</a>
																	</s:else>
																</b>
																<s:if test="#trafficStationObj.descript != null">
																	(${trafficStationObj.descript })
																</s:if>——
															</s:else>
														</s:iterator>
													</s:if>
													<br />
													票价：${trafficLine.price }<br>
													<br />发车频率：${trafficLine.departureRate }<br />
													首班车时间：<s:date name="#trafficLine.startTime" format="HH:mm" />，末班车时间：<s:date name="#trafficLine.endTime" format="HH:mm" /><br/>
													<s:if test="#trafficLine.remark != null">
														注：${trafficLine.remark }
													</s:if>
													</li>
												</ul>
											</dd>
										</dl>
										</div>
									</s:if>
								</s:iterator>
							</s:if>
						</s:iterator>
					</s:if>
					<s:else>
					<div class="mr40 tab_ls">
						${noDataMessage }
					</div>
				</s:else>
				</div>
				<%@include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@include file="../common/left.jsp"%>
		</div>
	</body>
</html>