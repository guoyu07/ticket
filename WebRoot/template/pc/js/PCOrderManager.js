

function realDelete(orderId){
	
	if(confirm('你确定删除订单吗？')){
		
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'pcOrder_realDelete.action',
			data:'orderId='+orderId,
			success:function(data){
				
				try {
					var json = $.parseJSON(data);
					if(json.status == 'y'){
						
						JM.alert('删除成功', 2000);
						var ids1 = $("#tbc2_01").find("input");
						var ids2 = $("#tbc2_02").find("input");
						var ids3 = $("#tbc2_03").find("input");
						var ids4 = $("#tbc2_04").find("input");
						var ids5 = $("#tbc2_05").find("input");
						for(var i=0;i<ids1.length;i++){
							if( orderId == $(ids1[i]).attr("id") ){
								$("#" + orderId).parent().parent().remove();
							}
						}
						for(var i=0;i<ids2.length;i++){
							if(orderId == $(ids2[i]).attr("id")){
								$("#" + orderId).parent().parent().remove();
							}
						}
						for(var i=0;i<ids3.length;i++){
							if(orderId == $(ids3[i]).attr("id")){
								$("#" + orderId).parent().parent().remove();
							}
						}
						for(var i=0;i<ids4.length;i++){
							if(orderId == $(ids4[i]).attr("id")){
								$("#" + orderId).parent().parent().remove();
							}
						}
						for(var i=0;i<ids5.length;i++){
							if(orderId == $(ids5[i]).attr("id")){
								$("#" + orderId).parent().parent().remove();
							}
						}
					}else{
						
						JM.alert(json.info, 3000);
					}
				} catch (e) {
				}
			}
		});
	}
}

function orderInfo(orderId){
	JM.goUrl("pcOrder_orderDetailsPage.action?orderId=" + orderId);
}