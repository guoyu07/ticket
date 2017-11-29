<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
客保信息<br/>
<s:if test="customerProtectLog != null">
该客户处于保护期中<br/>
申请保护人员:
<ticket:systemCommon type="employeeInfoObj" value="${customerProtectLog.employeeInfo_id}"/>
${employeeInfoObj.name}<br/>
</s:if>
<s:else>
该客户未被保护<br/>
</s:else>
客户信息<br/>
客户名称:${channelCustomerInfo.name}<br/>
渠道分类:<ticket:systemCommon type="channelTypeObj" value="${channelCustomerInfo.channelTypeId}"/>
${channelTypeObj.name }<br/>
客户所属员工:
<ticket:systemCommon type="employeeInfoObj" value="${channelCustomerInfo.employeeInfo_id}"/>
${employeeInfoObj.name}<br/>
合同信息<br/>
<s:if test="agreement != null">
合同编号:${agreement.name}<br/>
合同客户:<ticket:systemCommon type="channelCustomerInfoObj" value="${agreement.channelCustomerInfo_id}"/>
${channelCustomerInfoObj.name }<br/>
是否有效:<s:if test="agreement.effective == 0">无效</s:if><s:else>有效</s:else><br/>
合同状态:
<s:if test="agreement.agreementState == 0">
合同未审批
</s:if>
<s:if test="agreement.agreementState == 1">
合同审批通过
</s:if>
<s:if test="agreement.agreementState == 2">
合同审批未通过
</s:if>
<s:if test="agreement.agreementState == 3">
合同已签回,未发放合同
</s:if>
<s:if test="agreement.agreementState == 4">
合同已签回并发放合同
</s:if>
<s:if test="agreement.agreementState == 5">
合同已归档
</s:if>
<br/>
签约金额:${agreement.firstRenewMoney}元<br/>
签约时间:<s:date name="agreement.signingDate" format="yyyy-MM-dd"/><br/>
</s:if>
<s:else>
无合同信息
</s:else>
<input type="hidden" name="agreement_id" value="${agreement.id}"/>
