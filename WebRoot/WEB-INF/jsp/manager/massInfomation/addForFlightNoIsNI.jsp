<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../common/head.jsp"%>
<script type="text/javascript" src="/manager/js/massInfomation.js"></script>
<body>
	<div class="site_warp">
		<%@ include file="../common/left.jsp"%>
		<!-- right content -->
		<div class="site_right">
			<%@ include file="../common/top.jsp"%>
			<div id="viewbox" class="site_right_box">
				<div class="my_table_list_nav">
					<span>推送航班延误信息</span>
				</div>
				<form action="/massInfomation_addForFlightNoIsNI.action" id="commonForm"
					name="commonForm">
					<input type="hidden" name="operationFlag" id="operationFlag"
						value="1" /> <input type="hidden" name="versionFlag"
						id="versionFlag" value="${versionFlag}" />
					<table class="my_table100 margin_top10">
						<tr>
							<td class="text_align_right" width="150">航班号：</td>
							<td><input id="flightNumber" name="flightNumber" class="my_input"
								datatype="*" value="${param.code}" />
								<p class="Validform_checktip" style="display:inline;">航班号</p></td>
						</tr>
						<tr>
							<td class="text_align_right" width="150">信息标题：</td>
							<td><input id="title" name="title" class="my_input"
								datatype="*" />
								<p class="Validform_checktip" style="display:inline;">
									请填写信息标题</p></td>
						</tr>
						<tr>
							<td class="text_align_right" width="150">短信内容：</td>
							<td><textarea id="content" name="content" class="my_input"
									datatype="*" style="height: 280px"></textarea>
								<p class="Validform_checktip" style="display:inline;">
									请填写短信内容</p></td>
						</tr>
						<tr>
								<td class="text_align_right" width="150">
									图片：
								</td>
								<td>
									<div id="fileQueue"></div>
									<div style="clear: both;">
									<input type="file" id="picture" name="picture" style="width: 200px;" tabindex="1" />
									</div>
									<span id="pictureTip" style="height: 22px;"></span>
									<div style="margin: 0 auto; float: left;" id="preImage" title="点击删除"></div>
							    </td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									是否发送短信：
								</td>
								<td>
									<select name="sendSms">
										<option value="false">不发送</option>
										<option value="true">发送</option>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									提醒时间：
								</td>
								<td>
									<input onclick="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" name="reminderTime" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写提醒时间</p>
								</td>
							</tr>
						<tr>
							<td class="text_align_right" width="150">短信链接：</td>
							<td><input id="url" name="url" class="my_input"
								ignore="ignore" />
								<p class="Validform_checktip" style="display:inline;">
									请填写短信链接</p></td>
						</tr>
						<tr>
							<td class="text_align_right" width="150">群发对象：</td>
							<td>所有该航班的对应的已激活但未使用的服务码的所有者</td>
						</tr>
					</table>
					<div class="my_table_list_r">
						<input id="submitBtn" type="submit" value="提交" class="btn_submit">
						<input id="resetBtn" type="submit" value="取消"
							class="btn_remove margin_left20"> <a id="addLink"
							href="/${actionName}_add.action?versionFlag=${versionFlag}"></a>
						<a id="manageLink"
							href="/${actionName }_manage.action?versionFlag=${versionFlag}"></a>
					</div>
				</form>
			</div>
		</div>
		<!-- right content end -->
	</div>
	<%@ include file="../common/popUp.jsp"%>
</body>
</html>