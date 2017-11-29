/**
 * @descript 系统配置专用JS
 * @author   HiSay
 * @datetime 2014-10-11 08:26:42
 */
 /*************************************************/

/**
 * @description 初始化系统配置方法参数
 * @author HiSay
 * @datetime 2014-10-11 08:26:42
 */
jQuery(function(){
	editSystemConfigInital();
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2014-10-11 08:26:42
 */
function editSystemConfigInital() {
	JM.selectValue('isAllowSignIn', $("#isAllowSignIn").attr("value"));
	JM.selectValue('isAllowCreate', $("#isAllowCreate").attr("value"));
}