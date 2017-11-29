$(function(){

	$('#nextPage').click(function(){
		
		TuYou.pagingQuery('nextPage', actionName + '_' + methodName + '.action?operationFlag=html',function(){
			$(".feedbacks").on('click',isFeedback);
		});
		
	});
	
	$('#nextPage').trigger('click');
});

function isFeedback(){
	var a = $(this);
	var id = $(this).attr("value");
	var feedback = a.attr("var");
	if(JM.isNull(feedback)){
		JM.alert("管理员未回复");
	}else{
		$.post("evaluation_isRend.action",{id : id},function(){
			JM.alert(feedback);
		});
	}
}