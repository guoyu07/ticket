<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp"></s:include>
				<div class="mobile-main">
					<div class="notice_ls">
						<h4>
							${news.title }
							<p>
								<s:date name="news.status.createTime" format="yyyy-MM-dd"/>
								<span class='float-right'><font>浏览次数</font>${news.status.hits }次</span>
							</p>
						</h4>
						<div class="text_content">
							${news.content }
						</div>
						<h3 class="notice_ft_ls">
							<ul>
								<li>
									<ticket:common type="prevNews" news="${news}"/>
									<s:if test="#request.prevNews != null">
										<a href="/airport/${prevNews.status.visitUrl }.ticket"><span>下一篇：</span>
										<ticket:format value="${prevNews.title}" size="20"/></a>
									</s:if>
									<s:else>
										<a><span>下一篇：</span><font></font>没有了</a>
									</s:else>
								</li>
								<li>
									<ticket:common type="nextNews" news="${news}"/>
									<s:if test="#request.nextNews != null">
										<a href="/airport/${nextNews.status.visitUrl }.ticket"><span>上一篇：</span>
										<ticket:format value="${nextNews.title}" size="20"/></a>
									</s:if>
									<s:else>
										<a><span>上一篇：</span><font></font>没有了</a>
									</s:else>
								</li>
							</ul>
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