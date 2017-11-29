<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="head.jsp"%>
	<script type="text/javascript">
		$(function(){
			var co = JM.getCookies();
		});
	</script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page ind">
				<div class="mobile-main">
					<div class="ind_main">
						<a href="javascript:;" class='nav_btn icon-navicon'><font>更多</font></a>
	                	<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="user_btn personalCenterBtn">
		                	<c:choose>
								<c:when test="${!empty sessionMember }">
									<ticket:common type="newMessages"/>
									<i></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
								</c:when>
								<c:otherwise>
									<ticket:common type="myAnnounecementCount"/>
									<i></i><c:if test="${myAnnounecementCount == 1 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
								</c:otherwise>
							</c:choose>
	                	</a>
	                	<%-- <ticket:common type="newMessages"/>
                		<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="user_btn personalCenterBtn"><i></i><s:if test="#request.newMessages > 0"><span style="margin-left: -15px;margin-top:27px;position: absolute;" class="badge bg-red">&nbsp;</span></s:if></a> --%>
						<ticket:cache group="资讯中心">
						<div class="ind_kv">
							<div class="swiper-container">
								<ul class="swiper-wrapper advertUrl">
									<ticket:common type="advertListNew" value="首页图片" size="10"/>
									<s:if test="#request.advertListNew != null">
										<s:iterator id="advert" value="#request.advertListNew">
											<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
											<li class="swiper-slide" href="${advert.url }">
												<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath}">
											</li>
										</s:iterator>
									</s:if>
								</ul>
							</div>

							<div class="kv_mask"></div>
							<div class="swiper-pagination kv_btns">
								<s:if test="#request.advertListNew != null">
									<s:iterator id="advert" value="#request.advertListNew" status="st">
										<a href="#"<s:if test="#st.first"> class="selected"</s:if>></a>
									</s:iterator>
								</s:if>
							</div>
							<div class="ind_news">
								<ul>
									<s:if test="#request.advertListNew != null">
										<s:iterator id="advert" value="#request.advertListNew">
											<li>
												<span>${advert.name }</span>
												<p>
													<ticket:format value="${advert.content }" size="10"/>
												</p>
											</li>
										</s:iterator>
									</s:if>
								</ul>
							</div>
							<%--<a href="javascript:;" class="wifi_btn"></a>--%></div>
						<div class="ind_text">
							昆明长水国际机场
							<p>
								Kunming Changshui International Airport
							</p>
						</div>
						<div style="padding-top: auto;top: auto;margin-top: 220px;margin-bottom: auto;">
							<table class="qlist-table">
								<tbody>
									<tr>
										<td><a href="/airport/hangbanchaxun.ticket"><i></i> <span></span>
											<p>航班查询</p></a></td>
										<td><a href="/airport_checkinServiceNotice.action?direct=true"><i></i><span></span>
											<p>网上值机</p></a></td>
										<td><a href="/airport_careServiceList.action"> <span></span>
											<p>爱心服务</p></a></td>
										<td><a href="/airport/bjdj.action"><i></i> <span></span>
											<p>便捷登机</p></a></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- <div class="qlist-box js-card-website-box">
							<div class="qlist">
								<table class="qlist-table">
									<tbody>
										<tr>
											<td><a onclick="st(this,'hangban')" data-id="hangban" href="/airport/hangbanchaxun.ticket"><i></i> <span class="qlist1"></span>
												<p>航班查询</p></a></td>
											<td><a onclick="st(this,'weixin')" data-id="zhiji" href="/airport_checkinServiceNotice.action?direct=true"><i></i><span class="qlist2"></span>
												<p>网上值机</p></a></td>
											<td><a onclick="st(this,'aixin')" data-id="zhihu" class="js-zhihu" href="＃"> <span class="qlist17"></span>
												<p>爱心服务</p></a></td>
											<td><a onclick="st(this,'dengji')" data-id="news" href="/airport/bjdj.action"><i></i> <span class="qlist3"></span>
												<p>便捷登机</p></a></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div> -->
						<div class="search_form">
							<form action="" method="get" onsubmit="javascript:$('#commonSearchBtn').trigger('click');return false;">
								<!-- <button type="button" onclick="javascript:window.location.href = '/airport/hangbanchaxun.ticket';">
									航班查询
								</button> -->
								<label>
		                            <input type="text" name="keyword" id="keyword" value="" placeholder='输入你的需求' class='dropdown-toggle'><i class="icon-search" id="commonSearchBtn"></i>
		                            <ul class="drop-menu">
		                                <li onclick="location.href = '/bjdj.action'">便捷登机</li>
		                                <li onclick="location.href = '/airport_trafficGuide.action'">交通指南</li>
		                                <li onclick="location.href = '/airport/guoneichengjixuzhi.ticket'">乘机须知</li>
		                            </ul>
		                        </label>
							</form>
						</div>
						<div class="ind_company">
							<p class="ft_logo"></p>
							yunnan airport group co.,ltd.
						</div>
						</ticket:cache>
					</div>
				</div>
			</div>
		</div>
		<div style='display: none;'>
			<div class="wifi_success_dialog box_dialog">
			    <div class="text-center b_white border10 mr40 padding-big">
			        <img src="/template/wap/images/wifi_demo.png" height="677" width="513" >
			    </div>
			</div>
			<%@ include file="left.jsp"%>
			<!-- <script type="text/javascript">$(function(){indexClickInit();});</script> -->
		</div>
	</body>
</html>