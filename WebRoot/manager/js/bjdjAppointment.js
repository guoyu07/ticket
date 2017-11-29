/**
 * @descript 便捷登机预约表专用JS
 * @author   HiSay
 * @datetime 2015-11-23 22:48:35
 */
 /*************************************************/

/**
 * @description 初始化便捷登机预约表方法参数
 * @author HiSay
 * @datetime 2015-11-23 22:48:35
 */
jQuery(function(){
	editBjdjAppointmentInital();
	$("#searchBtn").bind("click", search);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-23 22:48:35
 */
function editBjdjAppointmentInital() {
	jQuery(".batchUnActived").bind("click", function(){
		batchUnActivedEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"),jQuery(this).attr("methodName"));
	});
}

function search() {
	var methodName = $(this).attr("methodName");
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入姓名/航班关键词"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/bjdjAppointment_'+methodName+'.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}

/**
 * 取消激活
 */
function unactived(ids){
	
	$.post('bjdjAppointment_unActivate.action', {idsValue : ids}, function(data){

		JM.alert(data.info, 3000, function(){
			
			if(data.status == JM.YES){
				
				JM.reload(null);
			}
		});
	}, 'json');
}

/**
 * 批量取消激活/预约
 * @param entityName
 * @param selectElementId
 * @param methodName
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchUnActivedEntity(entityName, selectElementId,methodName,resultSucTitle, resultFailedTitle){
	var elementIds = JM.getValueByCheckedName(selectElementId);
   //var codes = getCodes(selectElementId);
   //判断是否有选中要操作的信息
   if(elementIds == null || elementIds == '') {
		JM.alert('请选择您要批量操作的信息！');
		return false;
   }
   easyDialog.open({
		container : {
			header : '系统提示',
			content : "是否批量取消激活/预约？",
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


