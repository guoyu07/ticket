/**
 * @descript 客保日志专用JS
 * @author   HiSay
 * @datetime 2016-01-02 10:21:06
 */
 /*************************************************/

/**
 * @description 初始化客保日志方法参数
 * @author HiSay
 * @datetime 2016-01-02 10:21:06
 */
jQuery(function(){
	editCustomerProtectLogInital();
	$("#searchKeywordBtn").on("click",searchChannelCustomerInfo);
	$("#validateIsProtectedBtn").on("click",ajaxValidateIsProtected);
	$(".changeExpireStatus").on("click",changeExpireStatus);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-01-02 10:21:06
 */
function editCustomerProtectLogInital() {
	//write code here.
}

/**
 * 取消保护
 * @return
 */
function changeExpireStatus(){
	var id = $(this).attr("attrValue");
	if(confirm("是否确认取消保护")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/customerProtectLog_changeExpire.action',
			data:'id='+id,
			success:function(data){
				window.location.reload();
			}
		});
	}
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
		url:'/customerProtectLog_getChannelCustomerInfoList.action',
		data:'keyword='+JM.encodeByJQuery(keyword),
		success:function(data){
			if(!JM.isNull(data)){
				$("#channelCustomerInfo_id").empty();
				data = JSON.parse(data);
				for(var i=0;i<data.length;i++){
					$("#channelCustomerInfo_id").append($("<option>").val(data[i].id).text(data[i].customerName));
				}
			}
		}
	});
}

/**
 * 检测用户是否能保护
 * @return
 */
function ajaxValidateIsProtected(){
	var channelCustomerInfo_id = $("#channelCustomerInfo_id");
	if(JM.isNullByJQuery(channelCustomerInfo_id)){
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/customerProtectLog_validateIsProtected.action',
		data:'channelCustomerInfo_id='+JM.encodeByJQuery(channelCustomerInfo_id),
		success:function(data){
			if(JM.SUCCESS == data){
				alert("该客户可保护");
			}else{
				alert(data);
			}
		}
	});
}