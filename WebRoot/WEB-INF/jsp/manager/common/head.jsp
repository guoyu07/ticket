<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<head>
	<title>${systemConfig.name }</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="Content-Language" content="UTF-8" />
	<meta content="width=device-width,user-scalable=no" name="viewport">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="distribution" content="global" />
	<meta name="author" content="www.kmcsia.com">
	<meta name="Copyright" content="版权所有,违者必究">
	<link rel="shortcut icon" href="/manager/images/logo.ico" type="images/x-icon" />
	<link href="/manager/css/login.css" rel="stylesheet" type="text/css">
	<%@ include file="/WEB-INF/jsp/manager/common/tags.jsp"%>
	<script type="text/javascript" >
		var actionName = '${actionName}';
	</script>
</head>