/**
 * 便捷登机工单进度的js
 */
$(function(){
	$("#shousuo").tap(shousuo);
});


function shousuo(){
	var code = $("#fwm").val();
	if(JM.isNull(code)){
		JM.alert("请输入服务码", 2000);
	}else{
		$.ajax({
			url:"wapBjdjDispatch_findBjdjByServiceCode.action",
			type:"post",
			dataType:'json',
			data:{"serviceCode":code},
			success:function(data){
				if(data.status == 'y'){
					$("#all").hide();
					$("#find").show();
					var date = data.info.buyTime;
					var serviceCode = data.info.serviceCode;
					var name = data.info.name;
					var flightNo = data.info.flightNo;
					var flightTime = data.info.useTime;
					var employeeName1 = data.info.employeeName0;
					var employeeName2 = data.info.employeeName1;
					var employeeName3 = data.info.employeeName2;
					var employeeName4 = data.info.employeeName3;
					var state1 = data.info.state0;
					var state2 = data.info.state1;
					var state3 = data.info.state2;
					var state4 = data.info.state3;
					var ended = data.info.ended;
					
					$("#date").text(date);
					$("#servicecode").text(serviceCode);
					$("#name").text(name);
					$("#flightNo").text(flightNo);
					$("#flightTime").text(flightTime);
					if(ended == true){
						ended = "结束";
					}else{
						ended = "进行中";
					}
					$("#ended").text(ended);
					$("#employeeName1").text(employeeName1);
					$("#employeeName2").text(employeeName2);
					$("#employeeName3").text(employeeName3);
					$("#employeeName4").text(employeeName4);
					if(state1 == 'wait'){
						state1 = "未接单";
					}else if(state1 == "ing"){
						state1 = "进行中";
					}else{
						state1 = "已核销";
					}
					if(state2 == 'wait'){
						state2 = "未接单";
					}else if(state2 == "ing"){
						state2 = "进行中";
					}else{
						state2 = "已核销";
					}
					if(state3 == 'wait'){
						state3 = "未接单";
					}else if(state3 == "ing"){
						state3 = "进行中";
					}else{
						state3 = "已核销";
					}
					if(state4 == 'wait'){
						state4 = "未接单";
					}else if(state4 == "ing"){
						state4 = "进行中";
					}else{
						state4 = "已核销";
					}
					$("#state1").text(state1);
					$("#state2").text(state2);
					$("#state3").text(state3);
					$("#state4").text(state4);
				}else{
					JM.alert(data.info,2000);
				}
			}
		});
	}
}








