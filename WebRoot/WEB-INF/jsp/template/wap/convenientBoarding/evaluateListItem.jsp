<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>

<s:iterator var="item" value="comments">
<div class="panel mr40 border10">
	<div class="panel-head fz22 padding-big-top padding-big-bottom">
		<s:if test="#item.showName">${item.member.phone }</s:if>
		<s:else>${fn:substring(item.member.phone, 0, 3) }****${fn:substring(item.member.phone, 7, 11) }</s:else>
		<a href="#" class="float-right">
			<c:forEach begin="1" end="${item.star }">
			<i class="icon-star text-dot fz30"></i>
			</c:forEach>
		</a>
	</div>
	<div class="panel-body fz22 padding-big-top padding-big-bottom">
		<ticket:shield value="${item.content }"/>
		<c:forEach var="image" items="${fn:split(item.images, ',') }">
			<c:if test="${image != null && image != '' ? true : false }">
				<a href="${image }" target="_self" class="padding-top inline_block">
					<img height="100" width="100" src="${image }"/>
				</a>
			</c:if>
		</c:forEach>
	</div>
</div>
</s:iterator>