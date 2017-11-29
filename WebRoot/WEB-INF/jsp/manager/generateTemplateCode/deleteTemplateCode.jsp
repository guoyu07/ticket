<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/generateTemplateCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>删除程序代码</span>
					</div>
					<form action="#" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag }" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right">
									删除路径：
								</td>
								<td>
									<select name="savePath" id="savePath" class="my_select" datatype="*">
										<option value="">
											请选择
										</option>
										<option value="manager_src_path">
											src_manager
										</option>
										<option value="common_src_path">
											src_common
										</option>
										<option value="template_src_path">
											src_template
										</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择删除路径</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									待删除实体对象名称：
								</td>
								<td>
									<input id="entityName" name="entityName" class="my_input"  datatype="s4-200"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入要删除的实体对象名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									要删除的文件：
								</td>
								<td>
									删除<span class="deleteEntityName"></span>Action文件：
								<select name="isDeleteAction" id="isDeleteAction">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								删除I<span class="deleteEntityName"></span>Service文件：
								<select name="isDeleteService" id="isDeleteService">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								删除<span class="deleteEntityName"></span>ServiceImpl文件：
								<select name="isDeleteServiceImpl" id="isDeleteServiceImpl">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								删除<span class="deleteEntityName"></span>Js文件：
								<select name="isDeleteJs" id="isDeleteJs">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								删除<span class="deleteEntityName"></span>Jsp文件：
								<select name="isDeleteJsp" id="isDeleteJsp">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								删除<span class="deleteEntityName"></span>Message_zh_CN文件：
								<select name="isDeleteMessage" id="isDeleteMessage">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								删除<span class="deleteEntityName"></span>Pojo文件：
								<select name="isDeletePojo" id="isDeletePojo">
									<option value="1" selected="selected">是</option>
									<option value="0">否</option>
								</select><br>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input name="" type="submit" value="提交" class="btn_submit">
						    <input name="" type="submit" value="取消" class="btn_remove margin_left20">
						    <a id="manageLink" href="/generateTemplateCode_delete.action?versionFlag=${versionFlag }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>