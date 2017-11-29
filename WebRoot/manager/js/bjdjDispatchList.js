/**
 * @descript 分单流程表专用JS
 * @author   HiSay
 * @datetime 2015-11-23 22:55:31
 */
 /*************************************************/

/**
 * @description 初始化分单流程表方法参数
 * @author HiSay
 * @datetime 2015-11-23 22:55:31
 */
jQuery(function(){
	editBjdjDispatchListInital();
	$("#searchBtn").bind("click", search);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-23 22:55:31
 */
function editBjdjDispatchListInital() {
	
	$('.batchAcceptBtn').click(function(){
		
		var ids = JM.getValueByCheckedName(jQuery(this).attr("value"));
		batchAcceptEntity(jQuery(this).attr("entityName"), ids, $(this).attr('methodName'));
	});
	
	$('.batchVerificateBtn').click(function(){
		
		var ids = JM.getValueByCheckedName(jQuery(this).attr("value"));
		batchVerificateEntity(jQuery(this).attr("entityName"), ids, $(this).attr('methodName'));
	});
}

function batchAcceptEntity(entityName, selectElementId, methodName, questionTitle, resultSucTitle, resultFailedTitle) {
//	var elementIds = JM.getValueByCheckedName(selectElementId);
//	//判断是否有选中要操作的信息
//	if(elementIds == null || elementIds == '') {
//		JM.alert('请选择您要批量接单的分单！');
//		return false;
//	}
	easyDialog.open({
		container : {
			header : '系统提示',
			content : questionTitle ? questionTitle : '你确定接单吗',
					yesFn : function(){
						var actionUrl = getActionUrlByEntity(entityName, methodName);
						jQuery.ajax({
							type:'POST',
							dataType:'json',
							url:actionUrl,
							data:'idsValue='+selectElementId+'&random='+JM.randomNumber,
							success:function(data){
								if(data.status == JM.YES){
									easyDialog.open({
										container : {
											header : '系统提示',
											content : resultSucTitle ? resultSucTitle : '接单成功！',
													yesFn : function(){
														window.location.reload();
													}
										},
										overlay : true,
										fixed : false,
										drag: false
									});
								} else if(data.status == JM.NO) {
									
									JM.alert(resultFailedTitle ? resultFailedTitle+data.info : '接单失败！'+data.info);
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

function batchVerificateEntity(entityName, selectElementId, methodName, questionTitle, resultSucTitle, resultFailedTitle) {
//   var elementIds = JM.getValueByCheckedName(selectElementId);
//   //判断是否有选中要操作的信息
//   if(elementIds == null || elementIds == '') {
//		JM.alert('请选择您要批量核销的分单！');
//		return false;
//   }
   easyDialog.open({
		container : {
			header : '系统提示',
			content : questionTitle ? questionTitle : '你确定核销吗<br/><textarea id="feedback" name="feedback" rows="" cols=""></textarea>',
			yesFn : function(){
				
		  		var actionUrl = getActionUrlByEntity(entityName, methodName);
		  		var feedback = $('#feedback').val();
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'idsValue='+selectElementId+'&random='+JM.randomNumber+'&feedback='+feedback,
		  			success:function(data){
		  				
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : resultSucTitle ? resultSucTitle : '核销成功！',
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					
		  					JM.alert(resultFailedTitle ? resultFailedTitle+data.info : '核销失败！'+data.info);
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

function acceptAllEntity(selectElementId, questionTitle, resultSucTitle, resultFailedTitle) {
//	var elementIds = JM.getValueByCheckedName(selectElementId);
//	//判断是否有选中要操作的信息
//	if(elementIds == null || elementIds == '') {
//		JM.alert('请选择您要批量接单的分单！');
//		return false;
//	}
	easyDialog.open({
		container : {
			header : '系统提示',
			content : questionTitle ? questionTitle : '你确定接单吗',
					yesFn : function(){
						var actionUrl = "bjdjDispatch_ing.action";
						jQuery.ajax({
							type:'POST',
							dataType:'json',
							url:actionUrl,
							data:'idsValue='+selectElementId+'&random='+JM.randomNumber,
							success:function(data){
								if(data.status == JM.YES){
									easyDialog.open({
										container : {
											header : '系统提示',
											content : resultSucTitle ? resultSucTitle : '接单成功！',
													yesFn : function(){
														window.location.reload();
													}
										},
										overlay : true,
										fixed : false,
										drag: false
									});
								} else if(data.status == JM.NO) {
									
									JM.alert(resultFailedTitle ? resultFailedTitle+data.info : '接单失败！'+data.info);
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

function verificateAllEntity(selectElementId, questionTitle, resultSucTitle, resultFailedTitle) {
//   var elementIds = JM.getValueByCheckedName(selectElementId);
//   //判断是否有选中要操作的信息
//   if(elementIds == null || elementIds == '') {
//		JM.alert('请选择您要批量核销的分单！');
//		return false;
//   }
	easyDialog.open({
		container : {
			header : '系统提示',
			content : questionTitle ? questionTitle : '你确定核销吗<br/><textarea id="feedback" name="feedback" rows="" cols=""></textarea>',
					yesFn : function(){
						
						var actionUrl = "bjdjDispatch_end.action";
						var feedback = $('#feedback').val();
						jQuery.ajax({
							type:'POST',
							dataType:'json',
							url:actionUrl,
							data:'idsValue='+selectElementId+'&random='+JM.randomNumber+'&feedback='+feedback,
							success:function(data){
								
								if(data.status == JM.YES){
									easyDialog.open({
										container : {
											header : '系统提示',
											content : resultSucTitle ? resultSucTitle : '核销成功！',
													yesFn : function(){
														window.location.reload();
													}
										},
										overlay : true,
										fixed : false,
										drag: false
									});
								} else if(data.status == JM.NO) {
									
									JM.alert(resultFailedTitle ? resultFailedTitle+data.info : '核销失败！'+data.info);
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

function search() {
	var methodName = $(this).attr("methodName");
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入服务码"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/bjdjDispatchList_'+methodName+'.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}