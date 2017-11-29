

$(function (){
	initUploadify("picture", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
	$("#commonFormSumit").on("click",commonFormSumit);
});

/**
 * 导入会员
 * @return
 */
function commonFormSumit(){
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'/channelCustomerAdmin_batchLeadingInMember.action',
		data:$("#commonForm").serialize(),
		success:function(data){
			$("#viewbox2").html(data);
		}
	});
}

