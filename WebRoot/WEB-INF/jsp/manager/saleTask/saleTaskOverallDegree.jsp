<%@page import="com.ticket.pojo.SaleTask"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript" src="/manager/js/saleTask.js"></script>
	<body>
		<div class="site_warp">
			<%@ include file="../common/left.jsp"%>
			<!-- right content -->
			<div class="site_right">
				<%@ include file="../common/top.jsp"%>
				<div id="viewbox" class="site_right_box">
					<div class="my_table_list_nav">
						<span>查看销售任务-总体完成进度</span>
					</div>
					<table class="my_table100 margin_top10">
						<tr class="my_table_top1">
						    <td width="80">考核类型</td>
						    <td width="80">考核名称</td>
						    <td width="80">任务数</td>
						    <td width="80">完成数</td>
						    <td width="80">完成进度</td>
						    <td width="80">任务占比</td>
						</tr>
						<tr>
							<td>业绩考核</td>
							<td>签单数</td>
							<td>${saleTask.signCount }</td>
							<td>${signCount2 }</td>
							<td><%=(Integer)request.getAttribute("signCount2") * 100 / ((SaleTask)request.getAttribute("saleTask")).getSignCount() %>%</td>
							<td>20%</td>
						</tr>
						<tr>
							<td>业绩考核</td>
							<td>充值金额</td>
							<td>${saleTask.recharge }</td>
							<td>${rechargeCount2 }</td>
							<td><%=((Double)request.getAttribute("rechargeCount2")).intValue() * 100 / (int)((SaleTask)request.getAttribute("saleTask")).getRecharge() %>%</td>
							<td>50%</td>
						</tr>
						<tr>
							<td>过程考核</td>
							<td>电话拜访</td>
							<td>${saleTask.phoneCount }</td>
							<td>${phoneCount2 }</td>
							<td><%=(Integer)request.getAttribute("phoneCount2") * 100 / ((SaleTask)request.getAttribute("saleTask")).getPhoneCount() %>%</td>
							<td>10%</td>
						</tr>
						<tr>
							<td>过程考核</td>
							<td>外出拜访</td>
							<td>${saleTask.visitCount }</td>
							<td>${visitCount2 }</td>
							<td><%=(Integer)request.getAttribute("visitCount2") * 100 / ((SaleTask)request.getAttribute("saleTask")).getVisitCount() %>%</td>
							<td>20%</td>
						</tr>
					</table>
					<div class="my_table_list_r">
					    <input id="returnBtn" type="button" value="取消" class="btn_remove margin_left20">
					</div>
				</div>
			</div>
			<!-- right content end -->
		</div>
		<%@ include file="../common/popUp.jsp"%>
	</body>
</html>