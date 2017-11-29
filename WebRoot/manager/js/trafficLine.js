/**
 * @descript 交通路线专用JS
 * @author   HiSay
 * @datetime 2015-11-19 09:55:18
 */
 /*************************************************/

/**
 * @description 初始化交通路线方法参数
 * @author HiSay
 * @datetime 2015-11-19 09:55:18
 */
jQuery(function(){
	editTrafficLineInital();
	
	remark = createEditor("remark");
	
	$("#trafficTypeId").bind("change", function(){
		if($(this).val() == '') {
			window.location.href = '/trafficLine_manage.action?random='+JM.randomNumber;
		} else {
			window.location.href = '/trafficLine_manage.action?trafficType_id='+$(this).val()+'&random='+JM.randomNumber;
		}
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-19 09:55:18
 */
function editTrafficLineInital() {
	//write code here.
}