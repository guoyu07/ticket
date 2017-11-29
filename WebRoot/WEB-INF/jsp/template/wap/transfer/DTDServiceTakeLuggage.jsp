<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="transferFlag" name="transferFlag" value="DTD"/>
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="j"/>
		<input type="hidden" name="memberSelfMenuId" id="memberSelfMenuId" value="${memberSelfMenuId }"/>
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="javascript:window.location.assign($.cookie('whereUrl'))" class="FL"><i class="icon-angle-left"></i>
						</a> 中转[
						<span class='select_tit inline_block'
							style='padding: 0px; margin: 0px; position: relative; z-index: 100;'>
							<div class="button-group fz36">
								<button type="button" class="button dropdown-toggle fz36"
									style="width: auto; margin-top: -15px;">
									<font class='height'>有托运行李</font>
									<span class="downward"></span>
								</button>
								<ul class="drop-menu TSchangeLuggageState">
									<li flag="yes">
										有托运行李
									</li>
									<li flag="no">
										无托运行李
									</li>
								</ul>
							</div> </span> ]
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if>
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
				</s:else>
				<div class="mobile-main">
					<div class="tit_tab mrlr40">
						<div class="line">
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
									</button>
									<%--<ul class="drop-menu">
										<li>
											国内
										</li>
										<li>
											国际
										</li>
									</ul>
								--%></div>
							</div>
							<div class="x2">
								<em class='icon-exchange'></em>
							</div>
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
									</button>
									<%--<ul class="drop-menu">
										<li>
											国内
										</li>
										<li>
											国内
										</li>
									</ul>
								--%></div>
							</div>
						</div>
					</div>
					<div id="TSContent">
						<div class="c_img text-center mr40">
							<s:if test='flag=="yes"'>
								<img src="/template/wap/images/area/pic3.png" usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									  <area shape="rect" coords="-8,484,136,606" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gn"/>" alt="" />
						              <area shape="rect" coords="-6,363,138,476" href="<ticket:common type="positionUrl" value="anjian_zhongzhuan_gn_gn"/>" alt="" />
						              <area shape="rect" coords="-13,243,131,353" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gn"/>" alt="" />
						              <area shape="rect" coords="0,122,144,239" href="<ticket:common type="positionUrl" value="quxingli_zhongzhuan_gn_gn"/>" alt="" />
						              <area shape="rect" coords="-4,0,140,119" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>" alt="" />
								</map>
							</s:if>
							<s:else>
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
							</s:else>
						</div>
					</div>
					<!-- <div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="javascript:;"  class='block c_white'>定制服务</a>
					</div> -->
					<div class="custom_menu mr40 TTDServiceQuickMenu">
						
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