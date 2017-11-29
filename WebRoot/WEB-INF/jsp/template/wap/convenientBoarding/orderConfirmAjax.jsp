<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<% String bjdjPage_id = (String) request.getParameter("bjdjPage_id"); %>
	<script type="text/javascript" src="/template/wap/js/convientBording/orderConfirm.js"></script>
	<body class="mobile quick">
		<div class="mobile-view">
			<div class="mobile-page">
				<jsp:include page="../common/title.jsp">
                	<jsp:param value="订单确认" name="title"/>
                </jsp:include>
				<div class="mobile-main">
					<div class="order_form">
						<form action="/${actionName}_generateOrder.action" method="post" id="memberForm">
							<a id="manageLink" href="/${actionName}_payPage.action?orderId" sytle="display: none;"></a>
							<input type="hidden" name="count" value="1" />
							<!-- 套餐id -->
							<input type="hidden" name="id" value="" /> 
							<div class='label'>
								<i class='fly_icon'></i>便捷登机 
								<span>
									<font class="c_red" id="bjdj_price">${bjdj_price}</font>RMB
								</span>
							</div> 
							<div class='label'>
								<i class='fly_icon'></i>选择套餐 
								<div class="clearfix tc_list">
									<s:iterator var="p" value="bjdjServicePackages" status="s">
									<div class="tit no-background border mrt20 <s:if test="#s.first">border-dot</s:if>" 
										price="${p.price }" package="${p.id }" onclick="JM.alert('${p.description }')">
										<a href="javascript:;" class="c_l_grey block">${p.name }</a>
									</div>
									</s:iterator>
								</div>
							</div>
							<div class='label'>
								数量 
								<span class='text-center line-large' style='width:100px'>
									<i class='icon-minus-square-o x4'></i>
                                    <div class="x4">
										<font id="number" class="c_red">1</font>
                                    </div>
									<i class='icon-plus-square-o x4'></i>
								</span>
							</div> 
							<div class='label'>
								<i class='count_icon'></i>总价 
								<span><font id="total_price" class="c_red">${bjdj_price}</font>RMB</span>
							</div>
							<div class="line clearfix mrlr40">
		                        <p style="margin-left: 0px;">
		                        	<input id="agree" type="checkbox" value="agree" name="agree" datatype="*"/>
		                        	<label for="agree" style="padding: 0;border: 0;">已阅读并同意</label>
		                        	<a href="/airport/${useSerms }.ticket" class='c_red'>《使用条款》</a>
		                        	<span class="Validform_checktip"></span>
		                        </p>
		                    </div>
		                    <s:if test="#session.sessionMember == null">
							<div>
                                <p>
    								<label for="notLoginBuy" style="padding: 0; border: 0;">
    									<input type="radio" value="notLogin" checked="checked" id="notLoginBuy" name="purchaseType">免登陆直接购买
    								</label>&nbsp;&nbsp;&nbsp;&nbsp;
    								<label for="loginBuy" style="padding: 0; border: 0;">
    									<input type="radio" value="login" id="loginBuy" name="purchaseType" onclick="location.href='bjdjMember_loginAjax.action?bjdjPage_id=<%=bjdjPage_id %>'">登陆购买
    								</label>
                                </p>
							</div>
							</s:if>
							<div class="line clearfix mr40">
			                    <s:if test="#session.sessionMember == null">
								<div class="x7">
									<input id="mobile" name="mobile" type="tel" class="input d_input b_l_grey" placeholder='请输入您的手机号' datatype="m">
									<div class="Validform_checktip"></div>
								</div>
								<div class="x1"></div>
								<div class="x4">
									<input type="button" id="getCaptcha" class="button d_button bg-yello block" value="获取验证码" datatype="*"/>
									<div class="Validform_checktip"></div>
								</div>
								<div class="x12 mrtb20">
									<input id='inputCaptcha' type="tel" name="authCode" class="input d_input b_l_grey block" placeholder='请输入您的验证码'>
									<s:fielderror cssStyle="float:left;">
										<s:param>authCode</s:param>
									</s:fielderror>
								</div>
								</s:if>
								<div class="x12">
									<input type="submit" id="submitBtn" class="button d_button bg-yello block" value="确认">
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="mobile-foot" style="height:0px">
				<!-- 	<p class='text-center fz12 padding-big-top'>COPYRIGHT YUNNAN
						AIRPORT GROUP CO.,LTD. ALL RIGHTS RESERVED</p> -->
						<%@ include file="../common/quickNav.jsp"%>
				</div>
				
			</div>
		</div>
		<div class="dialog" style="display:none;">
			<%@ include file="../common/left.jsp" %>
		</div>
	</body>
	<script type="text/javascript">
	$('.icon-minus-square-o').on('tap', minus);
	$('.icon-plus-square-o').on('tap', plus);
	$('#getCaptcha').on('tap', getCaptcha);
	$('.tc_list .tit').on('tap', selectPackage);
	$('.tc_list .tit').eq(0).trigger('tap');

	/**
	 * 选择套餐
	 */
	function selectPackage(){
		
		$(this).addClass('border-dot').siblings().removeClass('border-dot');
		$('input[name="id"]').val($(this).attr('package'));
		computePrice();
	};
	
	/**
	 * 计算价格
	 */
	function computePrice(){
		
		//获取单价
		var singlePrice = parseFloat($('.border-dot').attr('price'));
		var numberInt = parseFloat($('#number').text());
		$('#bjdj_price').text(singlePrice);
		$('#total_price').text(singlePrice * numberInt);
	} 
	
	/**
	 * 购物数量减
	 */
	function minus(){
		
		var numberInt = parseFloat($('#number').text());
		if(numberInt > 1){
			
			//数量
			var nowNumber = numberInt - 1;
			$('#number').text(nowNumber);
			$('input[name="count"]').val(nowNumber);
			
			computePrice();
		}
	}
	
	/**
	 * 购物数量加
	 */
	function plus(){
		
		//数量
		var numberInt = parseFloat($('#number').text());
		numberInt += 1;
		$('#number').text(numberInt);
		$('input[name="count"]').val(numberInt);

		computePrice();
	};
	
	/**
	 * 获取验证码
	 */
	function getCaptcha(){
		
		$('#mobileSms').text('');
		var mobile = $('#mobile').val();
		if(!mobile || $.trim(mobile) == ''){
		
			JM.alert('请输入你的手机号！',2000);
		}else if(mobile.length != 11){
			
			JM.alert('请输入正确的手机号码！',2000);
		}else{
			
			$.post(
					'bjdjOrderTemplate_generateAuthCode.action',
					{
						mobile : mobile
					},
					function(data){
						
						var json = $.parseJSON(data);
						if(json.status == 'n'){
							
							$('#mobileSms').text(json.info);
							return;
						}
						var text = $('#getCaptcha').val();
						var surplus = 60;
						var handler = setInterval(function(){
							
							if(surplus > 0){
								
								$('#getCaptcha').attr('disabled','disabled');
								$('#getCaptcha').val(--surplus + '秒后重新获取...');
							}else{
								
								$('#getCaptcha').removeAttr('disabled');
								$('#getCaptcha').val(text);
								clearInterval(handler);
							}
						}, 1000);
					}
			);
		}
	}
	</script>
</html>