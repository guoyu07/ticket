$(function(){
	
	var $minus = $('.icon-minus-square-o');
	var $plus = $('.icon-plus-square-o');
	var $number = $('#number');
	var $count = $('input[name="count"]');
	var $electromobile_price = $('#electromobile_price');
	var $total_price = $('#total_price');
	var $mobile = $('#mobile');
	var $getCaptcha = $('#getCaptcha');
	var $loginBuy = $('#loginBuy');
	var $submitForm = $('#submitForm');
	var $agree = $('#agree');
	
	initListener();
	
	/**
	 * 初始化各交互节点的事件
	 */
	function initListener(){
		
		$minus.on('tap', minus);
		
		$plus.on('tap', plus);
		
		$loginBuy.change(loginBuy);
		
		$submitForm.submit(submit);
		$getCaptcha.on('tap', getCaptcha);
	}
	
	/**
	 * 点击登录购买
	 */
	function loginBuy(){
		
		var $item = $(this);
		if($item.prop('checked')){
			
			toLogin();
		}
	}
	
	/**
	 * 购买，提交表单
	 */
	function submit(){
		
		if($agree.prop('checked')){
			
			return true;
		}else{
			
			JM.alert('必须阅读并同意《免责条款》', 2000);
			return false;
		}
	}
	
	/**
	 * 购物数量减
	 */
	function minus(){
		
		var numberInt = parseFloat($number.text());
		if(numberInt > 1){
			
			//数量
			var nowNumber = numberInt - 1;
			$number.text(nowNumber);
			$count.val(nowNumber);
			
			//单价
			var price = parseFloat($electromobile_price.text());
			
			var total_price = price * nowNumber;
			$total_price.text(total_price);
		}else{
			
			$number.text(1);
			$count.val(1);
		}
	}
	
	/**
	 * 购物数量加
	 */
	function plus(){
		
		//数量
		var numberInt = parseFloat($number.text());
		numberInt += 1;
		$number.text(numberInt);
		$count.val(numberInt);
		
		//单价
		var price = parseFloat($electromobile_price.text());
		
		//总价
		$total_price.text(price * numberInt);
	};
	
	/**
	 * 获取验证码
	 */
	function getCaptcha(){
		
		var mobile = $mobile.val();
		if(!mobile || $.trim(mobile) == ''){
		
			JM.alert('请输入你的手机号！');
		}else if(mobile.length != 11){
			JM.alert('请输入正确的手机号码！');
		}else{
			
			$.post(
					'electromobile_generateAuthCode.action',
					{
						mobile : mobile
					},
					function(data){
						
						var text = $getCaptcha.val();
						var surplus = 60;
						var handler = setInterval(function(){
							
							if(surplus > 0){
								
								$getCaptcha.attr('disabled','disabled');
								$getCaptcha.val(--surplus + '秒后重新获取...');
							}else{
								
								$getCaptcha.removeAttr('disabled');
								$getCaptcha.val(text);
								clearInterval(handler);
							}
						}, 1000);
					}
			);
		}
	}
});