var transferQuickMenuHandler;//定时器引用
$(function(){
	//启动定时器
	transferQuickMenuHandler = setInterval("reloadTransferQuickMenu()", 1000);
});
/**
 * 重载菜单
 */
function reloadTransferQuickMenu() {
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/airport_reloadTransferQuickMenuAjax.action',
		data:'',
		success:function(data) {
			if(data != null && data !='') {
				$(".transferQuickMenu").html(data);
				customerMenuInit();
			}
		}
	});
}
