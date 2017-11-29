$(function(){
	
	//订单
	$(".orderList").bind('click',function(){
		var orderStatus = $(this).attr("orderStatus");
		$(".orderList").removeClass("selected");
		$(this).addClass("selected");
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'bjdjOrderTemplate_orderAjax.action',
			data:'orderStatus='+orderStatus+'&mid='+mid,
			success:function(data){
				$("#orderList").html(data);
			}
		});
	});
	$(".orderList[orderStatus='all']").trigger('click');
});

function realDelete(orderId){
	
	if(confirm('你确定删除订单吗？')){
		
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'bjdjOrderTemplate_realDelete.action',
			data:'orderId='+orderId,
			success:function(data){
				
				try {
					var json = $.parseJSON(data);
					if(json.status == 'y'){
						
						JM.alert('删除成功', 2000, function(){
							
							location.reload();
						});
					}else{
						
						JM.alert(json.info, 3000);
					}
				} catch (e) {
				}
			}
		});
	}
}