<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjPage.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑便捷登机跳转页面</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											页面名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"
											       value="${bjdjPage.name}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写页面名称</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											页面编号：
										</td>
										<td>
											<input id="url" name="url" class="my_input" datatype="*"
											       value="${bjdjPage.url}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写页面跳转链接</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											该页面上的服务电话号码：
										</td>
										<td>
											<input id="servicePhone" name="servicePhone" class="my_input" datatype="*"
											       value="${bjdjPage.servicePhone}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务电话号码</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											该页面上导航的点位的别名：
										</td>
										<td>
											<input id="infoPositionAlias" name="infoPositionAlias" class="my_input" datatype="*"
											       value="${bjdjPage.infoPositionAlias}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写导航点位别名</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务流程：
										</td>
										<td>
											<select id="serviceFlow_id" name="serviceFlow_id" class="my_input" datatype="*">
												<option value="${bjdjPage.serviceFlow.id}">${bjdjPage.serviceFlow.title }</option>
												<s:if test="#newsList != null">
													<s:iterator value="#newsList" var="news">
														<option value="${news.id }">${news.title }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="serviceFlow_id" name="serviceFlow_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择服务流程页面</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务简介：
										</td>
										<td>
											<select id="serviceProfile_id" name="serviceProfile_id" class="my_input" datatype="*">
												<option value="${bjdjPage.serviceProfile.id}">${bjdjPage.serviceProfile.title}</option>
												<s:if test="#newsList != null">
													<s:iterator value="#newsList" var="news">
														<option value="${news.id }">${news.title }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="serviceProfile_id" name="serviceProfile_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择服务简介页面</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											使用条款：
										</td>
										<td>
											<select id="useSerms_id" name="useSerms_id" class="my_input" datatype="*">
												<option value="${bjdjPage.useSerms.id}">${bjdjPage.useSerms.title}</option>
												<s:if test="#newsList != null">
													<s:iterator value="#newsList" var="news">
														<option value="${news.id }">${news.title }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="useSerms_id" name="useSerms_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择使用条款页面</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											支付成功：
										</td>
										<td>
											<select id="paymentSuccess_id" name="paymentSuccess_id" class="my_input" datatype="*">
												<option value="${bjdjPage.paySuccess.id}">${bjdjPage.paySuccess.name}</option>
												<s:if test="#bjdjPageTemplates != null">
													<s:iterator value="#bjdjPageTemplates" var="bjdjPageTemplate">
														<option value="${bjdjPageTemplate.id }">${bjdjPageTemplate.name }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="paymentSuccess_id" name="paymentSuccess_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择支付成功页面</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											首页图片：
										</td>
										<td>
											<select id="advertType_id" name="advertType_id" class="my_input" datatype="*">
												<option value="${bjdjPage.advertType.id}">${bjdjPage.advertType.name}</option>
												<s:if test="#advertTypes != null">
													<s:iterator value="#advertTypes" var="advertType">
														<option value="${advertType.id }">${advertType.name }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="paymentSuccess_id" name="paymentSuccess_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择首页图片类型</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											短信模板：
										</td>
										<td>
											<select id="smsTemplate_id" name="smsTemplate_id" class="my_input" datatype="*">
												<option value="${bjdjPage.smsTemplate.id}">${bjdjPage.smsTemplate.name}</option>
												<s:if test="#dictionaries != null">
													<s:iterator value="#dictionaries" var="dictionary">
														<option value="${dictionary.id }">${dictionary.name }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="paymentSuccess_id" name="paymentSuccess_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择首页图片类型</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											激活成功：
										</td>
										<td>
											<select id="activeSuccess_id" name="activeSuccess_id" class="my_input" datatype="*">
												<option value="${bjdjPage.activeSuc.id}">${bjdjPage.activeSuc.name}</option>
												<s:if test="#bjdjPageTemplates != null">
													<s:iterator value="#bjdjPageTemplates" var="bjdjPageTemplate">
														<option value="${bjdjPageTemplate.id }">${bjdjPageTemplate.name }</option>
													</s:iterator>
												</s:if>
											</select>
											<!-- <input id="paymentSuccess_id" name="paymentSuccess_id" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请选择首页图片类型</p>
										</td>
									</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="editLink" href="/${actionName}_edit.action?versionFlag=${versionFlag}"></a>
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