/**
 * @descript 有赞商品绑定机场商家专用JS
 * @author   HiSay
 * @datetime 2017-01-11 10:26:44
 */
 /*************************************************/
jQuery(function(){
	editBindYouZanInital();
	jQuery(".selectCheckBoxAllChks").bind("click", function(){
		var obj = jQuery(this);
		var name = jQuery(this).attr("objectChkName");
		if(obj.prop("checked")){
			jQuery("input[name='"+name+"']").prop("checked",true);
		}else{
			jQuery("input[name='"+name+"']").prop("checked",false);
		}
	});
	
	$(".bind").bind("click",bind);
	$(".unBind").bind("click",unBind);
	jQuery(".batchBindBtn").bind("click", function(){
		batchBindEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'batchBind', '是否确认绑定选中信息？', '批量绑定信息成功！', '批量绑定信息失败！');
	});
	jQuery(".batchUnBindBtn").bind("click", function(){
		batchBindEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), 
							'batchUnBind', '是否确认取消绑定选中信息？', '批量取消绑定信息成功！', '批量取消绑定信息失败！');
	});
	$("#searchBtn").bind("click",search);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2017-01-11 10:26:44
 */
function editBindYouZanInital() {
	//write code here.
}

function search(){
	var keyword = $("#keyword");
	var business_id = $("#business").val();
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入商品名称关键词"){
		alert("请输入检索关键词~");
		keyword.focus();
		return false;
	}else{
		JM.goUrl("bindYouZan_edit.action?keyword="+JM.encodeByValue(keyword.val())+"&id="+business_id);
	}
}

/**
 * 绑定
 */
function bind(){
	var business_id = $("#business").val();
	var goods_id = $(this).attr("objValue");
	$.post("bindYouZan_bind.action",{business_id:business_id,goods_id:goods_id},function(data){
		JM.alert(data.info, 3000, function(){
			window.location.reload();
		});
	},"json");
}

function unBind(){
	var business_id = $("#business").val();
	var goods_id = $(this).attr("objValue");
	$.post("bindYouZan_unBind.action",{business_id:business_id,goods_id:goods_id},function(data){
		JM.alert(data.info, 3000, function(){
			window.location.reload();
		});
	},"json");
}

/**
 * 批量操作
 * @param entityName
 * @param selectElementId
 * @param batchOperationType
 * @param questionTitle
 * @param resultSucTitle
 * @param resultFailedTitle
 * @returns {Boolean}
 */
function batchBindEntity(entityName, selectElementId, batchOperationType, questionTitle, resultSucTitle, resultFailedTitle) {
	   var elementIds = JM.getValueByCheckedName(selectElementId);
	   var business_id = $("#business").val();
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
			  		var actionUrl = getActionUrlByEntity(entityName,batchOperationType);
			  		jQuery.ajax({
			  			type:'POST',
			  			dataType:'json',
			  			url:actionUrl,
			  			data:'ids='+elementIds+'&business_id='+business_id+'&isChecked='+isChecked.is(":checked")+'&random='+JM.randomNumber,
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