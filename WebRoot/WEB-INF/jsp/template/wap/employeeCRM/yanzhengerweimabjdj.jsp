<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/template/wap/js/employeeCRM/bjdjyanzhengerweima.js"></script>
<link rel="stylesheet" href="/template/wap/js/employeeCRM/erweimaVideo.css">
<body class="mobile">
	<div class="mobile-view">
		<div class="mobile-page">
		<s:if test="#session.fromApp == null && #parameters.fromApp == null">
			<div class="mobile-top">
				<div class="header">扫二维码验证</div>
			</div>
			</s:if>
			<s:else>
			<div class="mobile-top" style="display: none;">
				<div class="header">扫二维码验证</div>
			</div>
			</s:else>
			<div class="mobile-main">
				<div class="mr40">
					<div id="support"></div>
					<div id="contentHolder" class='video-wrapper'>
						<video id="video" width="50%" height="40%" autoplay></video>
						<canvas style="display:none; background-color:#F00;" id="canvas"
							width="300" height="300"></canvas>
						<br />
					</div>
				</div><br/><br/>
				<div class="tit_tab" style="padding-top: 0px;">
					<a href="javascript:void(0);" class="c_white" class="block" id="snap">开始扫描</a>
				</div>
				<div class="tit_tab">
					<a href="/wapValidation_manage.action"
						class="b_yello c_grey">验证记录</a> <a
						href="/employeeCRM_personInfoPage.action" class="b_yello c_grey">个人中心</a>
				</div>
				<div class="tit_tab">
					<a href="/wapBjdjAppointment_Index.action" class="b_yello c_grey">返回首页</a>
				</div>
			</div>
		</div>
	</div>
	<div class="dialog" style="display: none;">
		<%@ include file="../common/left.jsp"%>
	</div>
</body>
</html>