<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/agreement.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑客户合同</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm"
						name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag"
							value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag"
							value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
						
							<tr>
								<td class="text_align_right" width="150">
									申请类别：
								</td>
								<td>
									<ticket:systemCommon type="applayClassList" value="0" />
										<select name="applayClassId" id="applayClassId"
										class="my_select"  datatype="*">

										<option value="">
											请选择申请类别
										</option>
										
										<s:if test="#request.applayClassList != null">
											<s:iterator id="applayClass" value="#request.applayClassList">
												<s:if test="#applayClass.id == agreement.applayClassId">
													<option selected="selected" value="${applayClass.id }">
														${applayClass.name }
													</option>
												</s:if>
												<s:else>
													<option value="${applayClass.id }">
														${applayClass.name }
													</option>
												</s:else>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请选择申请类别
									</p>
									
									
								</td>
							</tr>
							<tr>
										<td class="text_align_right" width="150">
											客户名称：
										</td>
										<td>
											<ticket:systemCommon type="channelCustomerInfoObj" value="${agreement.channelCustomerInfo_id}" />
											${channelCustomerInfoObj.name}
										</td>
									</tr>
								<td class="text_align_right" width="150">
									申请时间：
								</td>
								<td>
									<input id="applyDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
										name="applyDate" class="my_input" datatype="*"
										value="<s:date name="agreement.applyDate" format="yyyy-MM-dd HH:mm:ss"/>" />
									<p class="Validform_checktip" style="display: inline;">
										请填写申请时间
									</p>
								</td>
							</tr>
							<tr>
									<td class="text_align_right" width="150">
										合同内容：
									</td>
									<td>
										<textarea id="content" name="content" class="my_input" >
										${agreement.content}
										</textarea>
									</td>
						    </tr>
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
							<input id="returnBtn" type="button" value="返回"
								class="btn_remove margin_left20">
							<a id="addLink"
								href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
							<a id="manageLink"
								href="/${actionName}_manage.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>