<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../common/headAjax.jsp" %>
<ul>
	<ticket:common type="trafficTypeListByKeyword" value="${keyword }"/>
	<s:if test="#request.trafficTypeListByKeyword != null">
		<s:iterator id="trafficType" value="#request.trafficTypeListByKeyword">
			<li class='c_text line margin-large-bottom'>
				<div class='x10 fz22'>
					${trafficType.name }
				</div>
				<div class='x2'>
					<a href="/airport_trafficGuideDetail.action?id=${trafficType.id }" class='b_blue c_white button fz22'>详情</a>
				</div>
			</li>
		</s:iterator>
	</s:if>
</ul>
