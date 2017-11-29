/**
 * @descript 新闻信息专用JS
 * @author   HiSay
 * @datetime 2015-10-13 17:14:59
 */
 /*************************************************/

/**
 * @description 初始化新闻信息方法参数
 * @author HiSay
 * @datetime 2015-10-13 17:14:59
 */
jQuery(function(){
	if($("#addLocation").length>0){
		editNewsInital();
	}
	JM.selectSelect('viewPageRedirectTemplate_id') ;
	introduce = createEditor("introduce");
	content = createEditor("content");
	pcContent = createEditor("pcContent");
	
	initUploadify("thumbnail", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	initUploadify("thumbnail2", 4, "fileQueue2", true, false, "preImage2", globalVersionFlag);
	initUploadify("image", 2, "fileQueue1", true, true, "preImage1", globalVersionFlag);
	initUploadifyAnnex("video", 3, "fileQueueVideo", true, true, "preImageVideo", globalVersionFlag);
	
	$("#searchBtn").bind("click",search);
	$("#newsClassManage_id").bind("change", function(){
		if($(this).val() == '') {
			window.location.href = '/news_manage.action?random='+JM.randomNumber;
		} else {
			window.location.href = '/news_manage.action?newsClass_id='+$(this).val()+'&random='+JM.randomNumber;
		}
	});

});
function xiugai(){
	$("#allready").hide();
	$("#infoPosition_id").show();
}
/**
 * 新增位置
 * @author dfq
 * @datetime 2015-10-22 11:50
 * @return
 */
function newLocation(){
	$("#addLocation").show();
}
/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-13 17:14:59
 */
function editNewsInital() {
//	$("#addLocation").hide();
	// 百度地图API功能
	var position = $("#newsLocation");
	if(position&&position.size()>0){
		var map = new BMap.Map("newsLocation");
		map.centerAndZoom("昆明", 11);
		function showInfo(e){
			//alert(e.point.lng + ", " + e.point.lat);
			
			var text = $(':focus');
			if(text.size() == 1){
				
				var parent = text.parent();
				var lng = $(parent.children("[name='longitude']")[0]);
				var lat = $(parent.children("[name='latitude']")[0]);
				lng.val(e.point.lng);
				lat.val(e.point.lat);
			}else{
				
				alert("请选择一个区域点");
			}
		}
		map.addEventListener("click", showInfo);
	}
}
function search(){
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入新闻标题关键词"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/news_search.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}

/**
 * 增加一个区域坐标
 */
function addArea(){
	var addLocation = $('#addLocation');
	//先检查没有输入地区名称项
	var locationNames = addLocation.children().children("[name='locationName']");
	var existNull = false;
	locationNames.each(function(i){
		var my = $(this);
		if(typeof my.val() == 'undefined' || my.val() == ''){
			alert("名称必须填写");
			existNull = true;
		}
	});
	if(existNull == true){
		return;
	}
	var div = $('<div>');
	var areaName = $('<input name="locationName">');
	var areaLong = $('<input type="text" name="longitude" style="width: 40">');
	var floorNumber = $('<input type="text" name="floorNumber" style="width: 40">');
	var areaLat = $('<input type="text" name="latitude" style="width: 40"/>');
	var areaMobile = $('<input type="text" style="width: 40" name="mobiles"/>');
	var areaUrl = $('<input type="text" name="url"/>');
	var buttong = $('<input type="button" onclick="removeArea(this)" value="移除">');
	div.append("地点名称：");
	div.append(areaName);
	div.append("楼层号：");
	div.append(floorNumber);
	div.append("&nbsp;经度：");
	div.append(areaLong);
	div.append("&nbsp;纬度：");
	div.append(areaLat);
	div.append("<br />");
	div.append("&nbsp;电话：");
	div.append(areaMobile);
	div.append("&nbsp;链接：");
	div.append(areaUrl);
	div.append("&nbsp;");
	div.append(buttong);
	addLocation.append(div);
}

/**
 * 删除一个区域坐标
 */
function removeArea(dom){
	var button = $(dom);
	var id = button.attr('lid');
	var parent = button.parent(); 
	if(JM.isNull(id)){
		parent.remove();
		return false;
	}
	if(window.confirm("是否移除所选位置")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'infoPosition_realDelete.action',
			data:'id='+id,
			success:function(){
				//移除位置成功
				alert('移除位置成功');
			}
		});
	}
	parent.remove();
}