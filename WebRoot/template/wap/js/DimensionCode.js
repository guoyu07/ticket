/**
 * 显示二维码的js
 */
$(function(){
	$("button").tap(show);
});

function show(btn){
	var codepath = $(this).parent().find("input").val();
	$("#img1").attr("src",codepath);
    $.do_dialog.open({'msg':$('.check_dialog')});
}