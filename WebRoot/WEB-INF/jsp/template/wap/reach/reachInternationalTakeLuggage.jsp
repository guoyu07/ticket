<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
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
								<ul class="drop-menu">
									<li>
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
						</a> 中转
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
					<div class="tit b_blue " style="height:60px;line-height:60px;">
                        <a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
                    </div>
					<div class="tit_tab mrlr40">
						<div class="line">
							<div class="x5">
								<div class="button-group">
									<button type="button" class="button dropdown-toggle">
										<font>国内</font>
										<span class="downward"></span>
									</button>
									<ul class="drop-menu">
										<li>
											国内
										</li>
										<li>
											国际
										</li>
									</ul>
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
									<ul class="drop-menu">
										<li>
											国内
										</li>
										<li>
											国际
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic14.png" usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							<area shape="rect" coords="202,245,330,356" href="javascript:;"
								alt="" />
							<area shape="rect" coords="203,363,331,474" href="javascript:;"
								alt="" />
							<area shape="rect" coords="201,486,329,597" href="javascript:;"
								alt="" />
							<area shape="rect" coords="2,484,130,595" href="javascript:;"
								alt="" />
							<area shape="rect" coords="0,357,128,468" href="javascript:;"
								alt="" />
							<area shape="rect" coords="198,126,326,237" href="javascript:;"
								alt="" />
							<area shape="rect" coords="-3,117,125,228" href="javascript:;"
								alt="" />
							<area shape="rect" coords="-4,0,124,111" href="javascript:;"
								alt="" />
						</map>
					</div>
					<div class="custom_menu mr40">
						<div class="line text-center">
							<ticket:common type="visiorMenuList" value="h"/>
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
							<ticket:common type="flightQuickMenuList" value="${memberSelfMenuId }" size="8"/>
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
									<a href="#" class='custom_menu_btn serviceQuickMenuBtn' flightQuickMenu="${flightNumber }${flightDate }" menuCount="${defaultCount }" defaultPosition="h"></a>
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