<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
		EvPNG.fix('img,ol,div,a,button');
		function MM_jumpMenu(targ,selObj,restore){eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
		if (restore) selObj.selectedIndex=0;}
	</script>
	<script type="text/javascript">
$(function(){
		$("li.mainmenu").hover(function(){
			$(this).find("dt").stop(true,true);
			$(this).find("dt").slideDown();
		},function(){
			$(this).find("dt").stop(true,true);
			$(this).find("dt").slideUp();
		});
		
	})
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
</script>
<script type="text/javascript">
	var Menu;
	window.onload = function() {
		Menu = new MyMenu("Menu");
		Menu.init();
	};
</script>
<div id="Menu" class="MyMenu hh">
	<h2>
		旅客服务
	</h2>
	<div class="collapsed">
		<ticket:common type="newsClassChilds" value="jichangfuwu"/>
		<span><a href="javascript:void(0);">${newsClassObj.name }</a>
		</span>
		<ticket:systemCommon type="getNewsClassObj" value="wenxuntai" />
		<p>
			<a href="/airport/newsList/${getNewsClassObj.id}.html">问询台</a>
		</p>
		<p><a href="/airportPc_serviceHotLine.action">服务热线</a></p>
		<p><a href="/airport/newsView/1446185801121.html">遗失物品查询</a></p>
		<p><a href="/airport/newsView/1446185280043.html">广播寻人</a></p>
		<p><a href="/pcOrder.action">便捷登机</a></p>
		<p><a href="/pcElectromobile.action">电瓶车服务</a></p>
		<p><a href="/airport/newsView/1446185885902.html">医疗健康</a></p>
		<p><a href="/airport/newsView/1446175448090.html">行李打包</a></p>
		<p><a href="/airport/newsView/1446175628277.html">行李寄存</a></p>
		
		<p><a href="/airport/newsList/d1a50bc2-a83a-41a4-adfb-b69c40ec4ed1.html">行李查询</a></p>
		<p><a href="/airport/newsView/1446185712168.html">临时乘机证明</a></p>
		<p><a href="/airport/newsView/1448099906050.html">货币兑换</a></p>
		<!-- <p><a href="/airport/newsView/1446183862684.html">中国邮政</a></p> -->
		<!-- <p><a href="/airport/newsView/1446211508168.html">保险服务</a></p> -->
		<p><a href="/airport/newsList/3fde1566-99f7-47d7-99f9-7ef5912bc304.html">商务中心</a></p>
		<p><a href="/airport/newsView/1446172391293.html">头等舱服务</a></p>
		<!-- <p><a href="/airport/newsView/1447050835041.html">百度翻译</a></p> -->
		
	</div>
	<div class="collapsed">
		<span><a href="javascript:void(0);">爱心服务</a>
		</span>
		<p><a href="/airport/newsView/1453864857457.html">爱心人士候机区</a></p>
		<p><a href="/airport/newsView/159ffc61-7b73-43b4-994e-85448da8dc41.html">母婴候机区</a></p>
		<p><a href="/airport/newsView/402fb597-d437-41c0-8486-081c0169f9a1.html">母婴室</a></p>
		<p><a href="/airport/newsView/1446436247822.html">无障碍卫生间</a></p>
		<p><a href="/airport/newsView/1445676839067.html">轮椅服务</a></p>
		<p><a href="/airport/newsView/1446173505027.html">儿童及婴儿手推车</a></p>
	</div>
	<div class="collapsed">
		<span><a href="javascript:void(0);">机场设施</a>
		</span>
		<p><a href="/airport/newsView/1446106183074.html">卫生间</a></p>
		<p><a href="/airport/newsView/1446175364027.html">吸烟室</a></p>
		<!-- <p><a href="/airport/newsView/1446175591418.html">祈祷室</a></p> -->
		<p><a href="/airport/newsView/1446175886277.html">更衣室</a></p>
		<p><a href="/airport/newsView/1453778265833.html">儿童娱乐区</a></p>
		<p><a href="/airport/newsView/1446176429246.html">公用电话</a></p>
		<p><a href="/airport/newsView/1446176545699.html">自动售货机</a></p>
		<p><a href="/airport/newsView/1446176637605.html">自助充电站</a></p>
		<p><a href="/airport/newsView/1446176733699.html">饮水机</a></p>
		<p><a href="/airport/newsView/1446176842684.html">手推车</a></p>
		<p><a href="/airport/newsView/1446176987074.html">WIFI取号机</a></p>
		<!-- <p><a href="/airport/newsView/1446177444418.html">城市候机楼</a></p> -->
		<p><a href="/airport/newsView/1446211475980.html">自助存取款机(ATM)</a></p>
		<p><a href="/airport/newsView/1446185024277.html">落地签自拍系统</a></p>
	</div>
	<div class="collapsed">
		<span><a href="/airportPc_airportFAQ.action">长水百问</a>
		</span>
	</div>
	<div class="collapsed">
		<span><a href="javascript:void(0);">乘机须知</a>
		</span>
		<ticket:systemCommon type="getNewsClassObj" value="pcchengjixuzhi" />
		<%-- <ticket:common type="newsClassChilds" value="pcchengjixuzhi"/>
		<s:if test="#request.newsClassChilds != null && #request.newsClassChilds.size > 0">
		<s:iterator id="n" value="#request.newsClassChilds">
		<p><a href="/airport/newsList/${n.id}.html">${n.name}</a></p>
		</s:iterator>
		</s:if> --%>
		<ticket:common type="newsList" value="${getNewsClassObj.id }" size="10000"/>
		<s:if test="#request.newsList != null">
		<s:iterator id="newsObj" value="#request.newsList">
			<s:if test="#newsObj.newsClass_id == #request.getNewsClassObj.id">
			<p><a href="/airport/newsView/${newsObj.status.visitUrl }.html">${newsObj.title }</a></p>
			</s:if>
		</s:iterator>
		</s:if>
	</div>
	<div class="collapsed">
		<span><a href="/airport/newsList/95ddbb7c-a1b5-45a6-9e88-8085b2f80231.html">旅游咨询</a>
		</span>
	</div>
</div MyMenu hh>
