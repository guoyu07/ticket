<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/saleTask.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑销售任务</span>
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
											<input id="startTime" name="startTime" class="my_input" datatype="*"
											       value="${saleTask.startTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写起始时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											结束时间：
										</td>
										<td>
											<input id="endTime" name="endTime" class="my_input" datatype="*"
											       value="${saleTask.endTime}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写结束时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											任务等级：
										</td>
										<td>
											<input id="department" name="department" class="my_input" datatype="*"
											       value="${saleTask.department}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写任务等级</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											创建员工：
										</td>
										<td>
											<input id="employee" name="employee" class="my_input" datatype="*"
											       value="${saleTask.employee}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写创建员工</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											签约数：
										</td>
										<td>
											<input id="signCount" name="signCount" class="my_input" datatype="*"
											       value="${saleTask.signCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写签约数</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											充值金额：
										</td>
										<td>
											<input id="recharge" name="recharge" class="my_input" datatype="*"
											       value="${saleTask.recharge}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写充值金额</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											电话拜访量：
										</td>
										<td>
											<input id="phoneCount" name="phoneCount" class="my_input" datatype="*"
											       value="${saleTask.phoneCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写电话拜访量</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											外出拜访量：
										</td>
										<td>
											<input id="visitCount" name="visitCount" class="my_input" datatype="*"
											       value="${saleTask.visitCount}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写外出拜访量</p>       
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