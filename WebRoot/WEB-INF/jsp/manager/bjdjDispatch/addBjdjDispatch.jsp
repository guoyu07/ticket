<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjDispatch.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增便捷登机分单表</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" value="${id}" />
						<input type="hidden" name="idsValue" value="${idsValue}" />
						<table class="my_table100 margin_top10">
							<s:iterator value="items" var="item">
							<tr>
								<td class="text_align_right" width="150">
									${item.name }：
								</td>
								<td>
									<select name="bjdjDispatchItem" class="my_input" datatype="*">
										<option value="">--未选择--</option>
										<s:iterator value="employee" var="e">
										<option value="${e.id }">${e.name }</option>
										</s:iterator>
									</select>
									<p class="receiveTip" style="display:inline;"> </p>
								</td>
							</tr>
							</s:iterator>
						</table>
						<div class="my_table_list_r">
							<s:fielderror>
								<s:param>bjdjDispatchItem</s:param>
							</s:fielderror>
						
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" onclick="history.go(-1)" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/bjdjValidation_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>