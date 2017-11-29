//窗口句柄
var $handler;
//接单按钮
var $receiveDispatchBtn;
//确认接单按钮
var $receiveDispatchConfirmBtn;
//取消接单按钮
var $receiveDispatchCanelBtn;

$(function(){
	
	//注册接单事件
	$receiveDispatchBtn = $('#receiveDispatchBtn');
	$receiveDispatchBtn.on('tap', ing);
	
	//注册对话框确认按钮事件
	$receiveDispatchConfirmBtn = $('#confirm_dialog_yes');
	$receiveDispatchConfirmBtn.on('tap', receiveDispatch);
	
	//注册对话框取消按钮事件
	$receiveDispatchCanelBtn = $('#confirm_dialog_no');
	$receiveDispatchCanelBtn.on('tap', cancelDispatch);
});

/**
 * 接单确认框
 */
function ing(){
	
//	$handler = $.do_dialog.open({
//		'msg':$('.confirm_dialog')
//	});
	
	easyDialog.open({
		  container : {
		    header : '接单',
		    content : '是否确认接单',
		    yesFn : receiveDispatch,
		    noFn : true
		  }
		});
}

/**
 * 确认接单
 */
function receiveDispatch(){
	
//	$.do_dialog.close($handler);
	$.post('bjdjDispatchList_ing.action',
			{idsValue : $receiveDispatchBtn.attr('value')},
			function(data){
	 
			var json = $.parseJSON(data);
			if(json.status == 'y'){
	 
				JM.alert(json.info, 2000, toWorkOrderPage);
			}else{
	 
				JM.alert('接单失败：' + json.info, 3000);
			};
		});
 }

/**
 * 取消接单
 */
function cancelDispatch(){
  	 
    $.do_dialog.close($handler);
}

/**
 * 跳转到工单页
 */
function toWorkOrderPage(){
	
	window.location = "employeeCRM_workOrderPage.action";
}