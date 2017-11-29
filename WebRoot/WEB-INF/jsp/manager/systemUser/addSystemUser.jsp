<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/systemUser.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增系统管理员</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									管理员呢称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写管理员呢称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									管理员性别：
								</td>
								<td>
									<select name="sex" id="sex" class="my_select" datatype="*">
										<option value="1">
											男性
										</option>
										<option value="0">
											女性
										</option>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请选择管理员性别</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登陆名称：
								</td>
								<td>
									<input id="loginId" name="loginId" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登陆名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登陆密码：
								</td>
								<td>
									<input id="password" name="password" type="password" class="my_input" datatype="s6-20"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登陆密码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写手机号码</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/${actionName }_add.action?versionFlag=${versionFlag }"></a>
						    <a id="manageLink" href="/${actionName }_manage.action?versionFlag=${versionFlag }"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>