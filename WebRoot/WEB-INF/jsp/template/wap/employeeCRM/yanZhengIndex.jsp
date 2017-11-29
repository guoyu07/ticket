<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM/wapValid.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						电瓶车验证
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						电瓶车验证
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="/wapValidation_yanZhengErWeiMaIndex.action">扫二维码验证</a>
						<a href="javascript:;">扫身份证验证</a>
					</div>
					<div class="mr40">
						<input type="input" class='input d_input block fz22' name=""
							value="" placeholder='输入服务验证码' id="yzm"
							style="height: 70px; line-height: normal;">
					</div>
					<div class="tit b_blue" style="padding-top: 0px;">
						<a href="javascript:yzmYz();" class="c_white" class="block">确定</a>
					</div>
					<div class="tit_tab">
						<a href="/wapValidation_manageElectromobile.action" class="b_yello c_grey">验证记录</a>
						<a href="/employeeCRM_personInfoPage.action" class="b_yello c_grey">个人中心</a>
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