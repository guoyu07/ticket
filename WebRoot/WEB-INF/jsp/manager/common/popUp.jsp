<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--自定义弹出框-->
<div id="popupLayer" class="popupdiv">
	<div class="popup_title">
		<span>个人设置</span>
		<div id="closeDiv" class="popupclose_btn">
			<a href="#">&times;</a>
		</div>
	</div>
	<div class="popup_show">
		<!--通用选项卡开始     以下部分可替换-->
		<div class="slideTxtBox">
			<div class="hd">
				<ul>
					<li>
						账号设置
					</li>
					<li>
						修改密码
					</li>
				</ul>
			</div>
			<div class="bd">
				<ul>
					<table class="my_table100">
						<tr>
							<td class="text_align_right" width="150">
								用户名：
							</td>
							<td>
								${systemUser.loginId }
							</td>
						</tr>
						<tr>
							<td class="text_align_right">
								真实姓名：
							</td>
							<td>
								${systemUser.name }
							</td>
						</tr>
						<tr>
							<td class="text_align_right">
								联系电话：
							</td>
							<td>
								${systemUser.phone }
							</td>
						</tr>
						<tr>
							<td class="text_align_right">
								用户角色：
							</td>
							<td>
								系统管理员
							</td>
						</tr>
						<tr>
							<td class="text_align_right">
								用户状态：
							</td>
							<td>
								<img src="/manager/images/yes.gif" width="18" height="18">
							</td>
						</tr>
						<tr>
							<td class="text_align_right">
								最后登录时间：
							</td>
							<td>
								<ticket:systemCommon type="systemUserLoginLog" value="${systemUser.id }"/>
								<s:if test="#request.systemUserLoginLog != null">
									<s:date name="#request.systemUserLoginLog.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
								</s:if>
								<s:else>
									无
								</s:else>
							</td>
						</tr>
					</table>
				</ul>
				<ul>
					<form action="/systemUser_updatePassword.action" id="commonForm" name="commonForm">
						<table class="my_table100">
							<tr>
								<td class="text_align_right" width="150">
									原密码：
								</td>
								<td>
									<input name="oldPassword" id="oldPassword" datatype="s6-20" type="password" 
										   class="my_input" placeholder="请输入原始密码">
									<p class="Validform_checktip" style="display:inline;"> 请输入原始密码</p>		   
								</td>
							</tr>
							<tr>
								<td class="text_align_right">
									新密码：
								</td>
								<td>
									<input name="password" id="password" datatype="s6-20" type="password" 
									       class="my_input" placeholder="请输入新密码">
									<p class="Validform_checktip" style="display:inline;"> 请输入新密码</p>       
								</td>
							</tr>
							<tr>
								<td class="text_align_right">
									确认新密码：
								</td>
								<td>
									<input name="repassword" id="repassword" datatype="s6-20" type="password" 
									       class="my_input" placeholder="请再一次输入密码">
									<p class="Validform_checktip" style="display:inline;"> 请再一次输入密码</p>       
								</td>
							</tr>
						</table>
						<div class="my_table_list_l">
							<input id="submitBtn" type="submit" value="提交" class="btn_submit">
						    <input id="resetBtn" type="button" onclick="$('#closeDiv').trigger('click');" value="取消" class="btn_remove margin_left20">
						</div>
					</form>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			jQuery(".slideTxtBox").slide();
		</script>
		<!--通用选项卡结束-->
	</div>
</div>
<div id="popupbgLayer" class="popupbg"></div>
<iframe id='popupIframeLayer' class='popupIframe' frameborder='0'></iframe>