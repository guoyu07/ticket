<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta name="renderer" content="ie-comp"/>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="Build" content="2015-12" />
<meta name="Designer" content="QQ53477638" />

<link rel="shortcut icon" href="/manager/images/logo.ico" type="images/x-icon" />

<link rel="stylesheet" href="/template/pc/css/StyleSheet.css">

<script type="text/javascript" src="/template/pc/js/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="/common/JM.js"></script>
<script type="text/javascript" src="/template/pc/js/pc.js"></script>
<script type="text/javascript" src="/template/pc/js/script.js"></script>
<script type="text/javascript" src="/template/pc/js/png.js"></script>
<script type="text/javascript">
	EvPNG.fix('img,ol,div,a,button');
	function MM_jumpMenu(targ,selObj,restore){eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
	if (restore) selObj.selectedIndex=0;}
</script>
<script type="text/javascript" src="/template/pc/js/user.js"></script>
<script type="text/javascript" src="/template/pc/js/right_menu.js"></script>
<script type="text/javascript" src="/template/pc/js/door.js"></script>
<script type="text/javascript" src="/template/pc/js/ppp.js"></script>
<script type="text/javascript" src="/template/pc/js/pcBjdjMember.js"></script>
<script type="text/javascript" src="/template/pc/js/logouts.js"></script>
<script type="text/javascript" src="/common/TuYou.js"></script>
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

<!-- Easy Dialog -->

<script src="/common/easydialog/easydialog.min.js"></script>

<!-- jquery validForm -->
<script src="/common/validForm/Validform_v5.3.2_min.js"></script>

<!-- WdatePicker -->
<script type="text/javascript" src="/common/datePicker/WdatePicker.js" ></script>

<!-- 百度直达号 -->
<script type="text/javascript" name="baidu-tc-cerfication" data-appid="7807379" src="http://apps.bdimg.com/cloudaapi/lightapp.js"></script>
		<title>${news.title} - 云南省昆明市长水机场</title>
	</head>
	<body>
		<%@ include file="common/top.jsp" %>

		<%@ include file="common/nav.jsp" %>

		<%@ include file="common/search.jsp" %>

		<div class="banner_25"></div banner>
		<div class="place w980 mt30">
			当前位置:
			<a href="/airportPc.action">首页</a> 
			<a href="javascript:;">商家详细</a>
		</div place w980 mt30>

		
		<div class="other_article_article_22 w980 mt30">
			<h2>店铺详细</h2>
			<dl>
				<ticket:commonAnnex type="queryAnnexList"
											entityUuid="${businessInfo.id}" annexType="1" size="1" />
				<dd class="bhh"><img src="${queryAnnexList[0].annexPath}" width="230" height="230" /></dd>
				<dt class="bhh">
					<h3>${businessInfo.name}</h3><br />
					<p>主营：${businessInfo.mainSale}</p>
					<p>营业时间：${businessInfo.businessHours }</p>
					<p>电话：${businessInfo.phone }</p>
					<p>地址：${businessInfo.address }</p>
				</dt>
			</dl>
			<div class="article_22">
			<h3>商家简介</h3>
			${businessInfo.introduce }
			</div article_22>
		</div other_article_article_2 w980 mt30>
			<%@ include file="common/left.jsp" %>
		<%@ include file="common/bottom.jsp" %>
	</body>
</html>
