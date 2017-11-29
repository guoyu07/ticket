$(function(){
	
	$('.icon-minus-square-o').on('click', minus);
	$('.icon-plus-square-o').on('click', plus);
	$('.getCaptcha').on('click', getCaptcha);
	$('select[name="id"]').on('change', computePrice);
	$('select[name="id"]').trigger('change');
	
	/**
	 * 计算价格
	 */
	function computePrice(){
		
		//获取单价
		var singlePrice = parseFloat($('select[name="id"]').find(':selected').attr('price'));
		var numberInt = parseFloat($('#number').val());
		$('.bjdj_price').text(singlePrice);
		$('.total_price').text(singlePrice * numberInt);
	} 
	
	/**
	 * 购物数量减
	 */
	function minus(){
		
		var numberInt = parseFloat($('#number').val());
		if(numberInt > 1){
			
			//数量
			var nowNumber = numberInt - 1;
			$('#number').val(nowNumber);
			$('input[name="count"]').val(nowNumber);
			
			computePrice();
		}
	}
	
	/**
	 * 购物数量加
	 */
	function plus(){
		
		//数量
		var numberInt = parseFloat($('#number').val());
		numberInt += 1;
		$('#number').val(numberInt);
		$('input[name="count"]').val(numberInt);

		computePrice();
	};
});

/**
 * 获取验证码
 */
function getCaptcha(){
	
	var $getCaptcha = $('.getCaptcha');
	var $mobile = $('.mobile');
	
	var mobile = $mobile.val();
	if(!mobile || $.trim(mobile) == ''){
	
		JM.alert('请输入你的手机号！',2000);
	}else if(mobile.length != 11){
		
		JM.alert('请输入正确的手机号码！',2000);
		
	}else{
		
		$getCaptcha.eq(0).css('display', 'none');
		$getCaptcha.eq(1).css('display', 'inline');
		$.post(
				'bjdjOrderTemplate_generateAuthCode.action',
				{
					mobile : mobile
				},
				function(json){
					
					if(json.status == 'n'){
						
						return;
					}
					var text = $getCaptcha.eq(1).val();
					var surplus = 60;
					var handler = setInterval(function(){
						
						if(surplus > 0){
							
							$getCaptcha.eq(1).val(--surplus + '秒...');
						}else{
							
							$getCaptcha.eq(1).val(text);
							clearInterval(handler);
							$getCaptcha.eq(0).css('display', 'inline');
							$getCaptcha.eq(1).css('display', 'none');
						}
					}, 1000);
				}
		);
	}
}