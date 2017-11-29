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
						<span>新增便捷登机服务套餐</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											服务套餐名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务套餐名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务套餐价格：
										</td>
										<td>
											<input id="price" name="price" class="my_input" datatype="double"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务套餐价格</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务套餐对应服务厅：
										</td>
										<td>
											<select id="bjdjHall_id" name="bjdjHall_id" class="my_input" datatype="*">
												<s:iterator value="bjdjHalls" var="bjdjHall">
													<option value="${bjdjHall.id }">${bjdjHall.number }</option>
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
												<s:iterator value="bjdjPages" var="page">
													<option value="${page.id }">${page.name }</option>
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
											<textarea id="description" name="description" class="my_input" datatype="*"></textarea>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务项描述</p>
										</td>
									</tr>
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