<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/evaluationReportTask.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增评论报表任务</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									报表文件名：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写报表文件名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									报表性质：
									<!-- <input type="button" class="addSegment" value="新增时段"/> -->
								</td>
								<td>
									<select name="property" class="my_input" datatype="*">
										<s:iterator var="e" value="property">
										<option value="${e.value }">${e.text }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写时段</p>
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<div class="head">
										开始：<input name="startDate" class="my_input" datatype="*" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
										结束：<input name="endDate" class="my_input" datatype="*" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写时段</p>
									</div>
									<div class="tail">
										开始：<input name="startDate" class="my_input" datatype="*" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
										结束：<input name="endDate" class="my_input" datatype="*" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
										<p class="Validform_checktip" style="display:inline;"> 请填写时段</p>
									</div>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									报表类型：
								</td>
								<td>
									<select name="type" class="my_input" datatype="*">
										<s:iterator var="e" value="type">
										<option value="${e.value }">${e.text }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写报表类型</p>
								</td>
							</tr>
							<%-- <tr>
								<td class="text_align_right" width="150">
									更新频率：
								</td>
								<td>
									<select name="frequency" class="my_input" datatype="*">
										<s:iterator var="e" value="frequency">
										<option value="${e.value }">${e.text }</option>
										</s:iterator>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写更新频率</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									邮箱：
								</td>
								<td>
									<input id="email" name="email" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写邮箱</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否启动：
								</td>
								<td>
									<input type="radio" name="launch" value="true" checked="checked"/>&nbsp;&nbsp;启动
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="launch" value="false"/>&nbsp;&nbsp;关闭
								</td>
							</tr> --%>
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