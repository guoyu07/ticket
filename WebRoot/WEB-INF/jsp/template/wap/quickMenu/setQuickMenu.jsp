<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<ticket:common type="leftCookie"/>
						<!-- <a href='javascript:window.location.href=$.cookie("leftPreUrl");' class="FL1APP" id="comeback"><i -->
						<a href="javascript:location.href=$.cookie('leftPreUrl');" class="FL" id="comeback">
							<i class="icon-angle-left"></i> </a>
						设置快捷菜单
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
					<div class="custom_menu mr40">
						<input type="hidden" value="${defaultPosition }" id="defaultShowPosition" name="defaultShowPosition"/>
						<input type="hidden" value="${flightQuickMenu }" id="flightQuickMenu" name="flightQuickMenu"/>
						<ticket:common type="quickMenuList" />
						<s:if test="#request.quickMenuList != null">
							<s:iterator id="quickMenu" value="#request.quickMenuList">
								<h1>
									${quickMenu.name }
								</h1>
									<div class="line text-center">
										<ticket:common type="menuList" value="${quickMenu.id }" />
										<s:if test="#request.menuList != null">
											<s:iterator id="menu" value="#request.menuList">
												<div class="x3">
													<a href="#"> 
													<ticket:commonAnnex type="annex" entityUuid="${menu.id}" annexType="1" size="1" />
													<ticket:common type="memberHasSetList" value="${defaultPosition }"/>	
														<s:if test="#request.memberHasSetList != null">
															<s:set var="flag" value="false"></s:set>
															<s:iterator id="memberMenu" value="#request.memberHasSetList">
																<s:if test="#memberMenu.quickMenu_id == #menu.id">
																	<s:set var="flag" value="true"></s:set>
																	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="cancelSetQuickMenu" memberMenuId="${memberMenu.id }" menuCount="0" quickMenu_id="${menu.id }"/>
																</s:if>
															</s:iterator>
															<s:if test="#flag == false">
																<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="gray setQuickMenu" menuCount="0" quickMenu_id="${menu.id }"/>
															</s:if>
														</s:if>
														<s:else>
															<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="gray setQuickMenu" menuCount="0" quickMenu_id="${menu.id }"/>
														</s:else>
													</a>
												</div>
											</s:iterator>
										</s:if>
									</div>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
			<div class="dialog" style="display: none;">
				<%@ include file="../common/left.jsp"%>
			</div>
		</div>
	</body>
</html>
