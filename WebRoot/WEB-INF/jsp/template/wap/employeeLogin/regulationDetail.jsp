<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			   <s:if test="#session.fromApp == null && #parameters.fromApp == null">
					<div class="mobile-top">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i>
							</a> 内部员工登陆
							</a>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="mobile-top" style="display: none;">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i>
							</a> 内部员工登陆
							</a>
						</div>
					</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr40 line-big">
						<div class="x9">
							<input type="text" name="" value="" class="input"
								style='margin-left: -10px; height: 40px;'>
						</div>
						<div class="x2">
							<button type=""
								class='button fz20 padding-top padding-bottom bg-sub'
								style='margin-left: -15px;'>
								确定
							</button>
						</div>
						<div class="x1 regulationType">
							<button type="" class='button bg-sub' style='margin-left: -20px;'>
								<i class='icon-bars fz28'></i>
							</button>
						</div>
					</div>
					<div class="c_content">
						<h2 class='padding-big-bottom text-center'>
							${regulation.title }
						</h2>
						<div class="fz16">
							${regulation.content }
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
	</ticket:cache>
</html>