<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/memberFocusFlight.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增会员关注航班</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											会员id：
										</td>
										<td>
											<input id="member_id" name="member_id" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写会员id</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班号：
										</td>
										<td>
											<input id="flightNumber" name="flightNumber" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班日期：
										</td>
										<td>
											<input id="flightDate" name="flightDate" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班日期</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											会员角色：
										</td>
										<td>
											<input id="memberRole" name="memberRole" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写会员角色</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											航班状态：
										</td>
										<td>
											<input id="flightState" name="flightState" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写航班状态</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											是否携带行李：
										</td>
										<td>
											<input id="ifTakeLuggage" name="ifTakeLuggage" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写是否携带行李</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											携带特殊人员：
										</td>
										<td>
											<input id="takePerson" name="takePerson" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写携带特殊人员</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											乘机人数：
										</td>
										<td>
											<input id="personCount" name="personCount" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写乘机人数</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											是否已设置过旅程：
										</td>
										<td>
											<input id="ifSet" name="ifSet" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写是否已设置过旅程</p>
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