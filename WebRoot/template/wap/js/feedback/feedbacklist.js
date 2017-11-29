$(function(){
	
});

function zhuijiafankui(obj){
	
	JM.alert("追加反馈", 2000, function(){
		var feedback_id = $(obj).next().val();
		JM.goUrl("feedBackReply_addFeedback.action?feedback_id=" + feedback_id);
	});
}

function detail(obj){
	var id = $(obj).find("input").val();
	JM.goUrl("feedBack_detail.action?id=" + id);
}
