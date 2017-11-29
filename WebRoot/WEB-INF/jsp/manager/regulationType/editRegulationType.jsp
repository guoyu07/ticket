<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/regulationType.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑规章制度类别</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									制度类别名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${regulationType.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写制度类别名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									制度类别描述：
								</td>
								<td>
									<textarea id="descript" name="descript" class="my_input" ignore="ignore" style="width: 100%;height: 320px">
										${regulationType.descript}
									</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写制度类别描述</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="*"
									       value="${regulationType.status.orderValue}"/>
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