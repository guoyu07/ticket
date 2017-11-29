<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
   
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="机场商业"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="mr30 line-big tit_tabs bbc">
						<ticket:common type="businessTopTypeList"/>
						<s:if test="#request.businessTopTypeList != null">
							<s:iterator id="businessTopType" value="#request.businessTopTypeList" status="st">
								<s:if test="flag=='canyin'">
									<s:if test="#businessTopType.name=='餐饮'">
										<div class="x3">
											<a href="javascript:;" class="button bg-yello d_button block text-center selected businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:if>
									<s:else>
										<div class="x3">
											<a href="javascript:;" class="button bg-sub d_button block text-center businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:else>
								</s:if>
								<s:if test="flag=='lingshou'">
									<s:if test="#businessTopType.name=='零售'">
										<div class="x3">
											<a href="javascript:;" class="button bg-yello d_button block text-center selected businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:if>
									<s:else>
										<div class="x3">
											<a href="javascript:;" class="button bg-sub d_button block text-center businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:else>
								</s:if>
								<s:if test="flag=='xiuxian'">
									<s:if test="#businessTopType.name=='休闲'">
										<div class="x3">
											<a href="javascript:;" class="button bg-yello d_button block text-center selected businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:if>
									<s:else>
										<div class="x3">
											<a href="javascript:;" class="button bg-sub d_button block text-center businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:else>
								</s:if>
								<s:if test="flag=='jiudian'">
									<s:if test="#businessTopType.name=='酒店'">
										<div class="x3">
											<a href="javascript:;" class="button bg-yello d_button block text-center selected businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:if>
									<s:else>
										<div class="x3">
											<a href="javascript:;" class="button bg-sub d_button block text-center businessTopType" typeId="${businessTopType.id }">
											<i class="icon-caret-down"></i>${businessTopType.name }</a>
										</div>
									</s:else>
								</s:if>
							</s:iterator>
						</s:if>
					</div>
					<div class="secondType">
						<div class="c_content text-center clearfix ">
							<s:if test="#request.businessTopTypeList != null">
								<s:iterator id="businessTopType" value="#request.businessTopTypeList" status="st">
									<s:if test="flag=='canyin'">
										<s:if test="#businessTopType.name=='餐饮'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<s:if test="#request.businessSecondTypeList != null">
												<s:iterator id="businessSecondType" value="#request.businessSecondTypeList" status="st">
													<s:if test="#st.first">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }" style="color:red">${businessSecondType.name }</a>
													</s:if>
													<s:elseif test="#st.last">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }</a>
													</s:elseif>
													<s:else>
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }<span class='float-right' style="display:none">|</span></a>
													</s:else>
												</s:iterator>
											</s:if>
										</s:if>
									</s:if>
									<s:if test="flag=='lingshou'">
										<s:if test="#businessTopType.name=='零售'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<s:if test="#request.businessSecondTypeList != null">
												<s:iterator id="businessSecondType" value="#request.businessSecondTypeList" status="st">
													<s:if test="#st.first">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }" style="color:red">${businessSecondType.name }</a>
													</s:if>
													<s:elseif test="#st.last">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }</a>
													</s:elseif>
													<s:else>
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }<span class='float-right' style="display:none;">|</span></a>
													</s:else>
												</s:iterator>
											</s:if>
										</s:if>
									</s:if>
									<s:if test="flag=='xiuxian'">
										<s:if test="#businessTopType.name=='休闲'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<s:if test="#request.businessSecondTypeList != null">
												<s:iterator id="businessSecondType" value="#request.businessSecondTypeList" status="st">
													<s:if test="#st.first">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }" style="color:red">${businessSecondType.name }</a>
													</s:if>
													<s:elseif test="#st.last">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }</a>
													</s:elseif>
													<s:else>
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }<span class='float-right' style="display:none;">|</span></a>
													</s:else>
												</s:iterator>
											</s:if>
										</s:if>
									</s:if>
									<s:if test="flag=='jiudian'">
										<s:if test="#businessTopType.name=='酒店'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<s:if test="#request.businessSecondTypeList != null">
												<s:iterator id="businessSecondType" value="#request.businessSecondTypeList" status="st">
													<s:if test="#st.first">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }" style="color:red">${businessSecondType.name }</a>
													</s:if>
													<s:elseif test="#st.last">
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }</a>
													</s:elseif>
													<s:else>
														<a href="javascript:;" class='x3 fz20 businessInfoListByType' typeId="${businessSecondType.id }">${businessSecondType.name }<span class='float-right' style="display:none;">|</span></a>
													</s:else>
												</s:iterator>
											</s:if>
										</s:if>
									</s:if>
								</s:iterator>
							</s:if>
						</div>
						<div id="businessList">
							<s:if test="#request.businessTopTypeList != null">
								<s:iterator id="businessTopType" value="#request.businessTopTypeList" status="st">
									<s:if test="flag=='canyin'">
										<s:if test="#businessTopType.name=='餐饮'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<div id="businessList">
											<ticket:common type="businessInfoListByType" value="${id }"/>
												<s:if test="#request.businessInfoListByType != null">
													<s:iterator id="businessInfo" value="#request.businessInfoListByType" status="st">
														<div class="c_content ">
															<div class="media media-x">
																<a class="float-left" href="/airport_businessDetail.action?id=${businessInfo.id }">
																<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
																	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" width="100" height="100">
																</a>
																<div class="media-body line businessDetail" businessId="${businessInfo.id }">
																	<div class="x12 fz24 c_black">
																		${st.count }. ${businessInfo.name }
																	</div>
																	<%-- <div class="x6 padding-top">
																		<font class="c_orange fz22">${businessInfo.score }分</font>
																	</div> --%>
																	<div class="fz22 padding-top">
																		主营:
																		<font class="c_orange fz22">${businessInfo.mainSale }</font>
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																</div>
																<br>
																<hr>
																<div class="media-foot line height-large">
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${businessInfo.latitude },${businessInfo.longitude }&title=${businessInfo.name }&content=下载App能享受室内导航服务&output=html" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${businessInfo.longitude }&latitude=${businessInfo.latitude }&name=${businessInfo.name }&floorNumber=${businessInfo.floorNumber}" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:else>
																	<a href="tel:${businessInfo.phone }" class="x6 text-center"><font class='inline_block'><i
																			class='tel_icon float-left'></i>电话</font>
																	</a>
																</div>
															</div>
														</div>
													</s:iterator>
												</s:if>
												<s:else>
													<div class="c_content">
														${noDataMessage }
													</div>
												</s:else>
											</div>
										</s:if>
									</s:if>
									<s:if test="flag=='lingshou'">
										<s:if test="#businessTopType.name=='零售'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<div id="businessList">
											<ticket:common type="businessInfoListByType" value="${id }"/>
												<s:if test="#request.businessInfoListByType != null">
													<s:iterator id="businessInfo" value="#request.businessInfoListByType" status="st">
														<div class="c_content ">
															<div class="media media-x">
																<a class="float-left" href="/airport_businessDetail.action?id=${businessInfo.id }">
																<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
																	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" width="100" height="100">
																</a>
																<div class="media-body line businessDetail" businessId="${businessInfo.id }">
																	<div class="x12 fz24 c_black">
																		${st.count }. ${businessInfo.name }
																	</div>
																	<%-- <div class="x6 padding-top">
																		<font class="c_orange fz22">${businessInfo.score }分</font>
																	</div> --%>
																	<div class="fz22 padding-top">
																		主营:
																		<font class="c_orange fz22">${businessInfo.mainSale }</font>
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																</div>
																<br>
																<hr>
																<div class="media-foot line height-large">
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${businessInfo.latitude },${businessInfo.longitude }&title=${businessInfo.name }&content=下载App能享受室内导航服务&output=html" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${businessInfo.longitude }&latitude=${businessInfo.latitude }&name=${businessInfo.name }&floorNumber=${businessInfo.floorNumber}" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:else>
																	<a href="tel:${businessInfo.phone }" class="x6 text-center"><font class='inline_block'><i
																			class='tel_icon float-left'></i>电话</font>
																	</a>
																</div>
															</div>
														</div>
													</s:iterator>
												</s:if>
												<s:else>
													<div class="c_content">
														${noDataMessage }
													</div>
												</s:else>
											</div>
										</s:if>
									</s:if>
									<s:if test="flag=='xiuxian'">
										<s:if test="#businessTopType.name=='休闲'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<div id="businessList">
											<ticket:common type="businessInfoListByType" value="${id }"/>
												<s:if test="#request.businessInfoListByType != null">
													<s:iterator id="businessInfo" value="#request.businessInfoListByType" status="st">
														<div class="c_content ">
															<div class="media media-x">
																<a class="float-left" href="/airport_businessDetail.action?id=${businessInfo.id }">
																<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
																	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" width="100" height="100">
																</a>
																<div class="media-body line businessDetail" businessId="${businessInfo.id }">
																	<div class="x12 fz24 c_black">
																		${st.count }. ${businessInfo.name }
																	</div>
																	<%-- <div class="x6 padding-top">
																		<font class="c_orange fz22">${businessInfo.score }分</font>
																	</div> --%>
																	<div class="fz22 padding-top">
																		主营:
																		<font class="c_orange fz22">${businessInfo.mainSale }</font>
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																</div>
																<br>
																<hr>
																<div class="media-foot line height-large">
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${businessInfo.latitude },${businessInfo.longitude }&title=${businessInfo.name }&content=下载App能享受室内导航服务&output=html" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${businessInfo.longitude }&latitude=${businessInfo.latitude }&name=${businessInfo.name }&floorNumber=${businessInfo.floorNumber}" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:else>
																	<a href="tel:${businessInfo.phone }" class="x6 text-center"><font class='inline_block'><i
																			class='tel_icon float-left'></i>电话</font>
																	</a>
																</div>
															</div>
														</div>
													</s:iterator>
												</s:if>
												<s:else>
													<div class="c_content">
														${noDataMessage }
													</div>
												</s:else>
											</div>
										</s:if>
									</s:if>
									<s:if test="flag=='jiudian'">
										<s:if test="#businessTopType.name=='酒店'">
											<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
											<div id="businessList">
											<ticket:common type="businessInfoListByType" value="${id }"/>
												<s:if test="#request.businessInfoListByType != null">
													<s:iterator id="businessInfo" value="#request.businessInfoListByType" status="st">
														<div class="c_content ">
															<div class="media media-x">
																<a class="float-left" href="/airport_businessDetail.action?id=${businessInfo.id }">
																<ticket:commonAnnex type="annex" entityUuid="${businessInfo.id}" annexType="1" size="1" />
																	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" width="100" height="100">
																</a>
																<div class="media-body line businessDetail" businessId="${businessInfo.id }">
																	<div class="x12 fz24 c_black">
																		${st.count }. ${businessInfo.name }
																	</div>
																	<%-- <div class="x6 padding-top">
																		<font class="c_orange fz22">${businessInfo.score }分</font>
																	</div> --%>
																	<div class="fz22 padding-top">
																		主营:
																		<font class="c_orange fz22">${businessInfo.mainSale }</font>
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																	<div class="x6 fz16 c_l_grey padding-top">
																		
																	</div>
																</div>
																<br>
																<hr>
																<div class="media-foot line height-large">
																	<s:if test="#session.fromApp == null && #parameters.fromApp == null">
																		<a href="http://api.map.baidu.com/marker?location=${businessInfo.latitude },${businessInfo.longitude }&title=${businessInfo.name }&content=下载App能享受室内导航服务&output=html" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:if>
																	<s:else>
																		<a href="/airport_daohang.action?longitude=${businessInfo.longitude }&latitude=${businessInfo.latitude }&name=${businessInfo.name }&floorNumber=${businessInfo.floorNumber}" class="x6 text-center"><font class='inline_block'><i
																			class='go_hear_icon float-left'></i>到这去</font> <span class='float-right c_l_grey'>|</span> </a>
																	</s:else>
																	<a href="tel:${businessInfo.phone }" class="x6 text-center"><font class='inline_block'><i
																			class='tel_icon float-left'></i>电话</font>
																	</a>
																</div>
															</div>
														</div>
													</s:iterator>
												</s:if>
												<s:else>
													<div class="c_content">
														${noDataMessage }
													</div>
												</s:else>
											</div>
										</s:if>
									</s:if>
								</s:iterator>
							</s:if>
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>