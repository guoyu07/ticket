/**
 *登录，注册，注销用的js 
 */
$(function(){
	$("#memberForm1").Validform({
		btnSubmit : "#login", 
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
			login();
			return false;
		}
	});
	$("#yzm").click(sendAuthcodeLogin);
	
	$("#mobileForm").Validform({
		btnSubmit : "#login", 
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
		ajaxPost:true,
		beforeSubmit : function(curform) {
			mobileLogin();
			return false;
		}
	});
});

function login(){
	var loginId = $("#loginName").val();
	var pwd = $("#loginPwd").val();
	var url = $("#url").val();
	var reg = "airportPc.action";
	var indexUrl = $("#linkIndex").attr("href");
	$.ajax({
		url:"pcBjdjMember_login.action",
		type:"post",
		data:{"loginName":loginId,"loginPwd":pwd},
		dataType:"json",
		success:function(data){
			if(data.status == 'y'){
				JM.alert("登录成功", 2000, function(){
					if(url == "null"){
						JM.goUrl(indexUrl);
					}else{
						if(url.indexOf(reg) != -1){
							JM.goUrl(indexUrl);
						}else{
							JM.goUrl(url);
						}
					}
				});
			}else{
				alert(data.info);
			}
		},
		error:function(){
			alert("服务器出错了");
		}
	});
}
/**
 * 单独检验手机是否输入正确
 * @returns {Boolean}
 */
function checkPhone(){
	var isPhone = $("#mobileForm").Validform().check(false,"#mobile");
	if(!isPhone){
		return false;
	}
	return true;
}

function sendAuthcodeLogin(){
	var $getCaptcha = $("#getCaptchaLogin");
	var mobile = $("#mobileLogin").val();
	if(checkPhone()){
		$.ajax({
			url:"pcBjdjMember_generateAuthCode.action",
			type:"post",
			dataType:"json",
			data:{"phone":mobile},
			success:function(data){
				if(data.status == 'y'){
					var text = $getCaptcha.val();
					var surplus = 60;
					var handler = setInterval(function(){
						if(surplus > 0){
							$getCaptcha.show();
							$("#yzm").hide();
							$getCaptcha.attr('disabled','disabled');
							$getCaptcha.val(--surplus + '秒后重新获取...');
						}else{
							$getCaptcha.hide();
							$("#yzm").show();
							$getCaptcha.removeAttr('disabled');
							$getCaptcha.val(text);
							clearInterval(handler);
						}
					}, 1000);
				}else{
					JM.alert(data.info, 2000);
				}
			}
		});
	}
}

function mobileLogin(){
	var mobile = $("#mobileLogin").val();
	var authcode = $("#authcode").val();
	var url = $("#url").val();
	var reg = "airportPc.action";
	var indexUrl = $("#linkIndex").attr("href");
	$.ajax({
		url:"pcBjdjMember_checkAuthForMobile.action",
		type:"post",
		dataType:"json",
		data:{"phone":mobile,"authCode":authcode},
		success:function(data){
			if(data.status == 'y'){
				JM.alert("登录成功", 2000, function(){
					if(url == "null"){
						JM.goUrl(indexUrl);
					}else{
						if(url.indexOf(reg) != -1){
							JM.goUrl(indexUrl);
						}else{
							JM.goUrl(url);
						}
					}
				});
			}else{
				alert(data.info);
			}
		},
		error:function(){
			alert("服务器出错了");
		}
	});
}