/**
 * @descript 消息提醒专用JS
 * @author   HiSay
 * @datetime 2015-11-17 16:10:53
 */
 /*************************************************/

/**
 * @description 初始化消息提醒方法参数
 * @author HiSay
 * @datetime 2015-11-17 16:10:53
 */
jQuery(function(){
	editMessageReminderInital();
	content = createEditor("content");
	
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-17 16:10:53
 */
function editMessageReminderInital() {
	//write code here.
}