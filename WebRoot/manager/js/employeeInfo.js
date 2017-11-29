/**
 * @descript 员工信息专用JS
 * @author   HiSay
 * @datetime 2015-11-03 15:33:02
 */
 /*************************************************/

/**
 * @description 初始化员工信息方法参数
 * @author HiSay
 * @datetime 2015-11-03 15:33:02
 */
jQuery(function(){
	editEmployeeInfoInital();
	
	$("#searchBtn").bind("click", search);
	$("#searchKeywordBtn").on("click",searchChannelCustomerInfo);
});

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
		url:'/employeeInfo_getChannelCustomerInfoList.action',
		data:'keyword='+JM.encodeByJQuery(keyword),
		success:function(data){
			if(!JM.isNull(data)){
				$("#customerId").empty();
				data = JSON.parse(data);
				$("#customerId").append($("<option>").val("").text("请选择绑定客户"));
				for(var i=0;i<data.length;i++){
					$("#customerId").append($("<option>").val(data[i].id).text(data[i].customerName));
				}
			}
		}
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-03 15:33:02
 */
function editEmployeeInfoInital() {
	//write code here.
}

/**
 * @description 搜索会员(根据员工姓名/电话关键词)
 * @author HiSay
 * @datetime 2015-04-02 15:31:32
 */
function search() {
	var keyword = $("#keyword");
	if(JM.isNullByJQuery(keyword)||keyword.val()=="请输入员工姓名/电话关键词"){
		alert('请输入检索关键词~');
		keyword.focus();
		return false;
	} else {
		window.location.href = '/employeeInfo_search.action?keyword='+JM.encode(keyword)+'&random='+JM.randomNumber;
	}
}