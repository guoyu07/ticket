//$(function(){
	
//	$('.icon-minus-square-o').on('tap', minus);
//	$('.icon-plus-square-o').on('tap', plus);
//	$('#getCaptcha').on('tap', getCaptcha);
//	$('.tc_list .tit').on('tap', selectPackage);
//	$('.tc_list .tit').eq(0).trigger('tap');
//
//	/**
//	 * 选择套餐
//	 */
//	function selectPackage(){
//		
//		$(this).addClass('border-dot').siblings().removeClass('border-dot');
//		$('input[name="id"]').val($(this).attr('package'));
//		computePrice();
//	};
//	
//	/**
//	 * 计算价格
//	 */
//	function computePrice(){
//		
//		//获取单价
//		var singlePrice = parseFloat($('.border-dot').attr('price'));
//		var numberInt = parseFloat($('#number').text());
//		$('#bjdj_price').text(singlePrice);
//		$('#total_price').text(singlePrice * numberInt);
//	} 
//	
//	/**
//	 * 购物数量减
//	 */
//	function minus(){
//		
//		var numberInt = parseFloat($('#number').text());
//		if(numberInt > 1){
//			
//			//数量
//			var nowNumber = numberInt - 1;
//			$('#number').text(nowNumber);
//			$('input[name="count"]').val(nowNumber);
//			
//			computePrice();
//		}
//	}
//	
//	/**
//	 * 购物数量加
//	 */
//	function plus(){
//		
//		//数量
//		var numberInt = parseFloat($('#number').text());
//		numberInt += 1;
//		$('#number').text(numberInt);
//		$('input[name="count"]').val(numberInt);
//
//		computePrice();
//	};
//	
//	/**
//	 * 获取验证码
//	 */
//	function getCaptcha(){
//		
//		$('#mobileSms').text('');
//		var mobile = $('#mobile').val();
//		if(!mobile || $.trim(mobile) == ''){
//		
//			JM.alert('请输入你的手机号！',2000);
//		}else if(mobile.length != 11){
//			
//			JM.alert('请输入正确的手机号码！',2000);
//		}else{
//			
//			$.post(
//					'bjdjOrderTemplate_generateAuthCode.action',
//					{
//						mobile : mobile
//					},
//					function(data){
//						
//						var json = $.parseJSON(data);
//						if(json.status == 'n'){
//							
//							$('#mobileSms').text(json.info);
//							return;
//						}
//						var text = $('#getCaptcha').val();
//						var surplus = 60;
//						var handler = setInterval(function(){
//							
//							if(surplus > 0){
//								
//								$('#getCaptcha').attr('disabled','disabled');
//								$('#getCaptcha').val(--surplus + '秒后重新获取...');
//							}else{
//								
//								$('#getCaptcha').removeAttr('disabled');
//								$('#getCaptcha').val(text);
//								clearInterval(handler);
//							}
//						}, 1000);
//					}
//			);
//		}
//	}
//});