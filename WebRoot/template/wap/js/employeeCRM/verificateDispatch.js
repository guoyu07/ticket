//窗口句柄
var $handler;
//接单按钮
var $verificateBtn;
//确认接单按钮
var $verificateConfirmBtn;
//取消接单按钮
var $verificateCanelBtn;
//提交核销信息返回按钮
var $verificateSubmitBtn;

$(function(){
	
	//注册核销事件
	$verificateBtn = $('.verificate');
	$verificateBtn.on('tap', end);
	
	//注册对话框确认按钮事件
	$verificateConfirmBtn = $('.confirm_dialog .yes');
	$verificateConfirmBtn.on('tap', confirmVerification);
	
	//注册对话框取消按钮事件
	$verificateCanelBtn = $('.confirm_dialog .no');
	$verificateCanelBtn.on('tap', cancelVerification);
	
	//注册对话框提交按钮事件
	$verificateSubmitBtn = $('.question_dialog .no');
	$verificateSubmitBtn.on('tap', verificate);
});

/**
 * 核销确认框
 */
function end(){
	
//	$handler = $.do_dialog.open({'msg':$('.confirm_dialog')});
	easyDialog.open({
		  container : {
		    header : '核销',
		    content : '是否确认核销',
		    yesFn : confirmVerification,
		    noFn : true
		  }
		});
}

/**
 * 确认核销
 */
function confirmVerification(){
	
	$.do_dialog.close($handler);
	$handler = $.do_dialog.open({'msg':$('.question_dialog')});
}

/**
 * 取消核销
 */
function cancelVerification(){
  	 
    $.do_dialog.close($handler);
}

/**
 * 提交核销接单
 */
function verificate(){
	
	$.do_dialog.close($handler);
	var feedback = $('#feedback');
	
	alert(feedback.val());
	$.post('bjdjDispatchList_end.action',
			{
				idsValue : $verificateBtn.attr('value'),
				feedback : feedback.val()
			},
			function(json){
				
				if(json.status == 'y'){
					
					JM.alert(json.info, 2000, toWorkOrderPage);
				}else{
					
					JM.alert('核销失败：' + json.info, 3000);
				};
			});
 }

/**
 * 跳转到工单页
 */
function toWorkOrderPage(){
	
	window.location = "employeeCRM_workOrderPage.action";
}