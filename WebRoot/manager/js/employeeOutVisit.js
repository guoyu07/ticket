/**
 * @descript 员工外出拜访记录专用JS
 * @author   HiSay
 * @datetime 2015-11-02 22:49:40
 */
 /*************************************************/

/**
 * @description 初始化员工外出拜访记录方法参数
 * @author HiSay
 * @datetime 2015-11-02 22:49:40
 */
jQuery(function(){
	editEmployeeOutVisitInital();
	
	$("#searchBtn").on("click",searchChannelCustomerInfo);
	$("#searchFlowBtn").on("click",searchCustomerFlow);
	
	visitPurpose = createEditor('visitPurpose');
	
	visitResult = createEditor('visitResult');
	
	/**
	 * 根据客户id查找客户的外出拜访记录
	 */
	$("#customer_id").unbind().bind('change',function(){
		var customer_id = $(this).val();
		window.location.href = "channelCustomerInfo_viewCustomerOutVisitRecord.action?customer_id="+customer_id;
	});
	
	/**
	 * 查看客户流向
	 */
	$("#customerFlow_id").unbind().bind('change',function(){
		var id = $(this).val();
		queryCustomerFlow(id);
	});
});

function queryCustomerFlow(id){
	$("#customerFlow").empty();
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'channelCustomerInfo_showCustomerFlow.action',
		data:'customer_id='+id,
		success:function(data){
			if(data.status==JM.NO){
				$("#customerFlow").html("该客户未转接过");
			}
			else{
				$.each(data,function(i,item){
					if(i == data.length -1 ){
						
						$("#customerFlow").append("员工:"+item+"<br />");
					}else{
						$("#customerFlow").append("员工:"+item+">><br />");
						
					}
				});
			}
			
		}
	
	});
}
/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-02 22:49:40
 */
function editEmployeeOutVisitInital() {
	//write code here.
}

/**
 * ajax搜索渠道客户
 * @return
 */
function searchChannelCustomerInfo(){
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=='请输入公司名称或用户名关键词'){
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
					$("#customer_id").append($("<option>").val(data[i].id).text(data[i].name));
				}
			}
		}
	});
}

/**
 * ajax搜索客户
 * @return
 */
function searchCustomerFlow(){
	var keyword = $("#keywordByFlow");
	if(JM.isNullByJQuery(keyword)||keyword.val()=='请输入公司名称或用户名关键词'){
		$("#keywordByFlow").focus();
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/channelCustomerInfo_getChannelCustomerInfoList.action',
		data:'keyword='+JM.encodeByJQuery(keyword),
		success:function(data){
			if(!JM.isNull(data)){
				$("#customerFlow_id").empty();
				for(var i=0;i<data.length;i++){
					$("#customerFlow_id").append($("<option>").val(data[i].id).text(data[i].name));
				}
				queryCustomerFlow(data[0].id);
			}
		}
	});
}

