<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/common.js"></script>
	<script type="text/javascript" src="/manager/js/bjdjDispatchList.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						管理便捷登机核销流程表
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						管理便捷登机核销流程表
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div class="mr40">
						<table class="table table-bordered c_l_grey work_table">
							<tr>
								<td class="fz20">
									订单名称
								</td>
								<td class="fz20">
									服务码
								</td>
								<td class="fz20">
									身份证号
								</td>
								<td class="fz20">
									旅客姓名
								</td>
								<td class="fz20">
									预约时间
								</td>
								<td class="fz20">
									预约方式
								</td>
								<td class="fz20">
									起飞时间
								</td>
								<td class="fz20">
									验证时间
								</td>
								<td class="fz20">
									航班号
								</td>
								<td class="fz20">
									分单名称
								</td>
								<td class="fz20">
									服务人
								</td>
								<td class="fz20">
									状态
								</td>
								<td class="fz20">
									核销时间
								</td>
								<td class="fz20">
									核销类型
								</td>
								<td class="fz20">
									问题反馈
								</td>
								<td class="fz20">
									操作
								</td>
							</tr>
							<s:if test="pageModule != null && pageModule.totalCount > 0">
								<s:iterator id="c" value="pageModule.pageList" status="st">
									<tr>
										<s:if test="#st.index % 4 == 0">
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.serviceCode.order.name }
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.serviceCode.code }
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.idCard }
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.name }
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.time }
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.way=='online'?'在线预约':'线下预约'
												}
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												<s:date name="#c.dispatch.validation.appointment.useTime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.time }
											</td>
											<td class="fz20" rowspan="4" style="vertical-align:middle; text-align:center;">
												${c.dispatch.validation.appointment.flightNo }
											</td>
											</s:if>
											<td>
												${c.info.name }
											</td>
											<td>
												${c.employee.name }
											</td>
											<td>
												<s:if
													test="#c.state == @com.ticket.enumer.BjdjDispatchListState@wait">
													未接收
												</s:if>
												<s:elseif
													test="#c.state == @com.ticket.enumer.BjdjDispatchListState@ing">
													进行中
												</s:elseif>
												<s:else>
													已核销
												</s:else>
											</td>
											<td>
												<s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<s:if
													test="#c.way == @com.ticket.enumer.BjdjCheckWay@system">
													系统核销
												</s:if>
												<s:elseif
													test="#c.way == @com.ticket.enumer.BjdjCheckWay@artificial">
													人工核销
												</s:elseif>
												<s:else>
												</s:else>
											</td>
											<td>
												${c.feedback }
											</td>
											<td>
												<span> <s:if
														test="#c.state == @com.ticket.enumer.BjdjDispatchListState@wait">
														<a
															href="javascript:batchAcceptEntity('${entityName}','${c.id }', 'ing')">接单</a>
													</s:if> <s:elseif
														test="#c.state == @com.ticket.enumer.BjdjDispatchListState@ing">
														<a
															href="javascript:batchVerificateEntity('${entityName}','${c.id }','end')">核销</a>
													</s:elseif> </span>
											</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="18">
										${noDataMessage }
									</td>
								</tr>
							</s:else>
						</table>
					</div>
					<div class="tit_tab">
						<a href="/wapBjdjAppointment_Index.action" class="b_yello c_grey">返回首页</a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>