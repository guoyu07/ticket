<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/setout/daoda.js"></script>
<title>到达 - 云南省昆明市长水机场</title>
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
	<s:if test="#sessionMember != null">
	<%@ include file="../common/personalData.jsp"%>
	</s:if>

	<div class="w980">
    <div class="L bhh">
        <ul class="l_menu">
            <li class='m1'><a href="/pcMembers_domesticGoNoFlightInfo.action"><i></i></a></li>
            <li class='m2'><a href="javascript:;"><i></i></a></li>
            <li class='m3'><a href="/pcMembers_domesticToDomesticHaveNo.action"><i></i></a></li>
        </ul>
        <form action="/airportPc_flightQuery.action" method="post" class="l_search">
            <div class="tit_tabs"><a href="javascript:;" class="selected" id="chufa">出发</a><a href="javascript:;" id="daoda">到达</a></div>
            <input type="text" placeholder='请输入客机航班号或城市' name="keyword"/>
            <input type="hidden" name="isChufa" value="0" id="isChufa"/>
            <button type="submit">查询</button>
        </form>
    </div L bhh>
    
    <div class="cfzz hh">
        <div class="two_tabs clearfix">
            <a href="javascript:void(0);" class="selected" id="one" value="国内出发">国内到达</a>
            <a href="javascript:void(0);" id="two" value="国际出发">国际到达</a>
        </div>
        <div class="con_box">
            <div class="con_checkobx">
                <p><input type="checkbox" name="" value="" id="hava">有托运行李</p>
            </div>
            <div class="c_img text-center mr40" id="01">
                <img src="/template/pc/images/area/pic13.png" width="504" height="600" usemap="#m_pic" alt="" />
                <map name="m_pic" id="m_pic">
                    <area shape="rect" coords="172,316,324,462" href="/airportPc_trafficGuide.action" alt="" />
                    <area shape="rect" coords="172,120,324,268" href="<ticket:common type="positionUrl" value="daodajichang_daoda_guonei"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="02" style="display: none;">
            	<img src="/template/pc/images/area/pic12.png" width="496" height="612" usemap="#m_pic2" alt="" />
                <map name="m_pic2" id="m_pic2">
                    <area shape="rect" coords="176,432,324,576" href="<ticket:common type="positionUrl" value="jichangjiaotong_daoda_guonei"/>" alt="" />
                    <area shape="rect" coords="176,231,347,385" href="<ticket:common type="positionUrl" value="xinglizhuanpan_daoda_guonei"/>" alt="" />
                    <area shape="rect" coords="176,45,320,193" href="<ticket:common type="positionUrl" value="daodajichang_daoda_guonei"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="03" style="display: none;">
            	<img src="/template/pc/images/area/pic15.png" width="493" height="624" usemap="#m_pic3" alt="" />
                <map name="m_pic3" id="m_pic3">
                    <area shape="rect" coords="297,511,403,624" href="javascript:;" alt="" />
                    <area shape="rect" coords="91,511,223,624" href="javascript:;" alt="" />
                    <area shape="rect" coords="91,387,223,497" href="javascript:;" alt="" />
                    <area shape="rect" coords="91,262,223,376" href="javascript:;" alt="" />
                    <area shape="rect" coords="91,135,223,252" href="javascript:;" alt="" />
                    <area shape="rect" coords="91,19,206,125" href="javascript:;" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="04" style="display: none;">
            	<img src="/template/pc/images/area/pic14.png" usemap="#m_pic4" alt="" />
                <map name="m_pic4" id="m_pic4">
                    <area shape="rect" coords="202,245,330,356" href="javascript:;" alt="" />
                    <area shape="rect" coords="203,363,331,474" href="javascript:;" alt="" />
                    <area shape="rect" coords="201,486,329,597" href="javascript:;" alt="" />
                    <area shape="rect" coords="2,484,130,595" href="javascript:;" alt="" />
                    <area shape="rect" coords="0,357,128,468" href="javascript:;" alt="" />
                    <area shape="rect" coords="198,126,326,237" href="javascript:;" alt="" />
                    <area shape="rect" coords="-3,117,125,228" href="javascript:;" alt="" />
                    <area shape="rect" coords="-4,0,124,111" href="javascript:;" alt="" />
                </map>
            </div>
        </div>
    </div>
</div w980>
	<%@ include file="../common/left.jsp" %>
	<%@ include file="../common/bottom.jsp"%>
	<script type="text/javascript">
    $(function(){
        $('.pic1 area').eq(0).on('click',function(){
            $('.pic2').show();
            $('.pic1').hide();
        });
        $('.pic2 area').eq(0).on('click',function(){
            $('.pic1').show();
            $('.pic2').hide();
        })
    });
</script>
</body>
</html>
