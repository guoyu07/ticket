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
					<tr style="text-align: center"><td colspan="10">
						<form action="${actionName }_manage.action?versionFlag=site" method="get">
						  	验证时间:<input name="startTime" type="text" onclick="new WdatePicker();" value="<s:date name="startTime" format="yyyy-MM-dd" />"/>
					  				&nbsp;&nbsp;~&nbsp;&nbsp;
					  			<input name="endTime" type="text" onclick="new WdatePicker();" value="<s:date name="endTime" format="yyyy-MM-dd" />"/>
						  	航班起飞时间:<input name="startTime2" type="text" onclick="new WdatePicker();" value="<s:date name="startTime2" format="yyyy-MM-dd" />"/>
					  				&nbsp;&nbsp;~&nbsp;&nbsp;
					  			<input name="endTime2" type="text" onclick="new WdatePicker();" value="<s:date name="endTime2" format="yyyy-MM-dd" />"/>
					  		预约方式：<select name="type">
				    				<option value="">所有</option>
					    			<s:iterator var="dict" value="@com.ticket.enumer.BjdjAppointmentType@values()">
					    				<option value="${dict.value }" ${type==dict ? 'selected="selected"' : null }>${dict.text }</option>
					    			</s:iterator>
					    		</select>
					  		状态：<select name="state">
				    				<option value="">所有</option>
					    			<s:iterator var="dict" value="@com.ticket.enumer.BjdjDispatchListState@values()">
					    				<option value="${dict.value }" ${state==dict ? 'selected="selected"' : null }>${dict.text }</option>
					    			</s:iterator>
					    		</select>
					  			<br/>
					  			
						  	套餐名称:<input type="text" name="packageType" value="${packageType }"/>&nbsp;&nbsp;
						  	航班号:<input type="text" name="flightNo" value="${flightNo }"/>&nbsp;&nbsp;
						  	服务码:<input type="text" name="code" value="${code }"/>&nbsp;&nbsp;
						  	身份证号:<input type="text" name="IDCard" value="${IDCard }"/>&nbsp;&nbsp;<br/>
						  	
						  	旅客姓名:<input type="text" name="name" value="${name }"/>&nbsp;&nbsp;
						  	服务人:<input type="text" name="employeeName" value="${employeeName }"/>&nbsp;&nbsp;
						  	分单名称:<input type="text" name="dispatchListName" value="${dispatchListName }"/>&nbsp;&nbsp;
					  			<br/>
				  			<input type="submit" value="查询"/>
					    	<a style="float:none;" href="${actionName }_downReport.action?
					    		startTime=<s:date name="startTime" format="yyyy-MM-dd" />
					    		&endTime=<s:date name="endTime" format="yyyy-MM-dd" />
					    		&startTime2=<s:date name="startTime2" format="yyyy-MM-dd" />
					    		&endTime2=<s:date name="endTime2" format="yyyy-MM-dd" />
					    		&type=${type}&state=${state }&packageType=${packageType}&flightNo=${flightNo}
					    		&code=${code}&IDCard=${IDCard}&name=${name}&employeeName=${employeeName}&dispatchListName=${dispatchListName}">下载</a>
					  	</form>
					  </td></tr>
					<tr class="text_align_left">
						<td>
							<span>
								<a href="#" class="batchAcceptBtn" value="${actionName}CheckBox" entityName="bjdjDispatch" methodName="ing">批量接单</a>
								<a href="#" class="batchVerificateBtn" value="${actionName}CheckBox" entityName="bjdjDispatch" methodName="end">批量核销</a>
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
									<td width="80">身份证号</td>
									<td width="80">旅客姓名</td>
								    <td width="80">预约时间</td>
								    <td width="80">预约方式</td>
								    <td width="80">起飞时间</td>
								    <td width="80">验证时间</td>
								    <td width="80">航班号</td>
									<td width="80">分单名称</td>
									<td width="80">分单时间</td>
									<td width="80">服务人</td>
									<td width="80">状态</td>
									<td width="80">核销时间</td>
									<td width="80">核销类型</td>
									<td width="80">问题反馈</td>
									<td colspan="2" width="80" class="text_align_center">操作</td>
								</tr>
								<s:if test="pageModule != null && pageModule.totalCount > 0">
									<s:iterator id="c" value="pageModule.pageList" status="st">
										<s:iterator var="list" value="#c.dispatchList" status="st2">
										<tr>
											<s:if test="#st2.first">
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>
												<input name="${actionName}CheckBox" type="checkbox" value="${c.id }">
											</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>${c.validation.appointment.serviceCode.order.name }</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>${c.validation.appointment.serviceCode.code }</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>${c.validation.appointment.idCard }</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>${c.validation.appointment.name }</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>
												<s:date name="#c.validation.appointment.time" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>
												${c.validation.appointment.way.text }
											</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>
												<s:date name="#c.validation.appointment.useTime" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>
												<s:date name="#c.validation.time" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td rowspan='<s:property value="#c.dispatchList.size()"/>'>${c.validation.appointment.flightNo }</td>
											</s:if>
											<td>
												${list.info.name }
											</td>
											<td>
												<s:date name="#list.status.createTime" format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												${list.employee.name }
											</td>
											<td>
												${list.state.text }
											</td>
											<td><s:date name="#list.time" format="yyyy-MM-dd HH:mm:ss" /></td>
											<td>
												${list.way.text }
											</td>
											<td>
												${list.feedback }
											</td>
											<td>
												<span> 
													<s:if test="#list.state == @com.ticket.enumer.BjdjDispatchListState@wait">
														<a href="javascript:;" onclick="batchAcceptEntity('${entityName}','${list.id }', 'ing')">接单</a>
													</s:if> 
													<s:elseif test="#list.state == @com.ticket.enumer.BjdjDispatchListState@ing">
														<a href="javascript:;" onclick="batchVerificateEntity('${entityName}','${list.id }','end')">核销</a>
													</s:elseif>
												</span>
											</td>
											<s:if test="#st2.first">
												<td rowspan='<s:property value="#c.dispatchList.size()"/>' >
													<span> 
														<s:if test="dispatchService.waitList(#c).size() > 0">
															<a href="javascript:;" onclick="acceptAllEntity('${c.id }')">接单</a>
														</s:if>
														<s:elseif test="dispatchService.ingList(#c).size() > 0">
															<a href="javascript:;" onclick="verificateAllEntity('${c.id }')">核销</a>
														</s:elseif>
													</span>
												</td>
											</s:if>
										</tr>
										</s:iterator>
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