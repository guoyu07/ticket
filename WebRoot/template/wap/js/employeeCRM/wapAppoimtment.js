/**
 * 手机端预约情况的js
 */
$(function(){
	$("#today").click(onday);
	$("#threedays").click(threeday);
	$("#ss").click(findByid);
});

function onday(){
	$("#all").hide();
	$("#oneday").show();
	$("#threeday").hide();
	$("#byId").hide();
}

function threeday(){
	$("#all").hide();
	$("#oneday").hide();
	$("#threeday").show();
	$("#byId").hide();
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