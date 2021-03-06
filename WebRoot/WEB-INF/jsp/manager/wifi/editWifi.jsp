<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/wifi.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑wifi设备</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											设备id：
										</td>
										<td>
											<input id="sid" name="sid" class="my_input" datatype="*1-128"
											       value="${wifi.sid}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写设备id</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											区域名称：
										</td>
										<td>
											<select name="area_id" class="my_input" datatype="*1-128">
												<s:iterator var="area" value="wifiAreaService.queryAll(@com.ticket.pojo.WifiArea@class)">
												<option value="${area.id }" ${area.id==wifi.area.id ? 'selected="selected"' : null }>${area.name }</option>
												</s:iterator>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请填写区域名称</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											描述：
										</td>
										<td>
									       <textarea id="remark" name="remark" class="my_input" datatype="*0-1024" style="width: 300px; height: 150px;">${wifi.remark}</textarea>
											<p class="Validform_checktip" style="display:inline;"> 请填写描述</p>       
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