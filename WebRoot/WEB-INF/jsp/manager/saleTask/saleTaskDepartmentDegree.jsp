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
						<span>查看销售任务-部门完成进度</span>
					</div>
					<table class="my_table100 margin_top10">
						<tr class="my_table_top1">
						    <td width="80">部门名称</td>
						    <td width="80">考核类型</td>
						    <td width="80">考核名称</td>
						    <td width="80">任务数</td>
						    <td width="80">完成数</td>
						    <td width="80">完成进度</td>
						    <td width="80">任务占比</td>
						</tr>
						<s:iterator var="item" value="list">
							<tr>
								<td>${item[0] }</td>
								<td>${item[1] }</td>
								<td>${item[2] }</td>
								<td>${item[3] }</td>
								<td>${item[4] }</td>
								<td>${item[5] }</td>
								<td>${item[6] }</td>
							</tr>
						</s:iterator>
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