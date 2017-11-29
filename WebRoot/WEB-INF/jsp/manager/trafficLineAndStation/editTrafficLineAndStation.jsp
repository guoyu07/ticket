<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/trafficLineAndStation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑路线与车站关联</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									请选择路线：
								</td>
								<td>
									<ticket:systemCommon type="trafficLineObj" value="${trafficLineAndStation.trafficLine_id }"/>
									<ticket:systemCommon type="trafficTypeObj" value="${trafficLineObj.trafficType_id }"/>
									<select id="trafficLine_id" name="trafficLine_id" class="my_select" nullmsg="请选择路线" datatype="*" currentValue="${trafficLineAndStation.trafficLine_id }#${trafficTypeObj.id }">
										<option value="">请选择路线</option>
										<ticket:systemCommon type="trafficLineList"/>
										<s:if test="#request.trafficLineList != null">
											<s:iterator id="trafficLine" value ="#request.trafficLineList">
												<ticket:systemCommon type="trafficTypeObj" value="${trafficLine.trafficType_id }"/>
												<option value="${trafficLine.id }#${trafficTypeObj.id}">${trafficLine.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择路线</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									请选择车站：
								</td>
								<td>
									<select id="trafficStation_id" name="trafficStation_id" class="my_select" datatype="*" nullmsg="请选择车站" currentValue="${trafficLineAndStation.trafficStation_id }">
										<option value="">请选择车站</option>
										<ticket:systemCommon type="trafficStationList"/>
										<s:if test="#request.trafficStationList != null">
											<s:iterator id="trafficStation" value="#request.trafficStationList">
												<option value="${trafficStation.id }">${trafficStation.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择车站</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									车站类型：
								</td>
								<td>
									<select id="stationType" name="stationType" class="my_select" datatype="*" currentValue="${trafficLineAndStation.stationType }">
										<option value="1">往返车站</option>
										<option value="2">单程去</option>
										<option value="3">单程回</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写车站类型</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									排序值：
								</td>
								<td>
									<input id="orderValue" name="orderValue" value="${trafficLineAndStation.status.orderValue }" class="my_input" datatype="n"/>
									<p class="Validform_checktip" style="display:inline;"> 提示：排序值越大，越靠前！</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName}_add.action?versionFlag=${versionFlag}&trafficLine_id=${trafficLineAndStation.trafficLine_id }"></a>
						    <a id="manageLink" href="/${actionName}_manage.action?versionFlag=${versionFlag}&trafficLine_id=${trafficLineAndStation.trafficLine_id }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>