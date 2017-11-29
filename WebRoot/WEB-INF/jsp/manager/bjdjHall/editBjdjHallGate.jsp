<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑服务厅附近登机口</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											厅号：
										</td>
										<td>
											<select name="hall" class="my_input" datatype="*">
												<s:iterator var="h" value="halls">
												<option value="${h.id }" ${h.id==bjdjHallGate.hall.id ? "selected='selected'" : ""}>${h.number }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择厅号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											登机口：
										</td>
										<td>
											<select name="gate" class="my_input" datatype="*">
												<s:iterator var="g" value="gates">
												<option value="${g.id }" ${g.id==bjdjHallGate.gate.id ? "selected='selected'" : ""}>${g.name }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择登机口</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											距离(米)：
										</td>
										<td>
											<input name="meter" class="my_input" datatype="n" value="${bjdjHallGate.meter }"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写距离</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											描述：
										</td>
										<td>
											<textarea name="description" class="my_input" datatype="*">${bjdjHallGate.description }</textarea>
											<p class="Validform_checktip" style="display:inline;"> 请填写描述</p>
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