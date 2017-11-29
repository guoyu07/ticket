<%@page import="com.ticket.serviceImpl.AlipayServiceImpl"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.Date"%>
<%@page import="com.ticket.util.DateUtil"%>
<%@page import="com.ticket.pojo.BjdjOrder"%>
<%
/* *
 *功能：手机网站支付接口接入页
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、开发文档中心（https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1）
 *2、商户帮助中心（https://cshall.alipay.com/enterprise/help_detail.htm?help_id=473888）
 *3、支持中心（https://support.open.alipay.com/alipay/support/index.htm）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付宝手机网站支付接口</title>
	</head>
	<body>
		<form id="form" action="<%=AlipayServiceImpl.ALIPAY_GATEWAY_NEW%>" method="get" 
			_input_charset="<%=AlipayServiceImpl.input_charset%>">
			<c:forEach var="item" items="${params }">
				<input type="hidden" name="${item.key }" value="${item.value }" />
			</c:forEach>
			<input type="submit" value="提交" style="display: none;"/>
		</form>
		<script type="text/javascript">
			document.forms[0].submit();
		</script>
	</body>
</html>
