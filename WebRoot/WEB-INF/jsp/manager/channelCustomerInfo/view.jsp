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
						<span>查看渠道客户信息</span>
					</div>
						<table class="my_table100 margin_top10">
								<tr>
									<td class="text_align_right" width="150">
										客户名称：
									</td>
									<td>
										${channelCustomerInfo.name}
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										用户名：
									</td>
									<td>
										${channelCustomerInfo.loginId}
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										渠道分类：
									</td>
									<td>
					
										<ticket:systemCommon type="channelTypeList" value="0"/>
										<s:iterator id="channelType" value="#request.channelTypeList">
											<s:if test="#channelType.id == channelCustomerInfo.channelTypeId">
											${channelType.name }
											</s:if>
										</s:iterator>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										开户日期：
									</td>
									<td>
										<s:date name="channelCustomerInfo.openAccountDate" format="yyyy-MM-dd"/>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系人：
									</td>
									<td>
										<s:if test="#session.systemEmployeeInfo != null">
										    	<s:if test="#session.systemEmployeeInfo.id == channelCustomerInfo.employeeInfo_id">
										    			 ${channelCustomerInfo.contact}
										    	</s:if>
										    	<s:else>
										    		<s:if test="channelCustomerInfo.employeeInfo_id != null && channelCustomerInfo.employeeInfo_id != ''">
										    			保密
										    		</s:if>
										    		<s:else>
										    		${channelCustomerInfo.contact}
										    		</s:else>
										    	</s:else>
										    </s:if>
										    <s:else>
										     ${channelCustomerInfo.contact}
										    </s:else>
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										联系电话：
									</td>
									<td>
										<s:if test="#session.systemEmployeeInfo != null">
										    	<s:if test="#session.systemEmployeeInfo.id == channelCustomerInfo.employeeInfo_id">
										    			 ${channelCustomerInfo.contactPhone}
										    	</s:if>
										    	<s:else>
										    		<s:if test="channelCustomerInfo.employeeInfo_id != null && channelCustomerInfo.employeeInfo_id != ''">
										    			保密
										    		</s:if>
										    		<s:else>
										    		${channelCustomerInfo.contactPhone}
										    		</s:else>
										    	</s:else>
										    </s:if>
										    <s:else>
										     ${channelCustomerInfo.contactPhone}
										    </s:else>
										
									</td>
								</tr>
								<!-- 
								<tr>
									<td class="text_align_right" width="150">
										开户金额：
									</td>
									<td>
										${channelCustomerInfo.openAccountMoney}元   
									</td>
								</tr>
								
								<tr>
									<td class="text_align_right" width="150">
										可用余额：
									</td>
									<td>
										${channelCustomerInfo.balance}
									</td>
								</tr>
								 -->
								<tr>
									<td class="text_align_right" width="150">
										优惠政策：
									</td>
									<td>
										<ticket:systemCommon type="favouredPolicyList" value="0"/>
										<s:iterator id="favouredPolicy" value="#request.favouredPolicyList">
											<s:if test="#favouredPolicy.id == channelCustomerInfo.favouredPolicyId">
												${favouredPolicy.name}
											</s:if>
										</s:iterator>
									</td>
								</tr>
								<!-- 
								<tr>
									<td class="text_align_right" width="150">
										支付方式：
									</td>
									<td>
										${channelCustomerInfo.payWay}
									</td>
								</tr>
								 -->
								 <tr>
									<td class="text_align_right" width="150">
										营业执照号：
									</td>
									<td>
										${channelCustomerInfo.yyzzNumber}
									</td>
								</tr>
								<tr>
									<td class="text_align_right" width="150">
										身份证号：
									</td>
									<td>
										${channelCustomerInfo.idCard}
									</td>
								</tr>
						</table>
						<div class="my_table_list_r">
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