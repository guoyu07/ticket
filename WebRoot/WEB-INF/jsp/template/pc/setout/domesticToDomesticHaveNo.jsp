<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/setout/zhongzhuan.js"></script>
<title>中转 - 云南省昆明市长水机场</title>
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
            <li class='m2'><a href="/pcMembers_domesticArriveHaveNo.action"><i></i></a></li>
            <li class='m3'><a href="javascript:;"><i></i></a></li>
        </ul>
        <!-- <form action="" method="get" class="l_search">
            <div class="tit_tabs"><a href="javascript:;" class="selected">出发</a><a href="javascript:;">到达</a></div>
            <input type="text" placeholder='请输入客机航班号或城市'/>
            <button type="submit">查询</button>
        </form> -->
    </div L bhh>
    
   <div class="cfzz hh">
        <div class="two_tabs clearfix">
        	<select id="one">
        		<option value="国内">国内</option>
        		<option value="国际">国际</option>
        	</select>
        	<select id="two">
        		<option value="国内">国内</option>
        		<option value="国际">国际</option>
        	</select>
        </div>
        <div class="con_box">
            <div class="con_checkobx">
                <p><input type="checkbox" name="" value="" id="hava">有托运行李</p>
            </div>
            <div class="c_img text-center mr40" id="b01">
                <img src="/template/pc/images/area/pic17.png" usemap="#m_pic" alt="" />
                <map name="m_pic" id="m_pic">
                    <area shape="rect" coords="-6,432,150,565" href="<ticket:common type="positionUrl" value="dengjikou_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="-13,289,143,424" href="<ticket:common type="positionUrl" value="chayandengjipai_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="-1,144,155,278" href="<ticket:common type="positionUrl" value="banlizhongzhuanwu_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="2,2,158,134" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="b02" style="display: none;">
             	<img src="/template/pc/images/area/pic3.png" usemap="#m_pic2" alt="" />
                <map name="m_pic2" id="m_pic2">
                    <area shape="rect" coords="-8,484,136,606" href="<ticket:common type="positionUrl" value="dengjikou_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="-6,363,138,476" href="<ticket:common type="positionUrl" value="anjian_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="-13,243,131,353" href="<ticket:common type="positionUrl" value="banlizhongzhuanyou_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="0,122,144,239" href="<ticket:common type="positionUrl" value="quxingli_zhongzhuan_gn_gn"/>" alt="" />
                    <area shape="rect" coords="-4,0,140,119" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gn"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="b03" style="display: none;">
             	<img src="/template/pc/images/area/pic18.png" usemap="#m_pic3" alt="" />
                <map name="m_pi3c" id="m_pic3">
                    <area shape="rect" coords="197,492,326,607" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="197,366,326,484" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="197,247,326,355" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="1,486,126,604" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="0,368,125,480" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="3,244,128,362" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="-2,120,131,242" href="<ticket:common type="positionUrl" value="tiquxingli_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="-1,0,132,120" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gn_gj"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="b04" style="display: none;">
             	<img src="/template/pc/images/area/pic8.png" width="498" height="618" usemap="#m_pic4" alt="" />
                <map name="m_pic4" id="m_pic4">
                    <area shape="rect" coords="294,254,422,365" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="294,378,422,489" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="294,500,422,611" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="91,500,219,611" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="91,376,219,487" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="91,253,219,364" href="<ticket:common type="positionUrl" value="dengjipaijiancha_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="91,130,219,241" href="<ticket:common type="positionUrl" value="haiguanshenbao_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="91,0,220,125" href="<ticket:common type="positionUrl" value="banlizhongzhuan_zhongzhuan_gn_gj"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="b05" style="display: none;">
             	<img src="/template/pc/images/area/pic9.png" usemap="#m_pic5" alt="" />
                <map name="m_pic5" id="m_pic5">
                    <area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="-1,121,127,232" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="0,246,128,357" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="-1,367,127,478" href="<ticket:common type="positionUrl" value="xinglitiqu_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="-1,493,127,604" href="<ticket:common type="positionUrl" value="jianyanjianyixingli_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="201,492,329,603" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="201,250,329,361" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="203,373,331,484" href="<ticket:common type="positionUrl" value="zhongzhuanbanli_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="198,120,330,240" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gj_gn"/>" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="b06" style="display: none;">
             	<img src="/template/pc/images/area/pic10.png" usemap="#m_pic6" alt="" />
                <map name="m_pic6" id="m_pic6">
                    <area shape="rect" coords="205,494,333,605" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gn_gj"/>" alt="" />
                    <area shape="rect" coords="1,494,129,605" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="1,370,129,481" href="<ticket:common type="positionUrl" value="denghouxinglichayan_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="0,249,128,360" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="-1,122,127,233" href="<ticket:common type="positionUrl" value="jianyanjianyiren_zhongzhuan_gj_gn"/>" alt="" />
                    <area shape="rect" coords="1,1,129,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gn"/>" alt="" />
                </map>
            </div>
            <div class="c_img text-center mr40" id="b07" style="display: none;">
             	<img src="/template/pc/images/area/pic11.png" usemap="#m_pic7" alt="" />
                <map name="m_pic7" id="m_pic7">
                    <area shape="rect" coords="2,1,130,112" href="<ticket:common type="positionUrl" value="hangbandaoda_zhongzhuan_gj_gj"/>" alt="" />
                    <area shape="rect" coords="2,128,130,239" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gj"/>" alt="" />
                    <area shape="rect" coords="2,243,130,354" href="<ticket:common type="positionUrl" value="bianfangjiancha_zhongzhuan_gj_gj"/>" alt="" />
                    <area shape="rect" coords="3,371,131,482" href="<ticket:common type="positionUrl" value="xinglitiqu_zhongzhuan_gj_gj"/>" alt="" />
                    <area shape="rect" coords="2,494,130,605" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gj"/>" alt="" />
                    <area shape="rect" coords="174,1,306,111" href="<ticket:common type="positionUrl" value=" bianfangjiancha_zhongzhuan_gj_gj"/>" />
                    <area shape="rect" coords="173,122,306,236" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gj"/>" />
                    <area shape="rect" coords="170,246,304,357" href="<ticket:common type="positionUrl" value="jianyanjianyi_zhongzhuan_gj_gj"/>" />
                    <area shape="rect" coords="172,368,306,479" href="<ticket:common type="positionUrl" value="banlizhiji_zhongzhuan_gj_gj"/>" />
                    <area shape="rect" coords="169,489,302,603" href="<ticket:common type="positionUrl" value="haiguanjiancha_zhongzhuan_gj_gj"/>" />
                    <area shape="rect" coords="350,1,477,110" href="<ticket:common type="positionUrl" value="anquanjiancha_zhongzhuan_gj_gj"/>" />
                    <area shape="rect" coords="350,122,476,239" href="<ticket:common type="positionUrl" value="dengji_zhongzhuan_gj_gj"/>" />
                </map>
            </div>
        </div>
    </div>
</div w980>
	<%@ include file="../common/left.jsp" %>
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>
