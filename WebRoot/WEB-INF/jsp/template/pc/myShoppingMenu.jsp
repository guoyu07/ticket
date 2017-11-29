<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="common/head.jsp"%>
<title>我的订单 - 云南省昆明市长水机场</title>
<script type="text/javascript" src="/template/pc/js/door.js"></script>
<script type="text/javascript" src="/template/pc/js/PCOrderManager.js"></script>
</head>

<body>
	<%@ include file="common/top.jsp"%>
	<%@ include file="common/nav.jsp"%>

	<div class="gg w980 mt30">
		<ul class="bhh">
			<li class="bhh"><p>站内公告：</p>
			</li>
			<li id="rollTextMenu1" style="display:block">长水机场平台正式上线，欢迎各位用户监督指导1</li>
			<li id="rollTextMenu2" style="display:none">长水机场平台正式上线，欢迎各位用户监督指导2</li>
			<li id="rollTextMenu3" style="display:none">长水机场平台正式上线，欢迎各位用户监督指导3</li>
		</ul>

		<dl class="hh">
			<dd class="bhh">
				<a href="javascript:rollText(-1);"><img
					src="/template/pc/images/pre1.gif"
					onmouseover="this.src='/template/pc/images/pre2.gif'"
					onmouseout="this.src='/template/pc/images/pre1.gif'">
				</a>
			</dd>
			<dd class="bhh">
				<a href="javascript:rollText(1);"><img
					src="/template/pc/images/next1.gif"
					onmouseover="this.src='/template/pc/images/next2.gif'"
					onmouseout="this.src='/template/pc/images/next1.gif'">
				</a>
			</dd>
		</dl>
	</div gg w980 mt30>
	<SCRIPT type=text/javascript>
			<!--
			var rollText_k=3; //菜单总数
			var rollText_i=1; //菜单默认值
			//setFocus1(0);
			rollText_tt=setInterval("rollText(1)",8000);
			function rollText(a){
			clearInterval(rollText_tt);
			rollText_tt=setInterval("rollText(1)",8000);
			rollText_i+=a;
			if (rollText_i>rollText_k){rollText_i=1;}
			if (rollText_i==0){rollText_i=rollText_k;}
			//alert(i)
			 for (var j=1; j<=rollText_k; j++){
			 document.getElementById("rollTextMenu"+j).style.display="none";
			 }
			 document.getElementById("rollTextMenu"+rollText_i).style.display="block";
			} 
			//-->
		</SCRIPT>

	<%@ include file="common/personalData.jsp"%>

	<div class="menu1">
		<ul>
			<li class="bhh"><a href="/airportPc_myWay.action"><img
					src="/template/pc/images/ico1_1.gif"
					onmouseover="this.src='/template/pc/images/ico1_2.gif'"
					onmouseout="this.src='/template/pc/images/ico1_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_checkOnLine.action"><img
					src="/template/pc/images/ico2_1.gif"
					onmouseover="this.src='/template/pc/images/ico2_2.gif'"
					onmouseout="this.src='/template/pc/images/ico2_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_flightDynamic.action"><img
					src="/template/pc/images/ico3_1.gif"
					onmouseover="this.src='/template/pc/images/ico3_2.gif'"
					onmouseout="this.src='/template/pc/images/ico3_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_flightQuery.action"><img
					src="/template/pc/images/ico4_1.gif"
					onmouseover="this.src='/template/pc/images/ico4_2.gif'"
					onmouseout="this.src='/template/pc/images/ico4_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_myMessage.action"><img
					src="/template/pc/images/ico5_1.gif"
					onmouseover="this.src='/template/pc/images/ico5_2.gif'"
					onmouseout="this.src='/template/pc/images/ico5_1.gif'" />
			</a>
			</li>
			<li class="bhh"><a href="/airportPc_find.action"><img
					src="/template/pc/images/ico6_1.gif"
					onmouseover="this.src='/template/pc/images/ico6_2.gif'"
					onmouseout="this.src='/template/pc/images/ico6_1.gif'" />
			</a>
			</li>
		</ul>
	</div menu1>

	<div class="w980">
		<div class="L bhh">
			<ul>
				<li><a href="javascript:void(0)"><img
						src="/template/pc/images/button1_2.gif" />
				</a>
				</li>
				<li><a href="pcMembers_personalSetting.action"><img
						src="/template/pc/images/button2_1.gif"
						onmouseover="this.src='/template/pc/images/button2_2.gif'"
						onmouseout="this.src='/template/pc/images/button2_1.gif'" />
				</a>
				</li>
				<li><a href="pcMembers_myPayInfo.action"><img
						src="/template/pc/images/button3_1.gif"
						onmouseover="this.src='/template/pc/images/button3_2.gif'"
						onmouseout="this.src='/template/pc/images/button3_1.gif'" />
				</a>
				</li>
				<li><a href="airportPc_myFavorite.action"><img
						src="/template/pc/images/button4_1.gif"
						onmouseover="this.src='/template/pc/images/button4_2.gif'"
						onmouseout="this.src='/template/pc/images/button4_1.gif'" />
				</a>
				</li>
			</ul>
		</div L bhh>

		<div class="MyDD hh">
			<dl id="tb2_">
				<dd class="hovertab_2" id="tb2_1" onclick="x:HoverLi2(1);">
					<a href="javascript:void(0);">全部订单</a>
				</dd>
				<dd class="w1">|</dd>
				<dd class="normaltab_2" id="tb2_2" onclick="i:HoverLi2(2);">
					<a href="javascript:void(0);">待付款</a>
				</dd>
				<dd class="w1">|</dd>
				<dd class="normaltab_2" id="tb2_3" onclick="i:HoverLi2(3);">
					<a href="javascript:void(0);">赠送</a>
				</dd>
				<dd class="w1">|</dd>
				<dd class="normaltab_2" id="tb2_4" onclick="i:HoverLi2(4);">
					<a href="javascript:void(0);">待评价</a>
				</dd>
				<dd class="w1">|</dd>
				<dd class="normaltab_2" id="tb2_5" onclick="i:HoverLi2(5);">
					<a href="javascript:void(0);">已使用</a>
				</dd>
			</dl>
			<ticket:common type="advertObjByName" value="便捷登机商品图片"/>
			<ticket:commonAnnex type="annex" entityUuid="${advertObjByName.id }" annexType="1" size="1"/>	
			<table cellspacing="0" cellpadding="0" class="dis" id="tbc2_01">
				<tr>
					<th colspan="2">订单信息</th>
					<th>订单金额</th>
					<th>下单时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:if test="#allOrder != null && #allOrder.size() > 0">
					<s:iterator value="allOrder" var="order">
						<tr>
							<td width="88">
	        					<img src="${annex.annexPath }" />
							</td>
							<td width="74">${order.name }</td>
							<td width="155">${order.price * order.count }元</td>
							<td width="160"><s:date name="#order.status.createTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td width="140">${order.state.value }</td>
							<td width="83"><a href="javascript:orderInfo('${order.id }')"><p>详情</p> </a> <s:if
									test="orderService.queryCanDonationServiceCode(#order).size() > 0">
									<a href="/pcOrder_orderDonationPage.action?orderId=${order.id }"><p>转赠</p> </a>
								</s:if> <s:if test="#order.state.name == 'noPay'">
									<a href="pcOrder_payMethod.action?id=${order.id }"><p>付款</p> </a>
								</s:if> <s:if test="orderService.canDelete(#order)">
									<a href="javascript:realDelete('${order.id }')"><p>删除</p> </a>
									<input type="hidden" id="${order.id }" />
								</s:if> <s:if
									test="orderService.queryCanActivateServiceCode(#order).size() > 0">
									<a href="pcServiceCode_page.action"><p>激活</p> </a>
								</s:if> <s:if
									test="orderService.queryCanCommentServiceCode(#order).size() > 0">
									<a href="javascript:;"><p>评论</p> </a>
								</s:if> <s:if test="orderService.canRefund(#order)">
									<a
										href="/pcOrder_orderRefundPage.action?orderId=${order.id }"><p>退款</p></a>
								</s:if></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>

			<table cellspacing="0" cellpadding="0" class="undis" id="tbc2_02">
				<tr>
					<th colspan="2">订单信息</th>
					<th>订单金额</th>
					<th>下单时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:if test="#needPay != null && #needPay.size() > 0">
					<s:iterator value="needPay" var="order">
						<tr>
							<td width="88"><img src="${annex.annexPath }" />
							</td>
							<td width="74">${order.name }</td>
							<td width="155">${order.price * order.count }元</td>
							<td width="160"><s:date name="#order.status.createTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td width="140">${order.state.value }</td>
							<td width="83"><a href="javascript:;"><p>详情</p> </a> <s:if
									test="orderService.queryCanDonationServiceCode(#order).size() > 0">
									<a href=""><p>转赠</p> </a>
								</s:if> <s:if test="#order.state.name == 'noPay'">
									<a href="pcOrder_payMethod.action?id=${order.id }"><p>付款</p> </a>
								</s:if> <s:if test="orderService.canDelete(#order)">
									<a href="javascript:realDelete('${order.id }')"><p>删除</p> </a>
									<input type="hidden" id="${order.id }" />
								</s:if> <s:if
									test="orderService.queryCanActivateServiceCode(#order).size() > 0">
									<a href="pcServiceCode_page.action"><p>激活</p> </a>
								</s:if> <s:if
									test="orderService.queryCanCommentServiceCode(#order).size() > 0">
									<a href="javascript:;"><p>评论</p> </a>
								</s:if> <s:if test="orderService.canRefund(#order)">
									<a
										href=""><p>退款</p></a>
								</s:if></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>

			<table cellspacing="0" cellpadding="0" class="undis" id="tbc2_03">
				<tr>
					<th colspan="2">订单信息</th>
					<th>服务码</th>
					<th>赠送人</th>
					<th>增送时间</th>
					<th>有效期</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:if test="#donation != null && #donation.size() > 0">
					<s:iterator value="donation" var="d">
						<tr>
							<td width="88"><img src="${annex.annexPath }" />
							</td>
							<td width="50">${d.order.name }</td>
							<td width="130">${d.code }</td>
							<s:set var="donation"
								value="operationService.recentlyOperationToMember(#c, 'donation', #d.member)" />
							<td width="140"><s:if
									test="#donation.fromMember.realName == null || #donation.fromMember.realName.trim() == ''">
						               			${donation.fromMember.phone }
						               		</s:if> <s:else>
						               			${donation.fromMember.realName }
						               		</s:else>
							</td>
							<td width="120"><s:date name="#donation.status.createTime"
									format="yyyy-MM-dd HH:mm:ss" /></td>
							<td><s:date name="#d.expireTime"
									format="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${d.state.value }</td>
							<td width="83"><a href="javascript:;"><p>详情</p> </a> <s:if
									test="orderService.queryCanDonationServiceCode(#order).size() > 0">
									<a href=""><p>转赠</p> </a>
								</s:if> <s:if test="#order.state.name == 'noPay'">
									<a href="pcOrder_payMethod.action?id=${order.id }"><p>付款</p> </a>
								</s:if> <s:if test="orderService.canDelete(#order)">
									<a href="javascript:realDelete('${order.id }')"><p>删除</p> </a>
								</s:if> <s:if
									test="orderService.queryCanActivateServiceCode(#order).size() > 0">
									<a href="pcServiceCode_page.action"><p>激活</p> </a>
								</s:if> <s:if
									test="orderService.queryCanCommentServiceCode(#order).size() > 0">
									<a href="javascript:;"><p>评论</p> </a>
								</s:if> <s:if test="orderService.canRefund(#order)">
									<a
										href=""><p>退款</p></a>
								</s:if></td>
						</tr>

					</s:iterator>
				</s:if>
			</table>

			<table cellspacing="0" cellpadding="0" class="undis" id="tbc2_04">
				<tr>
					<th colspan="2">订单信息</th>
					<th>订单金额</th>
					<th>下单时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:if test="#needComment != null && #needComment.size() > 0">
					<s:iterator value="needComment" var="order">
						<tr>
							<td width="88"><img src="${annex.annexPath }" />
							</td>
							<td width="74">${order.name }</td>
							<td width="155">${order.price * order.count }元</td>
							<td width="160"><s:date name="#order.status.createTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td width="140">${order.state.value }</td>
							<td width="83"><a href="javascript:;"><p>详情</p> </a> <s:if
									test="orderService.queryCanDonationServiceCode(#order).size() > 0">
									<a href=""><p>转赠</p> </a>
								</s:if> <s:if test="#order.state.name == 'noPay'">
									<a href="${order.payUrl }"><p>付款</p> </a>
								</s:if> <s:if test="orderService.canDelete(#order)">
									<a href="javascript:realDelete('${order.id }')"><p>删除</p> </a>
								</s:if> <s:if
									test="orderService.queryCanActivateServiceCode(#order).size() > 0">
									<a href="pcServiceCode_page.action"><p>激活</p> </a>
								</s:if> <s:if
									test="orderService.queryCanCommentServiceCode(#order).size() > 0">
									<a href="javascript:;"><p>评论</p> </a>
								</s:if> <s:if test="orderService.canRefund(#order)">
									<a
										href=""><p>退款</p></a>
								</s:if></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>

			<table cellspacing="0" cellpadding="0" class="undis" id="tbc2_05">
				<tr>
					<th colspan="2">订单信息</th>
					<th>订单金额</th>
					<th>下单时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<s:if test="#used != null && #used.size() > 0">
					<s:iterator value="used" var="order">
						<tr>
							<td width="88"><img src="${annex.annexPath }" />
							</td>
							<td width="74">${order.name }</td>
							<td width="155">${order.price * order.count }元</td>
							<td width="160"><s:date name="#order.status.createTime"
									format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td width="140">${order.state.value }</td>
							<td width="83"><a href="javascript:;"><p>详情</p> </a> <s:if
									test="orderService.queryCanDonationServiceCode(#order).size() > 0">
									<a href=""><p>转赠</p> </a>
								</s:if> <s:if test="#order.state.name == 'noPay'">
									<a href="pcOrder_payMethod.action?id=${order.id }"><p>付款</p> </a>
								</s:if> <s:if test="orderService.canDelete(#order)">
									<a href="javascript:realDelete('${order.id }')"><p>删除</p> </a>
								</s:if> <s:if
									test="orderService.queryCanActivateServiceCode(#order).size() > 0">
									<a href="pcServiceCode_page.action"><p>激活</p> </a>
								</s:if> <s:if
									test="orderService.queryCanCommentServiceCode(#order).size() > 0">
									<a href="javascript:;"><p>评论</p> </a>
								</s:if> <s:if test="orderService.canRefund(#order)">
									<a
										href=""><p>退款</p></a>
								</s:if></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
		</div MyDD hh>
	</div w980>
			<%@ include file="common/left.jsp" %>
	<%@ include file="common/bottom.jsp"%>
</body>
</html>
