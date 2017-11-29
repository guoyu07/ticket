var reachQuickMenuHandler;//定时器引用
$(function(){
	//启动定时器
	reachQuickMenuHandler = setInterval("reloadReachQuickMenu()", 1000);
});
/**
 * 重载菜单
 */
function reloadReachQuickMenu() {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_reloadReachQuickMenuAjax.action',
		data:'',
		success:function(data) {
			if(data != null && data !='') {
				$(".reachQuickMenu").html(data);
				customerMenuInit();
			}
		}
	});
}
