<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjAppointment.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr class="text_align_left my_table_top">
					      <td >管理便捷登机预约表</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_manage.action?versionFlag=site" method="get">
							  	预约时间:<input name="startTime" type="text" onclick="new WdatePicker();" value="<s:date name="startTime" format="yyyy-MM-dd" />"/>
						  				&nbsp;&nbsp;~&nbsp;&nbsp;
						  			<input name="endTime" type="text" onclick="new WdatePicker();" value="<s:date name="endTime" format="yyyy-MM-dd" />"/>
						  		预约方式：<select name="type">
					    				<option value="">所有</option>
						    			<s:iterator var="dict" value="@com.ticket.enumer.BjdjAppointmentType@values()">
						    				<option value="${dict.value }" ${type==dict ? 'selected="selected"' : null }>${dict.text }</option>
						    			</s:iterator>
						    		</select>
						  		状态：<select name="state_id">
					    				<option value="">所有</option>
						    			<s:iterator var="dict" value="dictionaryService.querySubByParentName('service_code_status')">
						    				<option value="${dict.id }" ${state_id==dict.id ? 'selected="selected"' : null }>${dict.value }</option>
						    			</s:iterator>
						    		</select>
						  			<br/>
							  	套餐名称:<input type="text" name="packageType" value="${packageType }"/>&nbsp;&nbsp;
							  	航班号:<input type="text" name="flightNo" value="${flightNo }"/>&nbsp;&nbsp;
							  	航班起飞时间:<input name="startTime2" type="text" onclick="new WdatePicker();" value="<s:date name="startTime2" format="yyyy-MM-dd" />"/>
						  				&nbsp;&nbsp;~&nbsp;&nbsp;
						  			<input name="endTime2" type="text" onclick="new WdatePicker();" value="<s:date name="endTime2" format="yyyy-MM-dd" />"/><br/>
							  	服务码:<input type="text" name="code" value="${code }"/>&nbsp;&nbsp;
							  	身份证号:<input type="text" name="IDCard" value="${IDCard }"/>&nbsp;&nbsp;
							  	姓名:<input type="text" name="name" value="${name }"/>&nbsp;&nbsp;
							  	电话号码:<input type="text" name="mobile" value="${mobile }"/>&nbsp;&nbsp;
						  			<br/>
					  			<input type="submit" value="查询"/>
						    	<a style="float:none;" href="${actionName }_downReport.action?packageType=${packageType }&flightNo=${flightNo }&startTime=<s:date name="startTime" format="yyyy-MM-dd" />&endTime=<s:date name="endTime" format="yyyy-MM-dd" />&startTime2=<s:date name="startTime2" format="yyyy-MM-dd" />&endTime2=<s:date name="endTime2" format="yyyy-MM-dd" />&code=${code}&name=${name }&mobile=${mobile}&IDCard=${IDCard}">下载</a>
						  	</form>
						  </td></tr>
					    <tr>
					      <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="40">编号</td>
								    <td width="80">服务码</td>
								    <td width="80">身份证号</td>
								    <td width="80">姓名</td>
								    <td width="80">预约人</td>
								    <td width="80">预约时间</td>
								    <td width="80">预约方式</td>
								    <td width="80">航班起飞时间</td>
								    <td width="80">航班号</td>
								    <td width="80">放弃进厅</td>
								    <td width="80">套餐类型</td>
								    <td width="80">状态</td>
								    <td width="150" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator var="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<td><input name="${actionName}CheckBox" type="checkbox" value="${c.id }"></td>
										    <td>${st.count }</td>
										    <td>${c.serviceCode.code }</td>
										    <td>${c.idCard }</td>
										    <td>${c.name }</td>
										    <td>${c.member.phone}</td>
										    <td><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.way.text}</td>
										    <td><s:date name="#c.useTime" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.flightNo }</td>
										    <td>${c.hall == null ? '是' : '否'}</td>
										    <td>${c.serviceCode.packageType.name}</td>
										    <td>
										    	${c.serviceCode.state.value }
										    </td>
										    <td>
										    	<span>
										    		<%-- <a href="#" class="realDeleteBtn" entityName="${entityName}" value="${c.id }">删除</a> --%>
										    		<s:if test="#c.ifNI == 1 && #c.ifSendInfo == 0">
												    	<a href="massInfomation_addForFlightNoIsNI.action?code=${c.flightNo }" entityName="${entityName }" value="${c.id }">推送航班延迟信息</a>
										    		</s:if>
										    		<s:if test="#c.ifNI == 1 && #c.ifSendInfo == 1">
										    			<a href="javascript:;">已发送延误信息</a>
										    		</s:if>
										    		<s:if test="#c.serviceCode.state.value == '已激活'">
										    			<a href="javascript:;" onclick="unactived('${c.id }')">取消激活</a>
										    		</s:if>
										    	</span>
										    </td>
										 </tr>
								  	</s:iterator>
								  </s:if>
								  <s:else>
								  	 <tr>
									    <td colspan="18">${noDataMessage }</td>
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