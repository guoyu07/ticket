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
						<span>下发销售任务</span>
					</div>
					<form action="/${actionName}_publish.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr class="my_table_top1">
								<td>负责人(${saleTask.employee.name })</td>
								<td>签约数(${saleTask.signCount })</td>
								<td>充值金额(${saleTask.recharge })</td>
								<td>电话拜访量(${saleTask.phoneCount })</td>
								<td>外出拜访量(${saleTask.visitCount })</td>
							</tr>
							<s:set var="rowSpan" value="saleTask.department.childs.size()"></s:set>
							<s:if test="#rowSpan == 0">
								<s:iterator var="emp" value="list" status="st">
									<tr>
										<td>
											${emp.name }
											<input type="hidden" name="employees" value="${emp.id }" datatype="*" nullmsg="该部门未指定负责人"/>
										<td>
											<input id="signCount" name="signCounts" class="my_input" datatype="n"/>
											<p class="Validform_checktip"> 请填写签约数</p>
										</td>
										<td>
											<input id="recharge" name="recharges" class="my_input" datatype="double"/>
											<p class="Validform_checktip"> 请填写充值金额</p>
										</td>
										<td>
											<input id="phoneCount" name="phoneCounts" class="my_input" datatype="n"/>
											<p class="Validform_checktip"> 请填写电话拜访量</p>
										</td>
										<td>
											<input id="visitCount" name="visitCounts" class="my_input" datatype="n"/>
											<p class="Validform_checktip"> 请填写外出拜访量</p>
										</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>					
								<s:iterator var="subDepartment" value="saleTask.department.childs" status="st">
									<tr>
										<td>
											${subDepartment.name }：
											<input type="hidden" name="employees" value="${subDepartment.inCharge.id }" datatype="*" nullmsg="该部门未指定负责人"/>
											<s:if test="#subDepartment.inCharge.name != null">
												${subDepartment.inCharge.name }
											</s:if>
											<s:else>
												<span style="color: red;">未指定</span>
											</s:else>
											<p class="Validform_checktip"></p>
										<td>
											<input id="signCount" name="signCounts" class="my_input" datatype="n"/>
											<p class="Validform_checktip"> 请填写签约数</p>
										</td>
										<td>
											<input id="recharge" name="recharges" class="my_input" datatype="double"/>
											<p class="Validform_checktip"> 请填写充值金额</p>
										</td>
										<td>
											<input id="phoneCount" name="phoneCounts" class="my_input" datatype="n"/>
											<p class="Validform_checktip"> 请填写电话拜访量</p>
										</td>
										<td>
											<input id="visitCount" name="visitCounts" class="my_input" datatype="n"/>
											<p class="Validform_checktip"> 请填写外出拜访量</p>
										</td>
									</tr>
								</s:iterator>
							</s:else>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
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