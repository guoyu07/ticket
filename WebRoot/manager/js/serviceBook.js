/**
 * @descript 服务订单专用JS
 * @author   HiSay
 * @datetime 2015-10-15 12:56:11
 */
 /*************************************************/

/**
 * @description 初始化服务订单方法参数
 * @author HiSay
 * @datetime 2015-10-15 12:56:11
 */
jQuery(function(){
	editServiceBookInital();
	
	$("#searchBtn").bind("click",search);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-10-15 12:56:11
 */
function editServiceBookInital() {
	JM.selectSelect('payStatus');
	//write code here.
}

function search(){
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入订单联系电话关键词"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/serviceBook_search.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}
