<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta name="renderer" content="ie-comp">
			<meta http-equiv="X-UA-Compatible" content="IE=7" />
			<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
			<title>机票验证 - 云南省昆明市长水机场</title>
			<meta name="Build" content="2015-12" />
			<meta name="Designer" content="QQ53477638" />
			<link rel="stylesheet" media="screen" type="text/css"
				href="StyleSheet.css" />
			<!-- png -->
			<script language=JavaScript src="js/png.js"></script>
			<script type="text/javascript">
		EvPNG.fix('img,ol,div,a,button');
		function MM_jumpMenu(targ,selObj,restore){eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
		if (restore) selObj.selectedIndex=0;}
	</script>
			<!-- /png-->
			<!-- user -->
			<script language=JavaScript src="js/user.js"></script>
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
			<!-- /user-->
			<script type="text/javascript" src="js/right_menu.js"></script>
			<script type="text/javascript">
	var Menu;
	window.onload = function() {
		Menu = new MyMenu("Menu");
		Menu.init();
	};
</script>
			<script type="text/javascript" src="js/door.js"></script>
	</head>




	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>
		<div class="banner_11"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a>
			<a href="#">在线购票</a>
		</div place w980 mt30>




		<div class="w980 mt30">
			<div class="JPYZ3_article other_L bhh">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td width="320">
							<ul id="tb12_">
								<li id="tb12_1" class="hovertab_12" onclick="x:HoverLi12(1);">
									<a href="javascript:void(0);">按航班号</a>
								</li>
								<li id="tb12_2" class="normaltab_12" onclick="x:HoverLi12(2);">
									<a href="javascript:void(0);">按城市名</a>
								</li>
							</ul>
						</td>
						<td>
							<span><ol></ol>
								<select name="">
									<option value="选择日期">
										选择日期
									</option>
									<option value="选择日期">
										选择日期
									</option>
								</select>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<p class="dis" id="tbc12_01">
								<input name="" type="text" style="width: 490px;" value="请输入航班号" />
								<a href="javascript:;"><img src="images/FAQ_search.gif"
										align="absmiddle" />
								</a>
							</p>
							<p class="undis" id="tbc12_02">
								<input name="" type="text" style="width: 490px;" value="请输入城市名" />
								<a href="javascript:;"><img src="images/FAQ_search.gif"
										align="absmiddle" />
								</a>
							</p>
						</td>
					</tr>
				</table>


			<%@ include file="common/FAQButton.jsp" %>
			</div JPYZ3_article other_L bhh>

			<%@ include file="common/rightMenu.jsp" %>
		</div w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
