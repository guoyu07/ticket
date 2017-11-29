<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<title>交通指南 - 云南省昆明市长水机场</title>
<script type="text/javascript" src="/template/pc/js/trafficGuide.js"></script>
<script type="text/javascript">
				var Menu;
				window.onload = function() {
					Menu = new MyMenu("Menu");
					Menu.init();
				};
			</script>
<script type="text/javascript">
				function g(o){return document.getElementById(o);} 
				function HoverLi6(n){ 
					var isize = $("#isize").val();
					for(var i=1;i<=isize;i++){
						g('tb6_'+i).className='normaltab_6';
						g('tbc6_0'+i).className='undis clearfix';
					}
					g('tbc6_0'+n).className='dis clearfix';
					g('tb6_'+n).className='hovertab_6';
				}
				function g(o){return document.getElementById(o);} 
				function HoverLi7(n){ 
					var isize1 = $("#isize1").val();
					for(var i=1;i<=isize1;i++){
						g('tb7_'+i).className='normaltab_7';
						g('tbc7_0'+i).className='undis clearfix';
					}
					g('tbc7_0'+n).className='dis clearfix';
					g('tb7_'+n).className='hovertab_7';
				}
			</script>
</head>
<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<%@ include file="common/search.jsp"%>
	<div class="banner_28"></div banner>
	<div class="place w980 mt30">
		当前位置: <a href="/airportPc.action">首页</a> > <a href="#">交通指南</a>
	</div place w980 mt30>

	<div class="w980 mt30">
		<div class="JTZN_L bhh clearfix">
			<ul id="tb5_">
				<ticket:pcCommon type="trafficTypeList" />
				<s:if test="#request.trafficTypeList != null">
					<s:iterator id="trafficType" value="#request.trafficTypeList"
						status="st">
						<s:if test="#st.first">
							<li id="tb5_${st.count }" class="hovertab_5"
								onclick="x:HoverLi5(${st.count});"><a href="javascript:;"
								class="trafficTypeAjax" trafficType_id="${trafficType.id }">${trafficType.name }</a>
							</li>
						</s:if>
						<s:else>
							<li id="tb5_${st.count }" class="normaltab_5"
								onclick="x:HoverLi5(${st.count});"><a href="javascript:;"
								class="trafficTypeAjax" trafficType_id="${trafficType.id }">${trafficType.name }</a>
							</li>
						</s:else>
					</s:iterator>
				</s:if>
			</ul>
			<ticket:pcCommon type="trafficTypeList" />
			<s:if test="#request.trafficTypeList != null">
				<s:iterator id="trafficType" value="#request.trafficTypeList"
					status="st">
					<s:if test="#st.first">
						<div class="dis clearfix" id="tbc5_0${st.count }">
							<h2>
								<ticket:pcCommon type="trafficLineListByTypeId"
									value="${trafficType.id }" />
								<s:if test="#request.trafficLineListByTypeId != null">
									<s:set var="size"
										value="#request.trafficLineListByTypeId.size()"></s:set>
									<input value="${size }" id="isize" type="hidden" />
									<s:iterator id="trafficLine"
										value="#request.trafficLineListByTypeId" status="lineSt">
										<p id="tb6_${lineSt.count }" class="hovertab_6"
											onclick="x:HoverLi6(${lineSt.count });">${trafficLine.name
											}${trafficLine.startStation }-${trafficLine.endStation }</p>
										<s:if test="#lineSt.first">
											<h3 class="dis clearfix" id="tbc6_0${lineSt.count }">
												<ticket:common type="trafficStationListByLineAndGo"
													value="${trafficLine.id }" />
												<s:if test="#request.trafficStationListByLineAndGo != null">
													去程：
													<s:iterator id="trafficLineAndStation"
														value="#request.trafficStationListByLineAndGo" status="st">
														<ticket:common type="trafficStationObj"
															value="${trafficLineAndStation.trafficStation_id }" />
														<s:if test="#st.last">
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
														</s:if>
														<s:else>
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
													</s:iterator>
												</s:if>
												<br />
												<ticket:common type="trafficStationListByLineAndBack"
													value="${trafficLine.id }" />
												<s:if
													test="#request.trafficStationListByLineAndBack != null">
													回程：
													<s:iterator id="trafficLineAndStation"
														value="#request.trafficStationListByLineAndBack"
														status="st">
														<ticket:common type="trafficStationObj"
															value="${trafficLineAndStation.trafficStation_id }" />
														<s:if test="#st.last">
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
														</s:if>
														<s:else>
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
														</s:else>
													</s:iterator>
												</s:if>
												<br /> 票价：${trafficLine.price }<br> <br />发车频率：${trafficLine.departureRate
													}<br /> 首班车时间：<s:date name="#trafficLine.startTime"
														format="HH:mm" />，末班车时间：<s:date
														name="#trafficLine.endTime" format="HH:mm" /><br /> <s:if
														test="#trafficLine.remark != null">
													注：${trafficLine.remark }
												</s:if>
											</h3>
										</s:if>
										<s:else>
											<h3 class="undis clearfix" id="tbc6_0${lineSt.count }">
												<ticket:common type="trafficStationListByLineAndGo"
													value="${trafficLine.id }" />
												<s:if test="#request.trafficStationListByLineAndGo != null">
													去程：
													<s:iterator id="trafficLineAndStation"
														value="#request.trafficStationListByLineAndGo" status="st">
														<ticket:common type="trafficStationObj"
															value="${trafficLineAndStation.trafficStation_id }" />
														<s:if test="#st.last">
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
														</s:if>
														<s:else>
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
													</s:iterator>
												</s:if>
												<br />
												<ticket:common type="trafficStationListByLineAndBack"
													value="${trafficLine.id }" />
												<s:if
													test="#request.trafficStationListByLineAndBack != null">
													回程：
													<s:iterator id="trafficLineAndStation"
														value="#request.trafficStationListByLineAndBack"
														status="st">
														<ticket:common type="trafficStationObj"
															value="${trafficLineAndStation.trafficStation_id }" />
														<s:if test="#st.last">
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
														</s:if>
														<s:else>
															<s:if
																test="#session.fromApp == null && #parameters.fromApp == null">
																<a
																	href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:if>
															<s:else>
																<a
																	href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																	style="color:#0099FF">${trafficStationObj.name }</a>
															</s:else>
															<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
													</s:iterator>
												</s:if>
												<br /> 票价：${trafficLine.price }<br> <br />发车频率：${trafficLine.departureRate
													}<br /> 首班车时间：<s:date name="#trafficLine.startTime"
														format="HH:mm" />，末班车时间：<s:date
														name="#trafficLine.endTime" format="HH:mm" /><br /> <s:if
														test="#trafficLine.remark != null">
													注：${trafficLine.remark }
												</s:if>
											</h3>
										</s:else>
									</s:iterator>
								</s:if>
							</h2>
						</div>
					</s:if>
					<s:else>
						<s:if test="#trafficType.name == '出租车'">
							<div class="undis clearfix" id="tbc5_0${st.count }">
								<ticket:common type="trafficTypeObj" value="${trafficType.id }" />
								<h3 class='clearfix'>${trafficTypeObj.introduce }</h3>
							</div>
						</s:if>
						<s:elseif test="#trafficType.name == '停车楼'">
							<div class="undis clearfix" id="tbc5_0${st.count }">
								<ticket:common type="trafficTypeObj" value="${trafficType.id }" />
								<h3 class='clearfix'>${trafficTypeObj.introduce }</h3>
							</div>
						</s:elseif>
						<s:else>
							<div class="undis clearfix" id="tbc5_0${st.count }">
								<h2>
									<ticket:pcCommon type="trafficLineListByTypeId"
										value="${trafficType.id }" />
									<s:if test="#request.trafficLineListByTypeId != null">
										<s:set var="size"
										value="#request.trafficLineListByTypeId.size()"></s:set>
										<input value="${size }" id="isize1" type="hidden" />
										<s:iterator id="trafficLine"
											value="#request.trafficLineListByTypeId" status="lineSt">
											<p id="tb7_${lineSt.count }" class="hovertab_7"
												onclick="x:HoverLi7(${lineSt.count });">${trafficLine.name
												}${trafficLine.startStation }-${trafficLine.endStation }</p>
											<s:if test="#lineSt.first">
												<h3 class="dis" id="tbc7_0${lineSt.count }">
													<ticket:common type="trafficStationListByLineAndGo"
														value="${trafficLine.id }" />
													<s:if test="#request.trafficStationListByLineAndGo != null">
													去程：
													<s:iterator id="trafficLineAndStation"
															value="#request.trafficStationListByLineAndGo"
															status="st">
															<ticket:common type="trafficStationObj"
																value="${trafficLineAndStation.trafficStation_id }" />
															<s:if test="#st.last">
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
															</s:if>
															<s:else>
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
														</s:iterator>
													</s:if>
													<br />
													<ticket:common type="trafficStationListByLineAndBack"
														value="${trafficLine.id }" />
													<s:if
														test="#request.trafficStationListByLineAndBack != null">
													回程：
													<s:iterator id="trafficLineAndStation"
															value="#request.trafficStationListByLineAndBack"
															status="st">
															<ticket:common type="trafficStationObj"
																value="${trafficLineAndStation.trafficStation_id }" />
															<s:if test="#st.last">
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
															</s:if>
															<s:else>
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
														</s:iterator>
													</s:if>
													<br /> 票价：${trafficLine.price }<br> <br />发车频率：${trafficLine.departureRate
														}<br /> 首班车时间：<s:date name="#trafficLine.startTime"
															format="HH:mm" />，末班车时间：<s:date
															name="#trafficLine.endTime" format="HH:mm" /><br /> <s:if
															test="#trafficLine.remark != null">
													注：${trafficLine.remark }
												</s:if>
												</h3>
											</s:if>
											<s:else>
												<h3 class="undis clearfix" id="tbc7_0${lineSt.count }">
													<ticket:common type="trafficStationListByLineAndGo"
														value="${trafficLine.id }" />
													<s:if test="#request.trafficStationListByLineAndGo != null">
													去程：
													<s:iterator id="trafficLineAndStation"
															value="#request.trafficStationListByLineAndGo"
															status="st">
															<ticket:common type="trafficStationObj"
																value="${trafficLineAndStation.trafficStation_id }" />
															<s:if test="#st.last">
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
															</s:if>
															<s:else>
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
														</s:iterator>
													</s:if>
													<br />
													<ticket:common type="trafficStationListByLineAndBack"
														value="${trafficLine.id }" />
													<s:if
														test="#request.trafficStationListByLineAndBack != null">
													回程：
													<s:iterator id="trafficLineAndStation"
															value="#request.trafficStationListByLineAndBack"
															status="st">
															<ticket:common type="trafficStationObj"
																value="${trafficLineAndStation.trafficStation_id }" />
															<s:if test="#st.last">
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>
															</s:if>
															<s:else>
																<s:if
																	test="#session.fromApp == null && #parameters.fromApp == null">
																	<a
																		href="javascript:window.location.href='http://api.map.baidu.com/marker?location=${trafficStationObj.latitude },${trafficStationObj.longitude }&title=${trafficStationObj.name }&content=${trafficStationObj.name }&output=html'"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:if>
																<s:else>
																	<a
																		href="/airport_daohang.action?longitude=${trafficStationObj.longitude }&latitude=${trafficStationObj.latitude }"
																		style="color:#0099FF">${trafficStationObj.name }</a>
																</s:else>
																<s:if test="#trafficStationObj.descript != null">
																(${trafficStationObj.descript })
															</s:if>——
														</s:else>
														</s:iterator>
													</s:if>
													<br /> 票价：${trafficLine.price }<br> <br />发车频率：${trafficLine.departureRate
														}<br /> 首班车时间：<s:date name="#trafficLine.startTime"
															format="HH:mm" />，末班车时间：<s:date
															name="#trafficLine.endTime" format="HH:mm" /><br /> <s:if
															test="#trafficLine.remark != null">
													注：${trafficLine.remark }
												</s:if>
												</h3>
											</s:else>
										</s:iterator>
									</s:if>
								</h2>
							</div>
						</s:else>
					</s:else>
				</s:iterator>
			</s:if>
			<%@ include file="common/FAQButton.jsp"%>
		</div JTZN_L bhh>

		<%@ include file="common/rightMenu.jsp"%>
	</div w980 mt30>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
