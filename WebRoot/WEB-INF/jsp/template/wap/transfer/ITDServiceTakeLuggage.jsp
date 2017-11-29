<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="transferFlag" name="transferFlag" value="ITD"/>
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
									<font class='height'>无托运行李</font>
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
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
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
										<a href="/airport_transferNoLuggage.action?luggageState=no&left=outer&right=inner">无托运行李</a>
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
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>定制服务</a>
					</div>
					<div id="TSContent">
						<div class="c_img text-center mr40">
						<s:if test='flag=="yes"'>
							<img src="/template/wap/images/area/pic9.png" usemap="#m_pic" alt="" />
							<map name="m_pic" id="m_pic">
								<area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="-1,121,127,232" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="0,246,128,357" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="-1,367,127,478" href="<ticket:common type="positionUrl" value="xinglitiqu_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="-1,493,127,604" href="<ticket:common type="positionUrl" value="jianyanjianyixingli_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="201,492,329,603" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="201,250,329,361" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="203,373,331,484" href="<ticket:common type="positionUrl" value="zhongzhuanbanli_zhongzhuan_gj_gn"/>" alt="" />
				              <area shape="rect" coords="198,120,330,240" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gj_gn"/>" />
							</map>
							</s:if>
							<s:else>
								<img src="/template/wap/images/area/pic10.png" usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									<area shape="rect" coords="205,494,333,605" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="1,494,129,605" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>" alt="" />
						            <area shape="rect" coords="1,370,129,481" href="<ticket:common type="positionUrl" value="denghouxinglichayan_zhongzhuan_gj_gn"/>" alt="" />
						            <area shape="rect" coords="0,249,128,360" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
						            <area shape="rect" coords="-1,122,127,233" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
						            <area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
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
				<!-- <div class="tit b_blue " style="height:60px;line-height:60px;">
					<a href="javascript:;"  class='block c_white'>定制服务</a>
				</div> -->
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>