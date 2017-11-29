<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/commonTraveller.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增测试用户</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
											中文名：
										</td>
										<td>
											<input id="cName" name="cName" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写中文名</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											英文名：
										</td>
										<td>
											<input id="eName" name="eName" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写英文名</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											手机号：
										</td>
										<td>
											<input id="phone" name="phone" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写手机号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											身份证号：
										</td>
										<td>
											<input id="idCard" name="idCard" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写身份证号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											学生证号：
										</td>
										<td>
											<input id="studentCard" name="studentCard" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写学生证号</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											性别：
										</td>
										<td>
											<input id="gender" name="gender" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写性别</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											生日：
										</td>
										<td>
											<input id="birth" name="birth" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写生日</p>
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											会员ID：
										</td>
										<td>
											<input id="memberId" name="memberId" class="my_input" datatype="*"/>
											<p class="Validform_checktip" style="display:inline;"> 请填写会员ID</p>
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