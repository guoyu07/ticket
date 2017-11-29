<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjDispatchList.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
				<table class="my_table100 margin_top10">
					<tr class="text_align_left my_table_top">
						<td>管理核销流程表</td>
					</tr>
					<tr class="text_align_left">
						<td>
							<span>
								<input type="text" value="请输入服务码" 
					      		onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
					      		onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
								class="my_input" id="keyword" name="keyword" value="${keyword}" style="width:320px;height:23px">
					      	<input type="button" id="searchBtn" value="检索" methodName="${currentMethod}" class="my_input" style="width:55px;height: 33px;text-align: left" />
								<a href="#" class="batchAcceptBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="ing">批量接单</a>
								<a href="#" class="batchVerificateBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="end">批量核销</a>
								<%-- <a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a> --%>
					      		<a><input id="isChecked" type="checkbox"/></a>
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<table class="my_table100">
								<tr class="my_table_top1">
									<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
									<td width="80">订单名称</td>
									<td width="80">服务码</td>
								    <td width="80">预约时间</td>
								    <td width="80">预约方式</td>
								    <td width="80">验证时间</td>
									<td width="80">分单名称</td>
									<td width="80">服务人</td>
									<td width="80">状态</td>
									<td width="80">核销时间</td>
									<td width="80">核销类型</td>
									<td width="80">问题反馈</td>
									<td width="80" class="text_align_center">操作</td>
								</tr>
								<s:if test="pageModule != null && pageModule.totalCount > 0">
									<s:iterator id="c" value="pageModule.pageList" status="st">
										<tr>
											<td><input name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
											<td>${c.dispatch.validation.serviceCode.order.name }</td>
											<td>${c.dispatch.validation.serviceCode.code }</td>
											<td>${c.dispatch.validation.status.createTime }</td>
											<td>${c.dispatch.validation.appointment.way=='online'?'在线预约':'线下预约' }</td>
											<td>${c.dispatch.validation.time }</td>
											<td>${c.info.name }</td>
											<td>${c.employee.name }</td>
											<td>
												<s:if test="#c.state == @com.ticket.enumer.BjdjDispatchListState@wait">
													未接收
												</s:if>
												<s:elseif test="#c.state == @com.ticket.enumer.BjdjDispatchListState@ing">
													进行中
												</s:elseif>
												<s:else>
													已核销
												</s:else>
											</td>
											<td><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												<s:if test="#c.way == @com.ticket.enumer.BjdjCheckWay@system">
													系统核销
												</s:if>
												<s:elseif test="#c.way == @com.ticket.enumer.BjdjCheckWay@artificial">
													人工核销
												</s:elseif>
											</td>
											<td>${c.feedback }</td>
											<td><span> 
												<s:if test="#c.state == @com.ticket.enumer.BjdjDispatchListState@wait">
												<a href="javascript:;" onclick="batchAcceptEntity('${entityName}','${c.id }', 'ing')">接单</a>
												</s:if> 
												<s:elseif test="#c.state == @com.ticket.enumer.BjdjDispatchListState@ing">
												<a href="javascript:;" onclick="batchVerificateEntity('${entityName}','${c.id }','end')">核销</a>
												</s:elseif>
											</span></td>
										</tr>
									</s:iterator>
								</s:if>
								<s:else>
									<tr>
										<td colspan="19">${noDataMessage }</td>
									</tr>
								</s:else>
							</table>
						</td>
					</tr>
				</table>
				<%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>