<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/template/wap/js/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="/template/wap/js/plupload/plupload.upload.js"></script>
<script type="text/javascript">
    $(function(){
    	$('#regulations').cxSelect({ 
			url: 'infringement_getRegulation.action',// 如果服务器不支持 .json 类型文件，请将文件改为 .js 文件 
			selects: ['regulations_parent', 'regulations_child'],// 数组格式，请注意顺序 
			nodata: 'none' 
				}
		);
		
		$("#regulations_child").change(function(){
			var parent = $("#regulations_parent").val();
			var child = $("#regulations_child").val();
			$("#rules").val(parent + "," + child);
		});
    });
</script>
<style>
	body{ font-size: 12px;}
	body,p,div{ padding: 0; margin: 0;}
	.wraper{ padding: 30px 0;}
	.btn-wraper{ text-align: center;}
	.btn-wraper input{ margin: 0 10px;}
	#file-list{ width: 350px; margin: 20px auto;}
	#file-list li{ margin-bottom: 10px;}
	.file-name{ line-height: 30px;}
	.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
	.tip1{text-align: center; font-size:14px; padding-top:10px;}
    .tip2{text-align: center; font-size:12px; padding-top:10px; color:#b00}
    .catalogue{ position: fixed; _position:absolute; _width:200px; left: 0; top: 0; border: 1px solid #ccc;padding: 10px; background: #eee}
    .catalogue a{ line-height: 30px; color: #0c0}
    .catalogue li{ padding: 0; margin: 0; list-style: none;}
    .easyDialog_wrapper1 .close_btn{ font-family:arial; font-size:18px; _font-size:12px; font-weight:700; color:#FFF; text-decoration:none; float:right; }
	.easyDialog_wrapper1 .close_btn:hover{ color:#333; }
</style>
<ticket:cache group="资讯中心">
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<s:if test="#session.fromApp == null && #parameters.fromApp == null">
					<div class="mobile-top">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i> </a> 内部员工登陆
							</a>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="mobile-top" style="display: none;">
						<div class="header">
							<a href="" class="FL"><i class="icon-angle-left"></i> </a> 内部员工登陆
							</a>
						</div>
					</div>
				</s:else>
				<form action="/infringement_add.action" method="post" name="memberForm" id="memberForm">
				<input type="hidden" value="1" name='operationFlag'/>
				<input type="hidden" value="${count }" name="numberId"/>
				<div class="mobile-main">
					<div class="mr40">
						<h1 class='text-center c_black fz24'>现场处理表</h1>
						<h5 class='text-right c_black fz18 padding-big-bottom'>NO.0${count }号</h5>
						<table class="table table-bordered c_black work_table">
							<tr>
								<td class="fz18" width="120">时间</td>
								<td class="fz18"><input type="date" name="time" datatype="*"
									style="width:100%;height:100%;border:0px;">
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
								<td class="fz18">检查人</td>
								<td class="fz18"><input type="text" name="inspectName" datatype="*"
									style="width:100%;height:100%;border:0px;">
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">单位名称</td>
								<td colspan="3" class="fz18"><input type="text" name="unitName" datatype="*"
									style="width:100%;height:100%;border:0px;">
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">违规人员姓名</td>
								<td class="fz18"><input type="text" name="illegalName" datatype="*"
									style="width:100%;height:100%;border:0px;">
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
								<td class="fz18">违规人员证件号</td>
								<td class="fz18"><input type="text" name="illegalCard" datatype="*"
									style="width:100%;height:100%;border:0px;">
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">违规事项</td>
								<td colspan="3" class="fz18"><textarea name="illegalMatter"
										style="width:100%;height:100px;border:0px;"></textarea>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">涉及规章制度</td>
								<td colspan="3" class="fz18">
									<input type="hidden" value="" name="rules" id="rules"/>
									<div id="regulations">
									<select id="regulations_parent" class="regulations_parent">
									<%-- 	<option>规章制度选择</option>
									<s:if test="#request.regulationTypeList != null">
										<s:iterator id="regulationType" value="#request.regulationTypeList">
											<option value="${regulationType.id }">${regulationType.name }</option>
										</s:iterator>
									</s:if> --%>
									</select>
									<select id="regulations_child" class="regulations_child"></select>
									</div>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">整改意见</td>
								<td colspan="3" class="fz18"><textarea name="rectificationOpinion"
										style="width:100%;height:100px;border:0px;"></textarea>
								</td>
							</tr>
							<tr>
								<td class="fz18" width="120">现场照片</td>
								<td colspan="3" class="fz18">
									<input type="hidden" name="scenePhoto" id="scenePhoto"/>
									<div class="wraper">
										<div class="btn-wraper">
											<input type="button" value="选择文件..." id="browse" />
											<input type="button" value="上传" id="upload"/>
										</div>
										<ul id="file-list">

										</ul>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="tit_tab">
						<button id="submitBtn" class="button d_button bg-yello block">提交</button>
					</div>
					<a href="/infringement_manage.action" id="manageLink" style="display: none;"></a>
				</div>
				</form>
				<div class="easyDialog_wrapper1" id="easyDialogWrapper1" style="display: none;">
					<div class="easyDialog_content">
						<div class="easyDialog_title" id="easyDialogTitle">
							<a href="javascript:void(0)" title="关闭窗口" class="close_btn" id="closeBtn">&times;</a> 
						</div>
						<div class="easyDialog_text">
							<p id="message" style="display: none"></p>
							<img alt="" src="" id="showImg"/>
						</div>
					</div>
				</div>
				<%@ include file="../common/quickNav.jsp"%>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</ticket:cache>
</html>