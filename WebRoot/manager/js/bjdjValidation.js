/**
 * @description 初始化便捷登机验证表方法参数
 * @author HiSay
 * @datetime 2015-11-23 22:52:42
 */
jQuery(function(){
	editBjdjValidationInital();
	$("#searchBtn").bind("click", search);
	$("#shous").click(findByid);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-23 22:52:42
 */
function editBjdjValidationInital() {
	jQuery(".batchPaidanBtn").bind("click", function(){
		batchPaidanEntity("bjdjDispatch", jQuery(this).attr("value"),$(this).attr('methodName'));
	});
}

function search() {
	var methodName = $(this).attr("methodName");
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入服务码"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/bjdjValidation_'+methodName+'.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}

function findByid(){
	var code = $("#fwm").val();
	if(JM.isNull(code)){
		JM.alert("请输入服务码", 2000);
	}else{		
		$.ajax({
			url:"wapBjdjAppointment_findById.action",
			type:"post",
			dataType:"json",
			data:{"code":code},
			success:function(data){
				if(data.status == 'y'){
					$("#all").hide();
					$("#oneday").hide();
					$("#threeday").hide();
					$("#byId").show();
					var way = data.info.way;
					if(way == "online"){
						way = "在线预约";
					}else{
						way = "线下预约";
					}
					$("#ways").text(way);
					var time = data.info.usetime;
					
					$("#usetimes").text(time);
					$("#names").text(data.info.name);
					$("#codes").text(data.info.code);
					$("#mobiles").text(data.info.mobile);
					$("#flightNos").text(data.info.flightNo);
					$("#descriptions").text(data.info.description);
				}else{
					JM.alert(data.info, 2000);
				}
			}
		});
	}
}
/**
 * 批量派单
 */
function batchPaidanEntity(entityName, selectElementId, methodName, resultSucTitle, resultFailedTitle){
	var elementIds = JM.getValueByCheckedName(selectElementId);
	   //判断是否有选中要操作的信息
	   if(elementIds == null || elementIds == '') {
			JM.alert('请选择您要批量操作的信息！');
			return false;
	   }
	   var idsArr = new Array();
	    idsArr = elementIds.split(",");
	    var can = true;
	    for(var i=0;i<idsArr.length;i++){
	    	var input = $(".my_table100").find("input[value='"+idsArr[i]+"']");
	    	var $tr = input.parent().parent();
	    	var $lastTd = $tr.children().eq(9);
	    	var $text = $lastTd.text();
	    	var $trimText = JM.trim($text);
	    	if(JM.isNull($trimText)){
	    		can = false;
	    		break;
	    	}
	    }
	    if(!can){
	    	JM.alert("您选择的批量派单中含有已经分单的数据，请重新选择！", 2000);
	    }else{
	    	easyDialog.open({
	    		container : {
	    			header : '系统提示',
	    			content : '是否确定批量派单？',
	    			yesFn : function(){
	    				var isChecked = jQuery("#isChecked");
	    				var actionUrl = getActionUrlByEntity(entityName, methodName);
	    				JM.goUrl(actionUrl + "?idsValue=" + elementIds + '&random=' + JM.randomNumber);
	    				/*jQuery.ajax({
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
			 		});*/
	    			},
	    			noFn : true
	    		},
	    		overlay : true,
	    		fixed : false,
	    		drag: false
	    	});
	    }
	   return false;
}