<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/newsClass.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑新闻类别</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									所属上级类别
								</td>
								<td>
									<select name="parent_id" id="parent_id" class="my_select" datatype="*" ignore="ignore">
									<option value="">设为顶级类别</option>
										${newsClassHtml }
									</select>
									<a href="/systemModule_setNewsClassAsSystemModule.action?newsClass_id=${newsClass.id }">设置为导航模块</a>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									权限Id：
								</td>
								<td>
									<input id="permissionId" name="permissionId" class="my_input" ignore="ignore" datatype="*"
									       value="${newsClass.permissionId}" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写权限id</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"
									       value="${newsClass.name}" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写类别名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别英文名：
								</td>
								<td>
									<input id="englishName" name="englishName" class="my_input" datatype="*" value="${newsClass.englishName}" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写类别英文名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别别名：
								</td>
								<td>
									<input id="alias" name="alias" class="my_input" datatype="*" value="${newsClass.alias}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写类别别名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别描述：
								</td>
								<td>
									<textarea id="descript" name="descript" class="my_input" ignore="ignore" dataType="*">${newsClass.descript}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写类别描述</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									页面模板：
								</td>
								<td>
									<select name="listPageRedirectTemplate_id" id="listPageRedirectTemplate_id" class="my_select" ignore="ignore" datatype="*" currentValue="${newsClass.listPageRedirectTemplate_id}">
										<option value="">默认模板</option>
										<ticket:systemCommon type="pageRedirectTemplateList" value="1"/>
										<s:if test="#request.pageRedirectTemplateList != null">
											<s:iterator id="pageTemplate" value="#request.pageRedirectTemplateList">
												<option value="${pageTemplate.id }">${pageTemplate.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写所属页面模板
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									PC页面模板：
								</td>
								<td>
									<select name="pcListTemplate_id" id="pcListTemplate_id" class="my_select" ignore="ignore" datatype="*" currentValue="${newsClass.listPageRedirectTemplate_id}">
										<option value="">默认模板</option>
										<ticket:systemCommon type="pageRedirectTemplateList" value="1"/>
										<s:if test="#request.pageRedirectTemplateList != null">
											<s:iterator id="pageTemplate" value="#request.pageRedirectTemplateList">
												<option <s:if test="#pageTemplate.id == newsClass.pcListTemplate_id">selected</s:if> value="${pageTemplate.id }">${pageTemplate.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写所属页面模板
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									PC详细页面模板：
								</td>
								<td>
									<select name="pcViewTemplate_id" id="pcViewTemplate_id" class="my_select" ignore="ignore" datatype="*" currentValue="${newsClass.listPageRedirectTemplate_id}">
										<option value="">默认模板</option>
										<ticket:systemCommon type="pageRedirectTemplateList" value="1"/>
										<s:if test="#request.pageRedirectTemplateList != null">
											<s:iterator id="pageTemplate" value="#request.pageRedirectTemplateList">
												<option <s:if test="#pageTemplate.id == newsClass.pcViewTemplate_id">selected</s:if> value="${pageTemplate.id }">${pageTemplate.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写所属页面模板
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n" ignore="ignore" value="${newsClass.status.orderValue }"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写类别排序值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									类别图片：
								</td>
								<td>
									<div id="fileQueue">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${newsClass.id}" annexType="1" size="1"/>
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
						    		<input type="file" id="picture" name="picture" style="width: 200px;" tabindex="1" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage" title="点击删除图片"></div>
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