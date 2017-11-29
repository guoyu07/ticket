<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/questionnaireAnswer.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑问卷发放记录表</span>
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
									       value="${questionnaireAnswer.questionnaireId}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写问卷id</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									答题人id：
								</td>
								<td>
									<input id="object_id" name="object_id" class="my_input" datatype="*"
									       value="${questionnaireAnswer.object_id}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写答题人id</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问题编号：
								</td>
								<td>
									<input id="questionNo" name="questionNo" class="my_input" datatype="*"
									       value="${questionnaireAnswer.questionNo}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写问题编号</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									问题答案：
								</td>
								<td>
									<input id="qustionAnswer" name="qustionAnswer" class="my_input" datatype="*"
									       value="${questionnaireAnswer.qustionAnswer}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写问题答案</p>       
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