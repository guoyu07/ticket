<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/stationLetterSendRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑站内信发送记录</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											操作员id：
										</td>
										<td>
											<input id="opertator_id" name="opertator_id" class="my_input" datatype="*"
											       value="${stationLetterSendRecord.opertator_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写操作员id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											站内信id：
										</td>
										<td>
											<input id="letter_id" name="letter_id" class="my_input" datatype="*"
											       value="${stationLetterSendRecord.letter_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写站内信id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											对象标识：
										</td>
										<td>
											<input id="objectFlag" name="objectFlag" class="my_input" datatype="*"
											       value="${stationLetterSendRecord.objectFlag}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写对象标识</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											对象id：
										</td>
										<td>
											<input id="object_id" name="object_id" class="my_input" datatype="*"
											       value="${stationLetterSendRecord.object_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写对象id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											发送数量：
										</td>
										<td>
											<input id="sendCount" name="sendCount" class="my_input" datatype="*"
											       value="${stationLetterSendRecord.sendCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写发送数量</p>       
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
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