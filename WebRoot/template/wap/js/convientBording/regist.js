$(function(){
	
	var $agree = $('#agree');
	var $form = $('#commonForm');
	var $getCaptcha = $('#getCaptcha');
	var $mobileSms = $('#mobileSms');
	var $mobile = $('#phone');
	
	initListener();
	
	/**
	 * 初始化各交互节点的事件
	 */
	function initListener(){
		
		$getCaptcha.click(getCaptcha);
	}
	
	function getCaptcha(){
		
		$mobileSms.text('');
		var mobile = $mobile.val();
		if(!mobile || $.trim(mobile) == ''){
		
			alert('请输入你的手机号！');
		}else if(mobile.length != 11){
			
			alert('请输入正确的手机号码！');
		}else{
			
			$.post(
					'bjdjMember_generateAuthCode.action',
					{
						phone : mobile
					},
					function(json){
						
						if(json.status == 'n'){
							
							$mobileSms.text(json.info);
							return;
						}
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
	
	$form.submit(function(){
		
		if(!$agree.prop('checked')){
			
			alert('请阅读并接受《昆明长水机场用户注册协议》');
			return false;
		}
		return true;
	});
});