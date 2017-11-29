/**
 * @descript 后台各个实体设置CommonEntity里面属性的共用JS
             1. 可以单个（或者批量）设置实体的审核、热门、精华、推荐等。
             2. 可以单个（或者批量）删除实体。
 * @author   HiSay
 * @datetime 2013-07-25 11:20
 */
/*************************************************/

jQuery(function(){
	initEntityButtonClick();
	initValidForm();
	markIndexInit();
	sendSmsInit();
	commonSingleRequest();
	commonBatchRequest();
	showPackageDetail();
});

/**
 * @description 初始化实体按钮的点击事件
 * @author HiSay
 * @datetime 2013-07-31 10:12:37
 */
function initEntityButtonClick() {
	//jQuery(":checkbox").removeAttr("checked");
	
	//绑定热门按钮的事件
	$(".hotEntity").bind("click", function(){
		hotEntity($(this).attr("entityName"), $(this).attr("value"), $(this).attr("statusValue"),
				$(this).attr("tip"),$(this).attr("sucTip"),$(this).attr("failTip"));
	});
	
	//绑定返回按钮的事件
	jQuery("#returnBtn").bind("click", function(){JM.back();});
	
	//绑定刷新本页的事件
	jQuery("#reloadBtn").bind("click", function(){JM.reload();});
	
	//绑定逻辑删除(放入回收站)按钮的事件
	jQuery(".logicDeleteBtn").bind("click", function(){
		logicDeleteEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"));
	});
	
	//绑定还原按钮的事件
	jQuery(".restoreBtn").bind("click", function(){
		restoreEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"));
	});
	
	//绑定彻底删除按钮的事件
	jQuery(".realDeleteBtn").bind("click", function(){
		realDeleteEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), $(this).attr('method'));
	});
	
	//绑定审核按钮的事件
	jQuery(".auditEntity").bind("click", function(){
		auditEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), jQuery(this).attr("statusValue"));
	});
	
	//绑定列表页全选的事件
	jQuery("#selectCheckBoxAllChk").bind("click", function(){
		var obj = jQuery(this);
		/*if(obj.attr("objectChkName") != '') {
			JM.checkAll(jQuery(this).attr("objectChkName"), jQuery(this).attr("id"));
		} else {
			JM.checkAll("ids", jQuery(this).attr("id"));
		}*/
		var name = jQuery(this).attr("objectChkName");
		if(obj.prop("checked")){
			jQuery("input[name='"+name+"']").prop("checked",true);
			//JM.checkAll(jQuery(this).attr("objectChkName"), jQuery(this).attr("id"));
		}else{
			jQuery("input[name='"+name+"']").prop("checked",false);
			//JM.checkAll("ids", jQuery(this).attr("id"));
		}
	});
	
	//绑定批量逻辑删除(放入回收站)按钮的事件
	jQuery(".batchLogicDeleteBtn").bind("click", function(){
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'logicDelete', '是否确认把选中信息放入回收站？', '信息批量放入回收站成功！', '信息批量放入回收站失败！');
	});
	//绑定批量还原按钮的事件
	jQuery(".batchRestoreBtn").bind("click", function(){
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'restore', '是否确认还原选中信息？', '批量还原信息成功！', '批量还原信息失败！');
	});
	//绑定批量彻底删除按钮的事件
	jQuery(".batchRealDeleteBtn").bind("click", function(){
		batchRealDeleteEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"));
	});
	//绑定批量审核按钮的事件
	jQuery(".batchAuditBtn").bind("click", function(){
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'audit', '是否确认审核选中信息？', '批量审核信息成功！', '批量审核信息失败！');
	});
	//绑定批量推荐按钮的事件
	jQuery(".batchCommendBtn").bind("click", function(){
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'commend', '是否确认设置选中信息为推荐信息？', '批量设置推荐信息成功！', '批量设置推荐信息失败！');
	});
	//绑定批量热门按钮的事件
	jQuery(".batchHotBtn").bind("click", function(){
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'hot', '是否确认设置选中信息为热门信息？', '批量设置热门信息成功！', '批量设置热门信息失败！');
	});
}

/**
 * @description 根据实体名称和方法名称获取请求的action地址
 * @author HiSay
 * @datetime 2013-07-29 15:25
 */
function getActionUrlByEntity(entityName, methodName) {
	var entityNameLowerCase = JM.firstCharacterToLowerCase(entityName);
	//var actionUrl = '/' + entityNameLowerCase + '_' + methodName + entityName + '.action';
	var actionUrl = '/' + entityNameLowerCase + '_' + methodName + '.action';
	return actionUrl;
} 

/**
 * @descript 逻辑删除单个实体对象(放入回收站)
 * @param entityName 实体类别名称 例如:Module,Message.等.
 * @param entityId   实体对象ID
 * @return  
 */
function logicDeleteEntity(entityName, entityId) {
   easyDialog.open({
		container : {
			header : '系统提示',
			content : '是否把此条信息放入回收站？',
			yesFn : function(){
			   var actionUrl = getActionUrlByEntity(entityName, 'logicDelete');
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'id='+entityId+'&random='+JM.randomNumber,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : '信息放入回收站成功！',
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					JM.alert('信息放入回收站失败！');
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
function hotEntity(entityName, entityId, entityStatusValue,tip,sucTip,failTip) {
	
//	JM.confirm('是否确认推荐为热门信息？', function(){
//		
//		var actionUrl = getActionUrlByEntity(entityName, 'hot');
//		$.ajax({
//			type:'POST',
//			dataType:'json',
//			url:actionUrl,
//			data:'id='+entityId+'&statusValue='+entityStatusValue+'&random='+JM.randomNumber,
//			success:function(data){
//				
//				if(data.status == JM.YES){
//					
//					JM.alert('推荐热门信息成功！', 1500, function(){
//						
//						window.location.reload();
//					});
//				} else if(data.status == JM.NO) {
//					
//					JM.alert('推荐热门信息失败！', 1500);
//				}
//			}
//		});
//	});
	easyDialog.open({
		container : {
			header : '系统提示',
			content : tip,
			yesFn : function(){
			   var actionUrl = getActionUrlByEntity(entityName, 'hot');
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'id='+entityId+'&statusValue='+entityStatusValue+'&random='+JM.randomNumber,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : sucTip,
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					JM.alert(failTip);
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
 * @descript 物理删除单个实体对象
 * @param entityName 实体类别名称 例如:Module,Message.等.
 * @param entityId   实体对象ID
 * @return  
 */
function realDeleteEntity(entityName, entityId, method) {
   easyDialog.open({
		container : {
			header : '系统提示',
			content : '删除后不可恢复！是否确认删除此条信息？',
			yesFn : function(){
			   var actionUrl = getActionUrlByEntity(entityName, JM.isNull(method) ? 'realDelete' : method);
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'id='+entityId+'&random='+JM.randomNumber,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : '删除信息成功！',
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					JM.alert(data.info,2000);
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
 * @descript 通用的服务端调用(单条数据操作)
 * @return  
 */
function commonSingleRequest() {
	
	$('.singleRequest').click(function(){
		
		var item = $(this);
		if(!JM.isNull(item.attr('redirect'))){
			
			var href = item.attr('url');
			if(href.indexOf('?') > -1){
				
				window.location = item.attr('url') + '&id=' + item.attr('id');
			}else{
				
				window.location = item.attr('url') + '?id=' + item.attr('id');
			}
		}else{
			
			easyDialog.open({
				container : {
					header : '提示',
					content : '你确定操作吗？',
					yesFn : function(){
						jQuery.ajax({
							type:'POST',
							dataType:'json',
							url:item.attr('url'),
							data:'id=' + item.attr('id'),
							success:function(data){
								if(data.status == JM.YES){
									easyDialog.open({
										container : {
											header : '提示',
											content : '操作成功！',
											yesFn : function(){
												window.location.reload();
											}
										},
										overlay : true,
										fixed : false,
										drag: false
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
		}
	});
	return false;
}

/**
 * @descript 通用的服务端调用(多条数据操作)
 * @return  
 */
function commonBatchRequest() {
	
	$('.batchRequest').click(function(){
		
		var item = $(this);
		var elementIds = JM.getValueByCheckedName(item.attr('value'));
		if(!JM.isNull(item.attr('redirect'))){
			
			var href = item.attr('url');
			if(href.indexOf('?') > -1){
				
				window.location = item.attr('url') + '&id=' + elementIds;
			}else{
				
				window.location = item.attr('url') + '?id=' + elementIds;
			}
		}else{
			
			easyDialog.open({
				container : {
					header : '提示',
					content : '你确定操作吗？',
					yesFn : function(){
						jQuery.ajax({
							type:'POST',
							dataType:'json',
							url:item.attr('url'),
							data:'id=' + elementIds,
							success:function(data){
								if(data.status == JM.YES){
									easyDialog.open({
										container : {
											header : '提示',
											content : '操作成功！',
											yesFn : function(){
												window.location.reload();
											}
										},
										overlay : true,
										fixed : false,
										drag: false
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
		}
	});
	return false;
}

/**
 * @descript 从回收站单个还原实体对象
 * @param entityName 实体类别名称 例如:Module,Message.等.
 * @param entityId   实体对象ID
 * @return  
 */
function restoreEntity(entityName, entityId) {
   easyDialog.open({
		container : {
			header : '系统提示',
			content : '是否确认还原此信息？',
			yesFn : function(){
			   var actionUrl = getActionUrlByEntity(entityName, 'restore');
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'id='+entityId+'&random='+JM.randomNumber,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : '还原信息成功！',
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					JM.alert('还原信息失败！');
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
 * @descript 审核实体对象
 * @param entityName 实体类别名称 例如:Module,Message.等.
 * @param entityId   实体对象ID
 * @param entityStatusValue  实体的audit状态值
 * @return  
 */
function auditEntity(entityName, entityId, entityStatusValue) {
   easyDialog.open({
		container : {
			header : '系统提示',
			content : '是否确认审核此信息？',
			yesFn : function(){
			   var actionUrl = getActionUrlByEntity(entityName, 'audit');
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'id='+entityId+'&statusValue='+entityStatusValue+'&random='+JM.randomNumber,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : '审核信息成功！',
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					JM.alert('审核信息失败！');
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
 * @descript 批量物理删除多个实体对象
 * @param entityName 实体类别名称 例如:Module,Message.等.
 * @param selectElementId 复选框的name值 
 * @return  
 */
function batchRealDeleteEntity(entityName, selectElementId) {
   var elementIds = JM.getValueByCheckedName(selectElementId);
   //判断是否有选中要操作的信息
   if(elementIds == null || elementIds == '') {
		JM.alert('请选择您要批量操作的信息！');
		return false;
   }
   easyDialog.open({
		container : {
			header : '系统提示',
			content : '删除后不可恢复，是否批量删除信息？',
			yesFn : function(){
	   			var isChecked = jQuery("#isChecked");
			    var actionUrl = getActionUrlByEntity(entityName, 'batchRealDelete');
		 		jQuery.ajax({
		 			type:'POST',
		 			dataType:'json',
		 			url:actionUrl,
		 			data:'idsValue='+elementIds+'&random='+JM.randomNumber,
		 			success:function(data){
		 				if(data.status == JM.YES){
		 					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : '批量删除信息成功！',
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		 				} else if(data.status == JM.NO) {
		 					JM.alert('批量删除信息失败！');
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
 * @descript 批量操作多个实体对象为审核、热门、推荐、还原或者逻辑删除等操作。
 * @param entityName 实体类别名称 例如:Module,Message.等.
 * @param selectElementId 复选框的name值 
 * @param batchOperationType 操作类型 (例如: audit,commend,hot,logicDelete,restore)
 * @param questionTitle 弹出窗口的问题
 * @param resultSucTitle 设置成功后的文字提示
 * @param resultFailedTitle 设置失败后的文字提示
 * @return  
 */
function batchOperationEntity(entityName, selectElementId, batchOperationType, questionTitle, resultSucTitle, resultFailedTitle) {
   var elementIds = JM.getValueByCheckedName(selectElementId);
   //判断是否有选中要操作的信息
   if(elementIds == null || elementIds == '') {
		JM.alert('请选择您要批量操作的信息！');
		return false;
   }
   easyDialog.open({
		container : {
			header : '系统提示',
			content : questionTitle,
			yesFn : function(){
			   var isChecked = jQuery("#isChecked");
		  		var actionUrl = getActionUrlByEntity(entityName,'batchOperation');
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:actionUrl,
		  			data:'idsValue='+elementIds+'&batchOperationType='+batchOperationType+'&isChecked='+isChecked.is(":checked")+'&random='+JM.randomNumber,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : resultSucTitle,
		  							yesFn : function(){
		  								window.location.reload();
		  							}
		  						},
		  						overlay : true,
		  						fixed : false,
		  						drag: false
		  					});
		  				} else if(data.status == JM.NO) {
		  					JM.alert(resultFailedTitle);
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
 * 批量操作
 * @param url 要提交的到的action
 * @param selectElementId 复选框的值
 * @param tipMsg 弹出窗口的提示信息
 * @param params 提交的额外参数
 * @param showConfirmWindow 是否显示textarea填写信息
 * @returns {Boolean}
 */
function batchOperation(url, elementIds, tipMsg, params, showWindow, callback) {
//	var elementIds = JM.getValueByCheckedName(selectElementId);
	//判断是否有选中要操作的信息
	if(elementIds == null || elementIds == '') {
		JM.alert('请选择您要批量操作的信息！');
		return false;
	}
	if(!params){
		
		params = {};
	}
	params.idsValue = elementIds;
	params.isChecked = $("#isChecked").is(":checked");
	
	if(showWindow == true){
		
		var $textarea = '<textarea id="-feedback-" style="width: 250px; height: 50px; margin-top:5px;"></textarea>';
		easyDialog.open({
			container : {
				header : '填写信息',
				content : tipMsg ? tipMsg + '<br/>' + $textarea : $textarea,
				yesFn : function(){
					
			  		var feedback = $('#-feedback-').val();
			  		params.content = feedback;
			  		post();
				},
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	}else{
		
		easyDialog.open({
			container : {
				header : '系统提示',
				content : tipMsg,
				yesFn : post,
				noFn : true
			},
			overlay : true,
			fixed : false,
			drag: false
		});
	}
	
	/**
	 * 提交参数到服务器
	 */
	function post(){
		
		jQuery.ajax({
			type:'POST',
			dataType:'json',
			url:url,
			data:$.param(params),
			success:function(data){
				if(data.status == JM.YES){
					
					if(callback){
						
						callback(data.info);
					}else{
						
						easyDialog.open({
							container : {
								header : '系统提示',
								content : '操作成功！',
								yesFn : function(){
									window.location.reload();
								}
							},
							overlay : true,
							fixed : false,
							drag: false
						});
					}
				} else if(data.status == JM.NO) {
					JM.alert(data.info);
				}
			}
		});
	}
	return false;
}


/**
 * @description 执行回调函数
 * @author HiSay
 * @param funName 函数名称
 * @datetime 2013-07-31 10:45
 */
function executeCallBackFun(funName) {
	if(funName != null && funName != '' && funName != undefined) {
		JM.executeMethod(funName);
	}
}

/**
 * @description 添加或编辑后回调方法
 * @author HiSay
 * @datetime 2014-10-11 07:54:09
 */
function addOrEditSuc() {
	var manageLink = jQuery("#manageLink");
	JM.goUrl(manageLink.attr("href"));
}

/**
 * @description 根据textareaName创建简单的KindEditor编辑器
 * @author HiSay
 * @datetime 2013-08-08 11:11:27
 */
function createSimpleEditor(textareaName) {
	var editor = KindEditor.create('textarea[name="'+textareaName+'"]', {
		resizeType : 1,
		uploadJson : '/upload.action',
		fileManagerJson : '/uploadFileManager.action',
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
	return editor;
}

/**
 * @description 根据textareaName创建普通的KindEditor编辑器
 * @author HiSay
 * @datetime 2013-08-08 11:11:27
 */
function createEditor(textareaName) {
	var editor = KindEditor.create('textarea[name="'+textareaName+'"]', {
		uploadJson : '/upload.action',
		fileManagerJson : '/uploadFileManager.action'	});
	return editor;
}

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
 * 身份证的验证方法
 * @param gets
 * @param obj
 * @param curform
 * @param regxp
 * @returns {Boolean}
 */
function yzsfz(gets,obj,curform,regxp){
    //参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
    var rgp1 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    var rgp2 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    if(rgp1.test(gets) || rgp2.test(gets)){
        if(gets.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=gets.substring(i,i+1)*idCardWi[i];
            }

            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=gets.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                    return true;
                }else{
                    return false;
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                   return true;
                }else{
                    return false;
                }
            }
        }
        if(gets.length==15){
            var year =  gets.substring(6,8);
            var month = gets.substring(8,10);
            var day = gets.substring(10,12);
            var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
            // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
            if(temp_date.getYear()!=parseFloat(year)||temp_date.getMonth()!=parseFloat(month)-1||temp_date.getDate()!=parseFloat(day)){
                return false;
            }else{
                return true;
            }
        }
    }else{
        return false;
    }

}
/**
 * @description initial the commonForm to valid and submit.
 * @author HiSay
 * @datetime 2014-10-10 07:41:27
 */
function initValidForm() {
	var commonForm = jQuery("#commonForm");
	commonForm.Validform({
		tiptype:tipTypeFun,
		ajaxPost:true,
		datatype:{
			"double":/^$(\d{1,4})|^\d+(\.\d{1,5})?$/,
			"sf" : yzsfz,
			"np" : /^\d*$/ //非负数的验证
		},
		beforeSubmit:function(curform){
			commonForm.find("textarea").each(function(){
				try {
					var obj=$(this);
					if(!JM.isNull(obj)){
						try {
							var objKE = eval(obj.attr("name"));
							objKE.sync();//sync textarea editor content to commom's textarea
						} catch(ee){}
					}
				} catch(e) {}
			});
			var alertConfirm = commonForm.attr("alertConfirm");
			if(!JM.isNull(alertConfirm)) {
				return true;
			} else {
				if(confirm('是否确认提交？')){
				   return true;	
				} else {
					return false;
				}
			}
		},callback:function(data){
		   if(data.status == JM.YES) {
			   JM.alert(data.info, 3000, function(){
				   
				   var manageLink = jQuery("#manageLink");
				   if(!JM.isNull(manageLink) && manageLink.size() > 0){
					   var href = manageLink.attr("href");
					   // 是否不可返回，如果不可返回，这点击返回按钮不可返回
					   if (commonForm.attr('return') && commonForm.attr('return') == 'false') {
						   
						   window.location.replace(href);
					   } else {
						   
						   window.location.href = href;
					   }
				   }
			   });
		   } else {
			   JM.alert(data.info, 3000);
			   JM.flush();
		   }
		   return false;
		}
	});
}

/**
 * 选中后台的导航值
 * @return
 */
function markIndexInit() {
	try {
		if(!JM.isNull(markIndex)) {
			$(".manageTopNavClass").find("li[id=mt_"+markIndex+"]").addClass("current");
		}
		if(!JM.isNull(leftIndex)) {
			leftClick($(".manageLeftNavClass").find("p[id=ml_"+leftIndex+"]"));
		}
		jQuery(".manageTopNavClass").find("a").bind("click", function(){
			var parent_id = jQuery(this).attr("value");
			jQuery(".manageTopNavClass").find("li").removeClass("current");
			jQuery(".manageTopNavClass").find("li[id=mt_"+jQuery(this).attr("markIndex")+"]").addClass("current");
			jQuery.ajax({
				type:'POST',
				dataType:'text',
				url:'/systemModule_moduleNav.action',
				data:'parent_id='+parent_id+'&markIndex='+jQuery(this).attr("markIndex")+'&random='+JM.randomNumber,
				success:function(data){
					jQuery(".manageLeftNavClass").html(data);
					jQuery.getScript("/manager/js/jquery.Gu.js");
				}
			});
			return false;
		});
	} catch(e){}
}
/**
 * 发送短信验证码
 * @return
 */
function sendSmsInit() {
	$(".sendSmsClass").unbind().bind("click", function(){
		var sendSmsBtn = $(this);
		sendSmsBtn.val("发验证码");
		var sendType = sendSmsBtn.attr("sendType");
		var elementId = sendSmsBtn.attr("elementId");
		var mobile = $("#"+elementId);
		if(JM.isNullByJQuery(mobile)) {
			mobile.focus();
			return false;
		}
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/sms.action',
			data:'sendType='+sendType+'&mobile='+JM.encode(mobile)+'&random=' + JM.randomNumber,
			success:function(data){
				if(data.status == JM.YES){
  					sendSmsBtn.unbind();
  					sendSmsBtn.val("已发送");
  					window.setTimeout("sendSmsInit()", 3000);
  				} else if(data.status == JM.NO) {
  					alert(data.info);
  				}
			}
		});
	});
}

/**
 * 选择套餐提示
 */
function showPackageDetail(){
	
	$('#package').change(function(){
		
		var value = $(this).val();
		var description = $('[value="' + value + '"]').attr('description');
		JM.alert(description);
	});
}