var setoutQuickMenuHandler;//定时器引用
$(function(){
	//启动定时器
	setoutQuickMenuHandler = setInterval("reloadSetoutQuickMenu()", 1000);
});
/**
 * 重载菜单
 */
function reloadSetoutQuickMenu() {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_reloadSetoutQuickMenuAjax.action',
		data:'',
		success:function(data) {
			if(data != null && data !='') {
				$(".setoutQuickMenu").html(data);
				customerMenuInit();
			}
		}
	});
}
