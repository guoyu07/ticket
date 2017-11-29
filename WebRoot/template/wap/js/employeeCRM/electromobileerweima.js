
$(function(){
	$("#other").tap(function(){
		JM.goUrl("wapValidation_addElectromobile.action");
	});
});

function yanzheng(){
	var serviceCode = $("#code").val();
	$.ajax({
		url:"wapValidation_addElectromobile.action?operationFlag=site",
		type:"post",
		dataType:"json",
		data:{"serviceCode":serviceCode},
		success:function(data){
			if(data.status == 'y'){
				JM.goUrl("wapValidation_erWeiMaYanZhengSuccess.action");
			}else{
				JM.alert(data.info, 2000);
			}
		}
	});
}