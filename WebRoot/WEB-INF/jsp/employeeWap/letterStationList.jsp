<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="head.jsp"%>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
				<div class="mobile-top">
					<div class="header">
						销售后台页面
					</div>
				</div>
				<div class="mobile-main">
					<%@ include file="top.jsp"%>
					<div class="mr40">
						<div class="table-responsive border fz20">
							<table class="table">
								<tbody>
									<tr>
										<th class='b_l_grey'>
											发件人
										</th>
										<th class='b_l_grey'>
											时间
										</th>
										<th class='b_l_grey'>
											内容
										</th>
									</tr>
									<s:iterator id="c" value="pageModule.pageList">
									<tr>
										<td>
											<ticket:systemCommon type="channelCustomerInfoObj" value="${c.systemUser_id}" />
											<s:if test="#request.channelCustomerInfoObj != null">
											${channelCustomerInfoObj.name}
											</s:if>
											<s:else>
											系统
											</s:else>
										</td>
										<td>
											<s:date name="#c.status.createTime" format="yyyy/MM/dd HH:mm"/>
										</td>
										<td>
											${c.content}
										</td>
									</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
		</div>
	</body>
</html>