<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/timeSearch.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑搜索统计</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											落地页链接：
										</td>
										<td>
											<input id="goUrl" name="goUrl" class="my_input" datatype="*"
											       value="${timeSearch.goUrl}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写落地页链接</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											展现量：
										</td>
										<td>
											<input id="showRate" name="showRate" class="my_input" datatype="*"
											       value="${timeSearch.showRate}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写展现量</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											点击量：
										</td>
										<td>
											<input id="clickRate" name="clickRate" class="my_input" datatype="*"
											       value="${timeSearch.clickRate}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写点击量</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											触发展现的搜索词：
										</td>
										<td>
											<input id="showKeyword" name="showKeyword" class="my_input" datatype="*"
											       value="${timeSearch.showKeyword}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写触发展现的搜索词</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											触发点击的搜索词：
										</td>
										<td>
											<input id="clickKeyword" name="clickKeyword" class="my_input" datatype="*"
											       value="${timeSearch.clickKeyword}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写触发点击的搜索词</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											对应的预定义搜索结果id：
										</td>
										<td>
											<input id="preResultDefinitionId" name="preResultDefinitionId" class="my_input" datatype="*"
											       value="${timeSearch.preResultDefinitionId}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写对应的预定义搜索结果id</p>       
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