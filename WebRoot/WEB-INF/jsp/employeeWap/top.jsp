<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="label_bar  third_label_bar">
	<a href="/employeeInfoWap_index.action" class="<s:if test="markIndex == 1">selected</s:if>">我的业绩</a>
	<a href="/employeeInfoWap_employeeInfoZengLogAdd.action" class="<s:if test="markIndex == 2">selected</s:if>">我要销售</a>
	<a href="/employeeInfoWap_iWillActivate.action" class="<s:if test="markIndex == 3">selected</s:if>">我要激活</a>
	<a href="/employeeInfoWap_channelCustomerInfoAdd.action" class="<s:if test="markIndex == 4">selected</s:if>">录入客户</a>
	<a href="/employeeInfoWap_batchGetServiceCode.action" class="<s:if test="markIndex == 5">selected</s:if>">批量领码</a>
	<a href="/employeeInfoWap_getAgreement.action" class="<s:if test="markIndex == 6">selected</s:if>">领取合同</a>
	<a href="/employeeInfoWap_letterStationList.action" class="<s:if test="markIndex == 7">selected</s:if>">消息中心</a>
	<a href="/employeeInfoWap_logout.action">退出</a>
</div>