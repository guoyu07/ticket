/**
 * 手机crm首页js
 */
$(function(){
	$("#personInfo").click(function(){
		JM.goUrl("employeeCRM_personInfoPage.action");
		}
	);
	$("#yy").click(function(){
		JM.goUrl("wapBjdjAppointment_manage.action");
	});
	$("#loginout").tap(function(){
		JM.goUrl("employeeCRM_logout.action");
	});
	
});


