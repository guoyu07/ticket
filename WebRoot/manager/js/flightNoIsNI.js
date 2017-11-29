$(function(){
	
});

function send(){
	var flightNo = $("#flightNo").val();
	var title= $("#title").val();
	var content = $("#content").val();
	var url1 = $("#url").val();
	
	$.ajax({
		url:"",
		type:"post",
		dataType:"json",
		data:{"title":title,"content":content,"url":url1,"flightNo":flightNo},
		success:function(data){
			if(data.status == JM.YES){
				JM.alert(data.info, 3000, function(){
					JM.goUrl("bjdjAppointment_manage.action");
				});
			}else{
				JM.alert(data.info, 3000);
			}
		}
	});
}

