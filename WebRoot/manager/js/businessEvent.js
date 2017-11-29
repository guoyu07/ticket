/**
 * @descript 商家活动专用JS
 * @author   HiSay
 * @datetime 2016-12-14 10:20:43
 */
 /*************************************************/
jQuery(function(){
	editBusinessEventInital();
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	createEditor("content");
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-12-14 10:20:43
 */
function editBusinessEventInital() {
	//write code here.
}

function refresh(){
	window.location.reload();
}
/**
 * 冻结
 */
function freeze(){
	var id = $("#freeze").attr("attrId");
	easyDialog.open({
		container : {
			header : '系统提示',
			content : '是否确定冻结／暂停此活动？',
			yesFn : function(){
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:"businessEvent_freeze.action",
		  			data:'id='+id,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : data.info,
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
//	$.post("businessEvent_freeze.action",{id:id},function(data){
//		if(data.info == "y"){
//			JM.alert(data.info, 2000,refresh);
//		}else{
//			JM.alert(data.info, 2000);
//		}
//	},"json");
}


/**
 * 解冻
 */
function actived(){
	var id = $("#actived").attr("attrId");
	easyDialog.open({
		container : {
			header : '系统提示',
			content : '是否确定解冻／启用此活动？',
			yesFn : function(){
		  		jQuery.ajax({
		  			type:'POST',
		  			dataType:'json',
		  			url:"businessEvent_actived.action",
		  			data:'id='+id,
		  			success:function(data){
		  				if(data.status == JM.YES){
		  					easyDialog.open({
		  						container : {
		  							header : '系统提示',
		  							content : data.info,
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
//	var id = $("#actived").attr("attrId");
//	$.post("businessEvent_actived.action",{id:id},function(data){
//		if(data.info == "y"){
//			JM.alert(data.info, 2000, refresh);
//		}else{
//			JM.alert(data.info, 2000);
//		}
//	},"json");
}