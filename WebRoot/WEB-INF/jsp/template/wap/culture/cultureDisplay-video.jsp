<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp"></jsp:include>
				<div class="mobile-main">
					<div class="kv_pics video_kv">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
								<ticket:common type="newsListByNewsClassAlias" value="tupianzhanshi" size="5" />
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="newsObj" value="#request.newsListByNewsClassAlias">
										<ticket:commonAnnex type="annex" entityUuid="${newsObj.id}"
											annexType="1" size="1" />
										<li class="swiper-slide">
											<a href="/airport/${newsObj.status.visitUrl }.ticket"><img
													src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="376" width="640">
											</a>
										</li>
										<a href="javascript:;" class='kv_prev icon-angle-left'></a>
										<a href="javascript:;" class='kv_next icon-angle-right'></a>
									</s:iterator>
								</s:if>
							</ul>
						</div>
                        <div class="ind_news">
                            <ul>
                            	<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="newsObj" value="#request.newsListByNewsClassAlias">
										<li class='clearfix'>
											<p>${newsObj.title }</p>
										</li>
									</s:iterator>
								</s:if>
                            </ul>
                        </div>
						
						<div class="swiper-pagination kv_btns">
							<s:if test="#request.newsListByNewsClassAlias != null">
								<s:iterator id="newsObj" value="#request.newsListByNewsClassAlias">
									<a href="#" <s:if test="#st.first"> class="selected"</s:if>></a>
								</s:iterator>
							</s:if>
						</div>
					</div>
					<div class='mr40 line-big clearfix video_ls'>
					<ticket:common type="newsListByNewsClassAlias" value="shipinzhanshi" size="8" />
						<s:if test="#request.newsListByNewsClassAlias != null">
							<s:iterator id="newsVideo" value="#request.newsListByNewsClassAlias">
								<a href="/airport/${newsVideo.status.visitUrl }.ticket" class='x6'>
								 <em> 
								 <ticket:commonAnnex type="annex" entityUuid="${newsVideo.id}" annexType="1" size="1" />
									<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" height="190" width="320">
								 <span><i class='icon-play-circle'></i>
								</span> </em>
								<p class="fz20 padding-big-top padding-big-bottom text-center">
									${newsVideo.title }
								</p> </a>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>