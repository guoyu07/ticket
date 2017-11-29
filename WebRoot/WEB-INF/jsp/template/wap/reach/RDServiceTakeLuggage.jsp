<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="l"/>
		<input type="hidden" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="javascript:window.location.assign($.cookie('whereUrl'))" class="FL"><i class="icon-angle-left"></i> </a> 
						国内到达[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>有托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu RDChangeLuggageState">
		                            <li flag="yes">有托运行李</li>
		                            <li flag="no">无托运行李</li>
		                        </ul>
		                    </div>
		                    </span>
		                ]
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><s:if test="#request.newMessages > 0"><span class="badge bg-red">&nbsp;</span></s:if>
						</a>
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
				</div>
			</s:if>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="#" class="selected"><i class="icon-caret-down"></i>国内</a>
					</div>
					<div id="RDContent">
						<s:if test='flag=="no"'>
							<div class="c_img text-center mr40">
								<img src="/template/wap/images/area/pic13.png" usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									<area shape="rect" coords="172,316,324,462" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guonei"
										alt="" />
									<area shape="rect" coords="172,120,324,268" href="javascript:;"
										alt="" />
								</map>
							</div>
						</s:if>
						<s:else>
							<div class="c_img text-center mr40">
								<img src="/template/wap/images/area/pic12.png"  usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									<area shape="rect" coords="176,432,324,576" href="/airport_trafficGuide.action?flag=jichangjiaotong_daoda_guonei" alt="" />
			                    	<area shape="rect" coords="176,231,347,385" href="<ticket:common type="positionUrl" value="xinglizhuanpan_daoda_guonei"/>" alt="" />
			                    	<area shape="rect" coords="176,45,320,193" href="<ticket:common type="positionUrl" value="daodajichang_daoda_guonei"/>" alt="" />
								</map>
							</div>
						</s:else>
					</div>
					<!-- <div class="tit b_blue " style="height:60px;line-height:60px;">
	                    <a href="javascript:;"  class='block c_white'>定制服务</a>
	                </div> -->
					<div class="custom_menu mr40 RDServiceQuickMenu">
						
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