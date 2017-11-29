<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> ${newsClass.name }
						<a href="" class="FR"><i class="icon-bell"></i><ticket:common type="newMessages"/><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if>
						</a>
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						<a href="" class="FL"><i class="icon-angle-left"></i>
						</a> ${newsClass.name }
						<a href="" class="FR"><i class="icon-bell"></i><ticket:common type="newMessages"/><s:if test="#request.newMessages > 0"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></s:if>
						</a>
					</div>
				</div>
				</s:else>
				<s:if test="news == null ">
					<s:if test="news == null ">
						<div class="mobile-main">
							<%@ include file="../common/favoriteAndShare.jsp" %>
							<div class="c_content">
								${noDataMessage }
							</div>
						</div>
					</s:if>
				</s:if>
				<s:else>
				<div class="mobile-main">
					<%@ include file="../common/favoriteAndShare.jsp" %>
					<div class="tit">
						${news.title }
					</div>
					<div class="c_content">
						${news.content }
					</div>
					<div class="tit_tab">
						<a href="#" class="selected">国内超规办理柜台</a>
					</div>
				</div>
                <%@ include file="../common/quickNav.jsp" %>
			</div>
			</s:else>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>