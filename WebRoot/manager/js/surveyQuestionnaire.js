/**
 * @descript 调查问卷专用JS
 * @author   HiSay
 * @datetime 2015-11-11 17:10:59
 */
 /*************************************************/

/**
 * @description 初始化调查问卷方法参数
 * @author HiSay
 * @datetime 2015-11-11 17:10:59
 */
jQuery(function(){
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
		window.location.href = "/stationLetterReadLog_manage.action?versionFlag=site";
	});
	
	$(".showAnswerDetail").unbind().bind("click",function(){
		var obj =$(this);
		showAnswerDetail(obj);
	});
});

function showAnswerDetail(obj){
	var questionnaireId = obj.attr("questionnaireId");
	var questionNo = obj.attr("questionNo");
	var questionTitle = obj.attr("title");
	window.location.href="/surveyQuestionnaire_showAnswerDetail.action?id="+questionnaireId+"&questionNo="+questionNo+"&questionTitle="+JM.encodeByValue(questionTitle);
}

/**
 * 发送调查问卷
 */
function sendQuestionnaire(){
	var sendType = $("#sendFlag").val();
	var operationFlag = $("#operationFlag").val();
	var id=$("#id").val();
	if(sendType=="dept"){
		var deptIds = "";
		$('input:checkbox[name=selectedDept]:checked').each(function(i){
			if(0==i){
				deptIds = $(this).val();
			}else{
				deptIds += (","+$(this).val());
			}
		});
		if(JM.isNull(deptIds)){
			JM.alert("请选择部门");
			return;
		}else{
			JM.alert("正在执行……", -1);
			$.ajax({
				type:'POST',
				datatype:'json',
				url:'/surveyQuestionnaire_sendToObject.action',
				data:'id='+id+'&operationFlag='+operationFlag+"&idsValue="+deptIds+"&sendType="+sendType,
				success:function(val){
					if(val.status == JM.YES){
						JM.alert("问卷发送成功!");
						window.location.href = "/surveyQuestionnaire_manage.action?versionFlag=site";
						return;
					}else{
						JM.alert(val.info);
						return;
					}
				}
			});
		}
		
	}else{
		var typeIds = "";
		$('input:checkbox[name=selectType]:checked').each(function(i){
			if(0==i){
				typeIds = $(this).val();
			}else{
				typeIds += (","+$(this).val());
			}
		});
		if(JM.isNull(typeIds)){
			JM.alert("请选择渠道类别");
			return;
		}else{
			JM.alert("正在执行……", -1);
			$.ajax({
				type:'POST',
				datatype:'json',
				url:'/surveyQuestionnaire_sendToObject.action',
				data:'id='+id+'&operationFlag='+operationFlag+"&idsValue="+typeIds+"&sendType="+sendType,
				success:function(val){
					if(val.status == JM.YES){
						JM.alert("问卷发送成功!");
						window.location.href = "/surveyQuestionnaire_manage.action?versionFlag=site";
						return;
					}else{
						JM.alert("问卷发送失败!");
						return;
					}
				}
			});
		}
	}
}

/**
 * 根据部门选择发送问卷对象
 */
function checkByDept(){
	//allCheckboxDisChecked();
	$("#sendFlag").val("dept");
	$("#selectByChannelType").hide();
	$("#selectByDept").show();
}

/**
 * 根据客户类型选择发送问卷对象
 */
function checkByChannelType(){
	//allCheckboxDisChecked();
	$("#sendFlag").val("channelType");
	$("#selectByDept").hide();
	$("#selectByChannelType").show();
	
}
