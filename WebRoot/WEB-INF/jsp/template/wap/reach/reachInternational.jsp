<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="quickMenuPosition" name="quickMenuPosition" value="f"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>
						到达[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>有托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu changeLuggageState2">
		                            <li href="#">有托运行李</li>
		                            <li href="/airport_reachInternationalNoLuggage.action">无托运行李</li>
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
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>
						到达
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
						</a>
					</div>
					<div class="header_search">
						<div class="searchForm mr40">
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
				</s:else>
				<div class="mobile-main">
					<input type="hidden" value="no" id="luggageState"/>
                    <div class="tit b_blue " style="height:60px;line-height:60px;">
                        <a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
                    </div>
					<div class="tit_tab">
						<a href="/airport/daoda.ticket" class="" flag="guonei"><i class="icon-caret-down"></i>国内</a>
						<a href="#" class="selected" flag="guoji"><i class="icon-caret-down"></i>国际</a>
					</div>
					<div class="c_img text-center mr40">
				          <img src="/template/wap/images/area/pic14.png"  usemap="#m_pic" alt="" />
				          <map name="m_pic" id="m_pic">
				                <area shape="rect" coords="202,245,330,356" href="<ticket:common type="positionUrl" value="haiguanjiancha_daoda_guoji"/>" alt="" />
				                <area shape="rect" coords="203,363,331,474" href="<ticket:common type="positionUrl" value="jianyanjianyixingli_daoda_guoji"/>" alt="" />
				                <area shape="rect" coords="201,486,329,597" href="<ticket:common type="positionUrl" value="xinglitiqu_daoda_guoji"/>" alt="" />
				                <area shape="rect" coords="2,484,130,595" href="<ticket:common type="positionUrl" value="bianfangjiancha_daoda_guoji"/>" alt="" />
				                <area shape="rect" coords="0,357,128,468" href="<ticket:common type="positionUrl" value="kouanqianzheng_daoda_guoji"/>" alt="" />
				                <area shape="rect" coords="198,126,326,237" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guoji" alt="" />
				                <area shape="rect" coords="-3,117,125,228" href="<ticket:common type="positionUrl" value="jianyanjianyiren_daoda_guoji"/>" alt="" />
				                <area shape="rect" coords="-4,0,124,111" href="<ticket:common type="positionUrl" value="daodajichang_daoda_guoji"/>" alt="" />
				          </map>
				      </div>
					<div class="custom_menu mr40 RIQuickMenu">

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