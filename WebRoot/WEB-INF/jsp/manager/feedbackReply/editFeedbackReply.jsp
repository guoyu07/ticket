<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/feedbackReply.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑反馈与回复</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											所属反馈问题：
										</td>
										<td>
											<input id="feekBack" name="feekBack" class="my_input" datatype="*"
											       value="${feedbackReply.feekBack}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写所属反馈问题</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											反馈人员：
										</td>
										<td>
											<input id="member" name="member" class="my_input" datatype="*"
											       value="${feedbackReply.member}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写反馈人员</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问题描述：
										</td>
										<td>
											<input id="description" name="description" class="my_input" datatype="*"
											       value="${feedbackReply.description}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问题描述</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											客服人员：
										</td>
										<td>
											<input id="systemUser" name="systemUser" class="my_input" datatype="*"
											       value="${feedbackReply.systemUser}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写客服人员</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											回复内容：
										</td>
										<td>
											<input id="replyContent" name="replyContent" class="my_input" datatype="*"
											       value="${feedbackReply.replyContent}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写回复内容</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="button" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="editLink" href="/${actionName}_edit.action?versionFlag=${versionFlag}"></a>
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