/**
 * @description 初始化生成代码方法参数
 * @author HiSay
 * @datetime 2014-07-27 13:50
 */
jQuery(function(){
	bindEntityNameOnKeyUp();
});
function bindEntityNameOnKeyUp() {
	jQuery("#entityName").bind("keyup", function(){
		jQuery(".deleteEntityName").html(jQuery(this).val());
	});
}