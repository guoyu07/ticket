<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjServiceCodeRule.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑服务码规则表</span>
					</div>
					<form action='/${actionName}_saveConfig.action' id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						
						<table class="my_table100 margin_top10">
							<tr>
								<td width="150px">
									渠道客户
								</td>
								<td>
									<select id="type_id" name="type_id" datatype="*" class="my_input">
										<s:iterator var="item" value="types">
											<option value="${item.id }">${item.name }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择渠道类型</p>       
								</td>
							</tr>
							<tr>
								<td width="150px">
									1-3位
								</td>
								<td>
									<input type="text" name="rule" value="${rule[0] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[1] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[2] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
							<tr>
								<td>
									4-6位
								</td>
								<td>
									<input type="text" name="rule" value="${rule[3] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[4] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[5] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
							<tr>
								<td>
									7-9位
								</td>
								<td>
									<input type="text" name="rule" value="${rule[6] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[7] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[8] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
							<tr>
								<td>
									10-12位
								</td>
								<td>
									<input type="text" name="rule" value="${rule[9] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[10] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<input type="text" name="rule" value="${rule[11] }" dataType="/^[\w|*]{1}$/" class="my_input" style="width: 15px;"/>
									<p class="Validform_checktip" style="display:inline;"></p>
								</td>
							</tr>
						</table>
						
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="manageLink" href="/${actionName}_saveConfig.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>