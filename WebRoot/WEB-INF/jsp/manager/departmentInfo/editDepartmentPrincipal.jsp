<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/departmentInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增部门信息</span>
					</div>
					<form action="/${actionName}_editInCharge.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									现任负责人：
								</td>
								<td>
									${departmentInfo.inCharge.name }
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新负责人：
								</td>
								<td>
									<select name="parentId" class="my_select" datatype="*">
										<s:iterator var="item" value="list">
							        	<option value="${item.id }">${item.name }</option>
							        	</s:iterator>
							        </select>
									<p class="Validform_checktip" style="display:inline;"> 请选择部门新负责人</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
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