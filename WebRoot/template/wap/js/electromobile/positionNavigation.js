$(function(){
	
	if($("#newsLocation").length>0){
		editNewsInital();
	}
});

function editNewsInital() {
	
	// 百度地图API功能
	var map = new BMap.Map("newsLocation");
	map.centerAndZoom("昆明长水机场", 15);
}