/**
 * @descript 页面跳转模板专用JS
 * @author   HiSay
 * @datetime 2015-10-22 14:00:50
 */
 /*************************************************/

/**
 * @description 初始化页面跳转模板方法参数
 * @author HiSay
 * @datetime 2015-10-22 14:00:50
 */
jQuery(function(){
	editPageRedirectTemplateInital();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-22 14:00:50
 */
function editPageRedirectTemplateInital() {
	//write code here.
	JM.selectSelect('type');
	JM.selectSelect('isSinglePage');
}