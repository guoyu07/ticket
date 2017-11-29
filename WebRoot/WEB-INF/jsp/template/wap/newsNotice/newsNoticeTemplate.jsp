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
					<div class="notice_kv">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
								<ticket:common type="newsListByNewsClassAlias" value="gonggaolan" size="10" />
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="news" value="#request.newsListByNewsClassAlias">
										<ticket:commonAnnex type="annex" entityUuid="${news.id}"
											annexType="1" size="1" />
										<li class="swiper-slide">
											<a href="/airport/${news.status.visitUrl }.ticket"><img
													src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="376" width="640">
											</a>
										</li>
									</s:iterator>
								</s:if>
							</ul>
                            <a href="javascript:;" class='kv_prev icon-angle-left'></a>
                            <a href="javascript:;" class='kv_next icon-angle-right'></a>
							<div class="swiper-pagination kv_btns">
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="news" value="#request.newsListByNewsClassAlias" status="st">
										<a href="/airport/${news.status.visitUrl }.ticket" <s:if test="#st.first">class="selected"</s:if>></a>
									</s:iterator>
								</s:if>
							</div>
							<div class="ind_news">
								<ul>
									<s:if test="#request.newsListByNewsClassAlias != null">
										<s:iterator id="news" value="#request.newsListByNewsClassAlias">
											<li>
												<span><ticket:format value="${news.title }" size="6"/> </span>
												<p>
													<ticket:format value="${news.introduce}" size="15"/> <a href="/airport/gonggaolan.ticket" class='button float-right bg-sub c_white margin-big-right'>查看更多</a>
												</p>
											</li>
										</s:iterator>
									</s:if>
								</ul>
							</div>
						</div>
					</div>
					<div class="news_ind clearfix">
						<div class="xl">
							<ticket:common type="newsListByNewsClassAlias" value="tupianxinwen" size="10" />
							<h4>
								${newsClassObj.name }
							</h4>
							<div class="swiper-container"
								style='width: 240px; height: 340px;'>
								<ul class="swiper-wrapper">
									<s:if test="#request.newsListByNewsClassAlias != null">
										<s:iterator id="news"
											value="#request.newsListByNewsClassAlias">
											<ticket:commonAnnex type="annex" entityUuid="${news.id}" annexType="1" size="1" />
											<li class="swiper-slide">
												<a href="/airport/${news.status.visitUrl }.ticket"><img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="240" width="240">
												</a><p class='text-left fz24'><ticket:format value="${news.title }" size="8"/> </p>
												<a href="/airport/tupianxinwen.ticket" class="button float-right bg-yello c_l_grey margin-big-top no-border fz22">查看更多</a>
											</li>
										</s:iterator>
									</s:if>
								</ul>
							</div>
							<div class="swiper-pagination news_ind_btns">
								<a href="#"></a>
								<a href="#" class="selected"></a>
								<a href="#"></a>
							</div>
						</div>
						<div class="xr">
							<ticket:common type="newsListByNewsClassAlias"
								value="jichangyaowen" size="10" />
							<h4>
								${newsClassObj.name }
							</h4>
							<ul class="margin-big-bottom">
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="news" value="#request.newsListByNewsClassAlias" status="st">
										<s:if test="#st.first">
											<li>
												<a href="/airport/${news.status.visitUrl }.ticket" class='fz24'><span
													class="tag bg-red" style="display: none;">置顶</span> <ticket:format value="${news.title }" size="8" /> </a>
											</li>
										</s:if>
										<s:else>
											<li>
												<a href="/airport/${news.status.visitUrl }.ticket" class='fz24'> <ticket:format value="${news.title }" size="8" /> </a>
											</li>
										</s:else>
									</s:iterator>
								</s:if>
								<s:else>
									<li>
										<a class='fz24'><span></span>${noDataMessage }</a>
									</li>
								</s:else>
							</ul>
							<a href="/airport/jichangyaowen.ticket"
								class="button float-right yello c_l_grey margin-big-right fz22">查看更多</a>
						</div>
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