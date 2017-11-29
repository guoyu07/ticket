/**
 * @descript 便捷登机分单表专用JS
 * @author   HiSay
 * @datetime 2015-11-23 22:53:55
 */
 /*************************************************/

/**
 * @description 初始化便捷登机分单表方法参数
 * @author HiSay
 * @datetime 2015-11-23 22:53:55
 */
jQuery(function(){
	editBjdjDispatchInital();
	$("#searchBtn").bind("click", search);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-23 22:53:55
 */
function editBjdjDispatchInital() {
	
	$('select').change(function(){
		
		var item = $(this);
		var id = item.val();
		var tip = item.next('.receiveTip');
		if(typeof(id)=='undefined' || $.trim(id)==''){
			tip.text('');
			return;
		}
		
		$.post(
				actionName + "_receiveAmount.action",
				{id : id},
				function(json){
					
					if(json.status == 'y'){
						
						tip.text(json.info);
					}
				}
		);
	});
}

function search() {
	var methodName = $(this).attr("methodName");
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入服务码"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/bjdjDispatch_'+methodName+'.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}