<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="m"/>
		<input type="hidden" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:window.location.assign($.cookie('whereUrl'))" class='FL'><i class="icon-angle-left"></i></a>
						国际到达[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>有托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu RIChangeLuggageState">
		                            <li flag="yes">有托运行李</li>
		                            <li flag="no">无托运行李</li>
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
				<div class="mobile-main">
					<input type="hidden" value="no" id="luggageState"/>
					<div class="tit_tab">
						<a href="#" class="selected"><i class="icon-caret-down"></i>国际</a>
					</div>
					<div id="RIContent">
						<s:if test='flag=="no"'>
							<div class="c_img text-center mr40">
								<img src="/template/wap/images/area/pic15.png" usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									<area shape="rect" coords="297,511,403,624" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guoji" alt="" />
									<area shape="rect" coords="91,511,223,624" href="<ticket:common type="positionUrl" value="haiguanjiancha_daoda_guoji"/>" />
									<area shape="rect" coords="91,387,223,497" href="<ticket:common type="positionUrl" value="bianfangjiancha_daoda_guoji"/>" />
									<area shape="rect" coords="91,262,223,376" href="<ticket:common type="positionUrl" value="kouanqianzheng_daoda_guoji"/>" />
									<area shape="rect" coords="91,135,223,252" href="<ticket:common type="positionUrl" value="jianyanjianyiren_daoda_guoji"/>" />
									<area shape="rect" coords="91,19,206,125" href="javascript:;" alt="" />
								</map>
							</div>
						</s:if>
						<s:else>
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
			            </s:else>
					</div>
					<div class="custom_menu mr40 RIServiceQuickMenu">
						
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