<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjDispatch.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>编辑便捷登机分单表</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											派单时间：
										</td>
										<td>
											<input id="time" name="time" class="my_input" datatype="*"
											       value="${bjdjDispatch.time}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写派单时间</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											服务码ID：
										</td>
										<td>
											<input id="serviceCode_id" name="serviceCode_id" class="my_input" datatype="*"
											       value="${bjdjDispatch.serviceCode_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写服务码ID</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											用户ID：
										</td>
										<td>
											<input id="member_id" name="member_id" class="my_input" datatype="*"
											       value="${bjdjDispatch.member_id}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写用户ID</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班号：
										</td>
										<td>
											<input id="flightNo" name="flightNo" class="my_input" datatype="*"
											       value="${bjdjDispatch.flightNo}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班号</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											登机口：
										</td>
										<td>
											<input id="boardingGate" name="boardingGate" class="my_input" datatype="*"
											       value="${bjdjDispatch.boardingGate}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写登机口</p>       
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											是否结束：
										</td>
										<td>
											<input id="ended" name="ended" class="my_input" datatype="*"
											       value="${bjdjDispatch.ended}"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写是否结束</p>       
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