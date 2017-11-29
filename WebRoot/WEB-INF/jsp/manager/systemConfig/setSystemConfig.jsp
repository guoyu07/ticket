<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/systemConfig.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>设置系统配置</span>
					</div>
					<form action="#" id="commonForm" name="systemConfig.commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag"
							value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag"
							value="${versionFlag}" />
						<input type="hidden" name="systemConfig.id" id="id" value="${systemConfig.id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									系统名称：
								</td>
								<td>
									<input value="${systemConfig.name }" id="name" name="systemConfig.name"
										class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写系统名称
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									系统名称（前台）：
								</td>
								<td>
									<input value="${systemConfig.nameFront }" id="nameFront" name="systemConfig.nameFront"
										class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写系统名称
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									系统关键词：
								</td>
								<td>
									<input value="${systemConfig.keyword }" id="keyword"
										name="systemConfig.keyword" class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写系统关键词
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									系统关键词描述：
								</td>
								<td>
									<input value="${systemConfig.description }" id="description"
										name="systemConfig.description" class="my_input" datatype="*" />
									<p class="Validform_checktip" style="display: inline;">
										请填写系统关键词描述
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否允许登陆：
								</td>
								<td>
									<select value="${systemConfig.isAllowSignIn }"
										name="systemConfig.isAllowSignIn" id="isAllowSignIn" class="my_select"
										datatype="*">
										<option value="1">
											允许
										</option>
										<option value="0">
											不允许
										</option>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请选择是否允许登陆
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否允许注册：
								</td>
								<td>
									<select value="${systemConfig.isAllowCreate }"
										name="systemConfig.isAllowCreate" id="isAllowCreate" class="my_select"
										datatype="*">
										<option value="1">
											允许
										</option>
										<option value="0">
											不允许
										</option>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请选择是否允许注册
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									android版本：
								</td>
								<td>
									<input value="${systemConfig.androidVersion }" id="androidVersion"
										name="systemConfig.androidVersion" class="my_input"/>
									<p class="Validform_checktip" style="display: inline;">
										请填写android版本
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									android下载地址：
								</td>
								<td>
									<input value="${systemConfig.androidPath }" id="androidPath"
										name="systemConfig.androidPath" class="my_input"/>
									<p class="Validform_checktip" style="display: inline;">
										请填写android下载地址
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									ios版本：
								</td>
								<td>
									<input value="${systemConfig.iosVersion }" id="iosVersion"
										name="systemConfig.iosVersion" class="my_input"/>
									<p class="Validform_checktip" style="display: inline;">
										请填写ios版本
									</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									ios下载地址：
								</td>
								<td>
									<input value="${systemConfig.iosPath }" id="iosPath"
										name="systemConfig.iosPath" class="my_input"/>
									<p class="Validform_checktip" style="display: inline;">
										请填写ios下载地址
									</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
							<a id="manageLink"
								href="/${actionName }_setting.action?versionFlag=${versionFlag }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>