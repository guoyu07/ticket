/**
 * @descript 服务码表专用JS
 * @author   HiSay
 * @datetime 2015-10-23 15:16:42
 */
 /*************************************************/

/**
 * @description 初始化服务码表方法参数
 * @author HiSay
 * @datetime 2015-10-23 15:16:42
 */
jQuery(function(){
	
	addOrEditSysDictionary();
	editBjdjServiceCodeInital();
	//清空表单
	$('input:reset').click(function(){
		
		$("input[name='state_id']").val(null);
		$("input[name='type_id']").val(null);
	});
	
//	var table = $('#table');
//	if(table){
//		
//		manage();
//	}
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-23 15:16:42
 */
function editBjdjServiceCodeInital() {
	jQuery(".batchFrozeBtn").bind("click", function(){
		batchFrozeEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"),$(this).attr('methodName'));
	});
	jQuery(".batchActivateBtn").bind("click", function(){
		batchActivateEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"),$(this).attr('methodName'));
	});
	jQuery(".batchDonationBtn").bind("click", function(){
		batchDonationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"),$(this).attr('methodName'));
	});
	jQuery(".batchUnFrozeBtn").bind("click", function(){
		batchUnFrozeEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"),$(this).attr('methodName'));
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-24 15:27:39
 */
function addOrEditSysDictionary() {
	
	//下拉列表自动填充
	var type = $('#type_id');
	var state_id = $('#state_id');
	//清空表单
	$('#resetBtn').click(function(){
		type.combotree('setValue', null);
	});
	type.combotree({
		onChange : function(newValue, oldValue){
			type.val(newValue);
		}
	});
	state_id.combotree({
		onChange : function(newValue, oldValue){
			state_id.val(newValue);
		}
	});
}
/**
 * 单个冻结
 */
function froze(dom){
	var id = $(dom).attr("attrValue");
	jQuery.ajax({
			type:'POST',
			dataType:'json',
			url:"bjdjServiceCode_froze.action",
			data:'idsValue='+id+'&random='+JM.randomNumber,
			success:function(data){
				if(data.status == JM.YES){
					JM.alert(data.info, 2000,function(){
						JM.reload("");
					});
				} else if(data.status == JM.NO) {
					JM.alert(data.info);
				}
			}
		});
}
/**
 * 单个解冻结
 * @param dom
 */
function unFroze(dom){
	var id = $(dom).attr("attrValue");
	jQuery.ajax({
		type:'POST',
		dataType:'json',
		url:"bjdjServiceCode_unFroze.action",
		data:'idsValue='+id+'&random='+JM.randomNumber,
		success:function(data){
			if(data.status == JM.YES){
				JM.alert(data.info, 2000,function(){
					JM.reload("");
				});
			} else if(data.status == JM.NO) {
				JM.alert(data.info);
			}
		}
	});
}
/**
 * 批量冻结
 * @param entityName
 * @param selectElementId
 * @param methodName
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchFrozeEntity(entityName, selectElementId, methodName,resultSucTitle, resultFailedTitle) {
	   var elementIds = JM.getValueByCheckedName(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : "是否批量冻结服务码？",
				yesFn : function(){
					var actionUrl = getActionUrlByEntity(entityName, methodName);
					jQuery.ajax({
			  			type:'POST',
			  			dataType:'json',
			  			url:actionUrl,
			  			data:'idsValue='+elementIds+'&random='+JM.randomNumber,
			  			success:function(data){
			  				if(data.status == JM.YES){
			  					JM.alert(data.info, 2000,function(){
			  						JM.reload("");
			  					});
			  				} else if(data.status == JM.NO) {
			  					JM.alert(data.info);
			  				}
			  			}
			  		});
					
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	   return false;
	}
/**
 * 批量解冻结
 * @param entityName
 * @param selectElementId
 * @param methodName
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchUnFrozeEntity(entityName, selectElementId, methodName,resultSucTitle, resultFailedTitle) {
	   var elementIds = JM.getValueByCheckedName(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : "是否批量解冻结服务码？",
				yesFn : function(){
					var actionUrl = getActionUrlByEntity(entityName, methodName);
					jQuery.ajax({
			  			type:'POST',
			  			dataType:'json',
			  			url:actionUrl,
			  			data:'idsValue='+elementIds+'&random='+JM.randomNumber,
			  			success:function(data){
			  				if(data.status == JM.YES){
			  					JM.alert(data.info, 2000,function(){
			  						JM.reload("");
			  					});
			  				} else if(data.status == JM.NO) {
			  					JM.alert(data.info);
			  				}
			  			}
			  		});
					
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	   return false;
	}
/**
 * 批量激活
 * @param entityName
 * @param selectElementId
 * @param methodName
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchActivateEntity(entityName, selectElementId,methodName,resultSucTitle, resultFailedTitle) {
	   var elementIds = JM.getValueByCheckedName(selectElementId);
	   var codes = getCodes(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : "是否批量激活服务码？",
				yesFn : function(){
					var actionUrl = getActionUrlByEntity(entityName, methodName);
					JM.goUrl(actionUrl+"?idsValue="+elementIds+"&codes="+codes);
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	   return false;
	}
/**
 * 批量转增
 * @param entityName
 * @param selectElementId
 * @param methodName
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchDonationEntity(entityName, selectElementId,methodName,resultSucTitle, resultFailedTitle) {
	   var elementIds = JM.getValueByCheckedName(selectElementId);
	   var codes = getCodes(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }
	   easyDialog.open({
			container : {
				header : '系统提示',
				content : "是否批量转增服务码？",
				yesFn : function(){
					var actionUrl = getActionUrlByEntity(entityName, methodName);
					JM.goUrl(actionUrl+"?idsValue="+elementIds+"&codes="+codes);
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	   return false;
	}

function getCodes(checkedName) {
 	try {
 		var checked = jQuery("input[name="+checkedName+"]:checked");
		if(checked.size() <= 0) {
			return null;
		} else {
			var str = "";
			jQuery("input[name="+checkedName+"]:checked").each(function(){
				str+= jQuery(this).attr("attrValue") + ",";
			});
			return str.substring(0, str.length-1);
		}
 	}catch(e){
 		return null;
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