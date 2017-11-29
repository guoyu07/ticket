<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
	                	<jsp:param value="支付失败" name="title"/>
	                </jsp:include>
		        <div class="mobile-main">
		            <div class="c_content">
		                <div class="err_icon"></div>
		                <br><br>
		                <p class='text-center fz22'>问题提示</p><br>
		                <p class='text-center fz18'>网络问题，请检查网络</p>
		            </div>
		            <a href="javascript:history.back(-1);" class="FL"><div class="tit">返回</div></a>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
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