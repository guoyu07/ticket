<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-page">
			<jsp:include page="../common/title.jsp">
				<jsp:param value="爱心服务" name="title"/>
			</jsp:include>
	        <div class="mobile-main">
            	<div class="share_bar">
						<a href="#">
							<i class="memberFavorite icon-star-o" 
									title="爱心服务" url="airport_careServiceList.action"
								>
								<span style="font-style: normal;color: #4f4b47;margin-left: 5px">收藏</span>
							</i>
						</a>
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
	            <div class="careServiceList">
		            <div class="tit" href="/airport/muyingshi.ticket">母婴室及母婴候机厅</div>
		            <div class="tit" href="/airport/1445676839067.ticket">轮椅服务</div>
		            <div class="tit" href="/airport/1446436247822.ticket">爱心专用卫生间</div>
		            <div class="tit" href="/airport/airport/1453864857457.ticket">老年人及残障人士候机区</div>
		            <div class="tit" href="/airport/1446173505027.ticket">儿童及婴儿手推车</div>
		            <div class="tit" href="/airport/electromobile.action">电瓶车</div>
	            </div>
	        </div>
            <%@ include file="../common/quickNav.jsp"%>
	    </div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>