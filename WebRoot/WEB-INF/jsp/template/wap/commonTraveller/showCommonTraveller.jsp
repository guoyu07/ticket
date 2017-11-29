<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <%@ include file="../common/head.jsp"%>
  <script type="text/javascript" src="/template/wap/js/showCommonTraveller.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="常用旅客" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="guestlist">
						<ticket:common type="travellerList"/>
						<s:if test="#request.travellerList != null">
							<s:iterator value="#request.travellerList" var="list1" status="st">
								<dl>
								<s:if test="#list1.chaName != null">
									<dt>
										${list1.chaName}<i class="icon-angle-right"></i>
										<input type="hidden" value="${list1.id }"/>
										<a href="commonTraveller_editCommon.action" id="editCommon" style="display: none;"></a>
									</dt>
									<br/>
								</s:if>
								<s:if test="#list1.engName != null">
									<dt>
										${list1.engName}<i class="icon-angle-right"></i>
										<input type="hidden" value="${list1.id }"/>
										<a href="commonTraveller_editCommon.action" id="editCommon" style="display: none;"></a>
									</dt>
								</s:if>
								<s:if test="#list1.phone != null">
									<dd>
										<span>手机号</span>+86 ${list1.phone}
									</dd>	
								</s:if>
								<ticket:common type="travellerCardList" value="${list1.id }"/>
								<s:if test="#request.travellerCardList != null && #request.travellerCardList.size() > 0">
								<s:iterator value="#request.travellerCardList" id="list2">
									 <s:if test="#list2.parent.id == #list1.id">
										<dd>
											<span>${list2.cardType}</span>${list2.cardValue}
											<input type="hidden" value="${list2.cardValue}" name="card"/>
										</dd>
									</s:if>
								</s:iterator>
								</s:if>
								<s:if test="#list1.gender != null && #list1.gender != ''">
									<dd>
										<span>性别</span>${list1.gender}
									</dd>	
								</s:if>
								<s:if test="#list1.birth != null && #list1.birth != ''">
									<dd>
										<span>生日</span>${list1.birth}
									</dd>	
								</s:if>
								</dl>
							</s:iterator>
						</s:if>
					</div>
					<div id="addcomm" class="tit custom_menu_btn "  style="padding-top:0px;margin-bottom:40px;">添加</div>
						<a href="commonTraveller_add.action" id="manageLink" style="display: none;"></a>
				</div>
				<div class="c_ft clearfix">
					<a href="#" class="ft_more"></a>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>
