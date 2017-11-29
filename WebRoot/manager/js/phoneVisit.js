/**
 * @descript 电话回访记录专用JS
 * @author   HiSay
 * @datetime 2015-11-02 23:14:13
 */
 /*************************************************/

/**
 * @description 初始化电话回访记录方法参数
 * @author HiSay
 * @datetime 2015-11-02 23:14:13
 */
jQuery(function(){
	editPhoneVisitInital();
	
	$("#searchBtn").on("click",searchChannelCustomerInfo);
	
	content = createEditor('content');
	
	$("#department_id").bind('change',function(){
		var department_id = $("department_id").val();
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/phoneVisit_employeeAjax.action',
			data:'department_id='+department_id,
			success:function(data){
				$("#employeeAjax").html(data);
			}
		});
	})
	
	/**
	 * 根据客户id查找客户的电话拜访记录
	 */
	$("#customer_id").unbind().bind('change',function(){
		var customer_id = $(this).val();
		window.location.href = "channelCustomerInfo_viewCustomerPhoneVisitRecord.action?customer_id="+customer_id;
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-02 23:14:13
 */
function editPhoneVisitInital() {
	//write code here.
}

/**
 * ajax搜索客户
 * @return
 */
function searchChannelCustomerInfo(){
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=='请输入公司名称关键词'){
		$("#keyword").focus();
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/channelCustomerInfo_getChannelCustomerInfoList.action',
		data:'keyword='+JM.encodeByJQuery(keyword),
		success:function(data){
			if(!JM.isNull(data)){
				$("#customer_id").empty();
				data = JSON.parse(data);
				for(var i=0;i<data.length;i++){
					$("#customer_id").append($("<option>").val(data[i].id).text(data[i].customerName));
				}
			}
		}
	});
}