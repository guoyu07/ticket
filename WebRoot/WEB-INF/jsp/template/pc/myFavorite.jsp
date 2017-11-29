<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<script type="text/javascript">
$(function(){
	$(".toFavoritePage").bind('click',function(){
		var newsClassAlias=$(this).attr("newsClassAlias");
		var visitUrl = $(this).attr("visitUrl");
		var pcUrl=$(this).attr("pcUrl");
		if(!JM.isNull(newsClassAlias)){
			window.location.href="/airport/"+newsClassAlias+".ticket";
			return;
		}
		if(!JM.isNull(visitUrl)){
			window.location.href="/airport/"+visitUrl+".ticket";
			return;
		}else{
			window.location.href=pcUrl;
		}
	});

});
</script>
<title>我的收藏 - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh">
				<p>站内公告：</p>
			</li>
			<li id="rollTextMenu1" style="display: block">
				长水机场平台正式上线，欢迎各位用户监督指导1</li>
			<li id="rollTextMenu2" style="display: none">
				长水机场平台正式上线，欢迎各位用户监督指导2</li>
			<li id="rollTextMenu3" style="display: none">
				长水机场平台正式上线，欢迎各位用户监督指导3</li>
		</ul>

		<dl class="hh">
			<dd class="bhh">
				<a href="javascript:rollText(-1);"><img
					src="/template/pc/images/pre1.gif"
					onmouseover="this.src='/template/pc/images/pre2.gif'"
					onmouseout="this.src='/template/pc/images/pre1.gif'">
				</a>
			</dd>
			<dd class="bhh">
				<a href="javascript:rollText(1);"><img
					src="/template/pc/images/next1.gif"
					onmouseover="this.src='/template/pc/images/next2.gif'"
					onmouseout="this.src='/template/pc/images/next1.gif'">
				</a>
			</dd>
		</dl>
	</div gg w980 mt30>
	<SCRIPT type=text/javascript>
	<!--
		var rollText_k = 3; //菜单总数
		var rollText_i = 1; //菜单默认值
		//setFocus1(0);
		rollText_tt = setInterval("rollText(1)", 8000);
		function rollText(a) {
			clearInterval(rollText_tt);
			rollText_tt = setInterval("rollText(1)", 8000);
			rollText_i += a;
			if (rollText_i > rollText_k) {
				rollText_i = 1;
			}
			if (rollText_i == 0) {
				rollText_i = rollText_k;
			}
			//alert(i)
			for ( var j = 1; j <= rollText_k; j++) {
				document.getElementById("rollTextMenu" + j).style.display = "none";
			}
			document.getElementById("rollTextMenu" + rollText_i).style.display = "block";
		}
	//-->
	</SCRIPT>

	<%@ include file="common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_myMessage.action"><img
					src="/template/pc/images/ico5_1.gif"
					onmouseover="this.src='/template/pc/images/ico5_2.gif'"
					onmouseout="this.src='/template/pc/images/ico5_1.gif'" /> </a>
			</li>
			<li class="bhh"><a href="airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" /> </a>
			</li>
		</ul>
	</div menu1>

	<div class="MemberCenter w980">
		<div class="L bhh">
			<ul>
				<li><a href="pcOrder_allOrder.action"><img
						src="/template/pc/images/button1_1.gif"
						onmouseover="this.src='/template/pc/images/button1_2.gif'"
						onmouseout="this.src='/template/pc/images/button1_1.gif'" /> </a>
				</li>
				<li><a href="/pcMembers_personalSetting.action"><img
						src="/template/pc/images/button2_1.gif"
						onmouseover="this.src='/template/pc/images/button2_2.gif'"
						onmouseout="this.src='/template/pc/images/button2_1.gif'" /> </a>
				</li>
				<li><a href="/pcMembers_myPayInfo.action"><img
						src="/template/pc/images/button3_1.gif"
						onmouseover="this.src='/template/pc/images/button3_2.gif'"
						onmouseout="this.src='/template/pc/images/button3_1.gif'" /> </a>
				</li>
				<li><a href="javascript:void(0);"><img
						src="/template/pc/images/button4_2.gif" /> </a>
				</li>
			</ul>
		</div L bhh>

		<div class="favorites hh">
			<ul>
				<ticket:common type="memberFavoriteList" />
				<s:if test="#request.memberFavoriteList != null">
					<s:iterator id="memberFavorite" value="#request.memberFavoriteList">
						<s:if test="#memberFavorite.objectType == 'NewsClass'">
							<ticket:common type="newsClassObj"
								value="${memberFavorite.objectId }" />
							<li><a href="javascript:;"><p></p> </a> <a href="javascript:;">${newsClassObj.name
									}</a> <a href="javascript:unFavorite('${memberFavorite.id }')"
								class='fz20 float-right'><i class='icon-star c_blue'></i>&nbsp;&nbsp;已收藏</a>
							</li>
						</s:if>
						<s:elseif test="#memberFavorite.objectType == 'News'">
							<ticket:common type="newsCommonObj"
								value="${memberFavorite.objectId }" />
							<li> <a href="javascript:void(0);" class="toFavoritePage" url="${memberFavorite.url }">${newsClassObj.name
									}</a> <a href="javascript:unFavorite('${memberFavorite.id }')"
								class='fz20 float-right'><i class='icon-star c_blue'></i>&nbsp;&nbsp;已收藏</a>
							</li>
						</s:elseif>
						<s:else>
							<li><span class="toFavoritePage" pcUrl="${memberFavorite.url }">${memberFavorite.title }</span> 
							<a href="javascript:unFavorite('${memberFavorite.id }')"
								class='fz20 float-right'><i class='icon-star c_blue'></i>&nbsp;&nbsp;已收藏</a>
							</li>
						</s:else>
					</s:iterator>
				</s:if>
			</ul>
		</div favorites hh>
	</div MemberCenter w980>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>

