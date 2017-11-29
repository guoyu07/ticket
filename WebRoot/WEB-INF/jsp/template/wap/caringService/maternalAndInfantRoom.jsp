<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<s:if test="news != null ">
				<div class="mobile-page">
					<div class="mobile-top">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i>
							</a> ${newsClass.name }
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
					<div class="mobile-main">
						<%@ include file="../common/favoriteAndShare.jsp" %>
						<div class="tit_tab2">
							<ticket:common type="newsClassChilds" value="${newsClass.alias }"/>
							<s:if test="#request.newsClassChilds != null">
								<s:iterator id="nc" value="#request.newsClassChilds" status="st">
									<s:if test="#st.first">
										<a class="selectInOrOut selected" id="${nc.id }"><i class="icon-caret-down"></i>${nc.name }</a>
									</s:if>
									<s:else>
										<a class="selectInOrOut" id="${nc.id }"><i class="icon-caret-down"></i>${nc.name }</a>
									</s:else>
								</s:iterator>
							</s:if>
						</div>
						<div id="noticeList">
							<div class="c_content">
								<img src="/template/wap/images/pic/44.jpg">
							</div>
						</div>
						<div class="tit">
							导航到这
						</div>
					</div>
					<%@ include file="../common/quickNav.jsp" %>
				</div>
			</s:if>
			<s:else>
				<div class="mobile-page">
					<div class="mobile-top">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i>
							</a> ${newsClass.name }
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
					<div class="mobile-main">
						<%@ include file="../common/favoriteAndShare.jsp" %>
						<div class="tit_tab2">
							
						</div>
						<div id="noticeList">
							<div class="c_content">
								<img src="/template/wap/images/pic/44.jpg">
							</div>
						</div>
						<div class="tit">
							
						</div>
					</div>
					<%@ include file="../common/quickNav.jsp" %>
				</div>
			</s:else>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp" %>
		</div>
		
	</body>
	</ticket:cache>
</html>