/**
 * @descript 系统管理员专用JS
 * @author   HiSay
 * @datetime 2014-10-14 09:35:50
 */
 /*************************************************/

/**
 * @description 初始化系统管理员方法参数
 * @author HiSay
 * @datetime 2014-10-14 09:35:50
 */
jQuery(function(){
	editSystemUserInital();
	$(".selectedRole").bind("click",selectRole);
	$(".selectRole").bind("click",selectRole);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2014-10-14 09:35:50
 */
function editSystemUserInital() {
	//write code here.
	JM.selectValue('sex', $("#sex").attr("value"));
	$(".selectedRole").each(function(){
		$(this).prop("checked", "checked");
	});
}

function selectRole(){
	var roleId = $(this).attr("roleId");
	var userId = $("#userId").val();
	JM.alert("正在执行……", -1);
	//判断复选框是否已选中
	if($(this).prop("checked")){
		jQuery.ajax({
			type:'POST',
			dataType:'text',
			url:'/userRole_add.action',
			data:'roleId='+roleId+'&userId='+userId+'&operationFlag=1'+'&random='+JM.randomNumber,
			success:function(data){
				JM.alert("授权成功", 0);
				return;
				//alert('授权成功');
			}
		});
		
	}else{
		jQuery.ajax({
			type:'POST',
			dataType:'text',
			url:'/userRole_cancel.action',
			data:'roleId='+roleId+'&userId='+userId+'&operationFlag=1'+'&random='+JM.randomNumber,
			success:function(data){
				JM.alert("取消授权成功", 0);
				return;
				//alert('取消授权成功');
			}
		});
	}
}