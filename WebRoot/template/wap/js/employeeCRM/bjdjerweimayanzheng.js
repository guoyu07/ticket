
$(function(){
	$("#other").tap(function(){
		JM.goUrl("wapValidation_add.action");
	});
});

function yanzheng(){
	var serviceCode = $("#code").val();
	var hall = $("#hall").find("option:selected").val();
	$.ajax({
		url:"wapValidation_add.action?operationFlag=site",
		type:"post",
		dataType:"json",
		data:{"serviceCode":serviceCode,"hall":hall},
		success:function(data){
			if(data.status == 'y'){
				JM.goUrl("wapValidation_erWeiMaYanZhengSuccess1.action");
			}else{
				JM.alert(data.info, 2000);
			}
		}
	});
}