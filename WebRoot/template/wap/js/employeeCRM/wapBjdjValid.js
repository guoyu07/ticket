/**
 * wap验证便捷登机的js
 */
$(function(){
	$("#show").tap(findByserviceCode);
});

function yanzhengbjdj(){
	var hall = $("#hall").find("option:selected").val();
	var serviceCode = $("#yzm").val();
	if(JM.isNull(serviceCode)){
		JM.alert("请输入服务验证码", 2000);
	}else{
		$.ajax({
			url:"wapValidation_add.action?operationFlag=site",
			type:"post",
			dataType:"json",
			data:{"serviceCode":serviceCode,"hall":hall},
			success:function(data){
				if(data.status == 'y'){
					JM.alert(data.info, 2000,function(){
						JM.goUrl("wapBjdjDispatch_manage.action")
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
			url:"wapValidation_findByServiceCode1.action",
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