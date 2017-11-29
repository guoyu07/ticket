<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/pageCacheGroup.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑页面缓存group管理</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="group.id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									组名：
								</td>
								<td>
									<input id="groupName" name="group.groupName" class="my_input" datatype="*"
									       value="${group.groupName}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写组名</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									缓存生命范围：
								</td>
								<td>
							       <select id="scope" name="group.scope" class="my_input" datatype="*"
							    	   value="${group.scope}">
										<option value="session">session</option>
										<option value="application">application</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写缓存生命范围</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									缓存时间（秒）：
								</td>
								<td>
									<input id="time" name="group.time" class="my_input" datatype="n"
									       value="${group.time}"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写缓存时间（秒）</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									缓存表达式：
								</td>
								<td>
									<input id="cron" name="group.cron" class="my_input"
										value="${group.cron }"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写缓存表达式</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea id="remarks" name="group.remarks" class="my_input"
										style="height: 100px; width: 300px;">${group.remarks }</textarea>
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