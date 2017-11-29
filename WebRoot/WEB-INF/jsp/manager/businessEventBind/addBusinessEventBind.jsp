<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/businessEventBind.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增商家活动绑定</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											商家：
										</td>
										<td>
											${businessInfo.name }
											<input name="businessInfo_id" value="${businessInfo.id }" type="hidden"/>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											活动：
										</td>
										<td>
											<ticket:common type="canBindBusinessEventList"/>
											<select name="businessEvent_id" datatype="*">
												<option value="">请选择活动</option>
												<s:if test="#request.canBindBusinessEventList != null">
													<s:iterator value="#request.canBindBusinessEventList" var="businessEvent">
														<option value="${businessEvent.id }">${businessEvent.name }</option>
													</s:iterator>
												</s:if>
											</select>
											<p class="Validform_checktip" style="display:inline;"> 请选择活动</p>
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