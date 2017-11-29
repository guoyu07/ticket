<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/airportBusinessType.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑机场商业类别</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									类别名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${airportBusinessType.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写类别名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									所属上级类别
								</td>
								<td>
									<select name="parent_id" id="parent_id" class="my_select" datatype="*" ignore="ignore">
									<option value="">设为顶级类别</option>
										${classTree }
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="${airportBusinessType.status.orderValue }" class="my_input" datatype="n"/>
									<p class="Validform_checktip" style="display:inline;"> 提示：排序值越大，越靠前！</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别介绍：
								</td>
								<td>
									<textarea id="introduce" name="introduce" class="my_input" datatype="*" ignore="ignore" style="width: 100%;height: 240px;">${airportBusinessType.introduce}</textarea>
									<p class="Validform_checktip" style="display:inline;">请填写类别介绍</p>       
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