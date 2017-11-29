/**
 * @descript 推荐会员头像专用JS
 * @author   HiSay
 * @datetime 2016-03-14 10:49:59
 */
 /*************************************************/

/**
 * @description 初始化推荐会员头像方法参数
 * @author HiSay
 * @datetime 2016-03-14 10:49:59
 */
jQuery(function(){
	editMemberLogoInital();
	
	introduce = createEditor('introduce');
	activityForecast = createEditor('activityForecast');
	
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-03-14 10:49:59
 */
function editMemberLogoInital() {
	var scenePhoto = $("#scenePhoto").val();
	if(JM.isNull(scenePhoto)){
		JM.alert("请先上传", 2000);
		return false;
	}
}