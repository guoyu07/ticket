<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="tit_tab">
						<ticket:common type="newsClassChilds" value="${newsClass.alias }"/>
						<s:if test="#request.newsClassChilds != null">
							<s:iterator id="nc" value="#request.newsClassChilds" status="st">
								<s:if test="#st.first">
									<a class="selectInOrOut selected" id="${nc.id }"><i class="icon-caret-down"></i>${nc.name }</a>
								</s:if>
								<s:else>
									<a class="selectInOrOut" id="${nc.id }"><i class="icon-caret-down"></i>${nc.name }</a>
								</s:else>
							</s:iterator>
						</s:if>
						<s:else>
							${noDataMessage }
						</s:else>
					</div>
					<div id="noticeList">
						<ticket:common type="newsClassChilds" value="${newsClass.alias }"/>
						<s:if test="#request.newsClassChilds != null">
							<s:iterator id="nc" value="#request.newsClassChilds">
								<div class="tit" style="cursor:pointer;" onClick="location.href='/airport/${nc.alias }.ticket'">
									${nc.name }
								</div>
							</s:iterator>
						</s:if>
						<s:else>
							${noDataMessage }
						</s:else>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
	</ticket:cache>
</html>