<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/setout/chufa.js"></script>
<title>出发 - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="../common/top.jsp"%>
	<%@ include file="../common/nav.jsp"%>

	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh"><p>站内公告：</p></li>
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
            <li class='m1'><a href="javascript:;"><i></i></a></li>
            <li class='m2'><a href="/pcMembers_domesticArriveHaveNo.action"><i></i></a></li>
            <li class='m3'><a href="/pcMembers_domesticToDomesticHaveNo.action"><i></i></a></li>
        </ul>
        <form action="/airportPc_flightQuery.action" method="post" class="l_search">
            <div class="tit_tabs"><a href="javascript:;" class="selected" id="chufa">出发</a><a href="javascript:;" id="daoda">到达</a></div>
            <input type="text" placeholder='请输入客机航班号或城市' name="keyWord"/>
            <input type="hidden" name="isChufa" value="1" id="isChufa"/>
            <button type="submit">查询</button>
        </form>
    </div L bhh>

		<div class="cfzz hh">
			<div class="two_tabs clearfix">
				<a href="javascript:;" class="selected" id="one">国内出发</a> <a
					href="javascript:;" id="two">国际出发</a>
			</div>
			<div class="con_box">
				<div class="con_checkobx">
					<p>
						<input type="checkbox" name="" value="" id="zhiji">已在线值机
					</p>
					<p>
						<input type="checkbox" name="" value="" id="have">需要托运行李
					</p>
				</div>
				<div class="c_img text-center mr40" id="a01">
					<img src="/template/pc/images/area/pic1.png" width="545"
						height="639" usemap="#m_pic" alt="" />
					<map name="m_pic" id="m_pic">
						<area shape="rect" coords="193,492,375,616"
							href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="193,324,375,474"
							href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="309,195,432,288"
							href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="144,195,273,288"
							href="<ticket:common type="positionUrl" value="zizhuzhiji_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="193,0,375,158"
							href="<ticket:common type="positionUrl" value="chufadating"/>"
							alt="" />
					</map>
				</div>
				<div class="c_img text-center mr40" id="a02" style="display: none;">
					<img src="/template/pc/images/area/pic1.png" usemap="#m_pic2"
						alt="" />
					<map name="m_pic2" id="m_pic2">
						<area shape="rect" coords="193,492,375,616"
							href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="193,324,375,474"
							href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="309,195,432,288"
							href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="144,195,273,288"
							href="<ticket:common type="positionUrl" value="zizhuzhiji_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="193,0,375,158"
							href="<ticket:common type="positionUrl" value="chufadating"/>"
							alt="" />
					</map>
				</div>
				<div class="c_img text-center mr40" id="a03" style="display: none;">
					<img src="/template/pc/images/area/pic6.png" width="497"
						height="604" usemap="#m_pic3" alt="" />
					<map name="m_pic3" id="m_pic3">
						<area shape="rect" coords="171,410,348,569"
							href="<ticket:common type="positionUrl" value="chufadating"/>"
							alt="" />
						<area shape="rect" coords="171,216,348,378"
							href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="171,36,348,186"
							href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
							alt="" />
					</map>
				</div>
				<div class="c_img text-center mr40" id="a04" style="display: none;">
					<img src="/template/pc/images/area/pic7.png" width="496"
						height="604" usemap="#m_pic4" alt="" />
					<map name="m_pic4" id="m_pic4">
						<area shape="rect" coords="180,465,341,604"
							href="<ticket:common type="positionUrl" value="chufadating"/>"
							alt="" />
						<area shape="rect" coords="180,317,341,450"
							href="<ticket:common type="positionUrl" value="xinglituoyun_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="180,165,341,302"
							href="<ticket:common type="positionUrl" value="anjian_chufa_guonei"/>"
							alt="" />
						<area shape="rect" coords="180,9,341,152"
							href="<ticket:common type="positionUrl" value="dengjikou_chufa_guonei"/>"
							alt="" />
					</map>
				</div>
				<div class="c_img text-center mr40" id="a05" style="display: none;">
					<div class="pic1 padding-big">
						<img src="/template/pc/images/area/pic2_1.png" usemap="#m_pic5"
							alt="" />
						<map name="m_pic5" id="m_pic5">
							<area shape="rect" coords="272,389,388,489"
								href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="272,260,388,360"
								href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="271,130,387,233"
								href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="2,410,122,510"
								href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="2,290,131,398"
								href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="29,177,70,213" href="#" alt="" />
							<area shape="rect" coords="0,0,130,108"
								href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
								alt="" />
						</map>
					</div>
					<div class="pic2 padding-big" style='display:none'>
						<img src="/template/pc/images/area/pic2.png" usemap="#m_pic6"
							alt="" />
						<map name="m_pic6" id="m_pic6">
							<area shape="rect" coords="329,129,460,232"
								href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="329,260,460,368"
								href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="328,389,459,490"
								href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="329,522,460,620"
								href="<ticket:common type="positionUrl" value="haiguan_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="63,522,194,620"
								href="<ticket:common type="positionUrl" value="jianyanjianyi_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="62,412,193,513"
								href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="67,306,196,401"
								href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="224,184,260,220" href="#" alt="" />
							<area shape="rect" coords="86,205,175,287"
								href="<ticket:common type="positionUrl" value="haiguanshenbao_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="86,114,175,192"
								href="<ticket:common type="positionUrl" value="jianyanjianyishenbao_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="61,1,186,103"
								href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
								alt="" />
						</map>
					</div>
				</div>
				<div class="c_img text-center mr40" id="a06" style="display: none;">
					<img src="/template/pc/images/area/pic2.png" usemap="#m_pic7"
						alt="" />
					<map name="m_pic7" id="m_pic7">
						<area shape="rect" coords="329,129,460,232"
							href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="329,260,460,368"
							href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="328,389,459,490"
							href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="329,522,460,620"
							href="<ticket:common type="positionUrl" value="haiguan_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="63,522,194,620"
							href="<ticket:common type="positionUrl" value="jianyanjianyi_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="62,412,193,513"
							href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="67,306,196,401"
							href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="224,184,260,220" href="#" alt="" />
						<area shape="rect" coords="86,205,175,287"
							href="<ticket:common type="positionUrl" value="haiguanshenbao_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="86,114,175,192"
							href="<ticket:common type="positionUrl" value="jianyanjianyishenbao_chufa_guoji"/>"
							alt="" />
						<area shape="rect" coords="61,1,186,103"
							href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
							alt="" />
					</map>
				</div>
				<div class="c_img text-center mr40" id="a07" style="display: none;">
					<div class="pic01">
						<img src="/template/pc/images/area/pic4_1.png" width="510"
							height="623" usemap="#m_pic8" alt="" />
						<map name="m_pic8" id="m_pic8">
							<area shape="rect" coords="237,186,275,223" href="javascript:;"
								alt="" />
							<area shape="rect" coords="360,520,474,623" href="javascript:;"
								alt="" />
							<area shape="rect" coords="360,391,474,499" href="javascript:;"
								alt="" />
							<area shape="rect" coords="360,252,474,360" href="javascript:;"
								alt="" />
							<area shape="rect" coords="80,527,198,623" href="javascript:;"
								alt="" />
							<area shape="rect" coords="80,412,198,514" href="javascript:;"
								alt="" />
							<area shape="rect" coords="80,301,198,406" href="javascript:;"
								alt="" />
							<area shape="rect" coords="94,207,188,288" href="javascript:;"
								alt="" />
							<area shape="rect" coords="94,115,188,199" href="javascript:;"
								alt="" />
							<area shape="rect" coords="74,0,198,108" href="javascript:;"
								alt="" />
						</map>
					</div>
					<div class="pic02" style='display:none'>
						<img src="/template/pc/images/area/pic4.png" width="510"
							height="623" usemap="#m_pic9" alt="" />
						<map name="m_pic9" id="m_pic9">
							<area shape="rect" coords="237,186,275,223" href="javascript:;"
								alt="" />
							<area shape="rect" coords="360,520,474,623" href="javascript:;"
								alt="" />
							<area shape="rect" coords="360,391,474,499" href="javascript:;"
								alt="" />
							<area shape="rect" coords="360,252,474,360" href="javascript:;"
								alt="" />
							<area shape="rect" coords="80,527,198,623" href="javascript:;"
								alt="" />
							<area shape="rect" coords="80,412,198,514" href="javascript:;"
								alt="" />
							<area shape="rect" coords="80,301,198,406" href="javascript:;"
								alt="" />
							<area shape="rect" coords="94,207,188,288" href="javascript:;"
								alt="" />
							<area shape="rect" coords="94,115,188,199" href="javascript:;"
								alt="" />
							<area shape="rect" coords="74,0,198,108" href="javascript:;"
								alt="" />
						</map>
					</div>
				</div>
				<div class="c_img text-center mr40" id="a08" style="display: none;">
					<div class="pic001">
						<img src="/template/pc/images/area/pic16_1.png" width="498"
							height="612" usemap="#m_pic10" alt="" />
						<map name="m_pic10" id="m_pic10">
							<area shape="rect" coords="107,188,143,222"
								href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="353,126,469,234"
								href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="353,260,469,368"
								href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="353,390,469,498"
								href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="76,410,192,518"
								href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="76,300,192,408" href="javascript:;"
								alt="" />
							<area shape="rect" coords="76,0,192,108"
								href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
								alt="" />
						</map>
					</div>
					<div class="pic002" style='display:none'>
						<img src="/template/pc/images/area/pic16.png" width="519"
							height="627" usemap="#m_pic11" alt="" />
						<map name="m_pic11" id="m_pic11">
							<area shape="rect" coords="244,180,288,223"
								href="<ticket:common type="positionUrl" value="dengji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="367,517,483,627"
								href="<ticket:common type="positionUrl" value="anquanjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="367,387,483,495"
								href="<ticket:common type="positionUrl" value="bianfangjiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="367,258,483,360"
								href="<ticket:common type="positionUrl" value="haiguan_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="367,128,483,230"
								href="<ticket:common type="positionUrl" value="jianyanjianyi_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="86,525,216,627"
								href="<ticket:common type="positionUrl" value="dengjipaijiancha_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="86,413,216,517"
								href="<ticket:common type="positionUrl" value="rengongzhiji_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="86,304,216,402" href="javascript:;"
								alt="" />
							<area shape="rect" coords="103,198,197,289"
								href="<ticket:common type="positionUrl" value="haiguanshenbao_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="103,108,197,199"
								href="<ticket:common type="positionUrl" value="jianyanjianyishenbao_chufa_guoji"/>"
								alt="" />
							<area shape="rect" coords="86,0,208,108"
								href="<ticket:common type="positionUrl" value="jichang_chufa_guoji"/>"
								alt="" />
						</map>
					</div>
				</div>
			</div>
		</div>
	</div w980>
	<%@ include file="../common/left.jsp" %>
	<%@ include file="../common/bottom.jsp"%>
</body>
</html>
