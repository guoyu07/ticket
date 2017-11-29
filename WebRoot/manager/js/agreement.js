/**
 * @descript 客户合同专用JS
 * @author   HiSay
 * @datetime 2015-11-04 14:49:37
 */

/**
 * @description 初始化客户合同方法参数
 * @author HiSay
 * @datetime 2015-11-04 14:49:37
 */
jQuery(function(){
	editAgreementInital();
	content = createEditor('content');
	effectiveNoCan();
	$("#searchKeywordBtn").on("click",searchChannelCustomerInfo);
	$(".guidangAgreement").on("click",guidangAgreement);
	$(".changeEffective").on("click",changeEffective);
	$("#agreementState").on("change",effectiveNoCan);
});

/**
 * 审核状态改变
 */
function effectiveNoCan(){
	var agreementState = $("#agreementState").val();
	if(agreementState == "2"){
		$("#name").attr("ignore","ignore");
	}
}


/**
 * 废除合同
 * @return
 */
function changeEffective(){
	var id = $(this).attr("attrValue");
	if(confirm("是否确认废除合同")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/agreement_changeEffective.action',
			data:'id='+id,
			success:function(data){
				if(JM.SUCCESS == data){
					window.location.reload();
				}
			}
		});
	}
}

function reapply(id){
	if(confirm("是否确认重新申请")){
		$.ajax({
			type:'POST',
			dataType:'json',
			url:'/agreement_reapply.action',
			data:'id='+id,
			success:function(data){
				window.location.reload();
			}
		});
	}
}

/**
 *  归档合同
 * @return
 */
function guidangAgreement(){
	var id = $(this).attr("attrValue");
	if(confirm("是否确认归档合同")){
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/agreement_guidang.action',
			data:'id='+id,
			success:function(data){
				if(JM.SUCCESS == data){
					window.location.reload();
				}
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
		url:'/agreement_getChannelCustomerInfoList.action',
		data:'keyword='+JM.encodeByJQuery(keyword),
		success:function(data){
			if(!JM.isNull(data)){
				$("#customerId").empty();
				data = JSON.parse(data);
				for(var i=0;i<data.length;i++){
					$("#customerId").append($("<option>").val(data[i].id).text(data[i].name));
				}
			}
		}
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-04 14:49:37
 */
function editAgreementInital() {
	//write code here.
}