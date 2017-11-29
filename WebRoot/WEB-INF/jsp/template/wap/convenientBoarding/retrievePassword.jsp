<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="找回密码" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="c_text mr40 fz18 text-center">请选择找回密码的方式</div>
					<div class="tit">
						<i class='icon-mobile'></i>&nbsp;&nbsp;
						<a href="bjdjMember_retrievePasswordForMobile1Page.action?versionFlag=site">通过手机号码找回密码</a>
					</div>
					<div class="tit">
						<i class='icon-envelope'></i>&nbsp;&nbsp;
						<a href="bjdjMember_retrievePasswordForEmail1Page.action?versionFlag=site">通过邮箱找回密码</a>
					</div>
					<div class="c_content text-center">
						<p>如果以上方式都无法找回密码请致电便捷登机</p>
						<p class='fz20 c_grey padding-big-top'>
							<i class='icon-phone'></i>&nbsp;&nbsp;服务电话：
							<a href="tel:${service_phone}" class='h_tel'>
								${service_phone}
							</a>
						</p>
					</div>
				</div>
				<div class="mobile-foot">
					<p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN
						AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
					<%@ include file="../common/quickNav.jsp"%>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;"></div>
	</body>
	</ticket:cache>
</html>