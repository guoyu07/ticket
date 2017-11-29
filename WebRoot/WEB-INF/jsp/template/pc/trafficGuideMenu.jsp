<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<title>旅客指南频道 - 云南省昆明市长水机场</title>
<script type="text/javascript" src="/template/pc/js/trafficGuide.js"></script>
<script type="text/javascript">
	var Menu;
	window.onload = function() {
		Menu = new MyMenu("Menu");
		Menu.init();
	};
</script>
<script type="text/javascript">
	function g(o) {
		return document.getElementById(o);
	}
	function HoverLi10(n) {
		var isize = $("#isize").val();
		for ( var i = 1; i <= isize; i++) {
			g('tb10_' + i).className = 'normaltab_10';
			g('tbc10_0' + i).className = 'undis';
		}
		g('tbc10_0' + n).className = 'dis';
		g('tb10_' + n).className = 'hovertab_10';
	}
	function g(o) {
		return document.getElementById(o);
	}
	function HoverLi15(n) {
		var isize1 = $("#isize1").val();
		for ( var i = 1; i <= isize1; i++) {
			g('tb15_' + i).className = 'normaltab_15';
			g('tbc15_0' + i).className = 'undis';
		}
		g('tbc15_0' + n).className = 'dis';
		g('tb15_' + n).className = 'hovertab_15';
	}
</script>
</head>
<body>
	<%@ include file="common/top.jsp"%>

	<%@ include file="common/nav.jsp"%>

	<%@ include file="common/search.jsp"%>
	<div class="banner_28"></div>
	<div class="place w980 mt30">
		当前位置: <a href="/airportPc.action">首页</a> > <a href="#">旅客指南频道</a>
	</div>
	<div class="lczn w980 mt30">
			<ticket:systemCommon type="getNewsClassObj" value="wenxuntai" />
			<a href="/airport/newsList/${getNewsClassObj.id}.html">
			<dl class="bhh lczn_2"><dd>机场服务</dd><dt>AIRPORT SERVICE</dt></dl>
			</a>
			<ticket:systemCommon type="getNewsClassObj" value="jichangsheshi" />
			<a href="/airport/newsList/${getNewsClassObj.id}.html">
			<dl class="bhh lczn_3"><dd>机场设施</dd><dt>AIRPORT FACILITIES</dt></dl>
			</a>
			<ticket:systemCommon type="getNewsClassObj" value="aixinfuwu" />
			<a href="/airport/newsList/${getNewsClassObj.id}.html">
			<dl class="bhh lczn_5"><dd>爱心服务</dd><dt>CHARITABLE SERVICE</dt></dl>
			</a>
			<a href="/airportPc_airportFAQ.action">
			<dl class="bhh lczn_4"><dd>长水百问</dd><dt>COMMON PROBLEM</dt></dl>
			</a>
			<ticket:systemCommon type="getNewsClassObj" value="pcchengjixuzhi" />
			<a href="/airport/newsList/${getNewsClassObj.id}.html">
			<dl class="bhh lczn_6"><dd>乘机须知</dd><dt>CONVENIENCE SERVICES</dt></dl>
			</a>
			<ticket:systemCommon type="getNewsClassObj" value="lvyouzixun" />
			<a href="/airport/newsList/${getNewsClassObj.id}.html">
			<dl class="bhh lczn_1"><dd>旅游指南</dd><dt>AIRLINE</dt></dl>
			</a>
	</div>
	<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
