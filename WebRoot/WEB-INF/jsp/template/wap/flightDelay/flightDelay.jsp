<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param name="title" value="法律法规"/>
				</jsp:include>
				<div class="mobile-main">
					<ticket:common type="newsListByNewsClassAlias" value="hangbanyanwu" size="5" />
					<div class="c_content bg-sub c_white" style="display:none;">
						<h2 class='fz24 text-center padding-big-bottom'>
							延误公告
						</h2>
						<p class='fz16'>
							<s:if test="#request.newsListByNewsClassAlias != null">
								<s:iterator id="newsObj" value="#request.newsListByNewsClassAlias">
									<s:if test="#newsObj.title =='延误公告'">
										${newsObj.content }
									</s:if>
								</s:iterator>
							</s:if>
						</p>
					</div>
                    <div id="noticeList">
						<ticket:common type="systemDictionaryListByParentValue" value="航班延误法律法规"/>
						<s:if test="#request.systemDictionaryListByParentValue != null">
							<s:iterator var="sd" value="#request.systemDictionaryListByParentValue">
								<ticket:common type="newsByTitle" value="${sd.value }"/>
									<div class="tit">
										<a href="<s:if test="#request.newsByTitle != null">/airport_flightDelayLaws.action?id=${newsByTitle.id }</s:if>">${sd.value }</a>
									</div>
							</s:iterator>
						</s:if>
							
								
								<!-- <div class="tit">
									<a href="/airport/1452925641197.ticket">国内购票须知</a>
								</div>
							
								
								<div class="tit">
									<a href="/airport/1446465683917.ticket">国内值机办理须知</a>
								</div>
							
								
								<div class="tit">
									<a href="/airport/1446277772496.ticket">国内安全检查须知</a>
								</div>
							
								
								<div class="tit">
									<a href="/airport/1446030951793.ticket">国内行李运输须知</a>
								</div>
							
								
								<div class="tit">
									<a href="/airport/1446172459168.ticket">国内登机须知</a>
								</div> -->
							
						
						
					</div>
					<div class="c_content c_grey" style=" display:none;">
						<h2 class='fz24 text-center padding-big-bottom'>
							法律法规
						</h2>
						<p class='fz16'>
							<s:if test="#request.newsListByNewsClassAlias != null">
							<s:iterator id="newsObj" value="#request.newsListByNewsClassAlias">
								<s:if test="#newsObj.title =='航班延误法律法规'">
									<s:set name="newsId" value="#newsObj.id "/>
									${newsObj.introduce }
								</s:if>
							</s:iterator>
						</s:if>
						</p>
						<div class="text-center">
							<a href="/airport_flightDelayLaws.action?id=${newsId }" class='c_blue fz20'><i
								class='icon-chevron-circle-down margin-right '></i>查看详情</a>
						</div>
					</div>
                    <div style="display:none">
					<div class="tit_tab">
						<a href="/airport/1465267859654.airport" class="selected">机票改签</a>
						<a href="/airport/1465267905626.airport" class="selected">行李服务</a>
					</div>
					<div class="tit_tab">
						<a href="/airport_restaurant.action?flag=canyin" class="selected">餐饮零售</a>
						<a href="/airport_restaurant.action?flag=jiudian" class="selected">酒店住宿</a>
					</div>
					<div class="tit_tab">
						<a href="/airport_trafficGuide.action?fromQuickMenu=true" class="selected">交通查询</a>
						<a href="/airport_hotLinePhone.action?fromQuickMenu=true" class="selected">服务热线</a>
					</div>
                    </div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
	</body>
	</ticket:cache>
</html>