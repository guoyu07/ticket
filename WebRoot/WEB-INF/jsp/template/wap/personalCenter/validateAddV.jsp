<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
					<jsp:param value="验证加V" name="title"/>
				</jsp:include>
				<div class="mobile-main">
					<div class="logo_form mr40">
						<label>
							<span class='fz30 float-left'>姓&nbsp;&nbsp;&nbsp;名：</span>&nbsp;&nbsp;&nbsp;&nbsp;
							<i class='user_icon float-left' style="top: 0px;"></i>
							<input type="text" class='' name="" value=""
								placeholder='请输入手机号码' style='width: 320px'>
						</label>
						<label>
							<span class='fz30 float-left'>证件号：</span>&nbsp;&nbsp;&nbsp;&nbsp;
							<i class='user_passwd float-left' style="top: 0px;"></i>
							<input type="text" class='' name="" value=""
								placeholder='输6-30位数字或字母密码' style='width: 320px'>
						</label>
						</label>
						<button class='button bg-yello d_button block margin-big-top'>
							提交
						</button>
					</div>
					<div class="c_ft clearfix">
						<a href="#" class="ft_more"></a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
		<script type="text/javascript" src="../js/script.js"></script>
	</body>
</html>