$(function(){
	
	//接单
	$receiveDispatch = $('.receiveDispatch');
	$receiveDispatch.bind('tap', ing);
	
	//核销
	$verificateDispatch = $('.verificate');
	$verificateDispatch.bind('tap', end);
});

/**
 * 接单
 */
function ing(){
	
	var dom = $(this);
	$.do_dialog.open({'msg':$('.confirm_dialog'),'initBefore':function(rs){
		
		$('.confirm_dialog .yes').unbind('tap');
		$('.confirm_dialog .yes').bind('tap',function(){
			
			$.do_dialog.close(rs.index);
             $.post('bjdjDispatchList_ing.action',
            		 {idsValue : dom.attr('value')},
            		 function(json){
            			 
            			 if(json.status == 'y'){
            				 
            				 JM.alert(json.info, 2000, toWorkOrderPage);
            			 }else{
            				 
            				 JM.alert('接单失败：' + json.info, 3000);
            			 }
            		 });
         });
		$('.confirm_dialog .no').unbind('tap');
		$('.confirm_dialog .no').bind('tap',function(){
	 
			$.do_dialog.close(rs.index);
		});
	}});
}

/**
 * 核销
 */
function end(){
	
	var dom = $(this);
	$.do_dialog.open({'msg':$('.confirm_dialog'),'initBefore':function(rs){
		
		$('.confirm_dialog .content_text').text('确认核销？');
		
		$('.confirm_dialog .yes').unbind('tap');
		$('.confirm_dialog .yes').bind('tap',function(){
			
			$('.confirm_dialog .content_text').text('是否填写反馈信息？');
			$('.confirm_dialog .yes').unbind('tap');
			$('.confirm_dialog .yes').bind('tap',function(){
				
				$.do_dialog.close(rs.index);
				$.do_dialog.open({'msg':$('.question_dialog'),'initBefore':function(rs){
					
					$('.question_dialog .no').unbind('tap');
					$('.question_dialog .no').bind('tap',function(){
						
						$.do_dialog.close(rs.index);
						$.post('bjdjDispatchList_end.action',
								{
									idsValue : dom.attr('value'),
									feedback : $('.question_dialog .input').val()
								},
								function(json){
									
									if(json.status == 'y'){
										
										JM.alert(json.info, 2000, toWorkOrderPage);
									}else{
										
										JM.alert('核销失败：' + json.info, 3000);
									}
								});
					});
				}});
			});
			$('.confirm_dialog .no').unbind('tap');
			$('.confirm_dialog .no').bind('tap',function(){
				
				$.do_dialog.close(rs.index);
				$.post('bjdjDispatchList_end.action',
						{
							idsValue : dom.attr('value')
						},
						function(json){
							
							if(json.status == 'y'){
								
								JM.alert(json.info, 2000, toWorkOrderPage);
							}else{
								
								JM.alert('核销失败：' + json.info, 3000);
							}
						});
			});
		});
		$('.confirm_dialog .no').unbind('tap');
		$('.confirm_dialog .no').bind('tap',function(){
			
			$.do_dialog.close(rs.index);
		});
	}});
}

function toWorkOrderPage(){
	
	window.location = "employeeCRM_workOrderPage.action";
}