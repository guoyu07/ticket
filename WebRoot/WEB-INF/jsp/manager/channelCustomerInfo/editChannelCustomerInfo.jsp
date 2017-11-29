<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelCustomerInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑渠道客户信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<s:if test="channelCustomerInfo.loginId == null">
								<tr>
									<td class="text_align_right" width="150">
										客户名称：
									</td>
									<td>
										<input id="name" name="name" class="my_input" datatype="*"
										       value="${channelCustomerInfo.name}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写客户名称</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										所属行业：
									</td>
									<td>
										<select name="industry_id" id="industry_id" class="my_input" >
											<option value="">选择行业</option>
											${industryHtml}
										</select>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										用户名：
									</td>
									<td>
										<input id="loginId" name="loginId" class="my_input" 
										       value="${channelCustomerInfo.loginId}"/>
										<p id="loginmsg" class="Validform_checktip" style="display:inline;" ignore="ignore"> 请填写用户名</p>       
									</td>
								</tr>
								
								<tr>
									<td class="text_align_right" width="150">
										密码：
									</td>
									<td>
										<input type="password" id="password" name="password" class="my_input" datatype="*"
												value="${channelCustomerInfo.password}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写密码</p>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										确认密码：
									</td>
									<td>
										<input type="password2" id="password2" name="password2" class="my_input" datatype="*"
												recheck="password" errormsg="两次密码不一致!"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写确认密码</p>
									</td>
								</tr>
								
								<tr>
									<td class="text_align_right" width="150">
										渠道分类：
									</td>
									<td>
										<select name="channelTypeId" id="channelTypeId" class="my_select" ignore="ignore" datatype="*" >
										<ticket:systemCommon type="channelEmpCustomerTypeList" value="0"/>
										<s:if test="#request.channelEmpCustomerTypeList != null">
											<s:iterator id="channelType" value="#request.channelEmpCustomerTypeList">
											
											<s:if test="#channelType.id == channelCustomerInfo.channelTypeId">
											<option selected="selected" value="${channelType.id }">${channelType.name }</option>
											</s:if>
											<s:else>
												<option value="${channelType.id }">${channelType.name }</option>
											</s:else>
											</s:iterator>
											</s:if>
										</select>
										<p class="Validform_checktip" style="display: inline;">
											请填写渠道分类
										</p>
									</td>
								</tr>
								
								<tr>
									<td class="text_align_right" width="150">
										优惠政策：
									</td>
									<td>
										<select name="favouredPolicyId" id="favouredPolicyId" class="my_select" ignore="ignore" datatype="*" >
										<%-- <option value="">无</option>
										<ticket:systemCommon type="favouredPolicyList" value="0"/>
										<s:if test="#request.favouredPolicyList != null">
											<s:iterator id="favouredPolicy" value="#request.favouredPolicyList">
												<s:if test="#favouredPolicy.id == channelCustomerInfo.favouredPolicyId">
													<option selected="selected" value="${favouredPolicy.id }">${favouredPolicy.name }</option>
												</s:if>
												<s:else>
													<option value="${favouredPolicy.id }">${favouredPolicy.name }</option>
												</s:else>
											</s:iterator>
											</s:if> --%>
										</select>
										
									</td>
								</tr>
								
								<tr>
									<td class="text_align_right" width="150">
										开户日期：
									</td>
									<td>
										<input id="openAccountDate" onclick="WdatePicker();" name="openAccountDate" class="my_input" datatype="*"
										       value="<s:date name="channelCustomerInfo.openAccountDate" format="yyyy-MM-dd"/>"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写开户日期</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人：
									</td>
									<td>
										<input id="contact" name="contact" class="my_input" datatype="*"
										       value="${channelCustomerInfo.contact}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写联系人</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人岗位：
									</td>
									<td>
										<select name="channelPosition_id" id="channelPosition_id" class="my_input">
											<option value="">选择岗位</option>
											<s:iterator id="c" value="channelPositions" >
												<option <s:if test="#c.id == channelCustomerInfo.channelPosition_id">selected</s:if> value="${c.id}">${c.name}</option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人电话：
									</td>
									<td>
										<input id="contactPhone" name="contactPhone" class="my_input" datatype="*"
										       value="${channelCustomerInfo.contactPhone}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>       
									</td>
								</tr>
									<tr>
								<td class="text_align_right" width="150">
									营业执照副本：
								</td>
								<td>
									<div id="fileQueue">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${channelCustomerInfo.id}" annexType="1" size="100"/>
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
													<input type="hidden" id="fileNames" name="fileNames" value="/${fDirectory }/${fName}${UPLOAD_SEPARATOR_VALUE}1${UPLOAD_SEPARATOR_VALUE}"/>
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
										营业执照注册号：
									</td>
									<td>
										<input id="yyzzNumber" name="yyzzNumber" class="my_input"
										       value="${channelCustomerInfo.yyzzNumber}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写营业执照号码</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										身份证号：
									</td>
									<td>
										<input id="idCard" name="idCard" class="my_input"
										       value="${channelCustomerInfo.idCard}" dataType="sf" errormsg="请填写15或18位身份证号码!"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写身份证号</p>       
									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td class="text_align_right" width="150">
										所属行业：
									</td>
									<td>
										<select name="industry_id" id="industry_id" class="my_input" >
											<option value="">选择行业</option>
											${industryHtml}
										</select>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										密码：
									</td>
									<td>
										<input type="password" id="password" name="password" class="my_input" datatype="*"
												value="${channelCustomerInfo.password}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写密码</p>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										渠道分类：
									</td>
									<td>
										<select name="channelTypeId" id="channelTypeId" class="my_select" ignore="ignore" datatype="*" >
										<ticket:systemCommon type="channelEmpCustomerTypeList" value="0"/>
										<s:if test="#request.channelEmpCustomerTypeList != null">
											<s:iterator id="channelType" value="#request.channelEmpCustomerTypeList">
											
											<s:if test="#channelType.id == channelCustomerInfo.channelTypeId">
											<option selected="selected" value="${channelType.id }">${channelType.name }</option>
											</s:if>
											<s:else>
												<option value="${channelType.id }">${channelType.name }</option>
											</s:else>
											</s:iterator>
											</s:if>
										</select>
										<p class="Validform_checktip" style="display: inline;">
											请填写渠道分类
										</p>
									</td>
								</tr>
										
								<tr>
									<td class="text_align_right" width="150">
										优惠政策：
									</td>
									<td>
										<select name="favouredPolicyId" id="favouredPolicyId" class="my_select" ignore="ignore" datatype="*" >
										<option value="">无</option>
										<ticket:systemCommon type="favouredPolicyList" value="0"/>
										<s:if test="#request.favouredPolicyList != null">
											<s:iterator id="favouredPolicy" value="#request.favouredPolicyList">
												<s:if test="#favouredPolicy.id == channelCustomerInfo.favouredPolicyId">
													<option selected="selected" value="${favouredPolicy.id }">${favouredPolicy.name }</option>
												</s:if>
												<s:else>
													<option value="${favouredPolicy.id }">${favouredPolicy.name }</option>
												</s:else>
											</s:iterator>
											</s:if>
										</select>
										
									</td>
								</tr>
										
								<tr>
									<td class="text_align_right" width="150">
										联系人：
									</td>
									<td>
										<input id="contact" name="contact" class="my_input" datatype="*"
										       value="${channelCustomerInfo.contact}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写联系人</p>       
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人岗位：
									</td>
									<td>
										<select name="channelPosition_id" id="channelPosition_id" class="my_input">
											<option value="">选择岗位</option>
											<s:iterator id="c" value="channelPositions" >
												<option <s:if test="#c.id == channelCustomerInfo.channelPosition_id">selected</s:if> value="${c.id}">${c.name}</option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人电话：
									</td>
									<td>
										<input id="contactPhone" name="contactPhone" class="my_input" datatype="m"
										       value="${channelCustomerInfo.contactPhone}"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>       
									</td>
								</tr>
							</s:else>
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
		<script>
			$(function (){
				initUploadify("picture", 1, "fileQueue", true, true, "preImage", globalVersionFlag);
			})
		</script>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>