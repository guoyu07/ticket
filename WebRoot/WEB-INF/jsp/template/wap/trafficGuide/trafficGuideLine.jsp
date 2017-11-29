<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">机场交通</s:param>
				</s:include>
				<div class="mobile-main">
					<ticket:common type="trafficTypeList"/>
					<div class="select_tit">
						<div class="button-group no-background">
							<button type="button" class="button  bg dropdown-toggle fz22"
								style="border: 1px solid #ddd">
								<font>
									<s:if test="id != null && id !=''">
										<ticket:common type="trafficTypeObj" value="${id }"/>
										${trafficTypeObj.name }
									</s:if>
									<s:else>
										请选择路线类别
									</s:else>
								</font>
								<span class="downward"></span>
							</button>
							<ul class="drop-menu trafficLineAjax">
								<s:if test="#request.trafficTypeList != null">
									<s:iterator id="trafficType" value="#request.trafficTypeList">
										<li trafficTypeId="${trafficType.id }">
											${trafficType.name }
										</li>
									</s:iterator>
								</s:if>
							</ul>
						</div>
					</div>
					<div class="trafficLine">
						<div class="mr40 tab_ls">
							<s:if test="id != null && id !=''">
								<ticket:common type="trafficLineListByTypeId" value="${id }"/>
								<s:if test="#request.trafficLineListByTypeId != null">
									<s:iterator id="trafficLine" value="#request.trafficLineListByTypeId">
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
													票价：${trafficLine.price }元<br>
													<br />发车频率：${trafficLine.departureRate }<br />
													首班车时间：<s:date name="#trafficLine.startTime" format="HH:mm" />，末班车时间：<s:date name="#trafficLine.endTime" format="HH:mm" /><br/>
													<s:if test="#trafficLine.remark != null">
														注：${trafficLine.remark }
													</s:if>
													</li>
												</ul>
											</dd>
										</dl>
									</s:iterator>
								</s:if>
								<s:else>
									<dl>
										<dt class='fz20 line'>
											<div class='x11'>
												${noDataMessage }
											</div>
										</dt>
									</dl>
								</s:else>
							</s:if>
						</div>
						<s:if test="#request.trafficTypeObj.name == '停车楼'">
							<c:forEach var="info" items="${positions }">
							<a 
								<s:if test="#session.fromApp == null && #parameters.fromApp == null">
								href="http://api.map.baidu.com/marker?location=${info[0]},${info[1]}&title=${info[2]}&content=${info[2]}&output=html&floorNumber=${info[3]}"
								</s:if>
								<s:else>
								href="/airport_daohang.action?longitude=${info[0]}&latitude=${info[1] }&name=${info[2]}&floorNumber=${info[3]}"
								</s:else>
								>
								<div class="tit b_blue c_white">
									${info[2] }
									<%-- <s:if test="#request.trafficTypeObj.name == '自驾车'">
										导航
									</s:if>
									<s:else>
										乘车点导航
									</s:else> --%>
								</div>
							</a>
							</c:forEach>
						</s:if>
						<s:else>
						<a 
							<s:if test="#session.fromApp == null && #parameters.fromApp == null">
							href="http://api.map.baidu.com/marker?location=${trafficTypeObj.latitude },${trafficTypeObj.longitude }&title=${trafficTypeObj.name }&content=${trafficTypeObj.name }&output=html&floorNumber=${trafficTypeObj.floorNumber}"
							</s:if>
							<s:else>
							href="/airport_daohang.action?longitude=${trafficTypeObj.longitude }&latitude=${trafficTypeObj.latitude }&name=${trafficTypeObj.name}&floorNumber=${trafficTypeObj.floorNumber}"
							</s:else>
							>
							<div class="tit b_blue c_white">
								<s:if test="#request.trafficTypeObj.name == '自驾车'">
									导航
								</s:if>
								<s:if test="#request.trafficTypeObj.name == '出租车'">
									出租车乘车点
								</s:if>
								<s:if test='#request.trafficTypeObj.name == "机场公交（919）"'>
									机场公交乘车点
								</s:if>
								<s:if test="#request.trafficTypeObj.name == '地铁'">
									地铁乘坐点
								</s:if>
								<s:if test="#request.trafficTypeObj.name == '地铁（已停运）'">
									地铁乘坐点
								</s:if>
								
								<s:if test="#request.trafficTypeObj.name == '空港快线'">
									空港快线乘车点
								</s:if>
							</div>
						</a>
						</s:else>
					</div>
				</div>
				<%@include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>