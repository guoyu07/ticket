<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/systemFreebackInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>系统反馈信息详情</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									姓名或账号：
								</td>
								<td>
									<label>${systemFreebackInfo.name}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									手机号：
								</td>
								<td>
									<label>${systemFreebackInfo.phone}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									反馈网址：
								</td>
								<td>
									<label>${systemFreebackInfo.url}</label>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									反馈信息：
								</td>
								<td>
									<textarea disabled="disabled" style="width: 100%;height: 280px">${systemFreebackInfo.content}</textarea>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input type="button" value="返回" onclick="javascript:history.go(-1)" class="btn_remove margin_left20">
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>