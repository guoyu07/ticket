<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script type="text/javascript" src="/template/wap/js/reloadServiceQuickMenu.js"></script>
	<body class="mobile">
		<input type="hidden" id="transferFlag" name="transferFlag" value="DTI"/>
		<input type="hidden" name="quickMenuPosition" id="quickMenuPosition" value="k"/>
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
								<ul class="drop-menu">
									<li flag="yes">
										有托运行李
									</li>
									<li flag="no">
										<a href="/airport_transferNoLuggage.action?luggageState=no&left=inner&right=outer">无托运行李</a>
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
										<font>国际</font>
										<span class="downward"></span>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div id="TSContent">
						<div class="c_img text-center mr40">
							<s:if test='flag=="yes"'>
							<img src="/template/wap/images/area/pic18.png" usemap="#m_pic" alt="" />
							<map name="m_pic" id="m_pic">
								<area shape="rect" coords="197,492,326,607" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="197,366,326,484" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="197,247,326,355" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="1,486,126,604" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="0,368,125,480" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="3,244,128,362" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="-2,120,131,242" href="<ticket:common type="positionUrl" value="tiquxingli_zhongzhuan_gn_gj"/>" alt="" />
               <area shape="rect" coords="-1,0,132,120" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gj"/>" alt="" />
							</map>
							</s:if>
							<s:else>
								<img src="/template/wap/images/area/pic8.png" usemap="#m_pic" alt="" />
								<map name="m_pic" id="m_pic">
									<area shape="rect" coords="294,254,422,365" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="294,378,422,489" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="294,500,422,611" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="91,500,219,611" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="91,376,219,487" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="91,253,219,364" href="<ticket:common type="positionUrl" value="dengjipaijiancha_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="91,130,219,241" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
						            <area shape="rect" coords="91,0,220,125" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
								</map>
							</s:else>
						</div>
					</div>
					<!-- <div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="javascript:;"  class='block c_white'>定制服务</a>
					</div> -->
					<div class="custom_menu mr40">
						<div class="line text-center">
							<ticket:common type="visiorMenuList" value="k"/>
							<%
								int count = 0;
								int defaultCount = 0;
							%>
							<s:if test="#request.visiorMenuList != null">
								<s:iterator id="visitorMenu" value="#request.visiorMenuList">
									<div class="x3">
										<ticket:commonAnnex type="annex" entityUuid="${visitorMenu.id }" annexType="1" size="1"/>
										<a href="${visitorMenu.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
									</div>
									<%
										count += 1;
										defaultCount += 1;
									%>
								</s:iterator>
							</s:if>
							<ticket:common type="flightQuickMenuList" value="${memberSelfMenuId }&k" size="8"/>
							<s:if test="#request.flightQuickMenuList != null">
								<s:iterator id="quickMenu" value="#request.flightQuickMenuList">
									<s:if test="#request.visiorMenuList != null">
										<s:set var="flag" value="true" />
										<s:iterator id="visitorMenu" value="#request.visiorMenuList">
											<s:if test="#visitorMenu.id == #quickMenu.quickMenu_id">
												<s:set var="flag" value="false"></s:set>
											</s:if>
										</s:iterator>
										<s:if test="flag">
											<%
												count += 1;
											%>
											<div class="x3">
												<ticket:common type="quickMenuObj" value="${quickMenu.quickMenu_id }"/>
												<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
												
												<a href="${quickMenuObj.url }"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
											</div>
										</s:if>
									</s:if>
								</s:iterator>
							</s:if>
							<%
								request.setAttribute("count", count);
								request.setAttribute("defaultCount", defaultCount);
								
							%>
							<c:forEach begin="${count }" end="7" step="1">
								<div class="x3">
									<a href="#" class='custom_menu_btn serviceQuickMenuBtn' flightQuickMenu="${flightNumber }${flightDate }" menuCount="${defaultCount }" defaultPosition="k"></a>
								</div>
							</c:forEach>
						</div>
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