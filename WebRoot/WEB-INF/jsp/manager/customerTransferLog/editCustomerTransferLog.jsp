<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/customerTransferLog.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑客户转让日志</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											转让前员工：
										</td>
										<td>
											<input id="beforeEmployee_id" name="beforeEmployee_id" class="my_input" datatype="*"
											       value="${customerTransferLog.beforeEmployee_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写转让前员工</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											转让后员工：
										</td>
										<td>
											<input id="afterEmployee_id" name="afterEmployee_id" class="my_input" datatype="*"
											       value="${customerTransferLog.afterEmployee_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写转让后员工</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											操作员ID：
										</td>
										<td>
											<input id="operator_id" name="operator_id" class="my_input" datatype="*"
											       value="${customerTransferLog.operator_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写操作员ID</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											客户ID：
										</td>
										<td>
											<input id="customer_id" name="customer_id" class="my_input" datatype="*"
											       value="${customerTransferLog.customer_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写客户ID</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											备注：
										</td>
										<td>
											<input id="remark" name="remark" class="my_input" datatype="*"
											       value="${customerTransferLog.remark}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>       
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