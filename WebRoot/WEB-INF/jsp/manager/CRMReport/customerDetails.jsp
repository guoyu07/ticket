<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>销售CRM-客户详细信息</span>
					</div>
					<form action="/${actionName}_add.action" id="commonForm" name="commonForm">
						<table class="my_table100 margin_top10">
							<tr>
								<td class="text_align_right" width="100px" rowspan="11">基本信息：</td>
								<td width="200px">客户账号</td>
								<td width="200px">${channelCustomerInfo.loginId }</td>
							</tr>
							<tr>
								<td>渠道类型</td>
								<td><s:property value="channelTypeService.getByChannelCustomer(channelCustomerInfo.id).name"/></td>
							</tr>
							<tr>
								<td>公司名称</td>
								<td>${channelCustomerInfo.name }</td>
							</tr>
							<tr>
								<td>联系人</td>
								<td>${channelCustomerInfo.contact }</td>
							</tr>
							<tr>
								<td>联系电话</td>
								<td>${channelCustomerInfo.contactPhone }</td>
							</tr>
							<tr>
								<td>开户日期</td>
								<td><s:date name="channelCustomerInfo.openAccountDate" format="yyyy-MM-dd"/></td>
							</tr>
							<tr>
								<td>开户金额</td>
								<td>${channelCustomerInfo.openAccountMoney }</td>
							</tr>
							<tr>
								<td>身份证号</td>
								<td>${channelCustomerInfo.idCard }</td>
							</tr>
							<tr>
								<td>营业执照注册号</td>
								<td>${channelCustomerInfo.yyzzNumber }</td>
							</tr>
							<tr>
								<td>签约合同副本</td>
								<td>
									<%-- <ticket:commonAnnex type="queryAnnexList" entityUuid="${agreement.id}" annexType="2" size="1"/> --%>
									<ticket:systemCommon type="AgreeMentObj" value="${channelCustomerInfo.id}" />
									<ticket:commonAnnex type="queryAnnexList" entityUuid="${AgreeMentObj.id}" annexType="2" size="100"/>
									<s:if test="#request.queryAnnexList != null">
										<s:iterator id="annex" value="#request.queryAnnexList" status="st">
										<img id="3" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
										<s:if test="#request.queryAnnexList != null && #request.queryAnnexList.size >0">
											<a href="/downloadAnnex.action?id=${queryAnnexList[0].id}"
											class="b_blue c_white fz18 button">下载合同</a>
										</s:if>
										</s:iterator>
									</s:if>
								</td>
							</tr>
							<tr>
								<td>营业执照</td>
								<td><ticket:commonAnnex type="queryAnnexList" entityUuid="${channelCustomerInfo.id}" annexType="1" size="100"/>
									<s:if test="#request.queryAnnexList != null">
										<s:iterator id="annex" value="#request.queryAnnexList" status="st">
										<img id="2" src="<%=request.getScheme() %>://${image_server_url}${annex.annexPath }" width="100">
										</s:iterator>
									</s:if>
								</td>
							</tr>
							<tr>
								<td class="text_align_right" rowspan="6">消费信息：</td>
								<td>充值次数</td>
								<td>
									<a href="cRMReport_rechargeDetails.action?id=${channelCustomerInfo.id }">
										<s:property value="rechargeRecordService.count(channelCustomerInfo.id)"/>
									</a>
								</td>
							</tr>
							<tr>
								<td>累计充值金额</td>
								<td><s:property value="rechargeRecordService.amount(channelCustomerInfo.id)"/></td>
							</tr>
							<tr>
								<td>已开票次数</td>
								<td>0</td>
							</tr>
							<tr>
								<td>已开票金额</td>
								<td>0.0</td>
							</tr>
							<tr>
								<td>未开票次数</td>
								<td><s:property value="rechargeRecordService.count(channelCustomerInfo.id)"/></td>
							</tr>
							<tr>
								<td>未开票金额</td>
								<td><s:property value="rechargeRecordService.amount(channelCustomerInfo.id)"/></td>
							</tr>
							<tr>
								<td class="text_align_right" rowspan="5">服务码累计数据：</td>
								<td>购买数量</td>
								<td>
									<a href="cRMReport_serviceCodeDetails.action?id=${channelCustomerInfo.id }">
										<s:property value="orderInfoDetailService.buyServiceCodeCount(channelCustomerInfo.id)"/>
									</a>
								</td>
							</tr>
							<tr>
								<td>购买金额</td>
								<td><s:property value="orderInfoDetailService.buyServiceCodeAmount(channelCustomerInfo.id)"/></td>
							</tr>
							<tr>
								<td>验证数量</td>
								<td><s:property value="orderInfoDetailService.serviceCodeValidationCount(channelCustomerInfo.id)"/></td>
							</tr>
							<tr>
								<td>退款数量</td>
								<td>0</td>
							</tr>
							<tr>
								<td>退款率</td>
								<td>0%</td>
							</tr>
							<tr>
								<td class="text_align_right" rowspan="2">维护情况：</td>
								<td>电话拜访</td>
								<td>
									<a href="cRMReport_phoneVisitDetails.action?id=${channelCustomerInfo.id }">
										<s:property value="phoneVisitService.countByCustomerId(channelCustomerInfo.id)"/>
									</a>		
								</td>
							</tr>
							<tr>
								<td>外出拜访</td>
								<td>
									<a href="cRMReport_outVisitDetails.action?id=${channelCustomerInfo.id }">
										<s:property value="outVisitService.countByCustomerId(channelCustomerInfo.id)"/>
									</a>
								</td>
							</tr>
						</table>
						<div class="my_table_list_r">
						    <input id="submitBtn" type="submit" value="查看" class="btn_submit">
						    <input id="returnBtn" type="button" value="返回" class="btn_remove margin_left20">
						</div>
					</form>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>