<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">商务中心</s:param>
				</s:include>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content">
		               <ticket:common type="systemDicObjByValue" value="businessCenter"/>
		               ${systemDicObjByValue.description }
		               <ticket:common type="newsList" value="${newsClass.id }" size="100"/>
						<s:if test="#request.newsList != null">
							<s:iterator id="businessNews" value="#request.newsList">
								<a href="/airport/${businessNews.status.visitUrl }.ticket">
									<div class="tit">
										${businessNews.title }
									</div>
								</a>
							</s:iterator>
						</s:if>
		               
		               <br>
		               <dd class='padding-bottom fz22' style="fli">
							商务中心
							<a href="<ticket:common type="positionUrl" value="shangwuzhongxin"/>" class='c_blue float-right'><em class='quick_map'></em>导航到这</a>
						</dd>
		            </div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>