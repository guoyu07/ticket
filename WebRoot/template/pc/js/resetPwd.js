$(function(){
	$("#quxiao").click(quxiao);
	var memberForm = $("#memberForm2");
	memberForm.Validform({
		btnSubmit : "#submitBtn", 
		ajaxPost:true,
		tiptype:function(msg, o, cssctl) {
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip = o.obj.siblings(".Validform_checktip");
				cssctl(objtip, o.type);
				objtip.text(msg);
			} else {
				var objtip = o.obj.find("#msgdemo");
				cssctl(objtip, o.type);
				objtip.text(msg);
			}
		},
		beforeSubmit : function(curform) {
			memberForm.find("textarea").each(function(){
				try {
					var obj=$(this);
					if(!JM.isNull(obj)){
						try {
							var objKE = eval(obj.attr("name"));
							objKE.sync();//sync textarea editor content to commom's textarea
						} catch(ee){}
					}
				} catch(e) {}
			});
			var alertConfirm = memberForm.attr("alertConfirm");
			if(!JM.isNull(alertConfirm)) {
				return true;
			} else {
				if(confirm('是否确认提交？')){
				   return true;	
				} else {
					return false;
				}
			}
		},
		callback:function(data){
			   if(data.status == JM.YES) {
				   JM.alert(data.info, 2000, function(){
					   
					   var manageLink = jQuery("#manageLink");
					   if(!JM.isNull(manageLink)){
						   
						   if(memberForm.attr('return') && memberForm.attr('return') == 'false'){
							   
							   window.location.replace(manageLink.attr("href"));
						   }else{
							   
							   window.location.href = manageLink.attr("href");
						   }
					   }
				   });
				   
			   } else {
				   JM.alert(data.info);
				   JM.flush();
			   }
			   return false;
			}
	});
	$("#yzm1").click(getCaptcha1);
});


/**
 * 获取验证码
 */
function getCaptcha1(){
	var $getCaptcha = $("#getCaptchaLogins");
	var $mobile = $("#mobile");
	var mobile = $mobile.val();
	if(!mobile || $.trim(mobile) == ''){
	
		JM.alert('请输入你的手机号！', 2000);
	}else if(mobile.length != 11){
		JM.alert('请输入正确的手机号码！', 2000);
	}else{
		
		$.post(
				'bjdjOrderTemplate_generateAuthCode.action',
				{
					mobile : mobile
				},
				function(data){
					
					var text = $getCaptcha.val();
					var surplus = 60;
					var handler = setInterval(function(){
						
						if(surplus > 0){
							
							$getCaptcha.show();
							$("#yzm1").hide();
							$getCaptcha.attr('disabled','disabled');
							$getCaptcha.val(--surplus + '秒后重新获取...');
						}else{
							
							$getCaptcha.hide();
							$("#yzm1").show();
							$getCaptcha.removeAttr('disabled');
							$getCaptcha.val(text);
							clearInterval(handler);
						}
					}, 1000);
				}
		);
	}
}

function quxiao(){
	var url = $("#url").val();
	JM.goUrl(url);
}