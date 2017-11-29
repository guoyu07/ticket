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
						<span>新增新闻信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10" style="overflow: show;">
							<tr>
								<td class="text_align_right" width="150">
									所属类别
								</td>
								<td>
									<s:if test="permissionId != null">
										<s:set var="newsClass" value="newsClassService.queryByPermissionId(permissionId,'site')"/>
										${newsClass.name }
										<input name="newsClass_id" type="hidden" datatype="*" nullmsg="请选择新闻类别" value="${newsClass.id }"/>
									</s:if>
									<s:else>
										<select name="newsClass_id" id="newsClass_id" class="my_select" datatype="*" nullmsg="请选择新闻类别">
										<option value="">请选择类别</option>
											${newsClassHtml }
										</select>
										<p class="Validform_checktip" style="display:inline;"> 请选择类别</p>
									</s:else>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息编辑：
								</td>
								<td>
									<input id="author" name="author" class="my_input"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息标题：
								</td>
								<td>
									<input id="title" name="title" class="my_input" datatype="*" nullmsg="请填写信息标题"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写信息标题</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									信息副标题：
								</td>
								<td>
									<input id="subTitle" name="subTitle" class="my_input"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									页面模板：
								</td>
								<td>
									<select name="viewPageRedirectTemplate_id" id="viewPageRedirectTemplate_id" class="my_select" ignore="ignore" datatype="*" >
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
									<div id="fileQueue1"></div>
									<div style="clear: both;">
									<input type="file" id="thumbnail" name="thumbnail"
									style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									PC缩略图：
								</td>
								<td>
									<div id="fileQueue2"></div>
									<div style="clear: both;">
									<input type="file" id="thumbnail2" name="thumbnail2"
									style="width: 200px;" tabindex="1" />
									</div>
									<span id="picture2Tip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage2" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻图片：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
									<input type="file" id="image" name="image"
									style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip1" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage1" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻视频：
								</td>
								<td>
									<div id="fileQueueVideo"></div>
									<div style="clear: both;">
									<input type="file" id="video" name="video"
									style="width: 200px;" tabindex="1" />
									</div>
									<span id="videoTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImageVideo" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									文章摘要：
								</td>
								<td>
									<textarea id="introduce" name="introduce" class="my_input" style="width: 100%;height: 200px;"></textarea>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻内容：
								</td>
								<td>
									<textarea id="content" name="content" class="my_input" datatype="*" nullmsg="请填写新闻内容" style="width: 100%;height: 500px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写新闻内容</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻PC端内容：
								</td>
								<td>
									<textarea id="pcContent" name="pcContent" class="my_input" datatype="*" nullmsg="请填写新闻PC端内容" ignore="ignore" style="width: 100%;height: 500px;"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写新闻PC端内容</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									关联类型别名：
								</td>
								<td>
									<input id="useNewsClassName" name="useNewsClassName" class="my_input"/>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									新闻排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="0" class="my_input" datatype="n"/>
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
									<input id="mobile" name="mobile" class="my_input"/>
								</td>
							</tr>
							<tr>
								<td></td>
								<td><div id="newsLocation" style="height: 240px;width: 50%"></div></td>
							</tr>
							<tr>
								<!-- <td align="center"><input type="button" onclick="addArea()" value="新增位置"/></td> -->
								<!-- <td>
									<div id="addLocation">
									</div>
								</td> -->
								<td class="text_align_right" width="150">选择位置：</td>
								<td>
								<select name="infoPosition_id">
										<option value="">选择位置</option>
										<s:if test="#infoPositionList != null">
											<s:iterator value="#infoPositionList" var="list">
											<option value="${list.id }">${list.name }</option>
											</s:iterator>
										</s:if>
								</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									关联景点：
								</td>
								<td>
									<ticket:common type="scenicSpotsList"/>
									<select name="scenicSpots_id" id="scenicSpots_id">
										<option value="">选择景点</option>
										<s:if test="#request.scenicSpotsList != null">
											<s:iterator value="#request.scenicSpotsList" var="scenicSpots">
												<option value="${scenicSpots.id }">${scenicSpots.name }</option>
											</s:iterator>
										</s:if>
									</select>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r" >
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="manageLink" href="<%=request.getHeader("referer")%>"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>