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
						<span>新增意向客户信息</span>
					</div>
					<form action="/${actionName}_addBase.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									客户名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
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
									渠道分类：
								</td>
								<td>
									<select name="channelTypeId" id="channelTypeId" class="my_select" ignore="ignore" datatype="*" >
									<ticket:systemCommon type="channelTypeList"/>
									<s:if test="#request.channelTypeList != null">
										<s:iterator id="channelType" value="#request.channelTypeList">
											<option value="${channelType.id }">${channelType.name }</option>
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
									联系人：
								</td>
								<td>
									<input id="contact" name="contact" class="my_input" datatype="*"/>
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
											<option value="${c.id}">${c.name}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="contactPhone" name="contactPhone" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>
								</td>
							</tr>
							
							<tr>
								<td class="text_align_right" width="150">
									联系邮箱：
								</td>
								<td>
									<input id="email" name="email" class="my_input" dataType="e" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系邮箱</p>
								</td>
							</tr>
									
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						    <a id="manageLink" href="/${actionName}_manageBase.action?versionFlag=${versionFlag}"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>