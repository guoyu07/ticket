/**
 * @descript 航空公司信息专用JS
 * @author   HiSay
 * @datetime 2015-11-03 13:39:40
 */
 /*************************************************/

/**
 * @description 初始化航空公司信息方法参数
 * @author HiSay
 * @datetime 2015-11-03 13:39:40
 */
jQuery(function(){
	editFlightCompanyInital();
	initUploadify("logo", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	
	phone = createEditor("phone");
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-03 13:39:40
 */
function editFlightCompanyInital() {
	$(".removePoi").unbind().bind("click",function(){
		var obj = $(this);
		var isYes = window.confirm("确定移除当前值机点？");
		if(isYes){
			removeP(obj);
			return;
		}
	});
	//write code here.
}

function addPosition(){
	var $jwdu = $("#jwdu");
	var $add = '<p>经度：<input class="selfLongitude" name="selfLongitude" datatype="*"/> '+
	'纬度：<input class="selfLatitude"  name="selfLatitude" datatype="*"/> '+
	'楼层号：<input class="floorNumber"  name="floorNumber" datatype="*"/>'+
	'单/多点位名称:<input class="positionName" name="positionName" datatype="*"/>'+
	'<a href="javascript:;" onclick="removeP(this);">移除<a/><p/>';
	$jwdu.append($add);
}
function removeP(dom){
	var a = $(dom);
	var parent = a.parent();
	parent.remove();
}
