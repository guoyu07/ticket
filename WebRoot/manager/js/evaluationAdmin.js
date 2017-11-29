$(function(){
	
	//批量送审
	$('.batchSendBtn').click(function(){
		
		batchSend(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个送审
	$('.sendBtn').click(function(){
		
		batchSend($(this).attr('value'));
	});
	
	//批量回复
	$('.batchFeedbackBtn').click(function(){
		
		batchFeedback(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个回复
	$('.feedbackBtn').click(function(){
		
		batchFeedback($(this).attr('value'));
	});
	
	//批量屏蔽
	$('.batchShieldBtn').click(function(){
		
		batchShield(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个屏蔽
	$('.shieldBtn').click(function(){
		
		batchShield($(this).attr('value'));
	});
	
	//批量发布
	$('.batchPublishBtn').click(function(){
		
		batchPublish(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个发布
	$('.publishBtn').click(function(){
		
		batchPublish($(this).attr('value'));
	});
	
	//批量取消发布
	$('.batchUnpublishBtn').click(function(){
		
		batchUnpublish(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个取消发布
	$('.unpublishBtn').click(function(){
		
		batchUnpublish($(this).attr('value'));
	});
	
	//批量发送到部门
	$('.batchSendDepartmentBtn').click(function(){
		
		sendToDepartment(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个发送到部门
	$('.sendDepartmentBtn').click(function(){
		
		sendToDepartment($(this).attr('value'));
	});
	
	//部门批量处理
	$('.batchProcessBtn').click(function(){
		
		departmentProcess(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//部门单个处理
	$('.processBtn').click(function(){
		
		departmentProcess($(this).attr('value'));
	});
	
	//批量回复部门
	$('.batchFeedbackDepartmentBtn').click(function(){
		
		feedbackDepartment(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个回复部门
	$('.feedbackDepartmentBtn').click(function(){
		
		feedbackDepartment($(this).attr('value'));
	});
	
	//批量置顶
	$('.batchTopBtn').click(function(){
		
		batchTop(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个置顶
	$('.topBtn').click(function(){
		
		batchTop($(this).attr('value'));
	});
	
	//批量发回
	$('.batchSendbackBtn').click(function(){
		
		sendback(JM.getValueByCheckedName(actionName + 'CheckBox'));
	});
	
	//单个发回
	$('.sendbackBtn').click(function(){
		
		sendback($(this).attr('value'));
	});
	
	//设置选择的部门id，以便上传到服务器
	if($('.department_tree').size() > 0){
		
		$('.department_tree').tree({
			
			onCheck : function(node, checked){
				
				var checkedNodes = $('.department_tree').tree('getChecked');
				var department_id = '';
				for(var i = 0; checkedNodes && i < checkedNodes.length; i++){
					
					department_id += checkedNodes[i].id;
					department_id += ",";
				}
				department_id = department_id.substring(0, department_id.length-1);
				$('input[name="department_id"]').val(department_id);
			}
		});
	}
	
	//选中评论模板的时候，插入到textarea里面
	if($('.easyui-datalist').size() > 0){
		
		$('.easyui-datalist').datalist({
			
			onClickRow : function(index, row){
				
				var text = $('textarea[name="content"]');
				var index = TuYou.getCursortPosition(text.get(0));
				
				var start = text.val().substring(0, index);
				var end = text.val().substring(index);
				var newIndex = start.length + row.value.length;
				
				text.val(start + row.value + end);
				TuYou.setCaretPosition(text.get(0), newIndex);
			}
		});
	}
});

/**
 * 批量送审
 */
function batchSend(ids){
	
	batchOperation(actionName + '_send.action', ids, '你确定送审吗？', null, false);
}

/**
 * 批量回复
 */
function batchFeedback(ids){
	
//	batchOperation(actionName + '_feedback.action', ids, '你确定回复吗？', null, true);
	window.location = actionName + '_feedbackPage.action?idsValue='+ids;
}

/**
 * 批量屏蔽
 */
function batchShield(ids){
	
	batchOperation(actionName + '_shield.action', ids, '你确定屏蔽吗？', null, false);
}

/**
 * 批量发布
 */
function batchPublish(ids){
	
	batchOperation(actionName + '_publish.action', ids, '你确定发布吗？', null, false);
}

/**
 * 批量取消发布
 */
function batchUnpublish(ids){
	
	batchOperation(actionName + '_unpublish.action', ids, '你确定取消发布吗？', null, false);
}

/**
 * 批量发送到部门
 */
function sendToDepartment(ids){
	
	window.location = actionName + '_sendToDepartmentPage.action?idsValue='+ids;
}

/**
 * 批量处理评论意见
 */
function departmentProcess(ids){
	
	window.location = actionName + '_departmentProcessPage.action?idsValue='+ids;
}

/**
 * 批量回复部门
 */
function feedbackDepartment(ids){
	
//	window.location = actionName + '_feedbackDepartmentPage.action?idsValue='+ids;
	batchOperation(actionName + '_feedbackDepartment.action', ids, '你确定追发部门吗？', null, false);
}

/**
 * 批量置顶部门
 */
function batchTop(ids){
	
	batchOperation(actionName + '_topOrNot.action', ids, '你确定置顶吗？', null, false);
}

/**
 * 批量发回
 */
function sendback(ids){
	
	batchOperation(actionName + '_sendBack.action', ids, '你确定发回吗？', null, false);
}
