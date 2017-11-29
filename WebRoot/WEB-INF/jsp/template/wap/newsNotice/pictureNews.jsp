<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">服务热线</s:param>
				</s:include>
				<div class="mobile-main">
				<input type="hidden" id="newsClass_id" value="${newsClass.id }"/>
					<div class="notice_ls">
						<h3>
							${newsClass.name }
						</h3>
						<ticket:common type="newsList" value="${newsClass.id }" size="4" />
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList" status="st">
								<div id="loadNews">
									<dl class="clearfix">
										<dt>
											<a href="/airport/${news.status.visitUrl }.ticket">
												<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1"/>
												<s:if test="#request.queryAnnexList != null">
													<s:iterator id="annex" value="#request.queryAnnexList" status="st">
														<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="78" width="121">
													</s:iterator>
												</s:if>
											</a>
										</dt>
										<a href="/airport/${news.status.visitUrl }.ticket">
											<dd>
												<h6 class='fz18'>
													${news.title }
												</h6>
												<div  class='desc fz16'>
													摘要：<ticket:format value="${news.introduce }" size="50"/>
												</div>
											</dd>
										</a>
									</dl>
								</div>
							</s:iterator>
						</s:if>
						<s:else>
							<div class="tit">
								${noDataMessage }
							</div>
						</s:else>
						<h3 class="notice_ft" startSize="4" getCount="4">
							<i class="fa fa-download loadMore"></i>加载更多
						</h3>
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