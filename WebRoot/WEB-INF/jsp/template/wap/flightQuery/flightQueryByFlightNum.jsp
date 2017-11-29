<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<script src="/template/wap/layer/layer.js"></script>
    
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="航班查询" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="share_bar">
						<a href="#">
							<i class="memberFavorite icon-star-o" 
									title="航班查询" url="airport/hangbanchaxun.ticket"
								>
								<span style="font-style: normal;color: #4f4b47;margin-left: 5px">收藏</span>
							</i>
						</a>
						<s:if test="#session.fromApp != null || #parameters.fromApp != null">
							<a href="/comomExeShare.action?title=${newsClass.name }" style="display:;"><i class="icon-share-alt"></i>分享</a>
						</s:if>
						<s:else>
							<a href="#" class="showdiv_reg" style="display:;"><i class="icon-share-alt"></i>分享</a>
						</s:else>
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
					
					<div class="tit_tab tit_font22_tab">
						<a href="#" class="queryFlight" flag="/airport/hangbanchaxun.ticket?direct=true"><i class="fa fa-caret-down"></i>按目的地查询</a>
						<a href="#" class="selected"  flag="queryByFlightNo"><i class="fa fa-caret-down"></i>按航班号查询</a>
					</div>
					<div class="new_fly_ls search_fly_ls queryFlightContent">
						<div class="search_time c_content">
							<i class="icon-space-shuttle text-sub margin-large-right"></i>
							航班号&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="input input-auto d_input" name="flightNumber" id="flightNumber" value="${flightNumber }" size="20">
						</div>
						<div class="search_time c_content">
							<i class="icon-calendar text-sub margin-large-right"></i>
							选择日期&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="flightDate == null">
								<input type="date" id="flightDate" name="flightDate" value="<ticket:common type="currentDate"/>" class='no-border c_l_grey fz22'>
							</s:if>
							<s:else>
								<input type="date" id="flightDate" name="flightDate" value="${flightDate }" class='no-border c_l_grey fz22'>
							</s:else>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<button type="" class="button bg-sub radius-big queryFlightByNoAndDate">
							航班查询
						</button>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		<ticket:common type="vpnBroken" var="sms"/>
		<s:if test="#request.sms != null">
			<script type="text/javascript">
				$(function(){
					layer.alert('${sms.value}', {
						  skin: 'layui-layer-lan' //样式类名
						  ,closeBtn: 0
						});
				});
			</script>
		</s:if>
	</body>
</html>