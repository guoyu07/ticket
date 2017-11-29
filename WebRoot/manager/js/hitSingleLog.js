/**
 * @descript 撞单日志专用JS
 * @author   HiSay
 * @datetime 2016-01-03 09:38:37
 */
 /*************************************************/

/**
 * @description 初始化撞单日志方法参数
 * @author HiSay
 * @datetime 2016-01-03 09:38:37
 */
jQuery(function(){
	editHitSingleLogInital();
	$("#searchKeywordBtn").on("click",searchChannelCustomerInfo);
	$("#channelCustomerInfo_id").on("change",getAgreenmentAndCustomer);
});

/**
 * 获取客户及合同信息
 * @return
 */
function getAgreenmentAndCustomer(){
	var id = $(this).val();
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/hitSingleLog_getAgreenmentAndCustomer.action',
		data:'id='+id,
		success:function(data){
			$("#getAgreenmentAndCustomer").html(data);
		}
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-01-03 09:38:37
 */
function editHitSingleLogInital() {
	//write code here.
}

/**
 * ajax搜索渠道客户
 * @return
 */
function searchChannelCustomerInfo(){
	var keyword = $("#searchKeyword");
	if(JM.isNullByJQuery(keyword)){
		$("#searchKeyword").focus();
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/hitSingleLog_getChannelCustomerInfoList.action',
		data:'keyword='+JM.encodeByJQuery(keyword),
		success:function(data){
			if(!JM.isNull(data)){
				$("#channelCustomerInfo_id").empty();
				data = JSON.parse(data);
				$("#channelCustomerInfo_id").append($("<option>").val("").text("选择客户"));
				for(var i=0;i<data.length;i++){
					$("#channelCustomerInfo_id").append($("<option>").val(data[i].id).text(data[i].customerName));
				}
			}
		}
	});
}