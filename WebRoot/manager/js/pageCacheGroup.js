/**
 * @descript 页面缓存group管理专用JS
 * @author   HiSay
 * @datetime 2015-12-23 10:34:35
 */
 /*************************************************/

/**
 * @description 初始化页面缓存group管理方法参数
 * @author HiSay
 * @datetime 2015-12-23 10:34:35
 */
jQuery(function(){
	
	$('.batchRefreshBtn').click(function(){
		
		batchOperationEntity(jQuery(this).attr("entityName"), jQuery(this).attr("value"), $(this).attr('methodName'), 
				'你确定刷新吗？', '刷新成功', '刷新失败');
	});
});

function refresh(id){
	
	$.post('pageCacheGroup_refresh.action', 
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