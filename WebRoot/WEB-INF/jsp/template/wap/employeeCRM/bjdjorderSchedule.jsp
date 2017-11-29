<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript"
		src="/template/wap/js/employeeCRM/wapbjdjorder.js"></script>
	<body class="mobile">
		<div class="mobile-view">
			<div class="mobile-page">
			<s:if test="#session.fromApp == null && #parameters.fromApp == null">
				<div class="mobile-top">
					<div class="header">
						便捷登机工单进度
					</div>
				</div>
				</s:if>
				<s:else>
				<div class="mobile-top" style="display: none;">
					<div class="header">
						便捷登机工单进度
					</div>
				</div>
				</s:else>
				<div class="mobile-main">
					<div>
						<div class="searchForm">
							<label class='button'>
								<i class='icon-search c_l_grey'></i>
								<input type="text" name="" value="" placeholder='服务码' id="fwm">
							</label>
							<button class="button bg-yello" id="shousuo">
								搜索
							</button>
						</div>
					</div>
					<div class="mr40">
						<table class="table table-bordered c_l_grey work_table" id="find" style="display: none;">
							<tr>
								<td class="fz24 t_c" colspan="5">
									旅客信息
								</td>
								<td class="fz24 t_c" colspan="4">
									服务人员安排
								</td>
								<td class="fz24 t_c" rowspan="2">
									核销情况
								</td>
							</tr>
							<tr>
								<td class="fz20">
									派单日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<td class="fz20">
									旅客姓名
								</td>
								<td class="fz20">
									航班号
								</td>
								<td class="fz20">
									起飞时间
								</td>
								<s:iterator value="#items" var="item">
									<td class="fz20">
										${item.value }
									</td>
								</s:iterator>
							</tr>

							<tr>
								<td class="fz20 " rowspan="2" id="date">
								</td>
								<td class="fz20" rowspan="2" id="servicecode">
								</td>
								<td class="fz20" rowspan="2" id="name">
								</td>
								<td class="fz20" rowspan="2" id="flightNo">
								</td>
								<td class="fz20" rowspan="2" id="flightTime">
								</td>
								<td class="fz20" id="employeeName1">
								</td>
								<td class="fz20" id="employeeName2">
								</td>
								<td class="fz20" id="employeeName3">
								</td>
								<td class="fz20" id="employeeName4">
								</td>
								<td class="fz20" rowspan="2" id="ended">
								</td>
							</tr>
							<tr>
								<td class="fz20" id="state1">
								</td>
								<td class="fz20" id="state2">
								</td>
								<td class="fz20" id="state3">
								</td>
								<td class="fz20" id="state4">
								</td>
							</tr>

						</table>
						<table class="table table-bordered c_l_grey work_table" id="all">
							<tr>
								<td class="fz24 t_c" colspan="5">
									旅客信息
								</td>
								<td class="fz24 t_c" colspan="4">
									服务人员安排
								</td>
								<td class="fz24 t_c" rowspan="2">
									核销情况
								</td>
							</tr>
							<tr>
								<td class="fz20">
									派单日期
								</td>
								<td class="fz20">
									服务码
								</td>
								<td class="fz20">
									旅客姓名
								</td>
								<td class="fz20">
									航班号
								</td>
								<td class="fz20">
									起飞时间
								</td>
								<s:iterator value="#items" var="item">
									<td class="fz20">
										${item.value }
									</td>
								</s:iterator>
							</tr>
							<s:if test="pageModule != null && pageModule.totalCount > 0">
								<s:iterator id="c" value="pageModule.pageList" status="st">
									<tr>
										<td class="fz20 " rowspan="2">
											<s:date name="#c.status.createTime" format="yyyy-MM-dd HH:mm:ss"/>
										</td>
										<td class="fz20" rowspan="2">
											${c.validation.appointment.serviceCode.code }
										</td>
										<td class="fz20" rowspan="2">
											${c.validation.appointment.name }
										</td>
										<td class="fz20" rowspan="2">
											${c.validation.appointment.flightNo}
										</td>
										<td class="fz20" rowspan="2">
											<s:date name="#c.validation.appointment.useTime"
												format="yyyy-MM-dd HH:mm:ss" />
										</td>
										<s:iterator value="#c.dispatchList" var="dl">
											<td class="fz20">
												${dl.employee.name }
											</td>
										</s:iterator>
										<td class="fz20" rowspan="2">
											<s:if test="#c.ended == true">
											    结束
										    	</s:if>
											<s:else>
											    进行中
										    	</s:else>
										</td>
									</tr>
									<tr>
										<s:iterator value="#c.dispatchList" var="dl">
											<s:if
												test="#dl.state == @com.ticket.enumer.BjdjDispatchListState@wait">
												<td class="fz20">
													未接单
												</td>
											</s:if>
											<s:elseif
												test="#dl.state == @com.ticket.enumer.BjdjDispatchListState@ing">
												<td class="fz20">
													进行中
												</td>
											</s:elseif>
											<s:else>
												<td class="fz20">
													已核销
												</td>
											</s:else>
										</s:iterator>
									</tr>
								</s:iterator>
							</s:if>
							<s:else>
								<tr>
									<td colspan="11">
										${noDataMessage }
									</td>
								</tr>
							</s:else>
						</table>
					</div>
					<div class="tit_tab">
						<a href="/wapBjdjDispatch_manage.action" class="b_yello c_grey">返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog" style="display: none;">
			<%@ include file="../common/left.jsp"%>
		</div>
	</body>
</html>