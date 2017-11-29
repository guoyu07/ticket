<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="h"/>
		<input type="hidden" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:window.location.assign($.cookie('whereUrl'));" class='FL'><i class="icon-angle-left"></i></a>
							乘机导航
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
					<div class="tit_tab">
						<a href="#" class="selected"><i class="icon-caret-down"></i>国内出发</a>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic1.png" usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							<ticket:common type="flightCompanyByTwoCode" value="${acw }"/>
							<area shape="rect" coords="193,492,375,616" href="<ticket:common type="flightPositionUrl" value="${gat }djk"/>"
								alt="" />
							<area shape="rect" coords="193,324,375,474" href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
								alt="" />
							<%-- <s:if test="#session.fromApp == null && #parameters.fromApp == null">
								<area shape="rect" coords="309,195,432,288" href="http://api.map.baidu.com/marker?location=${flightCompanyByTwoCode.latitude },${flightCompanyByTwoCode.longitude }&title=${flightCompanyByTwoCode.name }人工值机点&content=人工值机点&output=html" alt="" />
							</s:if>
							<s:else>
								<area shape="rect" coords="309,195,432,288" href="javascript:window.location.href='/airport_daohang.action?longitude=${flightCompanyByTwoCode.longitude }&latitude=${flightCompanyByTwoCode.latitude }&name=自助值机&floorNumber=F3'" alt="" />
							</s:else> --%>
							<area shape="rect" coords="309,195,432,288" href="<ticket:common type="infopositionByFlight" value="${id }"/>" alt="" />
							<ticket:common type="departFromPort1" value="${id }"/>
							<c:choose>
							<c:when test="${!empty departFromPort1.mFlightNo}">
								<area shape="rect" coords="144,195,273,288" href="<ticket:common type="infoposionByZhiji" value="${departFromPort1.id }"/>"
								alt="" />
							</c:when>
							<c:otherwise>
								<area shape="rect" coords="144,195,273,288" href="<ticket:common type="infoposionByZhiji1" value="${departFromPort1.id }"/>"
								alt="" />
							</c:otherwise>
							</c:choose>
							<%-- <area shape="rect" coords="144,195,273,288" href="<ticket:common type="positionUrl" value="zizhuzhiji_chufa_guonei"/>"
								alt="" /> --%>
							<area shape="rect" coords="193,0,375,158" href="<ticket:common type="positionUrl" value="chufadating"/>"
								alt="" />
						</map>
					</div>
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<%-- <s:set var="memberFocusFlightObj" value="memberFocusFlightService.queryById('MemberFocusFlight',id)"></s:set>
						<s:if test="#memberFocusFlightObj.ifSet==1"> --%>
							<a href="/airportm_editFocus.action?id=${id }"  class='block c_white'>定制服务<!-- 修改乘机人信息 --></a>
						<%-- </s:if>
						<s:else>
							<a href="javascript:;"  class='block c_white customizedService' id="${id }">定制服务</a>
						</s:else> --%>
					</div>
					<div class="custom_menu mr40 SDServiceQuickMenu">

					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>