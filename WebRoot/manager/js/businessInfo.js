/**
 * @descript 商家信息专用JS
 * @author   HiSay
 * @datetime 2015-11-16 15:35:54
 */
 /*************************************************/

/**
 * @description 初始化商家信息方法参数
 * @author HiSay
 * @datetime 2015-11-16 15:35:54
 */
jQuery(function(){
	editBusinessInfoInital();
	initPositionMap();
	
	introduce = createEditor('introduce');
	activityForecast = createEditor('activityForecast');
	
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	
	JM.selectSelect("businessType_id");
	JM.selectSelect("lc");
	JM.selectSelect("wz");
	
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-16 15:35:54
 */
function editBusinessInfoInital() {
	$("#searchBtn").unbind().bind("click",function(){
		searchBusinessByKeyword();
	});
	
	$("#searchByConditions").unbind().bind("click",function(){
		searchByConditions();
	});
	//write code here.
}
/**
 * 根据商家名称关键词搜索商家信息列表
 */
function searchBusinessByKeyword(obj){
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入商家名称关键词"){
		alert("请输入检索关键词~");
		keyword.focus();
		return false;
	}else{
		window.location.href="/businessInfo_queryByKeyword.action?keyword="+JM.encodeByValue(keyword.val());
	}
}

/**
 * 根据条件搜索商家信息列表
 */
function searchByConditions(obj){
	var type_id = $("#businessType").val();//类别id
	var lc = $("#lc").val(); //楼层
	var wz = $("#wz").val(); //位置
	
	window.location.href = "/businessInfo_queryByConditions.action?businessType_id="+type_id+"&lc="+lc+"&wz="+wz;
//	alert("类别ID"+type_id+"楼层ID"+lc+"位置ID"+wz);
}
//初始化地图
function initPositionMap(){
	var position = $("#businessMap");
	if(position&&position.size()>0){
		var map = new BMap.Map("businessMap");
		map.centerAndZoom("昆明", 11);
		map.addEventListener("click",function(e){
			//alert(e.point.lng + "," + e.point.lat);
			$("#longitude").val(e.point.lng);
			$("#latitude").val(e.point.lat);
		});
	}
}

