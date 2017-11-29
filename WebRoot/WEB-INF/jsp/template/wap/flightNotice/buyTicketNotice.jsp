<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@include file="../common/head.jsp"%>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:include value="../common/title.jsp">
					<s:param name="title">${flag==1 ? '国内购票须知' : '国际购票须知'}</s:param>
				</s:include>
				<div class="mobile-main">
					<div class="share_bar">
						<s:if test="#request.flag==1">
							<a href="#"><i class="memberFavorite icon-star-o" title="国内购票须知" url="/airport_buyTicketNotice.action?flag=1">
							<span style="font-style: normal;color: #4f4b47;margin-left: 5px">收藏</span></i></a>
						</s:if>
						<s:else>
							<a href="#"><i class="memberFavorite icon-star-o" title="国际购票须知" url="/airport_buyTicketNotice.action?flag=2">
						<span style="font-style: normal;color: #4f4b47;margin-left: 5px">收藏</span></i></a>
						</s:else>
						<a href="#" class="showdiv_reg" style="display:;"><i class="icon-share-alt"></i>分享</a>
					</div>
					
					<!--自定义登陆弹出框-->
					<div id="popupbgLayer" class="popupbg"></div>
					<iframe id='popupIframeLayer' class='popupIframe' frameborder='0' ></iframe>
					
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style_32x32 popupdiv" id="popupLayer_reg">
						<div class="popup_title"><span>分享到</span>
							<div id="closeDiv_reg" class="popupclose_btn"><a href="#">&times;</a></div>
						</div>	
						<div class="popup_show">
							<a class="jiathis_button_tsina">新浪微博</a>
							<a class="jiathis_button_weixin">微信</a>
							<a class="jiathis_button_qzone">QQ空间</a>
							<a class="jiathis_button_cqq">QQ好友</a>
							<a class="jiathis_button_email">邮件</a>
							<a class="jiathis_button_tieba">百度贴吧</a>
							<a class="jiathis_button_baidu">百度空间</a>
							<a class="jiathis_button_meilishuo">美丽说</a>
							<a class="jiathis_button_copy">复制网址</a>
							<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis" target="_blank">更多</a>
							<script type="text/javascript" src="http://v3.jiathis.com/code_mini/jia.js" charset="utf-8"></script>
						</div>
					</div>
					<div class="select_tit">
						<%--<s:if test="#request.flag==1">
							<ticket:common type="systemDictionaryListByParentValue" value="domesticBuyTicketNotice"/>
						</s:if>
						<s:else>
							<ticket:common type="systemDictionaryListByParentValue" value="internationalBuyTicketNotice"/>
						</s:else>
						--%>
						<s:if test="#request.flag == 1">
							<ticket:common type="newsListByNewsClassAlias" value="guoneigoupiaoxuzhi" size="100"/>
						</s:if>
						<s:else>
							<ticket:common type="newsListByNewsClassAlias" value="guojigoupiaoxuzhi" size="100"/>
						</s:else>
						<div class="button-group no-background">
							<button type="button" class="button  bg dropdown-toggle fz22"
								style="border: 1px solid #ddd">
								<%--<s:if test="#request.systemDictionaryListByParentValue != null">
									<s:iterator id="sysObj" value="#request.systemDictionaryListByParentValue" status="st">
										<s:if test="#st.first">
											<font id="currentBuyTicketNoticeValue">${sysObj.name }</font>
										</s:if>
									</s:iterator>
								</s:if>
								--%>
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="buyTicketNotice" value="#request.newsListByNewsClassAlias" status="st">
										<s:if test="#st.first">
											<font id="currentBuyTicketNoticeValue">${buyTicketNotice.title }</font>
										</s:if>
									</s:iterator>
								</s:if>
								<span class="downward"></span>
							</button>
							<ul class="drop-menu buyTicketAjax">
								<s:if test="#request.newsListByNewsClassAlias != null">
									<s:iterator id="buyTicketNotice" value="#request.newsListByNewsClassAlias">
										<li ticketNoticeId="${buyTicketNotice.id }">${buyTicketNotice.title }</li>
									</s:iterator>
								</s:if>
							</ul>
						</div>
					</div><%--
					<div class="c_content sysObjContent">
						<s:if test="#request.systemDictionaryListByParentValue != null">
							<s:iterator id="systemObj" value="#request.systemDictionaryListByParentValue" status="st">
								<s:if test="#st.first">
									${systemObj.description }
								</s:if>
							</s:iterator>
						</s:if>
					</div>--%>
					<div class="c_content ticketNoticeContent">
						<s:if test="#request.newsListByNewsClassAlias != null">
							<s:iterator id="buyTicketNotice" value="#request.newsListByNewsClassAlias" status="st">
								<s:if test="#st.first">
									${buyTicketNotice.content }
								</s:if>
							</s:iterator>
						</s:if>
					</div>
				</div>
				<%@include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>