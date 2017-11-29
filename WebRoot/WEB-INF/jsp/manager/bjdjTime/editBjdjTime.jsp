<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjTime.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑便捷登机时间段分配</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											起始时间：
										</td>
										<td>
											<input onclick="WdatePicker({dateFmt:'HH:mm:ss'});" id="startTime" name="startTime" class="my_input" datatype="*"
											       value="${bjdjTime.startTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写起始时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											结束时间：
										</td>
										<td>
											<input onclick="WdatePicker({dateFmt:'HH:mm:ss'});" id="endTime" name="endTime" class="my_input" datatype="*"
											       value="${bjdjTime.endTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写结束时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											排序值：
										</td>
										<td>
											<input id="orderValue" name="orderValue" class="my_input" datatype="*"
											       value="${bjdjTime.status.orderValue}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写排序值(排序值越小则越在前面)</p>       
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