<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="h"/>
		<input type="hidden" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:;" class='FL'><i class="icon-angle-left"></i></a>
						国内出发[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>有托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu SDChangeLuggageState">
		                            <li flag="yes">有托运行李</li>
		                            <li flag="no">无托运行李</li>
		                        </ul>
		                    </div>
		                    </span>]
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
						<a href="#" class="selected"><i class="icon-caret-down"></i>国内</a>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic1.png"  usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							<area shape="rect" coords="193,492,375,616" href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
								alt="" />
							<area shape="rect" coords="193,324,375,474" href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
								alt="" />
							<%-- <area shape="rect" coords="309,195,432,288" href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guonei"/>"
								alt="" /> --%>
							<area shape="rect" coords="309,195,432,288" href="<ticket:common type="infopositionByFlight" value="${id }"/>"
								alt="" />	
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
						<a href="javascript:;"  class='block c_white customizedService' id="${id }" flightNumber="${flightNumber }" flightDate="${flightDate }">定制服务</a>
					</div>
					<div class="custom_menu mr40 SDServiceQuickMenu">
						
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>