<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjServicePackage.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑便捷登机服务套餐</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											服务套餐名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"
											       value="${bjdjServicePackage.name}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务套餐名称</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务套餐价格：
										</td>
										<td>
											<input id="price" name="price" class="my_input" datatype="double"
											       value="${bjdjServicePackage.price}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务套餐价格</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务套餐对应服务厅：
										</td>
										<td>
											<select id="bjdjHall_id" name="bjdjHall_id" class="my_input" datatype="*">
												<option value="${bjdjServicePackage.bjdjHall.id}">之前选择： ${bjdjServicePackage.bjdjHall.number}</option>
												<s:iterator value="bjdjHalls" var="bjdjHall">
													<option value="${bjdjHall.id}">${bjdjHall.number }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择服务厅</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务套餐前端显示页面：
										</td>
										<td>
											<select id="page_id" name="page_id" class="my_input" datatype="*">
												<option value="${bjdjServicePackage.bjdjPage.id }">之前选择： ${bjdjServicePackage.bjdjPage.name }</option>
												<s:iterator value="bjdjPages" var="page">
													<option value="${page.id}">${page.name }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择服务套餐前端显示页面</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务项描述：
										</td>
										<td>
											<input id="description" name="description" class="my_input" datatype="*"
											       value="${bjdjServicePackage.description}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务项描述</p>       
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