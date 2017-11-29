/**
 * wap电瓶车派单
 */
$(function(){
	$(".c_blue").tap(addId);
	$('.c_blue').on('tap', function() {
		$.do_dialog.open( {
			'msg' : $('.pd_dialog')
		});
	});
});

function addId(){
	var id = $(this).next().val();
	$("#ids").attr("value",id);
}

function add(){
	var id = $("#ids").val();
	var electromobileDispatchItems = $(".electromobileDispatchItem");
	var arr = new Array();
	for(var i=0;i<electromobileDispatchItems.length;i++){
		arr[i] = $(electromobileDispatchItems[i]).val();
	}
//	alert(arr.join(","));
	$.ajax({
		url:"wapBjdjDispatch_addForElectromobile.action?operationFlag=1&&versionFlag=site",
		type:"post",
		dataType:"json",
		data:{"id":id,"electromobileDispatchItem":arr.join(",")},
		success:function(data){
			if(data.status == 'y'){
				JM.alert(data.info, 2000, function(){JM.goUrl("wapBjdjDispatch_elemobileJinDu.action")});
			}else{
				JM.alert(data.info, 2000);
			}
		}
	});
}