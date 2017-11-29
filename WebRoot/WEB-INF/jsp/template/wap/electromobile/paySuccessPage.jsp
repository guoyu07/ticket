<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<ticket:cache group="便捷登机">
	<script type="text/javascript" src="/template/wap/js/convientBording/purchase.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="支付成功" name="title"/>
                </jsp:include>
				<div class="mobile-main fz24">
					<div class="c_content text-center fz30">
						<p class='padding-top'>服务名称：电瓶车</p>
						<hr>
						<s:iterator value="codes" var="code">
						<p class='padding-top'>服务码：${code}</p>
						</s:iterator>
					</div>
					<p class='mr40'>您的服务码已发送到${order.mobile}手机上，请查收谢谢！</p>
					<div class="mrlr40">
						<div class="msg">
							<h1 class='c_red padding-big-bottom'>
								<i class='icon-heart'></i>&nbsp;&nbsp;温馨提示：
							</h1>
							${electromobile_order_tip.description }
						</div>
					</div>
					<div class="line mr40">
		                <div class="x6">
		                	<button onclick="javascript:window.location='airport.action?versionFlag=site'" 
		                		class='b_orange c_white height-big button d_button block' 
		                		style='border:0px;border-radius:0px;width:99%'>长水首页</button>
		                </div>
		                <div class="x6">
		                	<button
		                		class='b_blue c_white height-big button d_button block personalCenterBtn'  
		                		style='border:0px;border-radius:0px;'>个人中心</button>
		                </div>
		            </div>
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