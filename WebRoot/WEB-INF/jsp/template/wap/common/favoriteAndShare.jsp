<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="share_bar">
	<a href="#">
		<i class="memberFavorite icon-star-o" 
			<s:if test="#request.news != null">
				objectId="${news.id }" objectType="News"
			</s:if><s:elseif test="#request.newsClass != null">
				objectId="${newsClass.id }" objectType="NewsClass"
			</s:elseif>
			<s:else>
				title="${param.title }" url="${param.url }"
			</s:else>
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

