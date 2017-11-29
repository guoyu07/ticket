<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/trafficLine.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增交通路线</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									路线类别：
								</td>
								<td>
									<select id="trafficType_id" name="trafficType_id" class="my_select" datatype="*">
										<option value="">请选择路线类别</option>
										<ticket:systemCommon type="trafficTypeList"/>
										<s:if test="#request.trafficTypeList != null">
											<s:iterator id="trafficType" value="#request.trafficTypeList">
												<option value="${trafficType.id }">${trafficType.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;">请选择路线类别</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									路线名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写路线名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									起始站：
								</td>
								<td>
									<input id="startStation" name="startStation" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写起始站</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									终到站：
								</td>
								<td>
									<input id="endStation" name="endStation" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写终到站</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									发车时间：
								</td>
								<td>
									<input id="startTime" onclick="new WdatePicker({dateFmt:'HH:mm'});" name="startTime" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写发车时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									末班车时间：
								</td>
								<td>
									<input id="endTime" onclick="new WdatePicker({dateFmt:'HH:mm'});" name="endTime" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写末班车时间</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									发班频率：
								</td>
								<td>
									<input id="departureRate" name="departureRate" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写发班频率</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									车辆数：
								</td>
								<td>
									<input id="carCount" name="carCount" class="my_input" datatype="n" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写车辆数</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									车辆型号：
								</td>
								<td>
									<input id="carModel" name="carModel" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写车辆型号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									座位数：
								</td>
								<td>
									<input id="seatCount" name="seatCount" class="my_input" datatype="n" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写座位数</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									票价：
								</td>
								<td>
									<input id="price" name="price" class="my_input" errormsg="请填写数字（支持两位小数）" datatype="double" />
									<p class="Validform_checktip" style="display:inline;"> 请填写票价</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									备注：
								</td>
								<td>
									<textarea id="remark" name="remark" class="my_input" ignore="ignore" style="width: 100%;height: 240px"></textarea>
									<p class="Validform_checktip" style="display:inline;"> 请填写备注</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="0" class="my_input" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写排序值，值越大，越靠前！</p>
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