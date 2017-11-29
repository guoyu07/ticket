/**
 * @description 初始化页面缓存key管理方法参数
 * @author HiSay
 * @datetime 2015-12-23 10:39:35
 */
jQuery(function(){

	$('.batchRefreshBtn').click(function(){
		
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), $(this).attr('methodName'), 
				'你确定刷新吗？', '刷新成功', '刷新失败');
	});
	
	$('.refreshAllBtn').click(refreshAll);
	
});

function refresh(id){
	
	$.post('pageCacheKey_refresh.action', 
			{
				idsValue : id
			},
			function(json){
				
				if(json.status == 'y'){
					
					JM.alert('刷新成功');
				}else{
					
					JM.alert('刷新失败');
				}
			}, 'json');
}

function refreshAll(){
	
	$.post('pageCacheKey_refreshAll.action', 
			{
			},
			function(json){
				
				if(json.status == 'y'){
					
					JM.alert('刷新成功');
				}else{
					
					JM.alert('刷新失败');
				}
			}, 'json');
}

