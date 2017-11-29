/**
 * 注销
 */
$(function(){
	
});

function logout(){
	$.getJSON("pcBjdjMember_logouts.action",function(data){
		if(data.status == 'y'){
			JM.alert(data.info, 1000, function(){
				 window.location.reload();
			});
		}
	});
}