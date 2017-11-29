<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">便捷登机机场CRM后台</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">便捷登机机场CRM后台</div>
				</div>
				</s:else>
				<div class="mobile-main">
	
					<dl class="">
						<dd class='mr40 fz30 text-center c_text clearfix' style='line-height:50px'>
							<a href="employeeCRM_workOrderNoReceivePage.action">
							未接工单
							<div class="txt radius-circle bg-red float-right">${noAccept }</div>
							</a>
						</dd>
						<dd class='mr40 fz30 text-center c_text clearfix' style='line-height:50px'>
							<a href="employeeCRM_workOrderReceivedPage.action">
							已接工单
							<div class="txt radius-circle bg-red float-right">${accept }</div>
							</a>
						</dd>
						<dd class='mr40 fz30 text-center c_text clearfix' style='line-height:50px'>
							<a href="employeeCRM_workOrderEndedPage.action">
							完成工单
							<div class="txt radius-circle bg-red float-right">${endedAccept }</div>
							</a>
						</dd>
					</dl>
					 <div class="tit_tab">
				     	<a href="employeeCRM_workOrderPage.action" class="b_yello c_grey">工单记录</a>
				     	<a href="employeeCRM_personInfoPage.action" class="b_yello c_grey">个人中心</a>
				     </div>
					</div>
				</div>
		</div>
		<div class="dialog" style="display:none;"></div>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
</html>