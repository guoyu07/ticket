<%@ page import="com.ticket.enumer.PayMethod"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp" %>
	<body class="mobile quick">
		<div class="mobile-view">
		    <div class="mobile-page">
		        <jsp:include page="../common/title.jsp">
                	<jsp:param value="订单支付" name="title"/>
                </jsp:include>
		        <div class="mobile-main">
		            <div class="panel mr40 border10">
		                <div class="panel-head fz22 padding-big-top padding-big-bottom text-center">${order.name }</div>
		                <div class="panel-body fz16 padding-big-top padding-big-bottom text-center">
		                	<p class="c_red fz22">${order.price * order.count}元</p>
		                </div>
		            </div>
		            <form method="post" action="/bjdjOrderTemplate_toPay.action">
		            	<input type="hidden" name="id" value="${order.id }"/>
		            	<input type="hidden" name="outer" value="false" />
		            	<input type="hidden" name="payMethod" value="${empty payMethod ? 'alipay' : payMethod}"/>
			            <div class="panel mr40 border10">
			                <div class="panel-head fz22 padding-big-top padding-big-bottom">支付方式类型</div>
			                <div class="panel-body fz16 padding-big-top padding-big-bottom line-big" id="payMethod">
		                    <input type="button" class="button margin-big-bottom x4 x1-move fz18 ${empty payMethod || payMethod == 'alipay' ? 'bg-sub' : null}" payMethod='alipay' value="支付宝">
		                    <input type="button" class="button margin-big-bottom x4 x1-move fz18 ${payMethod == 'swift' ? 'bg-sub' : null}" payMethod='swift' value="微信">
			                </div>
			            </div>
			            <a href="javascript:;" onclick="$(this).parent().submit()" class="tit" style="display: block;">确认支付</a>
		            </form>
		        </div>
		        <div class="mobile-foot">
		            <p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p>
		            <%@ include file="../common/quickNav.jsp"%>
		        </div>
		    </div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	<script type="text/javascript">
		$(function(){
			
			//设置选中样式
			$('#payMethod').children().click(function(){
				
				$(this).addClass('bg-sub');
				$('input[name="payMethod"]').val($(this).attr('payMethod'));
				$(this).siblings().removeClass('bg-sub');
			});
			/* if(TuYou.isWeiXin()){
				
				$('input[name="outer"]').val("false");
			}else{
				$('input[name="outer"]').val("true");
			} */
		});
	</script>
	<c:if test="${!empty sign}">
	<script type="text/javascript">
		if(TuYou.isWeiXin()){
			//alert("${url}");
			JM.goPage("${url}");
			
			//window.location = "${url}";
		}else{
			alert('请在微信里面支付');
		}
		
		/* var id = '${order.id }';
		if(!JM.isNull(id)){
		
		function checkStatus(){
				
				$.post(
					'pcOrder_orderStatus.action',
					{id : id},
					function(data){
						
						if(data.status == JM.YES){
							
							window.location = 'bjdjOrderTemplate_paySuccessPage.action?orderId=' + id;
						}
					},
					'json'
				);
			}
			$(function(){
				
				setInterval(checkStatus, 1000);
			});
		} */
		<%-- function pay(){
			WeixinJSBridge.invoke(
				'getBrandWCPayRequest',
				{"appId" : "${appid }","timeStamp" : "${timeStamp}", "nonceStr" : "${nonceStr}", "package" : "${packages}","signType" : "MD5", "paySign" : "${sign}" },
				function(res){

					//alert(res.err_code + "  " + res.err_desc + "  " + res.err_msg);
					if(res.err_msg == "get_brand_wcpay_request:ok"){  
						
						JM.goUrl("${actionName}_paySuccessPage.action?id=${order.id}");
					}else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
						
						JM.alert("用户取消支付!", 2000);  
		            }else{
		            	
						JM.alert("支付失败!", 2000);
		            }  
				});
				/* JM.alert("正在跳转~", 2000);
				window.location = "${url}"; */
		}
		
		if(TuYou.isWeiXin()){
			
			if (typeof WeixinJSBridge == "undefined"){
				if( document.addEventListener ){
					document.addEventListener('WeixinJSBridgeReady', pay, false);
				}else if (document.attachEvent){
					document.attachEvent('WeixinJSBridgeReady', pay); 
					document.attachEvent('onWeixinJSBridgeReady', pay);
				}
			}else{
				pay();
			}
		}else{
			
			alert('请在微信里面支付');
			<%//JM.goUrl("weixin://wap/pay?appid=${appid}&noncestr=${noncestr}&package=${package}&prepayid=${prepayid}&timestamp=${timestamp}&sign=${sign}");%>
		} --%>
	</script>
	</c:if>
</html>