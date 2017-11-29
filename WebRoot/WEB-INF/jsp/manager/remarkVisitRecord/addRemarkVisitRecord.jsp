<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/remarkVisitRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增备注拜访记录</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="record_id" id="record_id" value="${record_id }">
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									备注内容：
								</td>
								<td>
									<textarea id="remark" name="remark" class="my_input" style="width: 320px;height: 260px" datatype="*">
									
									</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注内容</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <s:if test="phoneOrOut == 'phone'">
							    <a id="manageLink" href="/phoneVisit_manage.action?customer_id=${customer_id }""></a>
						    </s:if>
						    <s:else>
							    <a id="manageLink" href="/employeeOutVisit_manage.action?customer_id=${customer_id }""></a>
						    </s:else>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>