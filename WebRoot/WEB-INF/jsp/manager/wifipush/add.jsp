<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/wifi.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>手动推送数据</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											手机号码：
										</td>
										<td>
											<input id="phone" name="phone" class="my_input" datatype="m"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写手机号码</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											消息：
										</td>
										<td>
											<select id="id" name="id" class="my_input" datatype="*">
												<s:iterator var="m" value="messages">
													<option value="${m.id }" ${m.id == wifiMessage.message.id ? 'selected="selected"' : null }>${m.title }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择消息</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="发送" class="btn_submit">
						    <input type="reset" value="重置" class="btn_remove margin_left20">
						    <a id="manageLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>