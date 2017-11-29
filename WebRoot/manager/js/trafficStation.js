/**
 * @descript 车站信息专用JS
 * @author   HiSay
 * @datetime 2015-11-19 10:07:53
 */
 /*************************************************/

/**
 * @description 初始化车站信息方法参数
 * @author HiSay
 * @datetime 2015-11-19 10:07:53
 */
jQuery(function(){
	editTrafficStationInital();
	
	descript = createEditor("descript");
	
	$("#trafficLineId").bind("change", function(){
		if($(this).val() == '') {
			window.location.href = '/trafficStation_manage.action?random='+JM.randomNumber;
		} else {
			window.location.href = '/trafficStation_manage.action?trafficLine_id='+$(this).val()+'&random='+JM.randomNumber;
		}
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-19 10:07:53
 */
function editTrafficStationInital() {
	JM.selectSelect("goOrBack");
	//write code here.
}