/**
 * @descript 快捷菜单专用JS
 * @author   HiSay
 * @datetime 2015-10-31 13:01:07
 */
 /*************************************************/

/**
 * @description 初始化快捷菜单方法参数
 * @author HiSay
 * @datetime 2015-10-31 13:01:07
 */
jQuery(function(){
	initUploadify("icon", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	
	editQuickMenuInital();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-31 13:01:07
 */
function editQuickMenuInital() {
	JM.selectSelect("defaultShowPosition");
	//write code here.
	//下拉列表自动填充
	var quickMenuType_id = $('#quickMenuType_id');
	//清空表单
	$('#resetBtn').click(function(){
		quickMenuType_id.combotree('setValue', null);
	});
	quickMenuType_id.combotree({
		onChange : function(newValue, oldValue){
			quickMenuType_id.val(newValue);
		}
	});
}

$(function(){

	$('input:checkbox').each(function(){

		var item = $(this);
		if(item.attr('check') == 'true'){
			item.prop('checked', true);
		}
	});
});
