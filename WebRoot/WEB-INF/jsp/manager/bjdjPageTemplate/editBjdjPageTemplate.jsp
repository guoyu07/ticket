<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjPageTemplate.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑jdj支付激活页面模板</span>
					</div>
					<form action="" id="memberForm3" name="memberForm3" onsubmit="javascript:return false;">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											模板名称：
										</td>
										<td>
											<s:if test="#name != null">
											<input id="name" name="name" class="my_input" datatype="s"
											       value="${name}"/>
											</s:if>
											<s:else>
											<input id="name" name="name" class="my_input" datatype="s"
											       value="${bjdjPageTemplate.name}"/>
											</s:else>
											<p class="Validform_checktip" style="display:inline;"> 请填写模板名称</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											页面内容：
										</td>
										<td>
											<s:if test="#content != null">
											<textarea id="content" name="content" class="my_input" datatype="*">${content}</textarea>
											</s:if>
											<s:else>
											<textarea id="content" name="content" class="my_input" datatype="*">${bjdjPageTemplate.content}</textarea>
											</s:else>
											<p class="Validform_checktip" style="display:inline;"> 请填写页面内容</p>       
										</td>
									</tr>
							<tr>
								<td>按钮&nbsp;&nbsp;<a href="javascript:addInfoPosition();">+</a></td>
								<td id='jwdu'>
									<s:if test="#buttonNames != null">
									<s:iterator value="#buttonNames" var="buttonName">
										<p>
										名称：<input class="buttonName" name="buttonName" value="${buttonName }" dataType="*"/>
										链接：<input class="buttonUrl"  name="buttonUrl" value="${buttonUrl }" dataType="*"/>
										类型：<%-- <input class="buttonType"  name="buttonType" value="${buttonType }" /> --%>
										<select class="buttonType"  name="buttonType" dataType="*">
											<s:if test="#buttonType == 1">
												<option value="1" selected="selected">a链接</option>
												<option value="2">button按钮</option>
											</s:if>
											<s:else>
												<option value="1">a链接</option>
												<option value="2" selected="selected">button按钮</option>
											</s:else>
										</select>
										样式:<input class="buttonClass"  name="buttonClass" value="${buttonClass }" dataType="*"/>
										<a href="javascript:;" onclick="removeP(this);">移除</a></p>
									</s:iterator>
									<s:iterator value="#buttonUrls" var="buttonUrl">
										<input type="hidden" value="${buttonUrl }" class="hidelatitude"/>
									</s:iterator>
									<s:iterator value="#buttonTypes" var="buttonType">
										<input type="hidden" value="${buttonType }" class="hidefloorNumber"/>
									</s:iterator>
									<s:iterator value="#buttonClasses" var="buttonClass">
										<input type="hidden" value="${buttonClass }" class="hideclassName"/>
									</s:iterator>
									</s:if>
									<s:else>
										<p>
										名称：<input class="buttonName" name="buttonName" value="${bjdjPageTemplate.buttonName }"/>
										链接：<input class="buttonUrl"  name="buttonUrl" value="${bjdjPageTemplate.buttonUrl }" />
										类型：<select class="buttonType"  name="buttonType">
											<s:if test="#bjdjPageTemplate.buttonType == 1">
												<option value="1" selected="selected">a链接</option>
												<option value="2">button按钮</option>
											</s:if>
											<s:else>
												<option value="1">a链接</option>
												<option value="2" selected="selected">button按钮</option>
											</s:else>
										</select>
										样式:<input class="buttonClass"  name="buttonClass" value="${bjdjPageTemplate.buttonClass }"/>
										<a href="javascript:;" onclick="removeP(this);">移除</a>
										</p>
									</s:else>
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