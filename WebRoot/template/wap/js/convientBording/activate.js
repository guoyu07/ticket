$(function(){
	
	if(!JM.isNull(idcard)){
		
		$('#idCard').val(idcard);
//		queryFlightByCertificate();
//		$('#idCard').val(null);
	}
	
	checkidcard();
	$("#select").change(checkidcard);
	
	$("#memberForm01").Validform({
		btnSubmit : "#tijiao", 
		tiptype:tipTypeFun,
		ajaxPost:true,
		beforeSubmit : function(curform) {
			TuYou.Cookie.remove("flag");
			queryFlightByCertificate();
			return false;
		}
	});
	$("#memberForm02").Validform({
		tiptype:tipTypeFun,
		ajaxPost:true,
		callback:function(data){
			
			if (data.status == JM.YES) {
				var code = [];
				$('input[name="code"]').each(function(){
					
					code.push($(this).val());
				});
				window.location.href = $("#manageLink").attr("href") + "?codes=" + code;
				
			} else {
				
				if(!JM.isNull(data.info) && !JM.isNull(data.info.saturation)){
					
					openSaturation();
				}else{
					
					JM.alert(data.info, 3000);
					JM.flush();
				}
			}
		}
	});
	
	
});

function saveName(){
	
	/**
	 * 把填入的值保存在html里面
	 */
	$('input[name="name"]').unbind('change').change(function(){
		
		$(this).attr('value', $(this).val());
	});
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

/**
 * 取消激活
 */
function unactived(ids){
	
	$.post('bjdjServiceCodeActivate_unActivate.action', {idsValue : ids}, function(data){

		JM.alert(data.info, 3000, function(){
			
			if(data.status == JM.YES){
				
				JM.reload(null);
			}
		});
	}, 'json');
}

/**
 * 打开饱和提示框
 */
function openSaturation(){
	
	saturationIndex = $.do_dialog.open({
		'msg' : $('.saturation')
	});
}

/**
 * 饱和提示框
 */
function closeSaturation(){
	
	$.do_dialog.close(saturationIndex);
}

/**
 * 打开等待提示框
 */
function openWait(){
	
	waitIndex = $.do_dialog.open({
		'msg' : $('.wait')
	});
	
	setTimeout(function(){
		
		window.location = 'bjdjOrderTemplate_allOrder.action';
	}, 3000);
}

/**
 * 关闭等待提示框
 */
function closeWait(){
	
	$.do_dialog.close(waitIndex);
}

/**
 * 打开未值机航班提示框
 */
function openNoCheckIn(){
	
	noCheckInIndex = $.do_dialog.open({
		'msg' : $('.warn_msg2')
	});
}

/**
 * 关闭未值机航班提示框
 */
function closeNoCheckIn(){
	
	$.do_dialog.close(noCheckInIndex);
}

/**
 * 打开值机航班列表提示框
 */
function openCheckInList(){
	
	checkInListIndex = $.do_dialog.open({
		'msg' : $('.zj_ok')
	});
}

/**
 * 关闭值机航班列表提示框
 */
function closeCheckInList(){
	
	$.do_dialog.close(checkInListIndex);
}

/**
 * 打开值机航班修改提示框
 */
function openUpdateCheckIn(){
	
	updateCheckInIndex = $.do_dialog.open({
		'msg' : $('.warn_msg')
	});
}

/**
 * 关闭值机航班修改提示框
 */
function closeUpdateCheckIn(){
	
	$.do_dialog.close(updateCheckInIndex);
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
				focusFlightIndex = $.do_dialog.open({
					'msg' : $('.fucus_ok')
				});
			});
	
	//设置已经焦点所在的文本域对象
	focusFlight = $(dom);
}

/**
 * 关闭关注值机航班提示框
 */
function closeFocusFlight(){
	
	$.do_dialog.close(focusFlightIndex);
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
function openCommonTraveller(dom){
	
	$.post('commonTraveller_traverList.action',
			{},
			function(json){
				$('.commonTraveller .c_content .line').children().remove();
				for(var i = 0; i < json.length; i++){
					
					var html = '<div class="x5 text-center fz24"><label>'
						+ '<input type="hidden" value="' + json[i][1] + '"/>'
						+ '<input name="radio2" type="radio" class="d_checkbox" />' + json[i][0] + '</label>'
						+ '</div>';
					$('.commonTraveller .c_content .line').append(html);
				}
				commonTravellerIndex = $.do_dialog.open({
					'msg' : $('.commonTraveller')
				});
			});
	
	//设置已经焦点所在的文本域对象
	commonTraveller = $(dom);
}

/**
 * 关闭常用旅客卡提示框
 */
function closeCommonTraveller(){
	
	$.do_dialog.close(commonTravellerIndex);
	var flightNo = $('.commonTraveller .c_content .line input:checked');
	//设置航班号的值
	commonTraveller.attr('value', flightNo.parent().text().trim());
	//设置航班日期的值
	commonTraveller.parent().parent().prev().attr('value', flightNo.prev().val());
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
	var cardSubs = $("#items").find("input[id='cardValue']");
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
	$("input[id='flightNo']").each(function(index){
		
		flightNo.push($(this).val());
	});
	$.post('bjdjServiceCodeActivate_checkInFlight.action', 
			{idCard : idCard.val(), flightNo : flightNo},
			function(json){
				
				JM.alert('', 1);
				if(json.info.length == 0){  //没有值机航班的情况
					
					openNoCheckIn();
				}else{

					$('.zj_ok .c_content .line').children().remove();
					for(var i = 0; i < json.info.length; i++){
						
						var html = '<div class="x5 text-center fz24"><label><input name="radio1" type="radio" class="d_checkbox" />' + json.info[i] + '</label></div>';
						$('.zj_ok .c_content .line').append(html);
					}
					openCheckInList();
				}
//				$('#idCard').val(null);
			});
}

/**
 * 增加一个激活项
 */
function addActivateItem(){
	
	closeCheckInList();
	JM.alert("请求中..", 0);
	var code = [];
	$('input[name="code"]').each(function(){
		
		code.push($(this).val());
	});
	
	var idCard = $('#idCard');
	//获取选中的航班号
	var flightNo = $('.zj_ok .c_content .line input:checked');
	
	var cardType = $(".input-auto").find("option:selected").text();
	$.post(
			'bjdjServiceCodeActivate_addByCertificate.action',
			{orderId : orderId, idCard : idCard.val(), code : code.join(','), mid : mid, cardType : cardType, flightNo : flightNo.parent().text().trim()},
			function(json){
				
				try {
					if(json.info == '1'){
						
						JM.alert('最多只能同时激活5个人', 2000);
					}
					return;
				} catch (e) {}
					
				JM.alert('', 1, function(){
					
					$('#items').append(data);
					saveName();
					JM.scrollTo(".mobile-main", "#items dd:last", 140);
					openUpdateCheckIn();
				});
			}
	);
}

/**
 * 增加一个激活项
 */
function addBlankActivateItem(){
	
	closeNoCheckIn();
	JM.alert("请求中..", 0);
	var code = [];
	$('input[name="code"]').each(function(){
		
		code.push($(this).val());
	});
	
	var idCard = $('#idCard');
	var cardType = $(".input-auto").find("option:selected").text();
	$.post(
			'bjdjServiceCodeActivate_addBlank.action',
			{orderId : orderId, idCard : idCard.val(), code : code.join(','), mid : mid, cardType : cardType},
			function(html){
				
				try {
					var json = $.parseJSON(html);
					if(json.info == '1'){
						
						JM.alert('最多只能同时激活5个人', 2000);
					}
					return;
				} catch (e) {}
				
				JM.alert('', 1, function(){
					
					$('#items').append(html);
					saveName();
					JM.scrollTo(".mobile-main", "#items dd:last", 140);
					openUpdateCheckIn();
				});
			}
	);
}

/**
 * 删除一个激活信息
 * @param dom 删除按钮
 */
function removeInput(dom){
	
	var $button = $(dom);
	$button.parent().parent().parent().remove();
}

/**
 * 在激活页面新买一个服务码时，为了把当前页面的状态保存下来
 */
function newAdd(url){
	
	var html = $('#items').html();
	$.post('bjdjServiceCodeActivate_saveActivateItem.action',
			{
				html : html
			},
			function(){
				
				window.location = url;
			});
}

/**
 * 短信提示
 */
function smsTip(){
	
	//获取所有的服务码
	var params = '?';
	$('input[name="code"]').each(function(){
		
		params += "&code=";
		params += $(this).val();
	});
	//获取所有的航班时间
	$('input[name="date"]').each(function(){
		
		params += "&date=";
		params += $(this).val();
	});
	$.ajax({
		
		url : 'bjdjServiceCodeActivate_smsTip.action',
		data : params,
		type : 'POST',
		success : function(){
			
			closeSaturation();
			openWait();
		}
	});
}

/**
 * 排队激活
 */
function waitActivate(){
	
	$('input[name="checkQueueWait"]').val('false');
	$("#memberForm02").submit();
}

/**
 * 新增一个关注航班
 */
function addFocusFlight(){
	
	TuYou.Cookie.set("flag", "active");
	newAdd("airport/hangbanchaxun.ticket");
}