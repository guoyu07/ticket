/**
 * @descript 充值记录专用JS
 * @author   HiSay
 * @datetime 2015-11-13 16:45:25
 */
 /*************************************************/

/**
 * @description 初始化充值记录方法参数
 * @author HiSay
 * @datetime 2015-11-13 16:45:25
 */
jQuery(function(){
	editRechargeRecordInital();
	$("#searchKeywordBtn").on("click",searchChannelCustomerInfo);
	getDetail();
	$("#channelCustomerInfoId").on("change",getDetail);
//	$("#channelCustomerInfoId").on("change",sumMoney);
	$("#amountOfMoney").on("keyup",sumMoney);
	$("body").on("click",".txkp",txkp);
	$("body").on("click",".tykp",tykp);
});

/**
 * 提醒开票
 */
function txkp(){
	var id = $(this).attr("attrValue");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/channelCustomerAdmin_txkp.action',
		data:'id='+id,
		success:function(data){
			if(JM.SUCCESS == data){
				alert("提醒成功!");
			}
			window.location.reload();
		}
	});
}

/**
 * 同意开票
 */
function tykp(){
	var id = $(this).attr("attrValue");
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/rechargeRecord_tykp.action',
		data:'id='+id,
		success:function(data){
			if(JM.SUCCESS == data){
				alert("操作成功!");
			}
			window.location.reload();
		}
	});
}



/**
 * 获取优惠政策
 * @return
 */
function getDetail(){
	var id  = $("#channelCustomerInfoId").val();
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/rechargeRecord_detail.action',
		data:'id='+id,
		success:function(data){
			$("#showDetail").html(data);
		}
	});
}

/**
 * 实际到账金额
 * @return
 */
function sumMoney(){
	var a = $("#chongzhibi").val() || 0;
	var b = $("#amountOfMoney").val() || 0;
	var amountOfMoney = $("#amountOfMoney").val();
	var zuidijine = $("#zuidijine").val();
	if(a == 0){
		$("#sumMoney").html('实际到账金额:'+b);
	}else{
		if(parseInt(amountOfMoney) >= parseInt(zuidijine)){
			$("#sumMoney").html('实际到账金额:'+a*b);
		}else{
			$("#sumMoney").html('实际到账金额:'+b);
		}
	}
}

/**
 * ajax搜索渠道客户
 * @return
 */
function searchChannelCustomerInfo(){
	var keyword = $("#searchKeyword").val();
	if(JM.isNull(keyword)){
		$("#searchKeyword").focus();
		return false;
	}
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/rechargeRecord_getChannelCustomerInfoList.action',
		data:'keyword='+keyword,
		success:function(data){
			if(!JM.isNull(data)){
				$("#channelCustomerInfoId").empty();
				data = JSON.parse(data);
				for(var i=0;i<data.length;i++){
					$("#channelCustomerInfoId").append($("<option>").val(data[i].id).text(data[i].name));
				}
				getDetail();
			}
		}
	});
}

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2015-11-13 16:45:25
 */
function editRechargeRecordInital() {
	//write code here.
}