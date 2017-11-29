<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="transferFlag" name="transferFlag" value="DTD"/>
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="j"/>
		<input type="text" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 中转[
						<span class='select_tit inline_block'
							style='padding: 0px; margin: 0px; position: relative; z-index: 100;'>
							<div class="button-group fz36">
								<button type="button" class="button dropdown-toggle fz36"
									style="width: auto; margin-top: -15px;">
									<font class='height'>无托运行李</font>
									<span class="downward"></span>
								</button>
								<ul class="drop-menu changeLuggageState">
									<li href="/airport_domesticToDomesticTakeLuggage.action">
										有托运行李
									</li>
									<li>
										无托运行李
									</li>
								</ul>
							</div> </span> ]
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if>
						</a>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> 
						中转[
						<span class='select_tit inline_block'
							style='padding: 0px; margin: 0px; position: relative; z-index: 100;'>
							<div class="button-group fz36">
								<button type="button" class="button dropdown-toggle fz36"
									style="width: auto; margin-top: -15px;">
									<font class='height'>无托运行李</font>
									<span class="downward"></span>
								</button>
								<ul class="drop-menu TSchangeLuggageState">
									<li flag="no">
										<a href="/airport_transferNoLuggage.action?luggageState=yes&left=inner&right=inner">有托运行李</a>
									</li>
									<li flag="yes">
										无托运行李
									</li>
								</ul>
							</div> </span> ]
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<i class="icon-bell"></i>
							<ticket:common type="newMessages"/>
							<s:if test="#request.newMessages > 0">
								<span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
							</s:if>
						</a>
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="tit_tab mrlr40">
						<div class="line">
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
										<span class="downward"></span>
									</button>
								</div>
							</div>
							<div class="x2">
								<em class='icon-exchange'></em>
							</div>
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
										<span class="downward"></span>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic17.png" usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							<area shape="rect" coords="-6,432,150,565" href="<ticket:common type="positionUrl" value="dengji_zhongzhuanw_gn_gn"/>"
								alt="" />
							<area shape="rect" coords="-13,289,143,424" href="<ticket:common type="positionUrl" value="chayandengjipai_zhongzhuan_gn_gn"/>"
								alt="" />
							<area shape="rect" coords="-1,144,155,278" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gn"/>"
								alt="" />
							<area shape="rect" coords="2,2,158,134" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>"
								alt="" />
						</map>
					</div>
					<!-- <div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="javascript:;"  class='block c_white'>定制服务</a>
					</div> -->
					<div class="custom_menu mr40 DTDServiceQuickMenu">
						
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