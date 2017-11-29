<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelType.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑渠道分类</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									分类名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${channelType.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写分类名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									服务码池子：
								</td>
								<td>
									<ticket:systemCommon type="systemDictionayList" value="service_code_type"/>
									<select class="my_select" ignore="ignore" name="systemDictionary_id" id="systemDictionary_id">
										<s:iterator id="s" value="#request.systemDictionayList">
											<option  <s:if test="#s.id == channelType.type.id">selected</s:if> value="${s.id}">${s.name}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									分类描述：
								</td>
								<td>
									<input id="descript" name="descript" class="my_input" datatype="*"
									       value="${channelType.descript}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写分类描述</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否员工客户分类：
								</td>
								<td>
									<select id="empCustomerFlag" name="empCustomerFlag" class="my_select" currentValue="${channelType.status.hot }">
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
									
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