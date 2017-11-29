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
					<div class="kv_pics">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
								<ticket:commonAnnex type="annex" entityUuid="${newsClass.id}" annexType="1" size="1" />
								<li>
									<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}">
								</li>
							</ul>
						</div>
					</div>
					<ul class='mr40 viewport_ls'>
	                	<ticket:common type="newsList" value="${newsClass.id }" size="100" />
						<s:if test="#request.newsList != null">
							<s:iterator id="news" value="#request.newsList">
								<li>
								<ticket:commonAnnex type="annex" entityUuid="${news.id}" annexType="1" size="1" />
				                   	<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}" class="cultureDetail" url="${news.status.visitUrl }">
				                    <div class="title">
				                        <div class="clearfix">
				                            <span class='float-left'>${news.title }</span>
				                            <span class='float-right'><font class="c_yello">2015/07/15-2016/07/15</font></span>
				                        </div>
				                    </div>
			                    </li>
		                    </s:iterator>
						</s:if>
						<s:else>
							<div class="title">
								${noDataMessage }
							</div>
						</s:else>
            		</ul>
					<%--<ticket:common type="newsList" value="${newsClass.id }" size="100" />
					<s:if test="#request.newsList != null">
						<s:iterator id="news" value="#request.newsList">
							<div class="tit">
								<a href="/airport/${news.status.visitUrl }.ticket"> <ticket:format
										value="${news.title }" size="20" /> </a>
							</div>
						</s:iterator>
					</s:if>
					<s:else>
						<div class="tit">
							${noDataMessage }
						</div>
					</s:else>
				--%></div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>