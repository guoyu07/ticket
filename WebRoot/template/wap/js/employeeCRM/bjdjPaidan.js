/**
 * wap便捷登机的js
 */
$(function(){
	$(".c_blue").tap(addId);
	$('.c_blue').on('tap', function() {
		$.do_dialog.open( {
			'msg' : $('.pd_dialog')
		});
	});
//	editBjdjDispatchInital();
});

function addId(){
	var id = $(this).next().val();
	$("#ids").attr("value",id);
}

function editBjdjDispatchInital() {
	
	$('select').change(function(){
		
		var item = $(this);
		var id = item.val();
		var tip = item.next('.receiveTip');
		if(typeof(id)=='undefined' || $.trim(id)==''){
			tip.text('');
			return;
		}
		
		$.post(
				actionName + "_receiveAmount.action",
				{id : id},
				function(json){
					
					if(json.status == 'y'){
						
						tip.text(json.info);
					}
				}
		);
	});
}

function add(){
	var id = $("#ids").val();
	var bjdjDispatchItems = $(".bjdjDispatchItem");
	var arr = new Array();
	for(var i=0;i<bjdjDispatchItems.length;i++){
		arr[i] = $(bjdjDispatchItems[i]).val();
	}
//	alert(arr.join(","));
	$.ajax({
		url:"wapBjdjDispatch_add.action?operationFlag=1&&versionFlag=site",
		type:"post",
		dataType:"json",
		data:{"id":id,"bjdjDispatchItem":arr.join(",")},
		success:function(data){
			if(data.status == 'y'){
				JM.alert(data.info, 2000, function(){JM.goUrl("wapBjdjDispatch_bjdjJIndu.action")});
			}else{
				JM.alert(data.info, 2000);
			}
		}
	});
}