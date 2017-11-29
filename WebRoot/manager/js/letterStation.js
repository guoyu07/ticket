/**
 * @descript 站内信专用JS
 * @author   HiSay
 * @datetime 2016-01-03 13:52:54
 */
 /*************************************************/

/**
 * @description 初始化站内信方法参数
 * @author HiSay
 * @datetime 2016-01-03 13:52:54
 */
jQuery(function(){
	content = createEditor('content');
	
	$("#selectAllDeptCheckBox").bind("click",function(){
		if($(this).prop("checked")){
			$('input:checkbox[name=selectedDept]').each(function(i){
				$(this).prop("checked",true);
			});
		}else{
			$('input:checkbox[name=selectedDept]').each(function(i){
				$(this).prop("checked",false);
			});
		}
	});
	$("#selectAllTypeCheckBox").bind("click",function(){
		if($(this).prop("checked")){
			$('input:checkbox[name=selectType]').each(function(i){
				$(this).prop("checked",true);
			});
		}else{
			$('input:checkbox[name=selectType]').each(function(i){
				$(this).prop("checked",false);
			});
		}
	});
	
	$(".returnToLetterStation").unbind().bind('click',function(){
		window.location.href = "/letterStation_manage.action?versionFlag=site";
	});
	
	$(".showAnswerDetail").unbind().bind("click",function(){
		var obj =$(this);
		showAnswerDetail(obj);
	});
});

/**
 * 发送站内信
 */
function sendLetter(){
	var sendType = $("#sendFlag").val();
	var operationFlag = $("#operationFlag").val();
	var id=$("#id").val();
	var deptIds = "";
	var typeIds = "";
	var sendType = "";
	var idsValue = "";
	$('input:checkbox[name=selectedDept]:checked').each(function(i){
		if(0==i){
			deptIds = $(this).val();
		}else{
			deptIds += (","+$(this).val());
		}
	});
	$('input:checkbox[name=selectType]:checked').each(function(i){
		if(0==i){
			typeIds = $(this).val();
		}else{
			typeIds += (","+$(this).val());
		}
	});
	if(JM.isNull(typeIds)){
		if(JM.isNull(deptIds)){
			JM.alert("您还没有选择发送对象！");
			return false;
		}
		else{
			sendType="1";
			idsValue = deptIds;
		}
	}else{
		if(JM.isNull(deptIds)){
			sendType="2";
			idsValue = typeIds;
		}else{
			sendType="3";
			idsValue=deptIds+"#"+typeIds;
		}
	}
	$.ajax({
		type:'POST',
		dataType:'json',
		url:'/letterStation_send.action',
		data:'id='+id+'&operationFlag='+operationFlag+"&idsValue="+idsValue+"&sendType="+sendType,
		success:function(val){
			if(val.status == JM.YES){
				JM.alert("站内信发送成功!");
				window.location.href = "/letterStation_manage.action?versionFlag=site";
				return;
			}else{
				JM.alert(val.info);
				return;
			}
		}
	});
}

