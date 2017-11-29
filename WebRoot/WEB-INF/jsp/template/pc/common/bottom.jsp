<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="footer" style="padding-top:0px;">
	<s:if test="#parameters.no_help != 1">
	<div class="wp posr" style="display:none;"><a href="javascript:;" class="scroll_top"></a></div>
	<div class="ind_help" style="display:block;background:#130c11 none repeat scroll 0 0;">
	    <div class='wp posr'>
	        <ul class="clearfix line">
	            <li class="x3">
	                <h2>乘机指南</h2>
	                <h3>FLY GUIDE</h3>
	                <ticket:systemCommon type="getNewsClassObj" value="pcchengjixuzhi" />
	                <a href="/airport/newsList/${getNewsClassObj.id}.html" class='effect'>乘机须知</a>
	                <a href="/airport/newsView/1446185712168.html" class='effect'>临时乘机证明</a>
	                <a href="/airportPc_serviceHotLine.action" class='effect'>服务热线（机场</a>
	                <a href="/airportPc_flightCompanyInfo.action" class='effect'>航空公司</a>
	                <a href="/airport/newsView/1446175559324.html" class='effect'>72小时过境签</a>
	                <a href="/airport/newsView/1446185024277.html" class='effect'>落地签自拍系统</a>
	                <a href="/airportPc_flightQuery.action" class='effect'>航班查询</a>
	                <a href="/airportPc_checkOnLine.action" class='effect'>网上值机</a>
	                <a href="" class='effect'>票务指南</a>
	                <a href="/airport/newsView/1446172391293.html" class='effect'>头等舱服务</a>
	                <a href="/airportPc_disclaimer.action" class='effect'>免责申明 </a>
	            </li>
	            <li class="x4">
	                <h2>信息查询</h2>
	                <h3>INFORMATION INQUIRY</h3>
	                <a href="/airport/newsView/1446185801121.html" class='effect'>遗失物品查询</a>
	                <a href="/pcElectromobile.action" class='effect'>电瓶车服务</a>
	                <a href="/pcOrder.action" class='effect'>便捷登机</a>
	                <ticket:systemCommon type="getNewsClassObj" value="wenxuntai" />
	                <a href="/airport/newsList/${getNewsClassObj.id}.html" class='effect'>问讯中心</a>
	                <a href="/airport/newsView/1446175448090.html" class='effect'>行李打包</a>
	                <a href="/airport/newsView/1446175628277.html" class='effect'>行李寄存</a>
	                <ticket:systemCommon type="getNewsClassObj" value="xinglichaxun" />
	                <a href="/airport/newsList/${getNewsClassObj.id}.html" class='effect'>行李查询</a>
	                <ticket:systemCommon type="getNewsClassObj" value="shangwuzhongxin" />
					<a href="/airport/newsList/${getNewsClassObj.id}.html" class='effect'>商务中心</a>
	            </li>
	            <li class="x2">
	                <h2>机场服务</h2>
	                <h3>AIRPORT SERVICE</h3>
	                <a href="/airport/newsView/1446106183074.html" class='effect'>卫生间</a>
	                <a href="/airport/newsView/1446175364027.html" class='effect'>吸烟室</a>
	                <a href="/airport/newsView/1446175886277.html" class='effect'>更衣室</a>
	                <a href="/airport/newsView/1453778265833.html" class='effect'>儿童娱乐区</a>
	                <a href="/airport/newsView/1446176429246.html" class='effect'>公用电话</a>
	                <a href="/airport/newsView/1446176545699.html" class='effect'>自动售货机</a>
	                <a href="/airport/newsView/1446176637605.html" class='effect'>自助充电站</a>
	                <a href="/airport/newsView/1446176733699.html" class='effect'>饮水机</a>
	                <a href="/airport/newsView/1446176842684.html" class='effect'>手推车</a>
	                <a href="/airport/newsView/1446176987074.html" class='effect'>WIFI取号机</a>
	                <a href="/airport/newsView/1446211475980.html" class='effect'>自助存取款机</a>
	            </li>
	            <li class="x2">
	                <h2>爱心服务</h2>
	                <h3>OTHER</h3>
	                <a href="/airport/newsView/1453864857457.html" class='effect'>老年人及残疾人候机区</a>
	                <a href="/airport/newsView/159ffc61-7b73-43b4-994e-85448da8dc41.html" class='effect'>母婴候机区</a>
	                <a href="/airport/newsView/402fb597-d437-41c0-8486-081c0169f9a1.html" class='effect'>母婴室</a>
	                <a href="/airport/newsView/1446436247822.html" class='effect'>残障人士专用卫生间</a>
	                <a href="/airport/newsView/1445676839067.html" class='effect'>轮椅服务</a>
	                <a href="/airport/newsView/1446173505027.html" class='effect'>儿童及婴儿手推车</a>
	                <a href="/airport/newsView/1446173978965.html" class='effect'>无人陪护老人/儿童旅客</a>
	            </li>
	        </ul>
	    </div>
	</div>
	</s:if> 
	<table cellspacing="0" cellpadding="0"  width="100%">
		<tr>
			<td height="96" bgcolor="#130c11">
				<dl class="w980">
					<dd class="bhh">
						COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD.ALL RIGHTS RESERVED
					</dd>
					<dt class="hh">
						<li class="bhh">
							<a href="tencent://message/?uin=53477638"><img
									src="/template/pc/images/footer_QQ1.gif"
									onmouseover="this.src='/template/pc/images/footer_QQ2.gif'"
									onmouseout="this.src='/template/pc/images/footer_QQ1.gif'" />
							</a>
						</li>
						<li class="bhh">
							<a href="javascript:void(0);"><img
									src="/template/pc/images/footer_weixin1.gif"
									onmouseover="this.src='/template/pc/images/footer_weixin2.gif'"
									onmouseout="this.src='/template/pc/images/footer_weixin1.gif'" />
							</a>
						</li>
						<li class="bhh">
							<a href="javascript:void(0);"><img
									src="/template/pc/images/footer_weibo1.gif"
									onmouseover="this.src='/template/pc/images/footer_weibo2.gif'"
									onmouseout="this.src='/template/pc/images/footer_weibo1.gif'" />
							</a>
						</li>
					</dt>
					<dl>
			</td>
		</tr>
	</table>
</div>

<a href="#0" class="cd-top">Top</a>
<style type="text/css">
.ind_help ul li h2{ height:25px;} 
.ind_help ul li a{ height:25px;}
.ind_help ul li h3{ height:25px;}


.cd-top {
  display: inline-block;
  height: 40px;
  width: 40px;
  position: fixed;
  bottom: 40px;
  right: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
  /* image replacement properties */
  overflow: hidden;
  text-indent: 100%;
  white-space: nowrap;
  background: rgba(243, 181, 18, 0.8) url(http://www.kmcsia.com/template/pc/images/cd-top-arrow.svg) no-repeat center 50%;
  visibility: hidden;
  opacity: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  transition: all 0.3s;
}
.cd-top.cd-is-visible {
  /* the button becomes visible */
  visibility: visible;
  opacity: 1;
}
.cd-top.cd-fade-out {
  /* 如果用户继续向下滚动,这个按钮的透明度会变得更低 */
  opacity: .5;
}
.no-touch .cd-top:hover {
  background-color: #e86256;
  opacity: 1;
}
@media only screen and (min-width: 768px) {
  .cd-top {
    right: 20px;
    bottom: 20px;
  }
}
@media only screen and (min-width: 1024px) {
  .cd-top {
    height: 60px;
    width: 60px;
    right: 30px;
    bottom: 30px;
  }
}


</style>
<script>
jQuery(document).ready(function($){
	// browser window scroll (in pixels) after which the "back to top" link is shown
	var offset = 300,
		//browser window scroll (in pixels) after which the "back to top" link opacity is reduced
		offset_opacity = 1200,
		//duration of the top scrolling animation (in ms)
		scroll_top_duration = 700,
		//grab the "back to top" link
		$back_to_top = $('.cd-top');

	//hide or show the "back to top" link
	$(window).scroll(function(){
		( $(this).scrollTop() > offset ) ? $back_to_top.addClass('cd-is-visible') : $back_to_top.removeClass('cd-is-visible cd-fade-out');
		if( $(this).scrollTop() > offset_opacity ) { 
			$back_to_top.addClass('cd-fade-out');
		}
	});
	//www.sucaijiayuan.com
	//smooth scroll to top
	$back_to_top.on('click', function(event){
		event.preventDefault();
		$('body,html').animate({
			scrollTop: 0 ,
		 	}, scroll_top_duration
		);
	});

});
</script>