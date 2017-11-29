<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjRefund.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>退款审核</span>
					</div>
					<form action="/${actionName}_edit.action" method="post">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<s:fielderror fieldName="id" cssStyle="color:red;"></s:fielderror>
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									退款原因说明：
								</td>
								<td>
									${bjdjRefund.reason}
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否允许退款：
								</td>
								<td>
									<select id="allow" name="allow" class="my_input" datatype="*">
										<option value="true" ${bjdjRefund.allow ? 'selected="selected"' : null }>是</option>
										<option value="false" ${bjdjRefund.allow ? null : 'selected="selected"' }>否</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 
									<s:fielderror fieldName="allow" cssStyle="color:red;"></s:fielderror>
									</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									管理员备注：
								</td>
								<td>
									<textarea id="remark" name="remark" class="my_input" datatype="*" style="width: 300px; height: 100px;">${bjdjRefund.remark}</textarea>
									<s:fielderror fieldName="other" cssStyle="color:red;"></s:fielderror>
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