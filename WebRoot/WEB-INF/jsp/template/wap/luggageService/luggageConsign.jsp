<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="select_tit">
						<ticket:common type="newsList" value="${newsClass.id }" size="100" />
						<div class="button-group no-background">
							<button type="button" class="button  bg dropdown-toggle fz22"
								style="border: 1px solid #ddd">
								<s:if test="#request.newsList != null">
									<s:iterator id="news" value="#request.newsList" status="st">
										<s:if test="#st.first">
											<font>${news.title }</font>
										</s:if>
									</s:iterator>
								</s:if>
								<s:else>
									<font>请选择你乘坐的航空公司</font>
								</s:else>
								<span class="downward"></span>
							</button>
							<ul class="drop-menu luggageConsign">
								<s:if test="#request.newsList != null">
									<s:iterator id="news" value="#request.newsList" status="st">
										<li news_id="${news.id }">
											${news.title }
										</li>
									</s:iterator>
								</s:if>
							</ul>
						</div>
					</div>
					<div class="c_content">
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList" status="st">
								<s:if test="#st.first">${news.content}</s:if>
							</s:iterator>
						</s:if>
					</div>
					<div class="c_text mr40 fz20 c_l_grey">
						<ticket:common type="kindlyReminder" value="行李托运温馨提示"/>
						${kindlyReminder.content }
					</div>
					 <div class="tit_tab">
		                <a href="#" class='selected'>国内超规办理柜台</a>
		                <a href="#">国际超规办理柜台</a>
		            </div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>