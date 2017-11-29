/**
 * @descript 新闻类别专用JS
 * @author   HiSay
 * @datetime 2015-10-13 17:40:45
 */
 /*************************************************/

/**
 * @description 初始化新闻类别方法参数
 * @author HiSay
 * @datetime 2015-10-13 17:40:45
 */
jQuery(function(){
	editNewsClassInital();
	descript = createSimpleEditor('descript');
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-13 17:40:45
 */
function editNewsClassInital() {
	//write code here.
	JM.selectSelect('listPageRedirectTemplate_id');
}