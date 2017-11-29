<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="/template/wap/css/base1.css"/>
<link rel="stylesheet" type="text/css" href="/template/wap/css/sy.css"/>
<script type="text/javascript" src="/template/wap/js/jquery.nicescroll.js"></script>
<script type="text/javascript" src="/template/wap/js/sy.js"></script>
<script type="text/javascript" src="/template/wap/js/restuarant.js"></script>
<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="机场商业" />
				</jsp:include>
				<div class="mobile-main">
				<s:if test="flag == 'canyin'">
					<ticket:common type="airportBusinessTypeObj" value="中餐"/>
					<input type="hidden" value="${airportBusinessTypeObj.id }" id="businessTypeId" name="${airportBusinessTypeObj.name }"/>
				</s:if>
				<s:elseif test="flag == 'lingshou'">
					<ticket:common type="airportBusinessTypeObj" value="特产"/>
					<input type="hidden" value="${airportBusinessTypeObj.id }" id="businessTypeId" name="${airportBusinessTypeObj.name }"/>
				</s:elseif>
				<s:elseif test="flag == 'xiuxian'">
					<ticket:common type="airportBusinessTypeObj" value="金融"/>
					<input type="hidden" value="${airportBusinessTypeObj.id }" id="businessTypeId" name="${airportBusinessTypeObj.name }"/>
				</s:elseif>
				<s:elseif test="flag == 'jiudian'">
					<ticket:common type="airportBusinessTypeObj" value="星级酒店"/>
					<input type="hidden" value="${airportBusinessTypeObj.id }" id="businessTypeId" name="${airportBusinessTypeObj.name }"/>
				</s:elseif>
				<div class="div_all">
					<div class="div_top">
						<div class="div_mask" id="div_mask" onclick="hide_mask();"></div>
						<ul class="ul_nav top_nav">
							<li onclick="hd(this)">活动<img class="navarrowdown" src="/template/wap/images/arrowdown.png" alt=" " /></li>
							<li onclick="fl(this)">分类<img class="navarrowdown" src="/template/wap/images/arrowdown.png" alt=" " /></li>
							<li onclick="wz(this)">位置<img class="navarrowdown" src="/template/wap/images/arrowdown.png" alt=" " /></li>
							<li onclick="px(this)">排序<img class="navarrowdown" src="/template/wap/images/arrowdown.png" alt=" " /></li>
						</ul>
						<!-- 排序 -->
						<div class="div_px" id="div_px">
							<ul id="paixu">
								<li class="order" ref="#" order="desc">按销量由高到低排序</li>
								<li class="order" ref="#" order="asc">按销量由低到高排序</li>
								<!-- <li ref="#">平均价格由低到高</li> -->
							</ul>
						</div>
						<div class="div_wz" id="div_wz">
							<div class="wz_l">
								<ul>
									<li onclick="sel_wz(1,this)" class="wz_sel">登机口</li>
									<li onclick="sel_wz(2,this)">楼层</li>
									<li onclick="sel_wz(3,this)">区域</li>

								</ul>
							</div>
							<!-- 位置 -->
							<div class="wz_r">
								<div id="ul_wz_c1" class="ul_wz_c show">
									<ul>
										<ticket:systemCommon type="systemDictionayList" value="businessInfo_djk"/>
										<s:if test="#request.systemDictionayList != null">
											<s:iterator value="#request.systemDictionayList" var="sys">
												<li ref="#" class="wz_kaifa" wz="${sys.id }">${sys.name }</li>
											</s:iterator>
										</s:if>
									</ul>
								</div>
								<div id="ul_wz_c2" class="ul_wz_c">
									<ul>
										<ticket:systemCommon type="systemDictionayList" value="businessInfo_lc"/>
										<s:if test="#request.systemDictionayList != null">
											<s:iterator value="#request.systemDictionayList" var="sys">
												<li ref="#" class="wz_kaifa" wz="${sys.id }">${sys.name }</li>
											</s:iterator>
										</s:if>
									</ul>
								</div>
								<div id="ul_wz_c3" class="ul_wz_c">
									<ul>
										<ticket:systemCommon type="systemDictionayList" value="businessInfo_wz"/>
										<s:if test="#request.systemDictionayList != null">
											<s:iterator value="#request.systemDictionayList" var="sys">
												<li ref="#" class="wz_kaifa" wz="${sys.id }">${sys.name }</li>
											</s:iterator>
										</s:if>
									</ul>
								</div>

							</div>
						</div>
						<div class="div_type" id="div_type">
							<div class="type_l">
								<ul>
									<ticket:common type="businessTopTypeList"/>
									<s:if test="#request.businessTopTypeList != null">
										<s:iterator id="businessTopType" value="#request.businessTopTypeList" status="st">
											<s:if test="#st.first">
												<li onclick="sel_fl(${st.count},this)" class="type_sel">${businessTopType.name }</li>
											</s:if>
											<s:else>
												<li onclick="sel_fl(${st.count},this)">${businessTopType.name }</li>
											</s:else>
										</s:iterator>
									</s:if>
									<!-- <li onclick="sel_fl(2,this) class="type_sel"">零售</li>
									<li onclick="sel_fl(3,this)">休闲</li>
									<li onclick="sel_fl(4,this)">酒店</li> -->
								</ul>
							</div>
							<!-- 分类 -->
							<div class="type_r">
								<s:if test="#request.businessTopTypeList != null">
									<s:iterator id="businessTopType" value="#request.businessTopTypeList" status="st">
										<ticket:common type="businessSecondTypeList" value="${businessTopType.id }"/>
										<s:if test="#st.first">
											<div id="ul_nav_c${st.count }" class="ul_nav_c show">
												<ul>
													<s:if test="#request.businessSecondTypeList != null">
														<s:iterator id="businessSecondType" value="#request.businessSecondTypeList" status="st1">
															<li ref="#" class="businessInfoListByType" typeId="${businessSecondType.id }">${businessSecondType.name }</li>
														</s:iterator>
													</s:if>
												</ul>
											</div>
										</s:if>
										<s:else>
											<div id="ul_nav_c${st.count }" class="ul_nav_c">
												<ul>
													<s:if test="#request.businessSecondTypeList != null">
														<s:iterator id="businessSecondType" value="#request.businessSecondTypeList" status="st1">
															<li ref="#" class="businessInfoListByType" typeId="${businessSecondType.id }">${businessSecondType.name }</li>
														</s:iterator>
													</s:if>
												</ul>
											</div>
										</s:else>
									</s:iterator>
								</s:if>
							</div>
						</div>
						<div class="nav_hd" id="nav_hd">
							<ul>
								<ticket:common type="canBindBusinessEventList"/>
								<s:if test="#request.canBindBusinessEventList != null">
									<s:iterator value="#request.canBindBusinessEventList" var="busiessEvent">
										<li ref="#" class="businessEvent" attrId="${busiessEvent.id }">
											<center>
												<ticket:commonAnnex type="annex" entityUuid="${busiessEvent.id }" annexType="1" size="1"/>
												<s:if test="#request.annex != null">
													<img src="${annex.annexPath }" width="159" height="66" alt=" " />
												</s:if>
											</center>
										</li>
									</s:iterator>
								</s:if>
							</ul>
						</div>
					</div>
					<div class="div_main">
						<div class="style-two"></div>
						<div class="sylist" id="sucai">
						<!-- ajax加载 -->
						</div>
						<script type="text/javascript">
							$("#sucai").niceScroll({
								cursorcolor : "#7f7d97",
								cursoropacitymax : 1,
								touchbehavior : false,
								cursorwidth : "10px",
								cursorborder : "0",
								cursorborderradius : "5px"
							});
						</script>
					</div>
				</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</ticket:cache>
</html>