<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/preResultDefinition.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑预定义搜索结果</span>
					</div>
					<form action="/${actionName}_edit.action" id="searchValidForm" name="searchValidForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									页面名称：
								</td>
								<td>
									<input id="pageName" name="pageName" class="my_input" datatype="*0-25"
									       value="${preResultDefinition.pageName}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写页面名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									系统关键词：
								</td>
								<td>
									<textarea id="keyword" name="keyword" class="my_input" datatype="*" style="width:95%; height: 150px;">${preResultDefinition.keyword}</textarea>
									<p class="Validform_checktip"> 请填写系统关键词</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									搜索否定词：
								</td>
								<td>
									<textarea id="negativeKeyword" name="negativeKeyword" class="my_input" datatype="*" ignore="ignore" style="width:95%; height: 150px;">${preResultDefinition.negativeKeyword}</textarea>
									<p class="Validform_checktip"> 请填写搜索否定词</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									精确否定词：
								</td>
								<td>
									<textarea id="negativeKeyword2" name="_negativeKeyword" class="my_input" datatype="*" ignore="ignore" style="width:95%; height: 150px;">${preResultDefinition._negativeKeyword}</textarea>
									<p class="Validform_checktip"> 请填写精确否定词</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									描述：
								</td>
								<td>
									<textarea id="description" name="description" class="my_input" style="width:95%; height: 150px;">${preResultDefinition.description}</textarea>
									<p class="Validform_checktip"> 请填写描述</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									H5链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*0-255"
									       value="${preResultDefinition.url}" ingore="ignore" style="width:70%;"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写页面链接</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									PC链接：
								</td>
								<td>
									<input id="pcUrl" name="pcUrl" class="my_input" datatype="*0-255"
									       value="${preResultDefinition.pcUrl}" ingore="ignore" style="width:70%;"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写页面链接</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n"
									       value="${preResultDefinition.status.orderValue}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写排序值，数字越大越靠前</p>       
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