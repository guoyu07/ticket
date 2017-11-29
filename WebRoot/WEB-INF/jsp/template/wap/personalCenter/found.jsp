<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top" <c:if test="${! empty fromApp }">style="display: none;"</c:if>>
					<div class="header">
						<s:if test="#session.fromApp != null">
							<a onclick="comeBack();" class="FLAPP" ><i class="icon-angle-left"></i></a>
						</s:if>
						<s:else>
							<a href="javascript:return_prepage();" class="FL" id="comeback"><i class="icon-angle-left"></i></a>
						</s:else>
							发现
						<a href='javascript:;' memberId="${empty sessionMember ? 1 : 0}" class="FR personalCenterBtn">
							<ticket:common type="newMessages"/>
							<i class="icon-bell"></i><c:if test="${newMessages > 0 }"><span class="badge bg-red" style="margin-left: -15px;margin-top:27px;position: absolute;">&nbsp;</span></c:if>
						</a>
					</div>
				</div>
				<div class="mobile-main">
					<ul class="font_cat_ls clearfix">
						<li>
							酒店
						</li>
						<li>
							美食
						</li>
						<li>
							景点
						</li>
						<li>
							娱乐
						</li>
					</ul>
					<ul class="font_pic_ls clearfix">
						<li>
							<a href="#"><img src="/template/wap/images/pic/68.jpg">
							</a>
						</li>
						<li>
							<a href="#"><img src="/template/wap/images/pic/68.jpg">
							</a>
						</li>
						<li>
							<a href="#"><img src="/template/wap/images/pic/68.jpg">
							</a>
						</li>
						<li>
							<a href="#"><img src="/template/wap/images/pic/68.jpg">
							</a>
						</li>
						<li>
							<a href="#"><img src="/template/wap/images/pic/68.jpg">
							</a>
						</li>
					</ul>
					<div class="c_ft clearfix">
						<a href="#" class="ft_more"></a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;"><%@ include file="../common/left.jsp" %></div>
		
	</body>
</html>