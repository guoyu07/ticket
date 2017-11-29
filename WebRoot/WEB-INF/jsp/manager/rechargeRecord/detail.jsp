<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ticket" uri="/WEB-INF/classes/tags.tld" %>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
客户名称:${channelCustomerInfo.name}<br/>
客户类型:${channelType.name}<br/>
优惠政策:<p><s:if test="favouredPolicy != null">${favouredPolicy.name},充值比率${favouredPolicy.discountRate}
</s:if>
<s:else>无</s:else></p>
<s:if test="favouredPolicy != null">
<input type="hidden" id="youhuizhengce" value="${favouredPolicy.discountRate}"/>
</s:if>
<s:else>
<input type="hidden" id="youhuizhengce" value="无"/>
</s:else>
客户用户名：${channelCustomerInfo.loginId}
<p id="sumMoney">实际到账金额:</p>
<script>
	$(function (){
		if(JM.trim($("#youhuizhengce").val()) == "无"){
			$("#chongzhibi").val(0);
			$("#zuidijine").val(0);
		}else{
			$("#chongzhibi").val(${favouredPolicy.discountRate});
			$("#zuidijine").val(${favouredPolicy.rechargeAmount});
		}
		sumMoney();
		
	})
	
</script>
