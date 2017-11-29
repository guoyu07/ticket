<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/feedback.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增公测反馈</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											反馈人员：
										</td>
										<td>
											<input id="member" name="member" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写反馈人员</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											问题描述：
										</td>
										<td>
											<input id="description" name="description" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写问题描述</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											手机号码：
										</td>
										<td>
											<input id="phone" name="phone" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写手机号码</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="button" value="提交" class="btn_submit">
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