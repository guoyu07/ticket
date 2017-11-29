<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/template/wap/js/employeeCRM.js"></script>
	<body class="mobile">
		<div class="mobile-view">
		    <div class="mobile-page">
		    <s:if test="#session.fromApp == null && #parameters.fromApp == null">
		        <div class="mobile-top">
		            <div class="header">
		                完成工单(${item.info.name })
		            </div>
		        </div>
		        </s:if>
		        <s:else>
		        <div class="mobile-top" style="display: none;">
		            <div class="header">
		                完成工单(${item.info.name })
		            </div>
		        </div>
		        </s:else>
		        <div class="mobile-main">
		            <div class="mr40">
		            	<dl class='fz20'>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>起飞日期：</em>
		                        <span class='c_l_grey'><s:date name="#dispatch.validation.appointment.useTime" format="yyyy-MM-dd HH:mm:ss"/></span></label>
		                    </dd>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>服务码：</em>
		                        <span class='c_l_grey'>${dispatch.validation.appointment.serviceCode.code }</span></label>
		                    </dd>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>旅客姓名：</em>
		                        <span class='c_l_grey'>${dispatch.validation.appointment.name }</span></label>
		                    </dd>
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>旅客电话：</em>
		                        <span class='c_l_grey'>${dispatch.validation.appointment.mobile }</span></label>
		                    </dd>
		                    <%-- <s:iterator var="l" value="list">
		                    <dd class='clearfix height-big border-bottom padding-big-bottom margin-big-bottom'>
		                        <label class='fz18'><em>${l.info.name }：</em>
		                        <span class='${systemUser.id==l.employee.id?"c_red":"c_l_grey" }'>${l.employee.name }</span></label>
		                    </dd>
		                    </s:iterator> --%>
		                </dl>
		            </div>
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
		                <textarea name="" class='input' style="height:200px;"></textarea>
		            </div>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-top no">提交</button>
		        </div>
		    </div>
		    <div class="alert_dialog box_dialog">
		        <div class="c_content b_white text-center">
		            <h2 class="fz30 c_black">接单成功</h2>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-top no">关闭</button>
		        </div>
		    </div>
		    <div class="confirm_dialog box_dialog">
		        <div class="c_content b_white text-center">
		            <h2 class="fz30 c_black">确认接单？</h2>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-top yes" >&nbsp;&nbsp;是&nbsp;&nbsp;</button>
		            <button type="button" class="button bg-yello fz26 padding-big margin-large-left margin-large-top no">&nbsp;&nbsp;否&nbsp;&nbsp;</button>
		        </div>
		    </div>
		</div>
	</body>
</html>