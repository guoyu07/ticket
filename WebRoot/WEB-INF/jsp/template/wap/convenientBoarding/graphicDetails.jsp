<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<ul class="serv_ls clearfix padding-big-top">
						<ticket:common type="newsList" value="${newsClass.id }" size="10"/>
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList">
								<li class='margin-big-bottom'>
				                    <a href="/airport/${news.status.visitUrl }.ticket">
				                    	<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="5"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<em><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" ></em>
											</s:iterator>
										</s:if>
				                        <p><span class='fz30 c_white'>—— ${news.title } ——</span></p>
				                    </a>
				                </li>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="tit">${noDataMessage }</div>
						</s:else>
					</ul>
				</div>
				<div class="mobile-foot">
					<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site" class="fz36">立即购买</a>
					<%@ include file="../common/quickNav.jsp"%>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>