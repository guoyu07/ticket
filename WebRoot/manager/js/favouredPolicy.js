/**
 * @descript 优惠政策专用JS
 * @author   HiSay
 * @datetime 2015-11-14 14:34:06
 */
 /*************************************************/

/**
 * @description 初始化优惠政策方法参数
 * @author HiSay
 * @datetime 2015-11-14 14:34:06
 */
jQuery(function(){
	editFavouredPolicyInital();
	$("#rechargeAmount").on("keyup",sumMoney);
	$("#discountRate").on("keyup",sumMoney);
});

function sumMoney(){
	var a = $("#rechargeAmount").val() || 0;
	var b = $("#discountRate").val() || 0;
	$("#sumMoney").html(a*b);
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-14 14:34:06
 */
function editFavouredPolicyInital() {
	//write code here.
}