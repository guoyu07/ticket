<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/member.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增会员信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									会员等级：
								</td>
								<td>
									<select id="memberLevel_id" name="memberLevel_id" class="my_input" datatype="*">
										<option value="">请选择类别</option>
										<ticket:systemCommon type="memberLevelList"/>
										<s:if test="#request.memberLevelList != null">
											<s:iterator id="memberLevel" value="#request.memberLevelList">
												<option value="${memberLevel.id }">${memberLevel.name }</option>
											</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display:inline;"> 请填写会员等级</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登录名：
								</td>
								<td>
									<input id="loginName" name="loginName" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登录名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登录密码：
								</td>
								<td>
									<input id="loginPwd" type="password" name="loginPwd" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登录密码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									昵称：
								</td>
								<td>
									<input id="nickName" name="nickName" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写昵称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									真实姓名：
								</td>
								<td>
									<input id="realName" name="realName" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写真实姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									身份证号：
								</td>
								<td>
									<input id="IDCard" name="IDCard" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写身份证号</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="phone" name="phone" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>
								</td>
							</tr>
							<!-- <tr>
								<td class="text_align_right" width="150">
									QQ号码：
								</td>
								<td>
									<input id="qq" name="qq" class="my_input" datatype="n6-16"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写QQ号码</p>
								</td>
							</tr> -->
							<tr>
								<td class="text_align_right" width="150">
									联系邮箱：
								</td>
								<td>
									<input id="email" name="email" class="my_input" datatype="e"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系邮箱</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系地址：
								</td>
								<td>
									<input id="address" name="address" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系地址</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									积分：
								</td>
								<td>
									<input id="record" name="record" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写积分</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									经验值：
								</td>
								<td>
									<input id="experience" name="experience" class="my_input" datatype="*" ignore="ignore"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写经验值</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									旅客类型：
								</td>
								<td>
									<input id="visitorType" name="visitorType" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写旅客类型</p>
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