/**
 * @descript 旅游景点专用JS
 * @author   HiSay
 * @datetime 2016-09-30 09:54:17
 */
 /*************************************************/
jQuery(function(){
	introduce = createEditor("introduce");
	detail = createEditor("detail");
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	editScenicSpotsInital();
	
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-09-30 09:54:17
 */
function editScenicSpotsInital() {
	//write code here.
}