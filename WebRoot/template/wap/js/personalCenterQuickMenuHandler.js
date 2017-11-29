var personalCenterQuickMenuHandler;//定时器引用
$(function(){
	//启动定时器
	personalCenterQuickMenuHandler = setInterval("reloadPersonalCenterQuickMenu()", 1000);
});
/**
 * 重载菜单
 */
function reloadPersonalCenterQuickMenu() {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_personalCenterQuickMenuAjax.action',
		data:'',
		success:function(data) {
			if(data != null && data !='') {
				$(".personalCenterQuickMenu").html(data);
				customerMenuInit();
			}
		}
	});
}
