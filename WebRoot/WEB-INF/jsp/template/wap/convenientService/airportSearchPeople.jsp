<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<s:if test="news == null ">
				暂无文章信息
			</s:if>
			<s:else>
			<div class="mobile-page">
				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> ${news.title }
						<a href="" class="FR">
						<ticket:common type="newMessages"/>
					<s:if test="#request.newMessages > 0">
						<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
					</s:if>
					<s:else>
						<i class="icon-bell"></i>
					</s:else>
						</a>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> ${news.title }
						<a href="" class="FR">
						<ticket:common type="newMessages"/>
					<s:if test="#request.newMessages > 0">
						<i class="icon-bell"></i><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span>
					</s:if>
					<s:else>
						<i class="icon-bell"></i>
					</s:else>
						</a>
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="c_content">
						${news.content }
					</div>
					<div class="tit">
						0871-67085806（点击拨号）
					</div>
					<div class="tit">
						导航到这
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp" %>
			</div>
			</s:else>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>