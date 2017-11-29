<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<a href="javascript:;" class='FL'><i class="icon-navicon"></i>
						</a> 中转[
						<span class='select_tit inline_block'
							style='padding: 0px; margin: 0px; position: relative; z-index: 100;'>
							<div class="button-group fz36">
								<button type="button" class="button dropdown-toggle fz36"
									style="width: auto; margin-top: -15px;">
									<font class='height'>有托运行李</font>
									<span class="downward"></span>
								</button>
								<ul class="drop-menu changeLuggageState">
									<li href="#">
										有托运行李
									</li>
									<li href="/airport_transferNoLuggage.action?luggageState=no">
										无托运行李
									</li>
								</ul>
							</div> </span> ]
							<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
								<c:choose>
									<c:when test="${!empty sessionMember }">
										<ticket:common type="newMessages"/>
										<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
									</c:when>
									<c:otherwise>
										<ticket:common type="myAnnounecementCount"/>
										<i class="icon-bell"></i><c:if test="${myAnnounecementCount == 1 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
									</c:otherwise>
								</c:choose>
								<%-- <ticket:common type="newMessages"/>
								<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if> --%>
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
					<input type="hidden" value="yes" id="luggageState" />
					<div class="tit b_blue " style="height:60px;line-height:60px;">
						<a href="/airport/hangbanchaxun.ticket?flag=true"  class='block c_white'>查询航班获取旅程助手</a>
					</div>
					<s:if
						test="#session.fromApp == null && #parameters.fromApp == null">
						<div class="tit_tab mrlr40">
							<div class="line">
								<div class="x5">
									<div class="button-group">
										<button type="button" class="button dropdown-toggle">
											<font id="leftValue"> <s:if
													test="left == null || left == 'inner'">
													国内
												</s:if> <s:else>
													国际
												</s:else> </font>
											<span class="downward"></span>
										</button>
										<ul class="drop-menu leftDropMenuClass">
											<li>
												国内
											</li>
											<li>
												国际
											</li>
										</ul>
									</div>
								</div>
								<s:if test="left == 'outer' || left == 'outer'">
								
								</s:if>
								<div class="x2 changeLR">
									<em class='icon-exchange'>&nbsp;&nbsp;<font id="middleVal"></font></em>
								</div>
								<div class="x5">
									<div class="button-group">
										<button type="button" class="button dropdown-toggle">
											<font id="rightValue"> <s:if test="right == null || right == 'inner'">
													国内
												</s:if> <s:else>
													国际
												</s:else> </font>
											<span class="downward"></span>
										</button>
										<ul class="drop-menu rightDropMenuClass">
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
						<div id="positionNavigate" class="positionNavigate">

						</div>
					</s:if>
					<s:else>
						<div class="tit_tab mrlr40">
							<div class="line">
								<div class="x5">
									<div class="button-group">
										<button type="button" class="button dropdown-toggle">
											<font id="appLeftValue"> <s:if
													test="left == null || left == 'inner'">
													国内
												</s:if> <s:else>
													国际
												</s:else> </font>
											<span class="downward"></span>
										</button>
										<ul class="drop-menu appLeftDropMenuClass">
											<li>
												国内
											</li>
											<li>
												国际
											</li>
										</ul>
									</div>
								</div>
								<div class="x2 appChangeLR">
									<em class='icon-exchange'></em>
								</div>
								<div class="x5">
									<div class="button-group">
										<button type="button" class="button dropdown-toggle">
											<font id="appRightValue"> <s:if
													test="right == null || right == 'inner'">
													国内
												</s:if> <s:else>
													国际
												</s:else> </font>
											<span class="downward"></span>
										</button>
										<ul class="drop-menu appRightDropMenuClass">
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
						<div id="positionNavigateApp" class="positionNavigateApp">

						</div>
					</s:else>
				</div>
			</div>
			<%@ include file="../common/quickNav.jsp"%>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>