/**
 * @descript 广告信息专用JS
 * @author   HiSay
 * @datetime 2015-10-27 10:31:54
 */
 /*************************************************/

/**
 * @description 初始化广告信息方法参数
 * @author HiSay
 * @datetime 2015-10-27 10:31:54
 */
jQuery(function(){
	editAdvertInital();
	
	content = createSimpleEditor('content');
	
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	
	jQuery(".selectCheckBoxAllChks").bind("click", function(){
		var obj = jQuery(this);
		/*if(obj.attr("objectChkName") != '') {
			JM.checkAll(jQuery(this).attr("objectChkName"), jQuery(this).attr("id"));
		} else {
			JM.checkAll("ids", jQuery(this).attr("id"));
		}*/
		var name = jQuery(this).attr("objectChkName");
		if(obj.prop("checked")){
//			var input = jQuery("input[name='"+name+"',attrValue='"+attrValue+"']");
			jQuery("input[name='"+name+"']").prop("checked",true);
			//JM.checkAll(jQuery(this).attr("objectChkName"), jQuery(this).attr("id"));
		}else{
			jQuery("input[name='"+name+"']").prop("checked",false);
//			jQuery("input[name='"+name+"']").prop("checked",false);
			//JM.checkAll("ids", jQuery(this).attr("id"));
		}
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-27 10:31:54
 */
function editAdvertInital() {
	//write code here.
}

