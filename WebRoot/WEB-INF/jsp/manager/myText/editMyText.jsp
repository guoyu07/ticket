<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/myText.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑我的记事本</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											关联客户信id：
										</td>
										<td>
											<input id="employeeInfo_id" name="employeeInfo_id" class="my_input" datatype="*"
											       value="${myText.employeeInfo_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写关联客户信id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											标题：
										</td>
										<td>
											<input id="title" name="title" class="my_input" datatype="*"
											       value="${myText.title}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写标题</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											内容：
										</td>
										<td>
											<input id="content" name="content" class="my_input" datatype="*"
											       value="${myText.content}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写内容</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											是否阅读：
										</td>
										<td>
											<input id="isRead" name="isRead" class="my_input" datatype="*"
											       value="${myText.isRead}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写是否阅读</p>       
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