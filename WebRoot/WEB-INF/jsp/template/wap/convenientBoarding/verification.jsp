<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="服务码验证" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="tit_tab">
						<a href="#" class='b_l_grey c_grey'>扫码验证</a> <a href="#"
							class='b_yello c_grey'>验证记录</a>
					</div>
	
					<div class="mr40">
						<input class='d_input block input' placeholder='输入服务码'> <br>
						<br> <img src="/template/wap/images/quick/keybord.png">
					</div>
					<div class="tit">确认验证</div>
				</div>
				<div class="mobile-foot">
					<p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN
						AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
					<%@ include file="../common/quickNav.jsp"%>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>