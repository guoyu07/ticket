<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/bjdjValidation.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<table class="my_table100 margin_top10">
					    <tr  class="text_align_left my_table_top">
					      <td >管理便捷登机验证表</td>
					    </tr>
					    <tr style="text-align: center"><td colspan="10">
							<form action="${actionName }_manage.action?versionFlag=site" method="get">
								服务码:<input type="text" name="serviceCode"/>
								航班号:<input type="text" name="flightNumber"/><br/>
							  	起止时间:<input name="startTime" type="text" onclick="new WdatePicker();"/>
							  	&nbsp;&nbsp;~&nbsp;<input name="endTime" type="text" onclick="new WdatePicker();"/>
							  	<br/>
					  			<input type="submit" value="查询"/>
					  			<input type="reset" value="重置"/>
						  	</form>
						  	<script type="text/javascript">
						  		$(function(){
						  			
						  			//初始化分页查询的条件值
						  			$('input[name="serviceCode"]').val("${param.serviceCode }");
						  			$('input[name="flightNumber"]').val("${param.flightNumber }");
						  			$('input[name="startTime"]').val("${param.startTime }");
						  			$('input[name="endTime"]').val("${param.endTime }");
						  		});
						  	</script>
							</td></tr>
							<tr class="text_align_left">
							  <td><span>
							  	<%-- <a href="#" class="batchPaidanBtn" value="${actionName}CheckBox" entityName="${entityName}" methodName="batchPaidan">批量派单</a> --%>
							  	<%-- <a href="#" class="batchRealDeleteBtn" value="${actionName}CheckBox" entityName="${entityName}">批量删除</a> --%>
							  	<a><input id="isChecked" type="checkbox"/></a>
							  </span></td>
							</tr>
							<tr>
							  <td>
								<table class="my_table100">
								  <tr class="my_table_top1">
								  	<td width="20"><input id="selectCheckBoxAllChk" objectChkName="${actionName}CheckBox" type="checkbox"></td>
								    <td width="40">编号</td>
								    <td width="80">验证时间</td>
								    <td width="80">服务码</td>
								    <td width="80">服务厅</td>
								    <td width="80">航班号</td>
								    <td width="80">航班起飞时间</td>
								    <td width="80">乘机人</td>
								    <td width="80">服务人</td>
								    <td width="80">预约方式</td>
								    <td width="80">套餐类型</td>
								    <td width="80">是否验证通过</td>
								    <td width="80" class="text_align_center">操作</td>
								  </tr>
								  <s:if test="pageModule != null && pageModule.totalCount > 0">
								  	<s:iterator id="c" value="pageModule.pageList" status="st">
								  		<tr>
								  			<s:if test="!dispatchService.isDispatch(#c.id)">
								  				<td><input  name="${actionName}CheckBox" type="checkbox" value="${c.id }" ></td>
										    </s:if>
										    <s:else>
										    	<td><input  name="${actionName}CheckBox1" type="checkbox" value="${c.id }" disabled="disabled"></td>
										    </s:else>
										    <td>${st.count }</td>
										    <td><s:date name="#c.time" format="yyyy-MM-dd HH:mm:ss"/></td>
										    <td>${c.appointment.serviceCode.code }</td>
										    <td>${c.appointment.hall.number }</td>
										    <td>${c.appointment.flightNo }</td>
										    <td>${c.appointment.useTime }</td>
										    <td>${c.appointment.name }</td>
										    <td>${c.employee.name }</td>
										    <td>${c.appointment.way.text }</td>
										    <td>${c.appointment.serviceCode.packageType.name }</td>
										    <td>${c.passed==true?"通过":"不通过" }</td>
										    <td>
										    	<s:if test="!dispatchService.isDispatch(#c.id)">
										    	<a href="bjdjDispatch_add.action?id=${c.id }" class="" entityName="${entityName}" value="">分单</a>
										    	</s:if>
										    </td>
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