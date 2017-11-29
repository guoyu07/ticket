<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>聚名统一资源管理后台</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<meta http-equiv="Content-Language" content="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<%@ include file="/WEB-INF/jsp/manager/common/tags.jsp"%>
		<link href="/manager/frame/style/sub.css" rel="stylesheet" type="text/css" />
		<link href="/common/jcrop/jquery-ui-1.8.12.custom.css" rel="Stylesheet" type="text/css" />
		<script type="text/javascript" src="/common/jcrop/jquery.js"></script>
		<script type="text/javascript" src="/common/jcrop/jquery-ui-1.8.12.custom.min.js"></script>
		<script type="text/javascript" src="/common/jcrop/jquery.cropzoom.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		     var cropzoom = $('#crop_container2').cropzoom({
		       width:768,
		       height:432,
		       bgColor: '#CCC',
		       enableRotation:true,
		       enableZoom:true,
		       zoomSteps:5,
		       rotationSteps:10,
		       selector:{        
		         centered:true,
		         w:150,
		         h:200,
		         borderColor:'blue',
		         borderColorHover:'red',
				 showPositionsOnDrag: false,
		         showDimetionsOnDrag: false
		       },
		       image:{
		           source:'/common/jcrop/test.jpg',
		           width:1280,
		           height:720,
		           minZoom:100,
		           maxZoom:300,
		        startZoom:60,
		           useStartZoomAsMinZoom:true,
		           snapToContainer:true //图片 边界不能移出容器边界
		       }
		   });
		   $("#crop_container2_selector").live("dblclick", function(){
		   	   cropzoom.send('/imageCutOnline.action','POST',{},function(rta){
		           $('#result_image').find('img').remove();
		           var img = $('<img />').attr('src',rta);
		           $('#result_image').append(img);
		       });
		   });
		});
		</script>
	</head>
	<body>
		<div id="fixed_top">
			<div class="panelBar">
				<div class="toolBar_left"></div>
				<div class="toolBar_right"></div>
			</div>
		</div>
		<div id="fixed_body">
			<form action="#" id="commonForm" name="commonForm">
				<table class="aykj_tab1" cellspacing="0" cellpadding="0"
					width="100%" border="0">
					<tr>
						<td align="center">
							源图片
						</td>
						<td align="center">
							裁剪后图片
						</td>
					</tr>
					<tr>
						<td align="center" width="50%">
							<div id="crop_container2"></div>
						</td>
						<td align="center" width="50%">
							<div id="result_image"></div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="fixed_down">
			<div class="panelBar">
				<div class="toolBar_right">
				</div>
			</div>
		</div>
	</body>
</html>