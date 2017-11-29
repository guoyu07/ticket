<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/headAjax.jsp" %>

<ticket:common type="newsObj" value="${newsClass.id }"/>
<div class="c_content">
	${newsObj.content }
</div>
