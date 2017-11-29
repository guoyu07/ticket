<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript"
		src="/manager/js/pageRedirectTemplate.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑页面跳转模板</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm"
						name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag"
							value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag"
							value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									页面名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
										value="${pageRedirectTemplate.name}" />
									<p class="Validform_checktip" style="display: inline;">
										请填写页面名称
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									跳转到哪个JSP：
								</td>
								<td>
									<input id="toPage" name="toPage" class="my_input" datatype="*"
										value="${pageRedirectTemplate.toPage}" />
									<p class="Validform_checktip" style="display: inline;">
										请填写跳转到哪个JSP
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									模板类型：
								</td>
								<td>
									<select name="type" id="type" class="my_select" datatype="*" currentValue="${pageRedirectTemplate.type }">
										<option value="1">列表页模板</option>
										<option value="0">详细页模板</option>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写模板类型
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否单篇文章：
								</td>
								<td>
									<select name="isSinglePage" id="isSinglePage" class="my_select" datatype="*" currentValue="${pageRedirectTemplate.isSinglePage}">
										<option value="1">单篇信息</option>
										<option value="0">多篇信息</option>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写是否单篇文章
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									所在目录：
								</td>
								<td>
									<input id="directory" name="directory" class="my_input" datatype="*" value="${pageRedirectTemplate.directory }" />
									<p class="Validform_checktip" style="display: inline;">请填写所在目录</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									到哪个页面Ajax：
								</td>
								<td>
									<input id="toPageAjax" name="toPageAjax" class="my_input" datatype="*" ignore="ignore" value="${pageRedirectTemplate.toPageAjax }"/>
									<p class="Validform_checktip" style="display: inline;">请填写页面Ajax</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
							<input id="returnBtn" type="button" value="返回"
								class="btn_remove margin_left20">
							<a id="addLink"
								href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
							<a id="manageLink"
								href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>