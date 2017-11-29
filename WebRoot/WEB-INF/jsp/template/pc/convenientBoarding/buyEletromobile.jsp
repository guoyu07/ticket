<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/template/pc/common/head.jsp"%>
<title>${electromobile }首页 - 云南省昆明市长水机场</title>
<script type="text/javascript"
	src="/template/pc/js/convenientBoarding/eletromobile.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/template/pc/common/top.jsp"%>

	<%@ include file="/WEB-INF/jsp/template/pc/common/nav.jsp"%>


	<div class="place w980 mt30">
		当前位置: <a href="javascript:void(0);">首页</a> > <a href="javascript:void(0);">${electromobile }</a>
	</div place w980 mt30>




	<div class="bjdj_index w980 mt30">
		<ul class="bhh">
			<img src="/template/pc/images/img.jpg" />
		</ul>
		<dl class="hh">
			<dd>
				<h2>${electromobile }</h2>
				快捷乘机，方便无忧
			</dd>
			<dt>
				<b>原价：</b>￥${electromobile_price }<br /> <b>现价：</b><span
					id="realPrice">￥${electromobile_price }</span><br /> <b>数量：</b><input
					type="button" value="-" id="cutSum" /><input type="text" id="num"
					value="1" class="text" /><input type="button" value="+" id="addSum" /><br />
				<!-- <a id="buy"><img src="/template/pc/images/buy.gif" />
				</a> -->
			</dt>
		</dl>
	</div bjdj_index w980 mt30>




	<div class="TiShi w980 mt30">
		<p>${electromobile_index_tip.description }</p>
	</div sleep w980 mt30>




	<div class="buy_ddc_door w980 mt30">
		<ul id="tb6_">
			<li class="hovertab_6" id="tb6_1" onclick="x:HoverLi6(1);"><a
				href="javascript:void(0);">服务介绍</a>
			</li>
			<li class="normaltab_6" id="tb6_2" onclick="x:HoverLi6(2);"><a
				href="javascript:void(0);">消费提示</a>
			</li>
		</ul>

		<dl class="dis" id="tbc6_01">
			<dd class="bhh">
				${serviceIntroduce.pcContent }
				
			</dd>
		</dl>

		<dl class="undis" id="tbc6_02">
			<dd class="bhh">${consumerTIPS.pcContent }</dd>
		</dl>
	</div buy_ddc_door w980 mt30>
		<%@ include file="../common/left.jsp" %>
	<%@ include file="/WEB-INF/jsp/template/pc/common/bottom.jsp"%>
</body>
</html>
