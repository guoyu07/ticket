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
						<span>新增jdj支付激活页面模板</span>
					</div>
					<form action="" id="memberForm2" name="memberForm2" onsubmit="javascript:return false;">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											模板名称：
										</td>
										<td>
											<input id="name" name="name" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写模板名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											页面内容：
										</td>
										<td>
											<textarea id="content" name="content" class="my_input" datatype="*">
											
											</textarea>
											<!-- <input id="content" name="content" class="my_input" datatype="*"/> -->
											<p class="Validform_checktip" style="display:inline;"> 请填写页面内容</p>
										</td>
									</tr>
									<!-- <tr>
										<td class="text_align_right" width="150">
											按钮名称：
										</td>
										<td>
											<input id="buttonName" name="buttonName" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写按钮名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											按钮链接：
										</td>
										<td>
											<input id="buttonUrl" name="buttonUrl" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写按钮链接</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											按钮类型：
										</td>
										<td>
											<input id="buttonType" name="buttonType" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写按钮类型</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											按钮样式：
										</td>
										<td>
											<input id="buttonClass" name="buttonClass" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写按钮样式</p>
										</td>
									</tr> -->
							<tr>
								<td>按钮&nbsp;&nbsp;<a href="javascript:addInfoPosition();">+</a></td>
								<td id='jwdu'>
									<p>
									名称：<input class="buttonName" name="buttonName" dataType="*"/>
									链接：<input class="buttonUrl"  name="buttonUrl" dataType="*"/>
									类型：<!-- <input class="buttonType"  name="buttonType"/> -->
									<select class="buttonType"  name="buttonType" dataType="*">
										<option value="1">a链接</option>
										<option value="2">button按钮</option>
									</select>
									样式:<input class="buttonClass"  name="buttonClass" dataType="*"/>
									<a href="javascript:;" onclick="removeP(this);">移除</a>
									</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
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