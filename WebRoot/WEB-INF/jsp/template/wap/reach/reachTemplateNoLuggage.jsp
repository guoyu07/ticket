<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="quickMenuPosition" name="quickMenuPosition" value="e"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>
						到达[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>无托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu changeLuggageState2">
		                            <li href="/airport/daoda.ticket">有托运行李</li>
		                            <li href="#">无托运行李</li>
		                        </ul>
		                    </div>
		                    </span>
		                ]
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
					<input type="hidden" value="no" id="luggageState"/>
					<div class="tit b_blue " style="height:60px;line-height:60px;">
                        <a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
                    </div>
					<div class="tit_tab">
						<a href="#" class="selected" flag="guonei"><i class="icon-caret-down"></i>国内</a>
						<a href="/airport_reachInternationalNoLuggage.action" class="" flag="guoji"><i class="icon-caret-down"></i>国际</a>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic13.png" usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							<area shape="rect" coords="172,316,324,462" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guonei"
								alt="" />
							<area shape="rect" coords="172,120,324,268" href="javascript:;"
								alt="" />
						</map>
					</div>
					<div class="custom_menu mr40 RDQuickMenu">
					
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