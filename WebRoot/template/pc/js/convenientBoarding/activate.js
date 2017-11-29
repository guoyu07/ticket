$(function(){
	
	code = $('input[name="code"]');
	
	checkidcard();
	$("#select").change(checkidcard);
	
	$("#cardForm").Validform({
		tiptype:tipTypeFun,
		ajaxPost:true,
		beforeSubmit : function(curform) {
			TuYou.Cookie.remove("flag");
			queryFlightByCertificate();
			return false;
		}
	});
});

/**
 * ValidForm的tipType自定义函数
 * @param msg
 * @param o
 * @param cssctl
 * @return
 */
function tipTypeFun (msg, o, cssctl) {
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
}

/**
 * 验证表单不能相同
 */
function checkCommon(name){
	
	var fields = $('input[name="'+name+'"]');
	for(var i = 0; i < fields.length; i++){
		
		var pre = $(fields[i]);
		for(var j = i+1; j < fields.length; j++){
			
			var next = $(fields[j]);
			if(pre.val() == next.val()){
				
				JM.alert(pre.val() + '存在相同的值，请改正', 2000);
				return true;
			}
		}
	}
	return false;
}

/**
 * 验证身份证
 */
function checkidcard(){
	
	var cardType = $("#optiondiv").find("option:selected").text();
	if(cardType == '身份证'){
		$("#idCard").attr("dataType","sf");
		$("#idCard").attr("errormsg","请输入正确的15位或18位证件号码");
	}else{
		$("#idCard").attr("dataType","*");
		$("#idCard").attr("errormsg","请输入正确的证件号码");
	}
}

function setName(){
	
	$('input[name="name"]').change(function(){
		
		var dom = $(this);
		dom.attr("value", dom.val());
	});
}

/**
 * 打开饱和提示框
 */
function openSaturation(){
	
	easyDialog.open({
		container : 'saturation'
	});
}

/**
 * 打开等待提示框
 */
function openWait(){
	
	easyDialog.open({
		container : 'wait'
	});
	
	setTimeout(function(){
		
		window.location = 'bjdjOrderTemplate_allOrder.action';
	}, 3000);
}

/**
 * 打开未值机航班提示框
 */
function openNoCheckIn(){
	
	easyDialog.open({
		container : 'warn_msg2'
	});
}

/**
 * 打开值机航班列表提示框
 */
function openCheckInList(){
	
	easyDialog.open({
		container : 'zj_ok'
	});
}

/**
 * 打开值机航班修改提示框
 */
function openUpdateCheckIn(){
	
	easyDialog.close();
	easyDialog.open({
		container : 'warn_msg'
	});
}

/**
 * 打开关注航班提示框
 */
function openFocusFlight(dom){
	
	var flightNo = [];
	$("input[id='flightNo']").each(function(index){
		
		flightNo.push($(this).val());
	});
	$.post('bjdjServiceCodeActivate_focusOnFlight.action',
			{flightNo : flightNo},
			function(json){
				
				$('.fucus_ok .c_content .line').children().remove();
				for(var i = 0; i < json.info.length; i++){
					
					var html = '<div class="x5 text-center fz24"><label>'
						 + '<input type="hidden" value="' + json.info[i][1] + '"/>'
						 + '<input name="radio2" type="radio" class="d_checkbox" />' + json.info[i][0] + '</label>'
						 + '</div>';
					$('.fucus_ok .c_content .line').append(html);
				}
				easyDialog.open({
					container : 'fucus_ok'
				});
			});
	
	//设置已经焦点所在的文本域对象
	focusFlight = $(dom);
}

/**
 * 关闭关注值机航班提示框
 */
function closeFocusFlight(){
	
	easyDialog.close();
	var flightNo = $('.fucus_ok .c_content .line input:checked');
	//设置航班号的值
	focusFlight.attr('value', flightNo.parent().text().trim());
	//设置航班日期的值
	focusFlight.parent().parent().next().children('.x8').children('input').val(flightNo.prev().val());
	focusFlight.parent().parent().next().children('.x8').children('input').attr('value', flightNo.prev().val());
}

/**
 * 打开常用旅客卡提示框
 */
function openCommonTraveller(){
	
	$.post('commonTraveller_traverList.action',
			{},
			function(json){
				
				$('#commonTraveller').find('.data').children().remove();
				for(var i = 0; i < json.length; i++){
					
					var html = '<div class="x5 text-center fz24"><label>'
						+ '<input type="hidden" value="' + json[i][1] + '"/>'
						+ '<input name="radio2" type="radio" class="d_checkbox" />' + json[i][0] + '</label>'
						+ '</div>';
					$('#commonTraveller').find('.data').append(html);
				}
				easyDialog.open({
					container : 'commonTraveller'
				});
			});
}

/**
 * 关闭常用旅客卡提示框
 */
function closeCommonTraveller(){
	
	easyDialog.close();
	var flightNo = $('#commonTraveller').find('.data').find('input:checked');
	//设置航班号的值
	$('#idCard').attr('value', flightNo.prev().val());
}

/**
 * 通过身份证等信息获取航班信息
 */
function queryFlightByCertificate(){
	
	JM.alert("请求中..", 0);
	var code = [];
	$('input[name="code"]').each(function(){
		
		code.push($(this).val());
	});
	if(code.length > 5){
		
		JM.alert('最多只能同时激活5个人', 2000);
	}
	
	var idCard = $('#idCard');
	var cardSubs = $("#items").find("input[name='cardValue']");
	if(!JM.isNull(cardSubs.val())){
		
		for ( var i = 0; i < cardSubs.length; i++) {
			
			var cardSub = $(cardSubs[i]);
			if (idCard.val() == cardSub.val()){
				
				JM.alert("此证件号码已提交过", 3000);
				return;
			}
		}
	}
	
	var flightNo = [];
	$("input[name='flightNo']").each(function(index){
		
		flightNo.push($(this).val());
	});
	$.post('pcServiceCode_checkInFlight.action', 
			{idCard : idCard.val(), flightNo : flightNo},
			function(json){
				
				JM.alert('', 1);
				if(json.info.length == 0){  //没有值机航班的情况
					
					openNoCheckIn();
				}else{

					$('#zj_ok').find('.data').children().remove();
					for(var i = 0; i < json.info.length; i++){
						
						var html = '<div class="x5 text-center fz24"><label><input name="radio1" type="radio" class="d_checkbox" />' + json.info[i] + '</label></div>';
						$('#zj_ok').find('.data').append(html);
					}
					openCheckInList();
				}
//				$('#idCard').val(null);
			});
}

/**
 * 通过身份证等信息添加激活信息
 */
function addActivateItem(){
	
	JM.alert("请求中..", 0);
	var code = '';
	$('input[name="code"]').each(function(){
		
		var item = $(this);
		code += item.val();
		code += ",";
	});
	
	var idCard = $('#idCard');
	if(JM.isNull(idCard.val())){
		
		JM.alert('请输入身份证号', 2000);
		return;
	}else if(idCard.val().length != 18){
		
		JM.alert('请输入正确的身份证号', 2000);
		return;
	}
	
	var flightNo = [];
	$("input[id='flightNo']").each(function(index){
		
		flightNo.push($(this).val());
	});
	var cardType = $("#select").find("option:selected").text();
	$.post(
			'pcServiceCode_addByCertificate.action',
			{orderId : orderId, idCard : idCard.val(), code : code, codeId : codeId,cardType : cardType},
			function(data){
				
				setName();
				JM.alert('', 1, function(){
					
					$('#items').after(data);
//					JM.scrollTo("#scrollTop", "#items tr:last", 140);
				});
			}
	);
}

/**
 * 增加一个激活项
 */
function addBlankActivateItem(){
	
	easyDialog.close();
	var code = [];
	$('input[name="code"]').each(function(){
		
		code.push($(this).val());
	});
	
	var idCard = $('#idCard');
	var cardType = $(".input-auto").find("option:selected").text();
	$.post(
			'pcServiceCode_addBlank.action',
			{orderId : orderId, idCard : idCard.val(), code : code.join(','), cardType : cardType},
			function(json){
				
				if(json.info == '1'){
					
					JM.alert('最多只能同时激活5个人', 2000);
				}
				return;
				
				$('#items').after(data);
				JM.scrollTo("body", "input[name='name']:last", 140);
				openUpdateCheckIn();
			}
	);
}

/**
 * 删除一个激活信息
 * @param dom 删除按钮
 */
function removeInput(dom){
	
	var $button = $(dom);
	var tr1 = $button.parent().parent();
	var tr2 = tr1.prev();
	var tr3 = tr2.prev();
	var tr4 = tr3.prev();
	var tr5 = tr4.prev();
	
	tr5.remove();
	tr4.remove();
	tr3.remove();
	tr2.remove();
	tr1.remove();
}

function newAdd(){
	
	var html = $('#items').html();
	$.post('bjdjServiceCodeActivate_saveActivateItem.action',
			{
				html : html
			},function(){
				
				window.location = 'pcOrder.action?type=newAdd';
			});
}