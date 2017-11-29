<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<ticket:common type="newsListByNewsClassAlias" value="gonggaolan" />
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="notice_kv">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="news" value="#request.newsListByNewsClassAlias">
										<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${news.id}" annexType="1" size="1" />
										<s:if test="#request.queryAnnexList != null">
											<ol>
												<s:iterator id="annex" value="#request.queryAnnexList"
													status="st">
													<li class="swiper-slide">
														<a href="/airport/${news.status.visitUrl }.ticket"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="376" width="640">
														</a>
													</li>
												</s:iterator>
											</ol>
										</s:if>
									</s:iterator>
								</s:if>
								<s:else>
									<img src="/template/wap/images/pic/8.jpg" height="376"
										width="640">
								</s:else>
							</ul>
							<div class="swiper-pagination kv_btns">
								<a href="#"></a>
								<a href="#" class="selected"></a>
								<a href="#"></a>
							</div>
						</div>
					</div>
					<div class="news_ind clearfix">
						<ticket:common type="newsListByNewsClassAlias" value="tupianxinwen" />
						<div class="xl">
							<h4>
								${newsClass.name }
							</h4>
							<ul style="width: 240px; height: 240px;">
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="news" value="#request.newsListByNewsClassAlias">
										<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${news.id}" annexType="1" size="1" />
										<s:if test="#request.queryAnnexList != null">
											<ol>
												<s:iterator id="annex" value="#request.queryAnnexList"
													status="st">
													<a href="/airport/${news.status.visitUrl }.ticket"><img
															id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" />
													</a>
												</s:iterator>
											</ol>
										</s:if>
									</s:iterator>
								</s:if>
							</ul>
							<div class="news_ind_btns">
								<a href="#"></a>
								<a href="#" class="selected"></a>
								<a href="#"></a>
							</div>
						</div>
						<div class="xr">
							<ticket:common type="newsListByNewsClassAlias" value="jichangyaowen" />
							<h4>
								${newsClass.name }
							</h4>
							<ul>
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="news" value="#request.newsListByNewsClassAlias">
										<li>
											<a href="/airport/${news.status.visitUrl }.ticket">${news.title }</a>
										</li>
									</s:iterator>
								</s:if>
								<s:else>
									<li>
										<a><span></span>${noDataMessage }</a>
									</li>
								</s:else>
							</ul>
							<a href="/airport/jichangyaowen.ticket"
								class="button float-right yello c_l_grey margin-big-right">查看更多</a>
						</div>
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