<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/manager/js/evaluationAdmin.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>发送到处理部门</span>
					</div>
					<form action="/${actionName}_sendToDepartment.action" id="commonForm" name="commonForm">
						<input type="hidden" name="idsValue" value="${idsValue}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									用户：
								</td>
								<td>
									<input name="member" class="my_input" value="${evaluation.member.phone }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									主题：
								</td>
								<td>
									<input name="classes" class="my_input" value="${evaluation.classes.name }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									项目：
								</td>
								<td>
									<input name="project" class="my_input" value="${evaluation.project.name }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									时间：
								</td>
								<td>
									<input name="project" class="my_input" value='<s:date name="evaluation.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>' disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									评价内容：
								</td>
								<td>
									<textarea name="content" class="my_input" disabled="disabled" style="height: 100px; width: 200px;">${evaluation.content }</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									涉及制度：
								</td>
								<td>
									<textarea name="involveSystem" class="my_input" datatype="*" style="height: 100px; width: 200px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写涉及制度</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									运质意见：
								</td>
								<td>
									<textarea name="shipmentQualityOpinion" class="my_input" style="height: 100px; width: 200px;" datatype="*"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写处理意见</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									运质备注：
								</td>
								<td>
									<textarea name="remark" class="my_input" datatype="*" style="height: 100px; width: 200px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									请选择部门：
								</td>
								<td>
									<table class="easyui-tree department_tree" style="width:150px;height:75px"  
									        data-options="url:'airportDepartment_traverse.action',checkbox:true">  
									    <thead>  
									        <tr>  
									            <th data-options="field:'id'">ID</th>  
									            <th data-options="field:'text',width:150,align:'right'">名称</th>  
									        </tr>  
									    </thead>  
									</table>  
									<input name="department_id" type="hidden" datatype="*" errormsg="请选择部门"/>
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="发送" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="manageLink" href="<%=request.getHeader("Referer") %>"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>