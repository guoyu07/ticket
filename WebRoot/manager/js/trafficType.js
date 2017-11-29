/**
 * @descript 交通路线类型专用JS
 * @author   HiSay
 * @datetime 2015-11-19 09:42:49
 */
 /*************************************************/

/**
 * @description 初始化交通路线类型方法参数
 * @author HiSay
 * @datetime 2015-11-19 09:42:49
 */
jQuery(function(){
	editTrafficTypeInital();
	
	introduce = createEditor("introduce");
	initPositionMap()
	
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-19 09:42:49
 */
function editTrafficTypeInital() {
	JM.selectSelect("hot");
	//write code here.
}

//初始化地图
function initPositionMap(){
	var position = $("#positionMap");
	if(position&&position.size()>0){
		var map = new BMap.Map("positionMap");
		map.centerAndZoom("昆明", 11);
		map.addEventListener("click",function(e){
			//alert(e.point.lng + "," + e.point.lat);
			$("#longitude").val(e.point.lng);
			$("#latitude").val(e.point.lat);
		});
	}
}