<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/questionnaireSendLog.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑问卷发放日志</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											问卷id：
										</td>
										<td>
											<input id="questionnaireId" name="questionnaireId" class="my_input" datatype="*"
											       value="${questionnaireSendLog.questionnaireId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问卷id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											发放人：
										</td>
										<td>
											<input id="employee_id" name="employee_id" class="my_input" datatype="*"
											       value="${questionnaireSendLog.employee_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写发放人</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问卷发送数量：
										</td>
										<td>
											<input id="sendCount" name="sendCount" class="my_input" datatype="*"
											       value="${questionnaireSendLog.sendCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问卷发送数量</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问卷完成数量：
										</td>
										<td>
											<input id="writeCount" name="writeCount" class="my_input" datatype="*"
											       value="${questionnaireSendLog.writeCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问卷完成数量</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>