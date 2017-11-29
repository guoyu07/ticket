<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjDispatchList.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑分单流程表</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											分单ID：
										</td>
										<td>
											<input id="dispatch_id" name="dispatch_id" class="my_input" datatype="*"
											       value="${bjdjDispatchList.dispatch_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写分单ID</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务员工ID：
										</td>
										<td>
											<input id="employee_id" name="employee_id" class="my_input" datatype="*"
											       value="${bjdjDispatchList.employee_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务员工ID</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											顺序：
										</td>
										<td>
											<input id="order" name="order" class="my_input" datatype="*"
											       value="${bjdjDispatchList.order}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写顺序</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											状态：
										</td>
										<td>
											<input id="state" name="state" class="my_input" datatype="*"
											       value="${bjdjDispatchList.state}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写状态</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											核销时间：
										</td>
										<td>
											<input id="time" name="time" class="my_input" datatype="*"
											       value="${bjdjDispatchList.time}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写核销时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问题反馈：
										</td>
										<td>
											<input id="feedback" name="feedback" class="my_input" datatype="*"
											       value="${bjdjDispatchList.feedback}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问题反馈</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											核销方式：
										</td>
										<td>
											<input id="way" name="way" class="my_input" datatype="*"
											       value="${bjdjDispatchList.way}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写核销方式</p>       
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