<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#hava").change(hava);
	});
	function hava(){
		var checkit = $("#hava").attr("check");
		if(checkit == undefined || checkit == "false"){
		JM.goUrl("pcMembers_domesticArriveHave.action");
		}
	}
</script>
<title>出发 - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh"><p>站内公告：</p>
			</li>
			<li id="rollTextMenu1" style="display:block">长水机场平台正式上线，欢迎各位用户监督指导1</li>
			<li id="rollTextMenu2" style="display:none">长水机场平台正式上线，欢迎各位用户监督指导2</li>
			<li id="rollTextMenu3" style="display:none">长水机场平台正式上线，欢迎各位用户监督指导3</li>
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
			var rollText_k=3; //菜单总数
			var rollText_i=1; //菜单默认值
			//setFocus1(0);
			rollText_tt=setInterval("rollText(1)",8000);
			function rollText(a){
			clearInterval(rollText_tt);
			rollText_tt=setInterval("rollText(1)",8000);
			rollText_i+=a;
			if (rollText_i>rollText_k){rollText_i=1;}
			if (rollText_i==0){rollText_i=rollText_k;}
			//alert(i)
			 for (var j=1; j<=rollText_k; j++){
			 document.getElementById("rollTextMenu"+j).style.display="none";
			 }
			 document.getElementById("rollTextMenu"+rollText_i).style.display="block";
			} 
			//-->
		</SCRIPT>

	<%@ include file="../common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="/airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_myMessage.action"><img
					src="/template/pc/images/ico5_1.gif"
					onmouseover="this.src='/template/pc/images/ico5_2.gif'"
					onmouseout="this.src='/template/pc/images/ico5_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" />
			</a>
			</li>
		</ul>
	</div menu1>

	<div class="w980">
    <div class="L bhh">
        <ul>
            <li><a href="pcOrder_allOrder.action"><img src="/template/pc/images/button1_1.gif" /></a></li>
            <li><a href="pcMembers_personalSetting.actionl"><img src="/template/pc/images/button2_1.gif" onmouseover="this.src='/template/pc/images/button2_2.gif'" onmouseout="this.src='/template/pc/images/button2_1.gif'" /></a></li>
            <li><a href="pcMembers_myPayInfo.action"><img src="/template/pc/images/button3_1.gif" onmouseover="this.src='/template/pc/images/button3_2.gif'" onmouseout="this.src='/template/pc/images/button3_1.gif'" /></a></li>
            <li><a href="airportPc_myFavorite.action"><img src="/template/pc/images/button4_1.gif" onmouseover="this.src='/template/pc/images/button4_2.gif'" onmouseout="this.src='/template/pc/images/button4_1.gif'" /></a></li>
        </ul>
    </div L bhh>
    
   <div class="cfzz hh">
        <div class="two_tabs clearfix">
            <a href="" class="selected">国内出发</a>
            <a href="">国际出发</a>
        </div>
        <div class="con_box">
            <div class="con_checkobx">
                <p><input type="checkbox" name="" value="">已在线直接</p>
                <p><input type="checkbox" name="" value="">需要托运行李</p>
            </div>
            <div class="c_img text-center mr40">
                <img src="/template/pc/images/area/pic9.png" usemap="#m_pic" alt="" />
                <map name="m_pic" id="m_pic">
                    <area shape="rect" coords="1,1,129,112" href="javascript:;" alt="" />
                    <area shape="rect" coords="-1,121,127,232" href="javascript:;" alt="" />
                    <area shape="rect" coords="0,246,128,357" href="javascript:;" alt="" />
                    <area shape="rect" coords="-1,367,127,478" href="javascript:;" alt="" />
                    <area shape="rect" coords="-1,493,127,604" href="javascript:;" alt="" />
                    <area shape="rect" coords="201,492,329,603" href="javascript:;" alt="" />
                    <area shape="rect" coords="201,250,329,361" href="javascript:;" alt="" />
                    <area shape="rect" coords="203,373,331,484" href="javascript:;" alt="" />
                    <area shape="rect" coords="198,120,330,240" href="javascript:;" />
                </map>
            </div>
        </div>
    </div>
</div w980>
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>
