<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjServiceCode.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增服务码表</span>
					</div>
					<form method="post" action='/${actionName}_add.action' id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									生成数量：
								</td>
								<td>
									<input id="number" name="number" class="my_input" datatype="n1-3"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写需要生成的个数</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									服务码类型：
								</td>
								<td>
									<select name="type_id" datatype="*">
										<s:iterator var="dict" value="dictionaryService.querySubByParentValue('service_code_type')">
											<option value="${dict.id }">${dict.name }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择服务码状态</p>       
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="resetBtn" type="reset" value="重置" class="btn_remove">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove">
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