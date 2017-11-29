var bjdjPage_id;
$(function(){
	
//	$nextPage = $('#nextPage');
//	nextPage();
	bjdjPage_id = $("#bjdjPage_id").val();
	$('#nextPage').click(function(){
		if(JM.isNull(bjdjPage_id)){
			TuYou.pagingQuery('nextPage', 'bjdjCommentTemplate_list.action');
		}else{
			TuYou.pagingQuery('nextPage', 'bjdjCommentTemplate_list.action?bjdjPage_id=' + bjdjPage_id);
		}
	});
	$('#nextPage').trigger('click');
});
//
//var $nextPage;
//function nextPage(){
//	
//	var start = parseInt($nextPage.attr("start"));
//	$.post('bjdjCommentTemplate_list.action', {pn : start}, function(data){
//		
//		try{
//			var json = $.parseJSON(data);
//			if(json.length == 0){
//				
//				$nextPage.parent().before(noDataMessage);
//				return;
//			}
//			
//			for(var i = 0; i < json.length; i++){
//				
//				var obj = json[i];
//				
//				var bigDiv = $('<div class="panel mr40 border10">');
//				
////				<div class="panel-head fz22 padding-big-top padding-big-bottom">
////					野原新之助
////					<a href="" class='float-right'>来自：昆明</a>
////				</div>
////				<i class='icon-star text-dot fz30'></i>
//				var titleDiv = $('<div class="panel-head fz22 padding-big-top padding-big-bottom">');
//				var contentDiv = $('<div class="panel-body fz22 padding-big-top padding-big-bottom"></div>');
//				var stars = $('<a href="#" class="float-right"></a>');
//				
//				//显示用户名
//				titleDiv.text(obj.order_id);
//				//显示星级
//				for(var j = 0; j < obj.star; j++){
//					
//					var star = $('<i class="icon-star text-dot fz30"></i>');
//					stars.append(star);
//				}
//				titleDiv.append(stars);
//				
//				//显示评论内容
//				contentDiv.text(obj.content);
//				//显示评论图片
//				if(typeof(obj.images) != 'undefined' && $.trim(obj.images) != ''){
//					
//					var br = $('<br/>');
//					contentDiv.append(br);
//					var images = obj.images.split(',');
//					for(var j = 0; j < images.length; j++){
//						
//						var a = $('<a href="'+images[j]+'" target="_self" class="padding-top inline_block"><img height="100" width="100" src="'+images[j]+'"/></a>');
//						br.after(a);
//					}
//				}
//				bigDiv.append(titleDiv);
//				bigDiv.append(contentDiv);
//				
//				$nextPage.parent().before(bigDiv);
//			}
//			$nextPage.attr('start', start + pageSize);
//			
//		}catch(e){}
//	});
//}