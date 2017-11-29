<%@page import="com.ticket.util.UrlUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/news.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑新闻信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<s:if test="permissionId != null">
								<input type="hidden" id="newsClass_id" name="newsClass_id" value="${news.newsClass_id}" />
								<input type="hidden" id="permissionId" name="permissionId" value="${permissionId }"/>
							</s:if>
							<s:else>
								<tr>
									<td class="text_align_right" width="150">
										所属类别
									</td>
									<td>
										<select name="newsClass_id" id="newsClass_id" class="my_select" datatype="*" errormsg="请选择所属类别">
										<ticket:systemCommon  type="newsClassObj" value="${news.newsClass_id}"/>
											<option value="${newsClassObj.id}">${newsClassObj.name}</option>
											${newsClassHtml }
										</select>
										<p class="Validform_checktip" style="display:inline;"> 请选择类别</p>
									</td>
								</tr>
							</s:else>
							<tr>
								<td class="text_align_right" width="150">
									信息编辑：
								</td>
								<td>
									<input id="author" name="author" class="my_input" value="${news.author }"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*" nullmsg="请填写标题"
									       value="${news.title}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写信息标题</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息副标题：
								</td>
								<td>
									<input id="subTitle" name="subTitle" class="my_input"
									       value="${news.subTitle}"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									页面模板：
								</td>
								<td>
									<select name="viewPageRedirectTemplate_id" id="viewPageRedirectTemplate_id" class="my_select" ignore="ignore" datatype="*" currentValue="${news.viewPageRedirectTemplate_id }">
										<option value="">默认模板</option>
										<ticket:systemCommon type="pageRedirectTemplateList" value="0"/>
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
									缩略图：
								</td>
								<td>
									<div id="fileQueue1">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="1" size="1"/>
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
						    		<input type="file" id="thumbnail" name="thumbnail" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									PC缩略图：
								</td>
								<td>
									<div id="fileQueue2">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="4" size="1"/>
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
						    		<input type="file" id="thumbnail2" name="thumbnail2" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="picture2Tip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage2" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻图片：
								</td>
								<td>
									<div id="fileQueue2">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="2" size="10"/>
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
						    		<input type="file" id="image" name="image" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="pictureTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImage1" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻视频：
								</td>
								<td>
									<div id="fileQueueVideo">
										<ticket:commonAnnex type="queryAnnexList" entityUuid="${news.id}" annexType="3" size="10"/>
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
													<input type="hidden" id="fileNames" name="fileNames" value="/${fDirectory }/${fName}${UPLOAD_SEPARATOR_VALUE}3${UPLOAD_SEPARATOR_VALUE}"/>
												</span>
												<img id="2" src="/common/jQueryUpload/videoAnnex.jpg" width="100">
												</span>
											</s:iterator>
										</s:if>
						       		</div>
						    		<input type="file" id="video" name="video" style="width: 200px;" tabindex="2" />
						         	&nbsp;<span id="videoTip" style="height: 22px;"></span>	
						     		<div style="margin: o auto;float: left;" id="preImageVideo" title="点击删除图片"></div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章摘要：
								</td>
								<td>
									<textarea id="introduce" name="introduce" class="my_input" style="width: 100%;height: 200px;">${news.introduce}</textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input" datatype="*" nullmsg="请填写新闻内容" style="width: 100%;height: 500px;">${news.content}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写新闻内容</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻PC端内容：
								</td>
								<td>
									<textarea id="pcContent" name="pcContent" class="my_input" datatype="*" nullmsg="请填写新闻PC端内容" ignore="ignore" style="width: 100%;height: 500px;">${news.pcContent}</textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写新闻PC端内容</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									关联类型别名：
								</td>
								<td>
									<input id="useNewsClassName" name="useNewsClassName" class="my_input" value="${news.useNewsClassName }"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="${news.status.orderValue }" class="my_input" datatype="n"/>
									<p class="Validform_checktip" style="display:inline;"> 提示：排序值越大，越靠前！</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
								</td>
								<td>
									其他关联信息
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									热线电话：
								</td>
								<td>
									<input id="mobile" name="mobile" class="my_input" value="${news.mobile }"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td><div id="newsLocation" style="height: 240px;width: 50%"></div></td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">选择位置：</td>
								<td>
									<s:if test="#position != null">
										<span id="allready">已选择地点为：${position.name }<a href="javascript:xiugai()">修改</a></span>
										<select name="infoPosition_id" style="display: none;" id="infoPosition_id">
										<option value="">选择位置</option>
										<option value="${position.id }" selected="selected">${position.name }</option>
										<s:if test="#infoPositionList != null">
											<s:iterator value="#infoPositionList" var="list">
											<option value="${list.id }">${list.name }</option>
											</s:iterator>
										</s:if>
									</select>
									</s:if>
									<s:else>
									<select name="infoPosition_id">
										<option value="">选择位置</option>
										<s:if test="#infoPositionList != null">
											<s:iterator value="#infoPositionList" var="list">
											<option value="${list.id }">${list.name }</option>
											</s:iterator>
										</s:if>
									</select>
									</s:else>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									关联景点：
								</td>
								<td>
									<select name="scenicSpots_id" id="scenicSpots_id">
										<option value="${news.scenicSpots.id }">${news.scenicSpots.name }</option>
										<ticket:common type="scenicSpotsList"/>
										<s:if test="#request.scenicSpotsList != null">
											<s:iterator value="#request.scenicSpotsList" var="scenicSpots">
												<option value="${scenicSpots.id }">${scenicSpots.name }</option>
											</s:iterator>
										</s:if>
									</select>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}&newsClass_id=${newsClass_id}&permissionId=${permissionId}"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}&newsClass_id=${newsClass_id}&permissionId=${permissionId}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>