/**
 * wap电瓶车工单js
 */
$(function(){
	$("#ss").tap(find);
});

function find(){
	var code = $("#fwm").val();
	if(JM.isNull(code)){
		JM.alert("请输入服务码", 2000);
	}else{
		$.ajax({
			url:"wapBjdjDispatch_findElemobileBySeiveiceCode.action",
			type:"post",
			dataType:"json",
			data:{"serviceCode":code},
			success:function(data){
				if(data.status == 'y'){
					$("#all").hide();
					$("#find").show();
					var time = data.info.time;
					var serviceCode = data.info.serviceCode;
					var employeeName = data.info.employeeName;
					var ended = data.info.ended;
					
					$("#time").text(time);
					$("#serviceCode").text(serviceCode);
					$("#employeeName").text(employeeName);
					if(ended == true){
						ended = "结束";
					}else{
						ended = "进行中";
					}
					$("#ended").text(ended);
				}else{
					JM.alert(data.info, 2000);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert(textStatus);
			}
		});
	}
}