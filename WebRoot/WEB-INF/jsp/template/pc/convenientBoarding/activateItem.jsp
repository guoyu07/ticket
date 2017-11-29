<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<% //通过航班接口查询得到%>
<tr>
	<td align="right">姓名：</td>
	<td>
		<input name="cardType" type="hidden" class="input d_input" value="${data['cardType'] }">
		<input name="idCard" type="hidden" class="input d_input" value="${data['idCard'] }">
		<input name="name" type="text" value="${data['name'] }" dataType="*" />
		<p class="Validform_checktip" style="display:inline;"></p>
	</td>
</tr>
<tr>
	<td align="right">航班号：</td>
	<td>
		<input name="flightNo" type="text" value="${data['flightNo'] }" dataType="*"/>
		<p class="Validform_checktip" style="display:inline;"></p>
	</td>
</tr>
<tr>
	<td align="right">时间：</td>
	<td>
		<input name="date" type="text" value="${data['date'] }" dataType="*" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		<p class="Validform_checktip" style="display:inline;" ></p>
	</td>
</tr>
<tr>
	<td align="right">对应服务码：</td>
	<td>
		<input name="code" type="text" value="${data['code'] }" dataType="*"
			ajaxurl="bjdjServiceCodeActivate_validateServiceCode.action" errormsg="此服务码不存在或者已经激活过，或者这是电瓶车的服务码"/>
		<p class="Validform_checktip" style="display:inline;"></p>
		<a href="javascript:newAdd();" style="font-size: 14px; color: blue;">购买服务码</a>
	</td>
</tr>
<tr>
	<td></td>
	<td><a href="javascript:;" onclick="removeInput(this)"><img src="/template/pc/images/ShanChuFuWu.jpg" /></a></td>
</tr>
