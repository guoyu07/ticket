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
						<span>签回客户合同</span>
					</div>
					<form action="/${actionName}_qh.action" id="commonForm"
						name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag"
							value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag"
							value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<input type="hidden" name="name" id="name" value="${agreement.name} " />
						<input type="hidden" name="applayClassId" id="applayClassId" value="${agreement.applayClassId}" />
						<input type="hidden" name="applyDate" id="applyDate" value="${agreement.applyDate}" />
						<input type="hidden" name="content" id="content" value="${agreement.content}" />
						
						<table class="my_table100 margin_top10">
						<tr>
								<tr>
								<td class="text_align_right" width="150">
									合同编号：
								</td>
								<td>
									${agreement.name} 
									
								</td>
							</tr>
						
							<tr>
								<td class="text_align_right" width="150">
									申请类别：
								</td>
								<td>
								    	<ticket:systemCommon type="applayClassObj" value="${agreement.applayClassId }"/>
									    	 ${applayClassObj.name }
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									申请员工：
								</td>
								<td>
								
								    <ticket:systemCommon type="employeeInfoObj" value="${agreement.employeeInfo_id2 }"/>
									    		${employeeInfoObj.name}
								
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									客户名称：
								</td>
								<td>
								
								  <ticket:systemCommon type="channelCustomerInfoObj" value="${agreement.channelCustomerInfo_id}"/>
									${channelCustomerInfoObj.name }
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									申请时间：
								</td>
								<td>
								<s:date name="agreement.applyDate" format="yyyy-MM-dd HH:mm:ss"/>
									
								</td>
							</tr>
							<tr>
										<td class="text_align_right" width="150">
											备注：
										</td>
										<td>
											${agreement.content}
											
										</td>
										
						    </tr>
						    <tr>
								<td class="text_align_right" width="150">
									审核日期：
								</td>
								<td>
									<s:date name="agreement.auditDate" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						   <tr>
								<td class="text_align_right" width="150">
									签订日期：
								</td>
								<td>
									<input id="signingDate" onclick="WdatePicker();"
										name="signingDate" class="my_input" datatype="*"
										value="<s:date name="agreement.signingDate" format="yyyy-MM-dd"/>" />
									<p class="Validform_checktip" style="display: inline;">
										请填写签订日期
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									签订金额：
								</td>
								<td>
									<input id="firstRenewMoney" name="firstRenewMoney"
										class="my_input" datatype="*"
										value="${agreement.firstRenewMoney}" />
									<p class="Validform_checktip" style="display: inline;">
										请填写签订金额
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									支付方式：
								</td>
								<td>
									<select id="payWay" name="payWay" class="my_input">
										<s:iterator var="way" value="#payWay">
											<option>${way.name }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display: inline;">
											请填写支付方式
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									真实合同副本：
								</td>
								<td>
									<div id="fileQueue">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${agreement.id}" annexType="2" size="1"/>
										<s:if test="#request.queryAnnexList != null">
											<s:iterator id="annex" value="#request.queryAnnexList" status="st">
												<s:set name="fId" value="#annex.annexPath.substring(#annex.annexPath.lastIndexOf('/')+1, #annex.annexPath.indexOf('.'))" />
												<s:set name="fName" value="#annex.annexPath.substring(#annex.annexPath.lastIndexOf('/')+1)" />
												<s:set name="fDirectory" value="#annex.annexPath.substring(#annex.annexPath.indexOf('/')+1, #annex.annexPath.lastIndexOf('/'))" />
												<span id="span${fId }">
												<span style="position: absolute; z-index: 10; float: right;">
													<a href="#" onClick="delImgUpdate('${fId}','${fName }');return false;">
														<img src="/common/jQueryUpload/cancel.png" border="0" />
													</a>
													<input type="hidden" id="fileNames" name="fileNames" value="/${fDirectory }/${fName}${UPLOAD_SEPARATOR_VALUE}2${UPLOAD_SEPARATOR_VALUE}"/>
												</span>
												<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
												</span>
											</s:iterator>
										</s:if>
						       		</div>
						    		<input type="file" id="picture" name="picture" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									签回备注：
								</td>
								<td>
									<textarea id="qhRemark" name="qhRemark" class="my_input"  style="width: 19%;height: 100px;">${agreement.qhRemark}</textarea>
								</td>
							</tr>
							<s:if test="agreement.secondAuditRemark != null && agreement.secondAuditRemark != '' && agreement.secondAuditState == 0">
							<tr>
								<td class="text_align_right" width="150">
									未批复原因：
								</td>
								<td>
									${agreement.secondAuditRemark}
								</td>
							</tr>
							</s:if>
							
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
							<input id="returnBtn" type="button" value="返回"
								class="btn_remove margin_left20">
							<a id="addLink"
								href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
							<a id="manageLink"
								href="/${actionName}_manageQh.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<script>
			$(function (){
				initUploadify("picture",2, "fileQueue", true, false, "preImage", globalVersionFlag);
			})
		</script>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>