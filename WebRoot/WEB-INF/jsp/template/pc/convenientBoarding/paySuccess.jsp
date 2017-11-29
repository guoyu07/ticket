<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/template/pc/common/head.jsp"%>
<script type="text/javascript" src="/template/pc/js/convenientBoarding/showCode.js"></script>
<title>${order.name } - 云南省昆明市长水机场</title>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/template/pc/common/top.jsp"%>

	<%@ include file="/WEB-INF/jsp/template/pc/common/nav.jsp"%>

	<div class="place w980 mt30">
		当前位置: <a href="/airportPc.action">首页</a> > <a>${order.name }</a> > 购买成功
	</div>


	<div class="pay_dd w980 mt30">
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td height="140"><img src="/template/pc/images/pay_dd_3.gif" />
				</td>
			</tr>
			<tr>
				<td>
					<p>
						<img src="/template/pc/images/smile.gif" />
					</p>
					<h2>恭喜您！购买成功</h2>
					<h3>
						凭服务码到机场验证！购买商品：${order.name }，服务码已发送到${order.mobile}，
						<a href="javascript:void(0)" id="showCode">没收到短信？</a>
						<s:iterator value="codes" var="code">
						<input value="${code}" type="hidden"/>
						</s:iterator>
					</h3></td>
			</tr>
			<tr>
				<td>
				<a href="pcServiceCode_page.action">
					<img src="/template/pc/images/JH.gif" />
				</a>
				<!-- <input type="button" value="确定"/> -->
				</td>
			</tr>
		</table>

	</div>
		<%@ include file="../common/left.jsp" %>
	<%@ include file="/WEB-INF/jsp/template/pc/common/bottom.jsp"%>
</body>
</html>
