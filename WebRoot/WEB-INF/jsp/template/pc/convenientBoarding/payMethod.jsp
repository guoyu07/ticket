<%@ page import="com.ticket.enumer.PayMethod"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/WEB-INF/jsp/template/pc/common/head.jsp"%>
		<title>${bjdj } - 云南省昆明市长水机场</title>
		<script type="text/javascript">
		$(function(){
			$('.payMethod').click(function(){
				
				var item = $(this);
				var siblings = $('.payMethod[id!="'+item.attr('id')+'"]');
				item.attr('src', item.attr('src').replace('1', '2'));
				siblings.each(function(){
					
					var s = $(this);
					s.attr('src', s.attr('src').replace('2', '1'));
				});
				$('input[name="payMethod"]').val(item.attr('id'));
			});
		});
		</script>
	</head>
	
	<body>
		<%@ include file="/WEB-INF/jsp/template/pc/common/top.jsp"%>
		<%@ include file="/WEB-INF/jsp/template/pc/common/nav.jsp"%>
	
		<div class="place w980 mt30">
			当前位置: <a href="/airportPc.action">首页</a> > <a href="javascript:;">${bjdj }</a>
		</div>
	
		<div class="ddc w980 mt30">
			<form action="pcOrder_toPay.action" method="get" id="pcForm" ajaxPost="false">
				<input type="hidden" name="id" value="${order.id }"/>
            	<input type="hidden" name="payMethod" value="<%=session.getAttribute("payMethod") == null ? PayMethod.baidu.getValue() : session.getAttribute("payMethod")%>"/>
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td align="right" width="130">名称：</td>
						<td width="780">${order.name }</td>
					</tr>
					<tr>
						<td align="right">价格：</td>
						<td class="price">${order.price }元</td>
					</tr>
					<tr>
						<td align="right">数量：</td>
						<td>${order.count }</td>
					</tr>
					<tr>
						<td align="right">订单总额：</td>
						<td>${order.price * order.count }元</td>
					</tr>
					<tr>
						<td colspan="2"><h2>请选择付款方式：</h2></td>
					</tr>
					<tr>
						<td colspan="2">
							<dl>
								<dt class="bhh" style="height: 50px;">
									<a href="javascript:;">
										<img src="/template/pc/images/login_alipay_${payMethod.value=='alipay'?2:1 }.gif" 
											id="<%=PayMethod.alipay %>" style="width:170px; height:60px" class="payMethod" />
									</a>
								</dt>
								<dt class="bhh" style="height: 50px;">
									<a href="javascript:;">
										<img src="/template/pc/images/login_WeiXin_${payMethod.value=='swift'?2:1 }.gif" 
											id="<%=PayMethod.swift%>" style="width:170px; height:60px" class="payMethod" />
									</a>
								</dt>
							</dl>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<a><input type="image" src="/template/pc/images/go_pay.gif" style="border: none; width:206px; height:64px; padding: 0; margin-top: 10px;"/></a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@ include file="../common/left.jsp" %>
		<%@ include file="/WEB-INF/jsp/template/pc/common/bottom.jsp"%>
	</body>
</html>
