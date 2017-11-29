<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="/template/pc/css/base.css">
<link rel="stylesheet" href="/template/pc/css/StyleSheet.css">
<link rel="stylesheet" href="/template/pc/css/pintuer.css">
<link rel="stylesheet" href="/template/pc/css/public.css">
<link rel="stylesheet" href="/template/pc/css/subpage.css">

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

<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "https://hm.baidu.com/hm.js?d19a4e62294642b8c11af8a1c64e1608";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
</script>

<!-- Easy Dialog -->
<link rel="stylesheet" href="/common/easydialog/easydialog.css" />
<script src="/common/easydialog/easydialog.min.js"></script>

<!-- jquery validForm -->
<script src="/common/validForm/Validform_v5.3.2_min.js"></script>

<!-- WdatePicker -->
<script type="text/javascript" src="/common/datePicker/WdatePicker.js" ></script>

<!-- 百度直达号 -->
<script type="text/javascript" name="baidu-tc-cerfication" data-appid="7807379" src="http://apps.bdimg.com/cloudaapi/lightapp.js"></script>