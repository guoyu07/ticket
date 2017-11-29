$(function(){
	
	var $email = $('#email');
	var $getAuthBtn = $('#getAuthBtn');
	var $authCodeText = $("#authCode");
	$getAuthBtn.click(getCaptcha);
	
	/**
	 * 获取验证码
	 */
	function getCaptcha(){
		
		var email = $email.val();
		if(!email || $.trim(email) == ''){
		
			alert('请输入你的邮箱！');
		}else if(false){
			alert('请输入正确的邮箱！');
		}else{
			
			$.post(
					'bjdjMember_generateAuthCodeForEmail.action',
					{
						email : email
					},
					function(json){
						
						if(json.status == 'n'){
							
							alert(json.info);
							return;
						}
						
						var text = $getAuthBtn.val();
						var surplus = 60;
						var handler = setInterval(function(){
							
							if(surplus > 0){
								
								$getAuthBtn.attr('disabled','disabled');
								$getAuthBtn.val(--surplus + '秒后重新获取...');
							}else{
								
								$getAuthBtn.removeAttr('disabled');
								$getAuthBtn.val(text);
								clearInterval(handler);
							}
						}, 1000);
					}
			);
		}
	}
});