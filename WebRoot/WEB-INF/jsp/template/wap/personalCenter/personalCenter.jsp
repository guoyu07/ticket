<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page ind">
				<div class="mobile-main">
					<div class="ind_main">
						<a href="javascript:;" class='nav_btn icon-navicon'><font>更多</font></a>
						 <s:if test="#session.sessionMember == null">
		                	<a href='javascript:;' memberId="1" class="user_btn personalCenterBtn"><i></i></a>
		                </s:if>
		                <s:else>
		                	<a href="javascript:;" memberId="0" class="user_btn personalCenterBtn"><i></i></a>
		                </s:else>
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
							<a href="javascript:;" class="wifi_btn"></a>
						</div>
						<div class="ind_text">
							昆明长水国际机场
							<p>
								CHANG SHUI INTERNATIONAL AIRPORT
							</p>
						</div>
						<div class="search_form">
							<form action="" method="get">
								<button type="button" onclick="javascript:window.location.href = '/airport/hangbanchaxun.ticket';">
									航班查询
								</button>
								<label>
		                            <input type="text" name="keyword" id="keyword" value="" placeholder='输入你的需求' class='dropdown-toggle'><i class="icon-search" id="commonSearchBtn"></i>
		                            <ul class="drop-menu">
		                                <li dataType="bjdj">便捷登机</li>
		                                <li dataType="jpyd">机票预订</li>
		                                <li dataType="hbyw">航班延误</li>
		                            </ul>
		                        </label>
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
			<div class="wifi_dialog box_dialog">
				<div class="text-center b_white border10 mr40 padding-big">
					<dl>
						<dd class="line">
							<div class="x3">
								手机号：
							</div>
							<div class="x9">
								<input type="text" class='input' name="" value="">
							</div>
						</dd>
						<dd class="line">
							<div class="x3">
								验证码：
							</div>
							<div class="x5">
								<input type="text" class='input' name="" value="">
							</div>
							<div class="x4">
								<button type=""
									class="b_blue button c_white d_button radius-none">
									获取验证码
								</button>
							</div>
						</dd>
						<dd class="line">
							<div class="x12">
								<button type="" class="bg-yello button block d_button">
									登录
								</button>
							</div>
						</dd>
						<dd class="line">
							<div class="x12">
								使用第三方登录
							</div>
							<br>
							<hr>
						</dd>
						<dt class='clearfix'>
							<a href="#">便捷登机</a>
							<a href="#">机场大巴</a>
							<a href="#">电瓶车</a>
						</dt>
					</dl>
				</div>
			</div>
			<%--<div class="wifi_success_dialog box_dialog">
				<div class="text-center b_white border10 mr40 padding-big">
					<img src="../images/wifi_demo.png" height="677" width="513">
				</div>
			</div>
			--%><div class="side_right_menu">
			    <div class="side_menu_con">
			        <div class="avatar">
			        	<a href="/airportm_personalSetting.action">
				            <div class="avatar_pic">
				            	<ticket:commonAnnex type="annex" entityUuid="${sessionMember.id}" annexType="1" size="1"/>
				                	<s:if test="#request.annex != null">
				                		<s:if test="#annex.annexPath != null">
											<img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" class='radius-circle' height="236" width="242">
										</s:if>
				                	</s:if>
				                	<s:else>
				                		<img src="/template/wap/images/no_avatar.png" height="236" width="242">
				                	</s:else>
				            </div>
			            </a>
			        </div>
			        <div class="clearfix text-center">
			        <span class="fz30 c_white"><i class='user_icon1'></i>&nbsp;&nbsp;
			        	<s:if test="#sessionMember.nickName != null">
			        		${sessionMember.nickName }
			        	</s:if>
			        	<s:else>
			        		未设置
			        	</s:else>
			        	 </span>
			        <a href="airportm_myMessage.action" class="badge-corner"><i class='icon-envelope-o c_white'  style="font-size:40px;"></i>
			        <span class="badge bg-red">
			        	<s:if test="session.sessionMember != null">
				            <ticket:common type="noReadMessageCount"/>
				            ${noReadMessageCount }
			            </s:if>
			            <s:else>0</s:else>
			        </span></a></div>
			        <div class="line text-center margin-large-top">
			            <div class="x4 fz18 c_white"><p class='fz40 margin-big-bottom'>0</p>我的积分</div>
			            <div class="x4 fz18 c_white" id="myFavorite"><p class='fz40 margin-big-bottom'>
			            <s:if test="session.sessionMember != null">
				            <ticket:common type="myFavoriteCount"/>
				            ${myFavoriteCount }
			            </s:if>
			            <s:else>0</s:else>
			            </p>我的收藏</div>
			            <div class="x4 fz18 c_white"><p class='fz40 margin-big-bottom'>0</p>我的评价</div>
			        </div>
			        <div class="label_bar" style="padding-left:0px;padding-right:20px;">
			            <a href="/airportm_myFocusOn.action" class="b_white c_grey">我的关注</a>
			            <a href="/bjdjOrderTemplate_allOrder.action" class="b_white c_grey">我的订单</a>
			            <a href="/airportm_VIPMember.action" class="b_white c_grey">VIP会员</a>
			            <a href="/airportm_myFlightRecord.action" class="b_white c_grey">飞行记录</a>
			            <a href="/commonTraveller_show.action" class="b_white c_grey">常用旅客</a>
			        </div>
			        
			        <div class="side_right_icon centerMenuList">
			            <ul class="clearfix">
			            	<s:if test="session.sessionMember != null">
				            	<ticket:common type="memberSetQuickMenuList" size="6"/>
				            	<s:if test="#request.memberSetQuickMenuList != null">
				            		<s:iterator id="memberQuickMenu" value="#request.memberSetQuickMenuList">
				            			<li>
				            				<ticket:common type="quickMenuObj" value="${memberQuickMenu.quickMenu_id }"/>
				            				<ticket:commonAnnex type="annex" entityUuid="${quickMenuObj.id }" annexType="1" size="1"/>
												<a href="${quickMenuObj.url }"> <img src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" /></a>
												<!-- <a href="#" memberQuickMenuId="${memberQuickMenu.id }" class="del deleteSetMenu">删除</a></li> -->
			                            </li>
				            		</s:iterator>
				            	</s:if>
				           		<s:if test="#request.defaultList != null">
									<s:iterator id="default" value="#request.defaultList">
										<li><a href="#" class="icons3 custom_menu_btn" defaultPosition="g"></a></li>
									</s:iterator>
								</s:if>
			            	</s:if>
			            </ul>
			            <div class="tit custom_menu_btn toSetQuickMenu" defaultPosition="g" style="padding-top:0px;margin-bottom:40px;">设置快捷菜单</div>
			        </div>
			    </div>
			</div>
			<div class="side_menu">
			    <div class="side_menu_con">
			        <a href="javascript:;" class="close_btn"></a>
			        <ul>
			        	<!-- first partition -->
			            <s:if test="tipMessage != 'indexPage'"><li><h6><a href="/airport.action">首页</a></h6></li></s:if>
			            <li><h6><a href="/airport/chufa.ticket">出发</a></h6></li>
			            <li><h6><a href="/airport/daoda.ticket">到达</a></h6></li>
			            <li><h6><a href="/airport/zhongzhuan.ticket">中转</a></h6></li>
			            <li class='border more'>
							<ticket:common type="newsClassChilds" value="lvkexuzhi"/>
							<h6>
								<a href="javascript:;">${newsClassObj.name }</a><i class='icon-sort-desc'></i>
							</h6>
							<dl>
								<s:if test="#request.newsClassChilds != null">
									<s:iterator id="nc" value="#request.newsClassChilds">
										<dd>
											<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
										</dd>
									</s:iterator>
								</s:if>
							</dl>
						</li>
						<!-- second partition -->
			            <li><h6><a href="/airport/hangbanchaxun.ticket">航班查询</a></h6></li>
			            <li><h6><a href="javascript:;">票务中心</a></h6>
			            <li><h6><a href="/airport_checkinServiceNotice.action">网上值机</a></h6></li>
			            <%--<li><h6><a href="#">机场屏显</a></h6></li> --%></li>
			            <li><h6><a href="/airport_trafficGuide.action">交通指南</a></h6></li>
			            <li class='border more'><h6><a href="javascript:;">航延服务</a><i class='icon-sort-desc'></i></h6>
			                <dl>
			                    <dd><a href="/airport_flightDelay.action">法律法规</a></dd>
			                    <dd><a href="/airport_flightDelay.action">延误公告</a></dd>
			                    <dd><a href="/airport_flightCompany.action">机票改签</a></dd>
			                    <dd><a href="/airport/1446175628277.ticket">行李服务</a></dd>
			                    <dd><a href="/airport_restaurant.action">餐饮零售</a></dd>
			                    <dd><a href="/airport_hotel.action">酒店住宿</a></dd>
			                    <dd><a href="#">休闲娱乐</a></dd>
			                    <dd><a href="/airport_trafficGuide.action">交通查询</a></dd>
			                    <dd><a href="/airport_hotLinePhone.action">服务热线</a></dd>
			                    <dd><a href="#">交通集结点</a></dd>
			                </dl>
			            </li>
			            <!-- third partition -->
			            <li class='more'>
							<ticket:common type="newsClassChilds" value="jichangfuwu"/>
							<h6>
								<a href="javascript:;">${newsClassObj.name }</a><i class='icon-sort-desc'></i>
							</h6>
							<dl>
								<s:if test="#request.newsClassChilds != null">
									<s:iterator id="nc" value="#request.newsClassChilds">
										<dd>
											<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
										</dd>
									</s:iterator>
								</s:if>
								<dd><a href="#">服务热线</a></dd>
								<%--<dd><a href="/airport_flightCompany.action">航空公司信息</a></dd> --%>
								
								<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
								<s:if test="#request.newsList != null">
									<s:iterator id="newsObj" value="#request.newsList">
										<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
											<dd>
												<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
											</dd>
										</s:if>
										<s:else>
											<dd>
												<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
											</dd>
										</s:else>
									</s:iterator>
								</s:if>
								<dd><a href="/airport/bjdj.action?direct=true">便捷登机</a></dd>
								<dd><a href="/airport/electromobile.action">电瓶车服务</a></dd>
							</dl>
						</li>
						<li class='more'>
							<ticket:common type="newsClassChilds" value="aixinfuwu"/>
							<h6>
								<a href="javascript:;">${newsClassObj.name }</a><i class='icon-sort-desc'></i>
							</h6>
							<dl>
								<s:if test="#request.newsClassChilds != null">
									<s:iterator id="nc" value="#request.newsClassChilds">
										<dd>
											<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
										</dd>
									</s:iterator>
								</s:if>
								<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
								<s:if test="#request.newsList != null">
									<s:iterator id="newsObj" value="#request.newsList">
										<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
											<dd>
												<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
											</dd>
										</s:if>
										<s:else>
											<dd>
												<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
											</dd>
										</s:else>
									</s:iterator>
								</s:if>
							</dl>
						</li>
			            <li class='more'>
							<ticket:common type="newsClassChilds" value="jichangsheshi"/>
							<h6>
								<a href="javascript:;">${newsClassObj.name }</a><i class='icon-sort-desc'></i>
							</h6>
							<dl>
								<s:if test="#request.newsClassChilds != null">
									<s:iterator id="nc" value="#request.newsClassChilds">
										<dd>
											<a href="/airport/${nc.alias }.ticket">${nc.name }</a>
										</dd>
									</s:iterator>
								</s:if>
								<ticket:common type="newsList" value="${newsClassObj.id }" size="100"/>
								<s:if test="#request.newsList != null">
									<s:iterator id="newsObj" value="#request.newsList">
										<s:if test="#newsObj.useNewsClassName != null && #newsObj.useNewsClassName != ''">
											<dd>
												<a href="/airport/${newsObj.useNewsClassName}.ticket">${newsObj.title }</a>
											</dd>
										</s:if>
										<s:else>
											<dd>
												<a href="/airport/${newsObj.status.visitUrl }.ticket">${newsObj.title }</a>
											</dd>
										</s:else>
									</s:iterator>
								</s:if>
							</dl>
						</li>
						<li class='more'><h6><a>机场商业</a><i class='icon-sort-desc'></i></h6>
							<dl>
								<dd><a href="/airport_restaurant.action">餐饮</a></dd>
								<dd><a href="#">零售</a></dd>
								<dd><a href="#">休闲</a></dd>
								<dd><a href="/airport_hotel.action">酒店</a></dd>
							</dl>
						</li>
						<li class="border"><h6><a href="/airport_travelInformation.action">旅游咨询</a></h6></li>
						<!-- forth partition -->
			            <li>
							<ticket:common type="newsClassChilds" value="jichangwenhua"/>
							<h6>
								<a href="/airport/jichangwenhua.ticket" >${newsClassObj.name }</a>
							</h6>
						</li>
						<li>
							<ticket:common type="newsClassChilds" value="changshuidongtai"/>
							<h6>
								<a href="/airport/changshuidongtai.ticket" >${newsClassObj.name }</a>
							</h6>
						</li>
						<li><h6><a href="/airportm_myMessage.action">消息中心</a></h6></li>
			            <li><h6><a href="/bjdjCommentTemplate_listPage.action?versionFlag=site">我要评价</a></h6></li>
			            <li><h6><a href="/airport_employeeLogin.action">员工通道</a></h6></li>
			            <li><h6><a href="/airportm_personalSetting.action">个人中心</a></h6></li>
						<li>
							<ticket:common type="newsClassChilds" value="mianzeshengming"/>
							<h6>
								<a href="/airport/mianzeshengming.ticket" >${newsClassObj.name }</a>
							</h6>
						</li>
						<li>
							<ticket:common type="newsClassChilds" value="guanzhuwomen"/>
							<h6>
								<a href="airport_payAttentionToUsTemplate2.action" >${newsClassObj.name }</a>
							</h6>
						</li>
			        </ul>
			    </div>
			</div>
		</div>
		<script type="text/javascript">
		    $(function(){
		        ops.right_menu_dialog=$.do_dialog.open({'msg':$('.side_right_menu'),'position':'top',initBefore:function(){
		        }});
		    })
		</script>
	</body>
</html>