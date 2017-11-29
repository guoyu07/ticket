<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/workOrder.js"></script>
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
		            <div class="text-center fz40 padding-large-top">已接工单</div>
		            <div class="text-center fz40 padding-large-top">
					    <a><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox" onclick="selectAll();"  Style="zoom: 200%"></a>
		            	<a href="javascript:end();" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量核销</a>
		            </div>
		            <s:iterator value="list" var="l">
		            <div class="line mr40">
		            	<div class="x1">
					    	<input name="${actionName}CheckBox" type="checkbox" value="${l.id }" Style="zoom: 200%">
		           		</div>
		                <div class="x8 fz30 c_text text-center" style="padding:8px;">
		                	${l.info.name }(${l.dispatch.validation.appointment==null? l.dispatch.validation.serviceCode.code : l.dispatch.validation.appointment.serviceCode.code})
		                </div>
		                <div class="x1"></div>
		                <div class="x3">
		                	<a href="employeeCRM_detailsReceivedPage.action?id=${l.id }">
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
		    <div class="question_dialog box_dialog">
		        <div class="c_content b_white">
		            <h2 class="fz30 c_black padding-big-bottom">问题反馈</h2>
		            <div class="">
		                <textarea name="feedback" class='input' style="height:200px;"></textarea>
		            </div>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-top no">提交</button>
		        </div>
		    </div>
		    <div class="alert_dialog box_dialog">
		        <div class="c_content b_white text-center">
		            <h2 class="fz30 c_black">核销成功</h2>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-top no">关闭</button>
		        </div>
		    </div>
		    <div class="confirm_dialog box_dialog">
		        <div class="c_content b_white text-center">
		            <h2 class="fz30 c_black content_text">确认核销？</h2>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-top yes" >&nbsp;&nbsp;是&nbsp;&nbsp;</button>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-left margin-large-top no">&nbsp;&nbsp;否&nbsp;&nbsp;</button>
		        </div>
		    </div>
		</div>
	</body>
</html>