<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/article.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增文章信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm"
						name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag"
							value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag"
							value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									所属栏目：
								</td>
								<td>
									<input id="newsClass_id" name="newsClass_id" class="my_input"
										datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写所属栏目
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写文章标题
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章内容：
								</td>
								<td>
									<input id="content" name="content" class="my_input"
										datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写文章内容
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章简介：
								</td>
								<td>
									<input id="introduce" name="introduce" class="my_input"
										datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写文章简介
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章缩略图：
								</td>
								<td>
									<input id="thumbnail" name="thumbnail" class="my_input"
										datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写文章缩略图
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章来源：
								</td>
								<td>
									<input id="source" name="source" class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写文章来源
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章编辑：
								</td>
								<td>
									<input id="author" name="author" class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写文章编辑
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									页面模板：
								</td>
								<td>
									<select name="viewPageRedirectTemplate_id" id="viewPageRedirectTemplate_id" class="my_select" datatype="*" >
										<option value="">默认模板</option>
										<ticket:systemCommon type="pageRedirectTemplateList" value="0"/>
										<s:if test="#request.pageRedirectTemplateList != null">
											<s:iterator id="pageTemplate" value="#request.pageRedirectTemplateList">
												<option value="${pageTemplate.id }">${pageTemplate.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写所属页面模板
									</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
							<input id="resetBtn" type="submit" value="取消"
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