<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/common/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js">
	<script type="text/javascript" src="/common/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>部门处理信息</span>
					</div>
					<form action="/${actionName}_departmentProcess.action" id="commonForm" name="commonForm">
						<input type="hidden" name="idsValue" value="${idsValue}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									用户：
								</td>
								<td>
									<input name="member" class="my_input" value="${process.evaluation.member.phone }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									主题：
								</td>
								<td>
									<input name="classes" class="my_input" value="${process.evaluation.classes.name }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									项目：
								</td>
								<td>
									<input name="project" class="my_input" value="${process.evaluation.project.name }" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									时间：
								</td>
								<td>
									<input name="project" class="my_input" 
										value='<s:date name="process.evaluation.status.createTime"
										format="yyyy-MM-dd HH:mm:ss"/>' disabled="disabled">
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									评价内容：
								</td>
								<td>
									<textarea name="content" class="my_input" disabled="disabled" style="height: 100px; width: 200px;">${process.evaluation.content }</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									涉及制度：
								</td>
								<td>
									<textarea name="involveSystem" class="my_input" style="height: 100px; width: 200px;" disabled="disabled">${process.evaluation.involveSystem }</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									运质备注：
								</td>
								<td>
									<textarea name="remark" class="my_input" style="height: 100px; width: 200px;" disabled="disabled">${process.evaluation.remark }</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									运质意见：
								</td>
								<td>
									<textarea name="shipmentQualityOpinion" class="my_input" style="height: 100px; width: 200px;" disabled="disabled">${process.evaluation.shipmentQualityOpinion }</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写处理意见</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									处理结果：
								</td>
								<td>
									<textarea name="msg" class="my_input" datatype="*" style="height: 100px; width: 200px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写处理结果</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea name="remark" class="my_input" datatype="*" style="height: 100px; width: 200px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问题是否关闭：
								</td>
								<td>
									<input name="close" type="radio" value="true"/>&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input name="close" type="radio" value="false"/>&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<p class="Validform_checktip" style="display:inline;"> 请填写问题是否关闭</p>       
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