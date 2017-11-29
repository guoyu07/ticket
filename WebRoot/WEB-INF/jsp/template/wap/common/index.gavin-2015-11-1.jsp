<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page ind">
				<div class="mobile-main">
					<div class="ind_main">
						<a href="" class="wifi_btn"></a>
						<a href="" class="user_btn"></a>
						<div class="ind_kv">
							<div class="swiper-container">
								<ul class="swiper-wrapper">
									<ticket:common type="advertListNew" value="首页图片" size="10"/>
									<s:if test="#request.advertListNew != null">
										<s:iterator id="advert" value="#request.advertListNew">
											<ticket:commonAnnex type="annex" entityUuid="${advert.id}" annexType="1" size="1" />
											<li class="swiper-slide">
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
						</div>
						<div class="ind_text">
							昆明长水国际机场
							<p>
								CHANG SHUI INTERNATIONAL AIRPORT
							</p>
						</div>
						<div class="search_form">
							<form action="" method="get">
								<label>
									<i class="fa fa-search"></i>
									<input type="text" name="" value="" placeholder='输入你的需求'>
								</label>
								<button type="submit">
									航班查询
								</button>
							</form>
						</div>
						<div class="ind_company">
							<p class="ft_logo"></p>
							COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED.
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style='display: none;'>
			<%@ include file="left.jsp"%>
		</div>
		
	</body>
</html>