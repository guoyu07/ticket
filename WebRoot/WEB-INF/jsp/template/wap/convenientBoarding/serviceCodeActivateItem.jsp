<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="ticket" uri="http://www.ynticket.com/tags/"%>

<% //通过航班接口查询得到%>
<dd class="clearfix border-bottom padding-big-bottom margin-big-bottom">
	<input name="cardType" type="hidden" class="input d_input" value="${data['cardType'] }" dataType="*">
	<input name="idCard" id="cardValue" type="hidden" class="input d_input" value="${data['idCard'] }" dataType="*">
	<div class="line padding-large-bottom">
		<div class="x4 padding-top text-right">姓名：</div>
		<div class="x8">
			<input name="name" type="text" placeholder="请填写真实的姓名" class="input d_input" value="${data['name'] }" dataType="zw | yw" errormsg="请填写真实姓名" nullmsg="请填写真实姓名">
			<p class="Validform_checktip" style="display:inline;"></p>
		</div>
	</div>
	<div class="line padding-large-bottom">
		<div class="x4 padding-top text-right">航班号：</div>
		<div class="x8">
			<input name="flightNo" type="text" class="input d_input" value="${data['flightNo'] }" dataType="*" onclick="openFocusFlight(this);" readonly="readonly">
			<p class="Validform_checktip" style="display:inline;"></p>
		</div>
	</div>
	<div class="line padding-large-bottom">
		<div class="x4  padding-top text-right">航班起飞时间：</div>
		<div class="x8">
			<input name="date" type="datetime-local" class="input d_input" value="${data['date'] }" dataType="*" readonly="readonly">
			<p class="Validform_checktip" style="display:inline;"></p>
		</div>
	</div>
	<div class="line padding-large-bottom">
		<div class="x4  text-right">
			<span class="select_tit text-right fz18" style="margin-left:0px;margin-right:0px;line-height:50px;"> 
				<span class="button-group no-background text-right">
					<button type="button"
						class="button  bg dropdown-toggle no-background text-right">
						<font>便捷服务码</font> 
						<span class="downward"></span>
					</button>
					<ul class="drop-menu text-center">
						<li>便捷服务码</li> 
						<!-- <li>其他服务码</li> -->
					</ul>
				</span>
			</span>
		</div>
		<div class="x8">
			<input name="code" type="text" class="input d_input" value="${data['code'] }" dataType="*" 
				ajaxurl="bjdjServiceCodeActivate_validateServiceCode.action" errormsg="此服务码不存在或者已经激活过，或者这是电瓶车的服务码">
			<p class="Validform_checktip" style="display:inline;"></p>
			<ticket:common type="bjdjPageByServiceCode" value="${data['code'] }"/>
			<s:if test="#request.bjdjPageByServiceCode == null || #request.bjdjPageByServiceCode.id == '07d9ece7-c766-45f1-9371-6c4443446cc6'">
				<a href="javascript:newAdd('bjdj.action?type=newAdd')" style="font-size: 14px; color: blue;">购买服务码</a>
			</s:if>
			<s:else>
				<a href="javascript:newAdd('bjdj_indexAjax.action?bjdjPage_id=${bjdjPageByServiceCode.id }&type=newAdd')" style="font-size: 14px; color: blue;">购买服务码</a>
			</s:else>
		</div>
	</div>
	<div class="line">
		<div class="text-center x12">
			<a class="button fz18 d_button" onclick="removeInput(this)">
				<i class="icon-minus c_blue fz22 margin-big-right"></i>删除
			</a>
		</div>
	</div>
</dd>