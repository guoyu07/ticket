/**
 * @descript 审核拜访记录专用JS
 * @author   HiSay
 * @datetime 2016-04-29 10:39:00
 */
 /*************************************************/

/**
 * @description 初始化审核拜访记录方法参数
 * @author HiSay
 * @datetime 2016-04-29 10:39:00
 */
jQuery(function(){
	editAuditVisitRecordInital();
	
	$("#auditState").unbind().bind("click",function(){
		var val = $(this).val();
		if(val==0){
			$("#remark").val("");
		}else{
			$("#remark").val("经过电话回访，证实本次拜访记录属实！");
		}
	});
	
	$("#queryByAuditState").unbind().bind("change",function(){
		var val = $(this).val();
		var phoneOrOut = $("#phoneOrOut").val();
		window.location.href="/auditVisitRecord_manage.action?auditState="+val+"&phoneOrOut="+phoneOrOut;
	});
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-04-29 10:39:00
 */
function editAuditVisitRecordInital() {
	//write code here.
}