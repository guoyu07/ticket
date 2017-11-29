/**
 * @descript 机场信息专用JS
 * @author   HiSay
 * @datetime 2016-04-01 16:37:25
 */
 /*************************************************/

/**
 * @description 初始化机场信息方法参数
 * @author HiSay
 * @datetime 2016-04-01 16:37:25
 */
jQuery(function(){
	editAirportInfoInital();
	setAirportHot();
	
	initUploadify("thumbnail", 1, "fileQueue", true, false, "preImage", globalVersionFlag);
});

/**
 * @description 当编辑的时候初始化一些参数
 * @author HiSay
 * @datetime 2016-04-01 16:37:25
 */
function editAirportInfoInital() {
	$("#selectDistrict").unbind().bind("change",function(){
		var flag = $(this).val();
		$("#domOrInt").val(flag);
		window.location.href = "/airportInfo_manage.action?domOrInt="+flag;
	});
	$("#searchBtn").unbind().bind("click",function(){
		var keyword = $("#keyword").val();
		var flag=$("#domOrInt").val();
		if(JM.isNull(keyword)){
			JM.alert("请输入机场关键词");
			$("#keyword").focus();
			return;
		}else{
			window.location.href ="/airportInfo_manage.action?domOrInt="+flag+"&keyword="+JM.encodeByValue(keyword);
		}
	});
	JM.selectSelect("selectDistrict");
	
	$(".downLoadExcel").unbind().bind("click",function(){
		var isConfirm = window.confirm("是否确认下载机场信息？");
		if(isConfirm){
			window.location.href="/airportInfo_exportToExcel.action";
//			JM.alert("正在下载……", -1);
//			$.ajax({
//				type:'POST',
//				dataType:'json',
//				url:'/airportInfo_exportToExcel.action',
//				data:'',
//				success:function(){
//					JM.alert("下载完成！",500);
//				}
//			});
		}
	});
	//write code here.
	$("#downloadTemplate").unbind().bind("click",function(){
		window.location.href="/airportInfo_downloadTemplate.action";
	});
}

function setAirportHot(){
	$(".hotAirport").bind("click",function(){
		var id = $(this).attr("value");
		var statusValue = $(this).attr("statusValue");
		var content = $(this).text().trim();
		if(content=='设为热门'){
			$(this).html("取消热门");
		}else{
			$(this).html("设为热门");
		}
		JM.alert('请稍后...', -1);
		$.ajax({
			type:'POST',
			dataType:'text',
			url:'/airportInfo_setHotAirport.action',
			data:'id='+id+"&statusValue="+statusValue,
			success:function(){
			JM.alert('设置成功！',500);
			}
		});
	});
}

function upload(){
	var fileUrl = jQuery("#fileNames").val();
	if(fileUrl == undefined){
		JM.alert("请选择文件!",500);
		return;
	}
	
	//	点击导入数据的时候,将按钮变成不可点击
	jQuery("#ajaxSubmit").attr("disabled","true");
	jQuery("#ajaxSubmit").css("background-color","#D9D9D9");
	jQuery("#ajaxSubmit").css("border","none");
	
	JM.alert("正在执行数据导入……", -1);
	// 获取相应的数据发起一个ajax 请求
	$.ajax({
	   type: "POST",
	   url: "airportInfo_importFromExcel.action",
	   data: {"filePath":fileUrl},
	   success: function(msg){
	     if(msg.indexOf("success")!=-1){
	    	 JM.alert("数据导入完成！");
	    	 jQuery("#importExcel").html("数据导入成功！");
	    	 //jQuery("#ajaxSubmit").removeAttr("disabled");
	    	 jQuery("#ajaxSubmit").css("background-color","red");
	     	
	     }else {
	     
	     	$(".illustrate").html("数据导入失败!");
	     	//	点击导入数据的时候,将按钮变成可点击
	     	jQuery("#ajaxSubmit").removeAttr("disabled");
			jQuery("#ajaxSubmit").css("background-color","red");
	     }
	   },
	   error:function(a,b,c){  
	        alert("请求失败："+b);  
	    }  
	});
}
