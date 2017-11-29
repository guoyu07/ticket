<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/systemModule.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑系统模块</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									所属上级模块：
								</td>
								<td>
									<select name="parent_id" id="parent_id" class="my_select" datatype="*">
							        	<option value="0">设为顶级模块</option>
										${moduleHtml }
							        </select>
									<p class="Validform_checktip" style="display:inline;"> 请选择上级模块</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									模块名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="s2-20"
									       value="${systemModule.name}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写模块名称</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									模块链接：
								</td>
								<td>
									<input id="url" name="url" class="my_input" datatype="*1-255"
									       value="${systemModule.url}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写模块链接</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									模块图标：
								</td>
								<td>
									<input id="icon" name="icon" class="my_input" datatype="s1-30"
									       value="${systemModule.icon}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写模块图标</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									模块排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" class="my_input" datatype="n1-10" value="${systemModule.status.orderValue }"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入模块排序值</p>
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