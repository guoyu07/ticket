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
						<a href="javascript:;" class='FL'><i class="icon-navicon"></i></a>
						到达[
		                    <span class='select_tit inline_block' style='padding:0px;margin:0px;position:relative;z-index:100;'>
		                    <div class="button-group fz36">
		                        <button type="button" class="button dropdown-toggle fz36" style="width:auto;margin-top:-15px;">
		                            <font class='height'>无托运行李</font> <span class="downward"></span>
		                        </button>
		                        <ul class="drop-menu changeLuggageState2">
		                            <li href="/airport_reachInternational.action">有托运行李</li>
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
					<div class="tit b_blue " style="height:60px;line-height:60px;">
                        <a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
                    </div>
					<div class="tit_tab">
						<a href="#" class="selected"><i class="icon-caret-down"></i>国际</a>
					</div>
					<div class="c_img text-center mr40">
						<img src="/template/wap/images/area/pic15.png" usemap="#m_pic" alt="" />
						<map name="m_pic" id="m_pic">
							<area shape="rect" coords="297,511,403,624" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,511,223,624" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,387,223,497" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,262,223,376" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,135,223,252" href="javascript:;"
				alt="" />
			<area shape="rect" coords="91,19,206,125" href="javascript:;"
				alt="" />
						</map>
					</div>
					<!-- <div class="tit b_blue " style="height:60px;line-height:60px;">
	                    <a href="javascript:;"  class='block c_white'>定制服务</a>
	                </div> -->
					<div class="custom_menu mr40">
						<div class="line text-center">
							<ticket:common type="visiorMenuList" value="m"/>
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
							<ticket:common type="flightQuickMenuList" value="${memberSelfMenuId }&m" size="8"/>
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
									<a href="#" class='custom_menu_btn serviceQuickMenuBtn' flightQuickMenu="${flightNumber }${flightDate }" menuCount="${defaultCount }" defaultPosition="m"></a>
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