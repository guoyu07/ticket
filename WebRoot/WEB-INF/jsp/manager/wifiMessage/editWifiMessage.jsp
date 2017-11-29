<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/wifiMessage.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑wifi消息关联</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											wifi区域：
										</td>
										<td>
											<select id="wifi" name="wifi.id" class="my_input" datatype="*">
												<s:iterator var="w" value="wifis">
													<option value="${w.id }" ${w.id == wifiMessage.wifiArea.id ? 'selected="selected"' : null }>${w.name }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择wifi区域</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											消息：
										</td>
										<td>
											<select id="message" name="message.id" class="my_input" datatype="*">
												<s:iterator var="m" value="messages">
													<option value="${m.id }" ${m.id == wifiMessage.message.id ? 'selected="selected"' : null }>${m.content }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择消息</p>
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