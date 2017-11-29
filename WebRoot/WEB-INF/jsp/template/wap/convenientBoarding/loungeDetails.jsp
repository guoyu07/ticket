<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="休息厅详情" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="c_text mr40 height-big c_l_grey fz16">
						<i class='icon-volume-up fz24 margin-big-right'
							style='position:relative;top:3px;'></i>已成功预约（${wait}）席位，剩余（${surplus}）席位
					</div>
					<div class="c_content">
						${news.content }
					</div>
					<a href="bjdjOrderTemplate_confirmPage.action?versionFlag=site"><div class="tit">立即购买</div></a>
					<p class='text-center fz12 padding-big-top'>
						COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED<br>
						<br>
					</p>
				</div>
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	</ticket:cache>
</html>