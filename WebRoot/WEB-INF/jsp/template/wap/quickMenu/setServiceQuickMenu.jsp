<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="设置快捷菜单" name="title"/>
					<jsp:param value="javascript:window.location.href=$.cookie('preUrl');" name="url"/>
				</jsp:include>
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
										<ticket:common type="serviceMenuList" value="${quickMenu.id }&${defaultPosition }" />
										<s:if test="#request.serviceMenuList != null">
											<s:iterator id="menu" value="#request.serviceMenuList">
												<div class="x3">
													<a href="#"> 
													<ticket:commonAnnex type="annex" entityUuid="${menu.id}" annexType="1" size="1" />
													<ticket:common type="memberHasSetServiceList" value="${defaultPosition }&${flightQuickMenu }"/>	
														<s:if test="#request.memberHasSetServiceList != null">
															<s:set var="flag" value="false"></s:set>
															<s:iterator id="memberMenu" value="#request.memberHasSetServiceList">
																<s:if test="#memberMenu.quickMenu_id == #menu.id">
																	<s:set var="flag" value="true"></s:set>
																	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="cancelSetQuickMenu" memberMenuId="${memberMenu.id }" menuCount="${menuCount }" quickMenu_id="${menu.id }"/>
																</s:if>
															</s:iterator>
															<s:if test="#flag == false">
																<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="gray setQuickMenu" quickMenu_id="${menu.id }" menuCount="${menuCount }"/>
															</s:if>
														</s:if>
														<s:else>
															<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class="gray setQuickMenu" quickMenu_id="${menu.id }" menuCount="${menuCount }"/>
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
