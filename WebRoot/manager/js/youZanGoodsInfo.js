/**
 * @descript 有赞出售中的商品专用JS
 * @author   HiSay
 * @datetime 2017-01-05 16:13:06
 */
 /*************************************************/
jQuery(function(){
	editYouZanGoodsInfoInital();
	$("#refrech").bind("click",refrech);
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
 * @datetime 2017-01-05 16:13:06
 */
function editYouZanGoodsInfoInital() {
	//write code here.
}

/**
 * 刷新
 */
function refrech(){
	$.post("youZanGoodsInfo_refrech.action",{versionFlag:"site"},function(data){
		JM.alert(data, 3000, function(){
			window.location.reload();
		});
	},"text");
}