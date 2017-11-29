/**
 * wap端机场电瓶车验证的js
 */
$(function(){
	$("#shous").tap(findByserviceCode);
});

function yzmYz(){
	var code = $("#yzm").val();
	if(JM.isNull(code)){
		JM.alert("请输入验证码", 2000);
	}else{
		$.ajax({
			url:"wapValidation_addElectromobile.action?operationFlag=site",
			type:"post",
			dataType:"json",
			data:{"serviceCode":code},
			success:function(data){
				if(data.status == 'y'){
					JM.alert(data.info, 2000,function(){
						JM.goUrl("wapBjdjDispatch_manageForElectromobile.action");
					});
				}else{
					JM.alert(data.info, 2000);
				}
			}
		});
	}
}

function findByserviceCode(){
	var serviceCode = $("#fuwuma").val();
	if(JM.isNull(serviceCode)){
		JM.alert("请输入服务码", 2000);
	}else{
		$.ajax({
			url:"wapValidation_findByServiceCode.action",
			type:"post",
			dataType:"json",
			data:{"serviceCode":serviceCode},
			success:function(data){
				if(data.status == 'y'){
					$("#allll").hide();
					$("#one").show();
					var time = data.info.time;
					var code = data.info.serviceCode;
					var name = data.info.name;
					var passed = data.info.passed;
					$("#yztime").text(time);
					$("#servicecode").text(code);
					$("#employee").text(name);
					if(passed == true){
						passed = "通过";
					}else{
						passed = "不通过";
					}
					$("#passed").text(passed);
				}else{
					JM.alert(data.info, 2000);
				}
			}
		});
	}
}