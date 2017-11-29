$(function(){
	
});

var saturationIndex;
var waitIndex;

function activate(orderId){
	
	$.post(
			'bjdjServiceCodeActivate_listWaitPerson.action', 
			{
				orderId : orderId
			},
			function(json){
		
				if(!json.status){
					
					alert(json.info);
				}else if(json.saturation){
					
					var saturationIndex = $.do_dialog.open({
						'msg' : $('.saturation')
					});
				}else if(json.wait > 0){
					
					$('#waitNumber').text(json.wait);
					var waitIndex = $.do_dialog.open({
						'msg' : $('.wait')
					});
					setTimeout(function(){
						
						toActivateSuccess(orderId);
					}, 3000);
				}else{
					
					toActivateSuccess(json.orderId);
				}
			}
	);
};

function queueWait(orderId){
	
	$.do_dialog.close(saturationIndex);
	
	$.post(
			'bjdjServiceCodeActivate_activate.action', 
			{
				orderId : orderId
			},
			function(json){
				
				if(!json.status){
					
					alert(json.info);
				}else{
					
					$('#waitNumber').text(json.wait);
					$.do_dialog.open({
						'msg' : $('.wait')
					});
					
					setTimeout(function(){
						
						toActivateSuccess(orderId);
					}, 3000);
				}
			}
	);
}

function closeSaturation(){
	
	$.do_dialog.close(saturationIndex);
}

function closeWait(){
	
	$.do_dialog.close(waitIndex);
}

function toActivateSuccess(orderId){
	
	window.location = 'bjdjServiceCodeActivate_success.action?orderId=' + orderId;
}