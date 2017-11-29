<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/WEB-INF/jsp/manager/common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/channelCustomerInfo.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="/WEB-INF/jsp/manager/common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="/WEB-INF/jsp/manager/common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>我的账户信息</span>
					</div>
					<form action="/${actionName}_edit.action" id="commonForm" name="commonForm">
						<input type="hidden" name="operationFlag" id="operationFlag" value="1" />
						<input type="hidden" name="versionFlag" id="versionFlag" value="${versionFlag}" />
						<input type="hidden" name="id" id="id" value="${id}" />
						<table class="my_table100 margin_top10">
									<tr>
										<td class="text_align_right" width="150">
										     名称:
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
											
											 ${channelCustomerInfo.contact}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											联系电话：
										</td>
										<td>
											      ${channelCustomerInfo.contactPhone}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											开户金额：
										</td>
										<td>
											       ${channelCustomerInfo.openAccountMoney}
										</td>
									</tr>
									<tr>
										<td class="text_align_right" width="150">
											当前可用余额：
										</td>
										<td>
											<ticket:systemCommon type="getCustomerAccountSumMoney" value="${channelCustomerInfo.id}"/>
										</td>
									</tr>
						</table>
						
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="/WEB-INF/jsp/manager/common/popUp.jsp"%>
	</body>
</html>