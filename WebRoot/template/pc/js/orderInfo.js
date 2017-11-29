
//$(function(){
//	$(".order_info_btn").click(back);
//});

function back(){
	JM.goUrl("pcOrder_allOrder.action");
}

/**
 * 取消激活
 */
function unactived(ids){
	
	$.post('bjdjServiceCodeActivate_unActivate.action', {idsValue : ids}, function(data){

		JM.alert(data.info, 3000, function(){
			
			if(data.status == JM.YES){
				
				JM.reload(null);
			}
		});
	}, 'json');
}