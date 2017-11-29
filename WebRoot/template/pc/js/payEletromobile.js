/**
 * 电瓶车支付页面js
 */
$(function(){
//	$mobile = $("#mobile");
//	$getCaptcha = $("#getCaptcha");
//	$inputCaptcha = $("#inputCaptcha");
	
	total();
	$("#num").blur(total);
	$("#addSum").click(total);
	$("#cutSum").click(total);
	$("#hqyzm").click(getCaptcha);
	$('#loginBuy').change(loginBuy);
	
	var isLogin = $("#isLogin").attr("id");
	if(isLogin != undefined){
		isvalid();
//		isvalid1();
	}else{
		$("#go_pay").click(constructOrder2);
//		$("#baiduPay").click(constructOrder2);
	}
});


function loginBuy(){
	
	var $item = $(this);
	if($item.prop('checked')){
		
		JM.goUrl("pcBjdjMember.action");
	}
}
/**
 * 点击百度进入百度支付页面
 */
function isvalid1(){
	var $payForm = $('#payForm');
	$payForm.Validform({
		btnSubmit : "#baiduPay", 
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
			constructOrder();
			return false;
		}
	});
}
/**
 * 点击去支付进入支付页面
 */
function isvalid(){
	var $payForm = $("#payForm");
	$payForm.Validform({
		btnSubmit : "#go_pay", 
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
			constructOrder();
			return false;
		}
	});
}
/**
 * 计算总价
 */
function total(){
	var num = $("#num").val();
	var numInt = parseInt(num);
	
	var priceStr = $("#priceid").text();
	var price = priceStr.substring(0,priceStr.length - 1);
	var priceFloat = parseFloat(price);
	
	var total = numInt * priceFloat;
	
	$("#total").html("￥" + total);
}
/**
 * 单独检验手机是否输入正确
 * @returns {Boolean}
 */
function checkPhone(){
	var isPhone = $("#payForm").Validform().check(false,"#mobile");
	if(!isPhone){
		return false;
	}
	return true;
}
/**
 * 获取验证码
 */
function getCaptcha(){
	var $getCaptcha = $("#getCaptcha");
	var mobile = $("#mobile").val();
	if(checkPhone()){
		$.ajax({
			url:"pcElectromobile_generateAuthCode.action",
			type:"post",
			dataType:"json",
			data:{"mobile":mobile},
			success:function(data){
				if(data.status == 'y'){
					var text = $getCaptcha.val();
					var surplus = 60;
					var handler = setInterval(function(){
						if(surplus > 0){
							$getCaptcha.show();
							$("#hqyzm").hide();
							$getCaptcha.attr('disabled','disabled');
							$getCaptcha.val(--surplus + '秒后重新获取...');
						}else{
							$getCaptcha.hide();
							$("#hqyzm").show();
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
/**
 * 未登录的构造订单,生成订单，并跳转到百度支付页面
 */
function constructOrder(){
	var mobile = $("#mobile").val();
	var authCode = $("#inputCaptcha").val();
	var count = $("#num").val();
	if(isNan()){
		$.ajax({
			url:"pcElectromobile_constructOrder.action",
			type:"post",
			dataType:"json",
			data:{"mobile":mobile,"authCode":authCode,"count":count},
			success:function(data){
				if(data.status == 'y'){
					JM.alert("请求中...", 2000, JM.goUrl(data.info));
				}else{
					JM.alert(data.info, 2000);
				}
			},
			error:function(){
				alert(1);
			}
		});
	}
}
/**
 * 已经登录的构造订单
 */
function constructOrder2(){
	var count = $("#num").val();
	if(isNan()){		
		$.ajax({
			url:"pcElectromobile_constructOrder2.action",
			type:"post",
			dataType:"json",
			data:{"count":count},
			success:function(data){
				if(data.status == 'y'){
					JM.alert("请求中。。。", 2000, JM.goUrl(data.info));
				}else{
					JM.alert(data.info, 2000);
				}
			},
			error:function(){
				alert("服务器出错了");
			}
		});
	}
}
