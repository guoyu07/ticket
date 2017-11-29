<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<title>${newsClass.name } ${systemConfig.name }</title>
	<meta property="qc:admins" content="24774714445651671163757112072457477166134176" />
	<meta property="qc:admins" content="24774714445331163757112072457477166134176" />
	<meta property="wb:webmaster" content="6c008415a8c940a5" />
	<meta charset="utf-8" />
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="viewport" content="target-densitydpi=device-dpi, width=640px,initial-scale=0.5625, minimum-scale=0.5625,  user-scalable=no">
	<link rel="shortcut icon" href="/manager/images/logo.ico" type="images/x-icon" />
	<link rel="stylesheet" href="/template/wap/css/swiper.min.css">
	<link rel="stylesheet" href="/template/wap/css/animate.min.css">
	<link rel="stylesheet" href="/template/wap/css/base.css">
	<link rel="stylesheet" href="/template/wap/css/style.css">
	<link rel="stylesheet" type="text/css" href="/template/wap/css/list.css"/>
	
	<script type="text/javascript">
		var actionName = '${actionName}';
		var noDataMessage = '${noDataMessage}';
		var pageSize = ${pageSize == null ? 0 : pageSize};
		var path = '<%=path%>';
		var basePath = '<%=basePath%>';
	</script>
	<script>
		var _hmt = _hmt || [];
		(function() {
		  var hm = document.createElement("script");
		  hm.src = "https://hm.baidu.com/hm.js?98ae83c7fa576385ac8e22f5d90b07b8";
		  var s = document.getElementsByTagName("script")[0]; 
		  s.parentNode.insertBefore(hm, s);
		})();
	</script>
	
	<script src="/template/wap/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="/template/wap/js/libraries.min.js"></script>
	<script type="text/javascript" src="/template/wap/js/tool.js"></script>
	<script type="text/javascript" src="/template/wap/js/iscroll.js"></script>
	<script type="text/javascript" src="/template/wap/js/swiper.min.js"></script>
	<script type="text/javascript" src="/template/wap/js/script.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Uf92Iz3LNvk94P4Htw2Wk5XdMR8REx2K"></script>
	<script type="text/javascript" src="/template/wap/js/jquery.cxselect.min.js"></script>
	
	<!-- 分享弹窗需要的js -->
	<script type="text/javascript" src="/template/wap/js/jquery.Gu.js"></script>
	<!-- DatePicker -->
	<script type="text/javascript" src="/common/datePicker/WdatePicker.js" ></script>
	<!-- ColorPicker -->
	<script type="text/javascript" src="/common/iColorPicker/iColorPicker.js" ></script>
	
	<!-- jquery validForm -->
	<script src="/common/validForm/Validform_v5.3.2_min.js"></script>
	
	<!-- cookie -->
	<script src="/common/jquery.cookie.js"></script>
	
	<!-- Easy Dialog -->
	<link rel="stylesheet" href="/common/easydialog/easydialog.css" />
	<script src="/common/easydialog/easydialog.min.js"></script>
	
	<!-- 百度直达号 -->
	<script type="text/javascript" name="baidu-tc-cerfication" data-appid="7728413" src="http://apps.bdimg.com/cloudaapi/lightapp.js"></script>
	
	<script type="text/javascript" src="/common/TuYou.js"></script>
	<script type="text/javascript" src="/common/JM.js"></script>
	<script type="text/javascript" src="/common/XW.js"></script>
	<script type="text/javascript" src="/template/wap/js/wap.js"></script>

</head>
