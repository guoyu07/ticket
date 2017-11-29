<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		    <s:if test="#session.fromApp == null && #parameters.fromApp == null">
		        <div class="mobile-top">
		            <div class="header">
		                便捷登机机场CRM后台
		            </div>
		        </div>
		        </s:if>
		        <s:else>
		        <div class="mobile-top" style="display: none;">
		            <div class="header">
		                便捷登机机场CRM后台
		            </div>
		        </div>
		        </s:else>
		        <div class="mobile-main">
		            <div class="text-center fz40 padding-large-top">完成工单</div>
		           <s:iterator value="list" var="l">
		            <div class="line mr40">
		                <div class="x8 fz30 c_text text-center" style="padding:8px;">
		                	${l.info.name }(${l.dispatch.validation.appointment==null? l.dispatch.validation.serviceCode.code : l.dispatch.validation.appointment.serviceCode.code})
		                </div>
		                <div class="x1"></div>
		                <div class="x3">
		                	<a href="employeeCRM_detailsEndedPage.action?id=${l.id }">
		                		<button class="button d_button bg-blue block">查看</button>
		                	</a>
		                </div>
		            </div>
		            </s:iterator>
		            <div class="tit_tab">
		                <a href="employeeCRM_workOrderPage.action" class="b_yello c_grey">工单记录</a>
		                <a href="employeeCRM_personInfoPage.action" class="b_yello c_grey">个人中心</a>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
</html>