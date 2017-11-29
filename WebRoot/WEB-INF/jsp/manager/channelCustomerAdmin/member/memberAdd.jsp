<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/rechargeRecord.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>添加会员</span>
					</div>
					<form action="/channelCustomerAdmin_memberAdd.action" id="commonForm" name="commonForm"
					>
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									用户名：
								</td>
								<td>
									<input id="loginName" name="loginName" type="text" class="my_input" datatype="*4-20"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入用户名4到20位</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									登录密码：
								</td>
								<td>
									<input id="loginPwd" name="loginPwd" type="password" class="my_input" datatype="*4-20"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写登录密码4到20位</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									昵称：
								</td>
								<td>
									<input id="nickName" name="nickName" type="text" class="my_input" datatype="*1-20"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入昵称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									真实姓名：
								</td>
								<td>
									<input id="realName" name="realName" type="text" class="my_input" />
									<p class="Validform_checktip" style="display:inline;"> 请输入真实姓名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									身份证号：
								</td>
								<td>
									<input id="IDCard" name="IDCard" type="text" class="my_input" datatype="*15-18"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入身份证号15-18位</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									手机号码：
								</td>
								<td>
									<input id="phone" name="phone" type="text" class="my_input" datatype="m"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入手机号码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									QQ：
								</td>
								<td>
									<input id="qq" name="qq" type="text" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入QQ号码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									电子邮箱：
								</td>
								<td>
									<input id="email" name="email" type="text" class="my_input" datatype="e"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入电子邮箱</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									地址：
								</td>
								<td>
									<input id="address" name="address" type="text" style="width:500px;" class="my_input"/>
									<p class="Validform_checktip" style="display:inline;"> 请输入地址</p>
								</td>
							</tr>
						</table>
						<div class="my_table_list_l">
						    <input id="submitBtn" type="submit" value="确认添加" class="btn_submit">
						    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
						    <a id="addLink" href="/channelCustomerAdmin_memberAdd.action"></a>
						    <a id="manageLink" href="/channelCustomerAdmin_memberManage.action"></a>
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>