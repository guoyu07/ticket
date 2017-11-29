
$(function() {
	
});

function selectAll(){
	var name = $("#selectCheckBoxAllChk").attr("objectChkName");
	var checkit   =   $("#selectCheckBoxAllChk").attr("check");     
	     var aa   =   document.getElementsByName(name);     
	      if   (checkit == undefined || checkit == "false")     
	      {     
	      for   (i=0;i<aa.length;i++)     
	        aa.item(i).checked=true;     
	        checkit   =   false;     
	      }     
	      if(checkit == 'true')     
	      {     
	      for   (i=0;i<aa.length;i++)     
	        aa.item(i).checked=false;     
	        checkit   =   true;     
	      }
}

function ing(){
	var name = $("#selectCheckBoxAllChk").attr("objectChkName");
	var names  =   $("input[name='"+name+"']");
	var arr = new Array();
	for(var i=0;i<names.length;i++){
		if(names[i].checked){
			arr[i] = $(names[i]).val();
		}
	}
	$.do_dialog.open({'msg':$('.confirm_dialog'),'initBefore':function(rs){
		
		$('.confirm_dialog .yes').unbind('tap');
		$('.confirm_dialog .yes').bind('tap',function(){
			
			$.do_dialog.close(rs.index);
             $.post('bjdjDispatchList_ing.action',
            		 {idsValue : arr.join(",")},
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

function end(){
	var name = $("#selectCheckBoxAllChk").attr("objectChkName");
	var names  =   $("input[name='"+name+"']");
	var arr = new Array();
	for(var i=0;i<names.length;i++){
		if(names[i].checked){
			arr[i] = $(names[i]).val();
		}
	}
	if(JM.isNull(arr)){
		JM.alert("请先选择需要核销的工单", 2000);
	}else{
		
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
									idsValue : arr.join(","),
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
							idsValue : arr.join(",")
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
	
}

function toWorkOrderPage(){
	window.location = "employeeCRM_workOrderPage.action";
}