<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/article.js"></script>
	<script type="text/javascript">
	
	jQuery(function(){
	
		initUploadify("thumbnail", 1, "fileQueue1", true, false, "preImage", globalVersionFlag);
	});
	
	/**
	*	点击导入数据执行的方法
	*/
	function importData(){
	
		var fileUrl = jQuery("#fileNames").val();
		if(fileUrl == undefined){
			
			alert("你还没有上传文件!");
			return;
		}
		
		//	点击导入数据的时候,将按钮变成不可点击
		jQuery("#ajaxSubmit").attr("disabled","true");
		jQuery("#ajaxSubmit").css("background-color","#D9D9D9");
		jQuery("#ajaxSubmit").css("border","none");
		
		//	将提示文字显示
		$(".box1").show();
		
		// 获取相应的数据发起一个ajax 请求
		$.ajax({
		   type: "POST",
		   url: "airportPlan_intoData.action",
		   data: {"filePath":fileUrl},
		   success: function(msg){
		     
		     if(msg.indexOf("success")!=-1){
		     
		     	$(".illustrate").html("数据导入成功.....!");
		     	$(".illustrate2").html("为了数据的安全防止数据重复导入,若要在导入数据请重新点击菜单进入");
		     	
		     	//	点击导入数据的时候,将按钮变成可点击
				//	jQuery("#ajaxSubmit").removeAttr("disabled");
				//	jQuery("#ajaxSubmit").css("background-color","red");
				
		     }else {
		     
		     	$(".illustrate").html("数据导入失败.....!");
		     	//	点击导入数据的时候,将按钮变成可点击
				jQuery("#ajaxSubmit").removeAttr("disabled");
				jQuery("#ajaxSubmit").css("background-color","red");
		     }
		   },
		   error:function(a,b,c){  
		        alert("请求失败："+b);  
		        $(".illustrate").html("请求导入失败!");
		        
		    }  
		});
	}
	</script>
	<style>
		.box1 { 
				float:left;
				margin-left: 100px; 
				display: none;
				text-align:left;
			  }
		.box2 { float:right; }
		.illustrate { font-size:30px;}
		.illustrate2 { font-size:20px;}
	</style>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>航班数据导入</span>
					</div>
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_center" width="50%">
									上传文件(请点击浏览,选择特定格式 excel 文件, 格式不对将不能导入数据) ：
								</td>
								<td>
									<div id="fileQueue1"></div>
									<div style="clear: both;">
									<input type="file" id="thumbnail" name="thumbnail"
									style="width: 100px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
						</table>
						<div class="my_table_list_r">
							<div class="box1">
								<font color="red" class="illustrate">正在导入数据请稍等......</font>
								<br>
								<font color="red" class="illustrate2"></font>
							</div>
							<div class="box2">
								<input id="ajaxSubmit" type="button" value="导入数据" class="btn_submit" onclick="importData()">
							</div>
						</div>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>