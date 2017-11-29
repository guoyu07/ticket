<%@page import="com.ticket.pojo.Evaluation"%>
<%@page import="com.ticket.pojo.MemberWeixin"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>

<s:iterator var="item" value="evaluations">
	<div class="panel mr40 border10">
		<div class="panel-head fz22 padding-big-top padding-big-bottom">
			<c:choose>
				<c:when test="${empty item.member.images }">
					<img src="/template/wap/images/no_avatar.png" height="50" width="50" class='radius-circle' style="vertical-align:middle; margin-right:10px;">
				</c:when>
				<c:otherwise>
					<img src="${item.member.images }" height="50" width="50" class='radius-circle' style="vertical-align:middle;margin-right:10px;">
				</c:otherwise>
			</c:choose>
			<s:if test="#item.member.getClass() == @com.ticket.pojo.Member@class">
			${fn:substring(item.member.phone, 0, 3) }****${fn:substring(item.member.phone, 7, 11) }
			</s:if>
			<s:else>
			${item.member.nickName }
			</s:else>
			<span style="float:right; margin-top:11px; font-size:18px; color:#b2b2b2;">
				<s:date name="#item.status.createTime" format="yyyy-MM-dd HH:mm" />
			</span>
		</div>
		<div class="panel-body fz22 padding-big-top padding-big-bottom">
			<ticket:shield value="${item.content }"/>
			<div class="clearfix"></div>
			<div class="photoblock-many">
				<c:forEach var="image" items="${fn:split(item.images, ',') }">
					<c:if test="${image != null && image != '' ? true : false }">
						<a i="${image }" href="javascript:;" class="padding-top inline_block Slide pl_${item.id }" title=" ">
							<img height="100" width="100" src="${image }" style="border-radius:8px;" />
						</a>
					</c:if>
				</c:forEach>
			</div>
			<script type="text/javascript">
			    $('.pl_${item.id }').simpleSlide();
			</script>

			<s:if test="#item.feedback != null">
				<hr class="border-bottom" />
				<span class="c_blue" style="color:#fff2c9;">管理员回复：</span> ${item.feedback }
			</s:if>
			<s:else>
				<hr class="border-bottom" />
				<s:set var="process" value="evaluationService.getProcess(#item)"></s:set>
				<s:if test="#process != null">
					<span class="c_blue">管理员处理信息：</span>${process[0].msg }
   				</s:if>
			</s:else>
		</div>
	</div>
</s:iterator>