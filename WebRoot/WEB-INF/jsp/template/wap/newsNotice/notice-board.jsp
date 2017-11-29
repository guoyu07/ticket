<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp"></s:include>
				<div class="mobile-main">
					<input type="hidden" id="newsClass_id" value="${newsClass.id }" />
					<div class="notice_ls">
						<h3>
							${newsClass.name }
						</h3>
						<ul>
							<ticket:common type="newsList" value="${newsClass.id }" size ="4"/>
							<s:if test="#request.newsList != null">
								<s:iterator id="news" value="#request.newsList" status="st">
									<div id="loadNews">
										<li>
											[${newsClass.name }]
											<a href="/airport/${news.status.visitUrl }.ticket">${news.title}</a>
										</li>
									</div>
								</s:iterator>
							</s:if>
							<s:else>
								<div class="tit">
									${noDataMessage }
								</div>
							</s:else>
						</ul>
						<h3 class="notice_ft" startSize="4" getCount="4">
							<i class="fa fa-download"></i>加载更多
						</h3>
					</div>
				</div>
                <%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>