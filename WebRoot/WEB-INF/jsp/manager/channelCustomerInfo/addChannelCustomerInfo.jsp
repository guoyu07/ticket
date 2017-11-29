<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelCustomerInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>新增渠道客户信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="150">
									客户名称：
								</td>
								<td>
									<input id="name" name="name" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写客户名称</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									所属行业：
								</td>
								<td>
									<select name="industry_id" id="industry_id" class="my_input" >
										<option value="">选择行业</option>
										${industryHtml}
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									用户名：
								</td>
								<td>
									<input id="loginId" name="username" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写用户名</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									密码：
								</td>
								<td>
									<input type="password" id="password" name="password" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写密码</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									确认密码：
								</td>
								<td>
									<input type="password" id="repassword" name="repassword" class="my_input" datatype="*"  placeholder="请再一次输入密码"/>
									<p class="Validform_checktip" style="display:inline;"> 请再一次输入密码</p>
								</td>
								
								
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									渠道分类：
								</td>
								<td>
									<select name="channelTypeId" id="channelTypeId" class="my_select" ignore="ignore" datatype="*" >
									<ticket:systemCommon type="channelEmpCustomerTypeList" value="0"/>
									<s:if test="#request.channelEmpCustomerTypeList != null">
										<s:iterator id="channelType" value="#request.channelEmpCustomerTypeList">
											<option value="${channelType.id }">${channelType.name }</option>
										</s:iterator>
										</s:if>
									</select>
									<p class="Validform_checktip" style="display: inline;">
										请填写渠道分类
									</p>
									
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									优惠政策：
								</td>
								<td>
									<select name="favouredPolicyId" id="favouredPolicyId" class="my_select" ignore="ignore" datatype="*" >
									<option value="">无</option>
									<ticket:systemCommon type="favouredPolicyList" value="0"/>
									<s:if test="#request.favouredPolicyList != null">
										<s:iterator id="favouredPolicy" value="#request.favouredPolicyList">
											<option value="${favouredPolicy.id }">${favouredPolicy.name }</option>
										</s:iterator>
										</s:if>
									</select>
								</td>
							</tr>
							
							<tr>
								<td class="text_align_right" width="150">
									开户日期：
								</td>
								<td>
									<input  id="openAccountDate" onclick="new WdatePicker();" name="openAccountDate" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写开户日期</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系人：
								</td>
								<td>
									<input id="contact" name="contact" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系人</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系人岗位：
								</td>
								<td>
									<select name="channelPosition_id" id="channelPosition_id" class="my_input">
										<option value="">选择岗位</option>
										<s:iterator id="c" value="channelPositions" >
											<option value="${c.id}">${c.name}</option>
										</s:iterator>
									</select>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									联系电话：
								</td>
								<td>
									<input id="contactPhone" name="contactPhone" class="my_input" datatype="*"/>
									<p class="Validform_checktip" style="display:inline;"> 请填写联系电话</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									开户金额：
								</td>
								<td>
									<input id="openAccountMoney"  name="openAccountMoney" class="my_input" />
									<p class="Validform_checktip" style="display:inline;"> 请填写开户金额</p>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" width="150">
									可用金额：
								</td>
								<td>
									<input id="balance" name="balance"  class="my_input" />
									<p class="Validform_checktip" style="display:inline;"> 请填写可用金额</p>
									
								</td>
							</tr>
							
							
							
							<tr>
								<td class="text_align_right" width="150">
									支付方式：
								</td>
								<td>
								
								   <input id="payWay" name="payWay" class="my_input" datatype="*"
									      />
									<p class="Validform_checktip" style="display:inline;"> 支付方式</p> 
		
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