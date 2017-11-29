/**
 * @descript 路线与车站关联专用JS
 * @author   HiSay
 * @datetime 2015-12-20 16:30:33
 */
 /*************************************************/

/**
 * @description 初始化路线与车站关联方法参数
 * @author HiSay
 * @datetime 2015-12-20 16:30:33
 */
jQuery(function(){
	editTrafficLineAndStationInital();
	
	JM.selectSelect("trafficLine_id");
	JM.selectSelect("trafficStation_id");
	JM.selectSelect("stationType");
	
	
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-12-20 16:30:33
 */
function editTrafficLineAndStationInital() {
	$("#trafficLineID").bind("change",function(){
		if($(this).val() == '') {
			window.location.href = '/trafficLineAndStation_manage.action?random='+JM.randomNumber;
		} else {
			window.location.href = '/trafficLineAndStation_manage.action?trafficLine_id='+$(this).val()+'&random='+JM.randomNumber;
		}
	});
}

