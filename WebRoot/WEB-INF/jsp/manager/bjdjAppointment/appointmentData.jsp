<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<!-- <script type="text/javascript" src="/manager/js/realTimeData.js"></script> -->
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<s:if test="#request.hall==null">
						<table class="my_table100 margin_top10">
						  <tr class="my_table_top1">
						  	<td width="45">日期</td>
						  	<td width="70">时间段</td>
						    <td width="50">公共池总数</td>
						    <td width="80">线上激活数(进厅)</td>
						    <td width="80">线上取消激活数(进厅)</td>
						    <td width="80">线下预约数(进厅)</td>
						    <td width="80">线下取消预约数(进厅)</td>
						    <td width="80">验证数(进厅)</td>
						    <td width="40">核销数(进厅)</td>
						  </tr>
						  	<s:iterator value="#list" var="data" status="st">
							  	<s:iterator value="#data" var="realTime" status="st2">
							  		<s:if test="#st2.first">
								  		<tr>
								  			<td rowspan='<s:property value="#data.size()"/>'>${realTime.date }</td>
										  	<td>${realTime.startTime }~${realTime.endTime }</td>
										    <td rowspan='<s:property value="#data.size()"/>'>${alls }</td>
										    <td>${realTime.online + realTime.online_ }(${realTime.online_ })</td>
										    <td>${realTime.onlineUnactiveLength + realTime.onlineUnactiveLength_ }(${realTime.onlineUnactiveLength_ })</td>
										    <td>${realTime.offline + realTime.offline_ }(${realTime.offline_ })</td>
										    <td>${realTime.offlineUnactivelength + realTime.offlineUnactivelength_ }(${realTime.offlineUnactivelength_ })</td>
										    <td>${realTime.validationLength }(${realTime.validationLength_ })</td>
										    <td>${realTime.dispatchListLength + realTime.dispatchListLength_ }(${realTime.dispatchListLength_ })</td>
										</tr>
							  		</s:if>
							  		<s:else>
							  			<tr>
										  	<td>${realTime.startTime }~${realTime.endTime }</td>
										    <td>${realTime.online + realTime.online_ }(${realTime.online_ })</td>
										    <td>${realTime.onlineUnactiveLength + realTime.onlineUnactiveLength_ }(${realTime.onlineUnactiveLength_ })</td>
										    <td>${realTime.offline + realTime.offline_ }(${realTime.offline_ })</td>
										    <td>${realTime.offlineUnactivelength + realTime.offlineUnactivelength_ }(${realTime.offlineUnactivelength_ })</td>
										    <td>${realTime.validationLength }(${realTime.validationLength_ })</td>
										    <td>${realTime.dispatchListLength + realTime.dispatchListLength_ }(${realTime.dispatchListLength_ })</td>
										</tr>
							  		</s:else>
							  	</s:iterator>
						  	</s:iterator>
						</table>
					</s:if>
					<s:else>
						<table class="my_table100">
						  <tr class="my_table_top1">
						  	<td width="45">日期</td>
						  	<td width="70">时间段</td>
						    <td width="50">${hall }容量</td>
						    <td width="80">线上激活数</td>
						    <td width="80">线上取消激活数</td>
						    <td width="80">线下预约数</td>
						    <td width="80">线下取消预约数</td>
						    <td width="80">验证数(超时)</td>
						    <td width="40">核销数</td>
						  </tr>
						  	<s:iterator value="#list" var="data" status="st">
							  	<s:iterator value="#data" var="realTime" status="st2">
							  		<s:if test="#st2.first">
								  		<tr>
								  			<td rowspan='<s:property value="#data.size()"/>'>${realTime.date }</td>
										  	<td>${realTime.startTime }~${realTime.endTime }</td>
										    <td rowspan='<s:property value="#data.size()"/>'>${alls }</td>
										    <td>${realTime.online }</td>
										    <td>${realTime.onlineUnactiveLength }</td>
										    <td>${realTime.offline }</td>
										    <td>${realTime.offlineUnactivelength }</td>
										    <td>${realTime.validationLength }(${realTime.validationLength_ })</td>
										    <td>${realTime.dispatchListLength }</td>
										</tr>
							  		</s:if>
							  		<s:else>
							  			<tr>
										  	<td>${realTime.startTime }~${realTime.endTime }</td>
										    <td>${realTime.online }</td>
										    <td>${realTime.onlineUnactiveLength }</td>
										    <td>${realTime.offline }</td>
										    <td>${realTime.offlineUnactivelength }</td>
										    <td>${realTime.validationLength }(${realTime.validationLength_ })</td>
										    <td>${realTime.dispatchListLength }</td>
										</tr>
							  		</s:else>
							  	</s:iterator>
						  	</s:iterator>
						</table>
					</s:else>
					  <%@ include file="../common/page.jsp"%>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>